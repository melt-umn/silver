grammar silver:compiler:definition:flow:driver;

import silver:compiler:definition:type only isNonterminal, typerep;

data nonterminal ProductionGraph with
  prod, lhsNt, flowTypeAttrs, graph, tileGraph, suspectEdges,
  stitchPoints, sigNtStitchPoints,
  stitchedGraph, tileEdges, edgeMap, tileEdgeMap, suspectEdgeMap, cullSuspect;

-- The full name of this production
-- This is, apparently, only used to look up production by name
annotation prod::String;

-- The full name of the nonterminal this production constructs
-- Only used by solveFlowTypes()
annotation lhsNt::String;

-- The attributes that we are inferring the flow types of.
-- (Syns and optionally fwd, minus those that are specified.)
-- Used in solveFlowTypes
annotation flowTypeAttrs::[String];
-- I'd prefer this not exist, but...

-- The edges within this production
annotation graph::g:Graph<FlowVertex>;

-- The edges used for tile stitch points, excluding edges from sigNtStitchPoints.
annotation tileGraph::g:Graph<FlowVertex>;

-- Edges that are not permitted to affect their OWN flow types (but perhaps some unknown other flowtypes)
annotation suspectEdges::[(FlowVertex, FlowVertex)];

-- Places where current flow types need grafting to this graph to yield a full flow graph
annotation stitchPoints::[StitchPoint];

-- Stitch points that arise from signature nonterminals, only used in graph and not tileGraph.
annotation sigNtStitchPoints::[StitchPoint];

{--
 - Given a set of flow types, stitches those edges into the graph for
 - all stitch points (i.e. children, locals, forward).
 - Either just a new graph, or nothing if no new edges were added.
 -}
synthesized attribute stitchedGraph :: (Maybe<ProductionGraph> ::= EnvTree<FlowType> EnvTree<ProductionGraph>);

{--
 - All edges between LHS and RHS vertices of the tile graph.
 -}
synthesized attribute tileEdges :: [(FlowVertex, FlowVertex)];
{--
 - Edge mapper
 -}
synthesized attribute edgeMap :: (set:Set<FlowVertex> ::= FlowVertex);
synthesized attribute tileEdgeMap :: (set:Set<FlowVertex> ::= FlowVertex);
synthesized attribute suspectEdgeMap :: ([FlowVertex] ::= FlowVertex);

synthesized attribute cullSuspect :: (Maybe<ProductionGraph> ::= EnvTree<FlowType>);

{--
 - An object for representing a production's flow graph.
 - Should ALWAYS be a transitive closure over the edges for 'vertexes'.
 -
 - @see constructProductionGraph for how to go about getting an object of this type
 -}
abstract production productionGraph
top::ProductionGraph ::=
{
  top.stitchedGraph = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    let
      edges :: [(FlowVertex, FlowVertex)] =
        flatMap(stitchEdgesFor(_, flowTypes, prodGraphs), top.stitchPoints),
      sigEdges :: [(FlowVertex, FlowVertex)] =
        flatMap(stitchEdgesFor(_, flowTypes, prodGraphs), top.sigNtStitchPoints)
    in let
      newEdges :: [(FlowVertex, FlowVertex)] =
        filter(edgeIsNew(_, top.graph), filter(notRhsEqDep, edges) ++ sigEdges),
      newTileEdges :: [(FlowVertex, FlowVertex)] =
        filter(edgeIsNew(_, top.tileGraph), edges)  -- sigEdges not included in tileGraph
    in let
      repaired :: g:Graph<FlowVertex> =
        g:repairClosure(newEdges, top.graph),
      repairedTile :: g:Graph<FlowVertex> =
        g:repairClosure(newTileEdges, top.tileGraph)
    in
      if null(newEdges) && null(newTileEdges)
      then nothing()
      else just(top(graph=repaired, tileGraph=repairedTile))
    end end end;

  top.tileEdges = filter(isSigEdge, g:toList(top.tileGraph));

  top.edgeMap = g:edgesFrom(_, top.graph);
  top.tileEdgeMap = g:edgesFrom(_, top.tileGraph);
  top.suspectEdgeMap = lookupAll(_, top.suspectEdges);
  
  top.cullSuspect = \ flowTypes::EnvTree<FlowType> ->
    -- Only update the regular graph, suspect edges are initially included in the tile graph.
    -- this potentially introduces the same edge twice, but that's a nonissue
    let newEdges :: [(FlowVertex, FlowVertex)] =
          flatMap(findAdmissibleEdges(_, top.graph, findFlowType(top.lhsNt, flowTypes)), top.suspectEdges)
    in let repaired :: g:Graph<FlowVertex> =
             g:repairClosure(newEdges, top.graph)
    in if null(newEdges) then nothing() else
         just(top(graph=repaired))
    end end;
}

fun updateGraph
Maybe<ProductionGraph> ::=
    graph::ProductionGraph
    prodEnv::EnvTree<ProductionGraph>
    ntEnv::EnvTree<FlowType> =
  case graph.stitchedGraph(ntEnv, prodEnv) of
  | just(newGraph) -> alt(newGraph.cullSuspect(ntEnv), just(newGraph))
  | nothing() -> graph.cullSuspect(ntEnv)
  end;


--------------------------------------------------------------------------------
-- Below, we have various means of constructing a production graph.
-- Four types are used as part of inference:
--  1. `constructProductionGraph` builds a graph for a normal production.
--  2. `constructDefaultProductionGraph` builds a graph for default equations for a nonterminal.
--       (key: like phantom, LHS is stitch point, to make dependencies clear.)
--  3. `constructPhantomProductionGraph` builds a "phantom graph" to guide inference.
--  4. `constructDispatchGraph` builds a graph for a dispatch signature,
--     to make projection stitch points work for them.
--
-- There are more types of "production" graphs, used NOT for inference, but
-- for error checking behaviors:
--  1. `constructFunctionGraph` builds a graph for a function.
--       (the key here: `aspect function` contributions `<-` needs to be handled.)
--  2. `constructAnonymousGraph` builds a graph for a global expression. (also action blocks)
--       (key: decorate/patterns create stitch points and things that need to be handled.)
--
-- This latter type should always call `updateGraph` to fill in all edges after construction.
--------------------------------------------------------------------------------


{--
 - Produces a ProductionGraph in some special way. Fixes up implicit equations,
 - figures out stitch points, and so forth.
 -
 - 1. All HOA synthesized attributes have a dep on their equation. 
 - 1b. Same for forwarding.
 - 2. All synthesized attributes missing equations have dep on their corresponding fwd.
 - 2b. OR use their default if not forwarding and it exists.
 - 3. All inherited attributes not supplied to forward have copies.
 -
 - @param dcl  The DclInfo of the production
 - @param defs  The set of defs from prodGraphContribs
 - @param flowEnv  A full flow environment
 -         (used to discover what explicit equations exist, find info on nonterminals)
 - @param realEnv  A full real environment
 -         (used to discover attribute occurrences, whether inh/syn)
 - @return A fixed up graph.
 -}
function constructProductionGraph
ProductionGraph ::= dcl::ValueDclInfo  flowEnv::FlowEnv  realEnv::Env
{
  -- The name of this production
  local prod :: String = dcl.fullName;
  -- The flow defs for this production
  local defs :: [FlowDef] = getGraphContribsFor(prod, flowEnv);
  -- The LHS nonterminal full name
  local nt :: NtName = dcl.namedSignature.outputElement.typerep.typeName;
  -- Just synthesized attributes.
  local syns :: [String] = getSynAttrsOn(nt, realEnv);
  -- Just inherited and inherited on translation attributes.
  local inhs :: [String] = getInhAndInhOnTransAttrsOn(nt, realEnv);
  -- Does this production forward?
  local nonForwarding :: Boolean = null(lookupFwd(prod, flowEnv));
  
  -- Normal edges!
  local normalEdges :: [(FlowVertex, FlowVertex)] =
    flatMap((.flowEdges), defs);
  
  -- Insert implicit equations.
  local fixedEdges :: [(FlowVertex, FlowVertex)] =
    normalEdges ++
    (if nonForwarding
     then addDefEqs(prod, nt, syns, flowEnv)
     else addFwdSynEqs(prod, synsBySuspicion.fst, flowEnv) ++ 
          addFwdInhEqs(prod, inhs, flowEnv)) ++
    flatMap(addFwdProdAttrInhEqs(prod, _, inhs, flowEnv), allFwdProdAttrs(defs)) ++
    flatMap(addSharingEqs(flowEnv, realEnv, _), defs);
  
  -- (safe, suspect)
  local synsBySuspicion :: Pair<[String] [String]> =
    partition(contains(_, getNonSuspectAttrsForProd(prod, flowEnv)), syns);
  
  -- No implicit equations here, just keep track.
  local suspectEdges :: [(FlowVertex, FlowVertex)] =
    flatMap((.suspectFlowEdges), defs) ++
    -- If it's forwarding .snd is attributes not known at forwarding time. If it's non, then actually .snd is all attributes. Ignore.
    if nonForwarding then [] else addFwdSynEqs(prod, synsBySuspicion.snd, flowEnv);

  -- RHS only.
  local sigNtStitchPoints :: [StitchPoint] =
    flatMap(rhsStitchPoints(realEnv, _), dcl.namedSignature.inputElements);

  -- locals and forward.
  local stitchPoints :: [StitchPoint] =
    localStitchPoints(realEnv, defs) ++
    patternStitchPoints(realEnv, defs) ++
    subtermDecSiteStitchPoints(defs) ++
    sigSharingStitchPoints(realEnv, defs) ++
    case dcl.implementedSignature of
    | just(sig) -> concat(zipWith(
        implementedSigStitchPoints(realEnv, nt, _, sig.fullName, _),
        dcl.namedSignature.inputElements,
        sig.inputElements))
    | nothing() -> []
    end ++
    if any(map((.elementShared), dcl.namedSignature.inputElements))
    -- TODO: We could be more precise here by only considering the productions
    -- that could have actually forwarded to this one and introducing tile stitch points.
    then nonterminalStitchPoints(realEnv, nt, forwardParentVertexType())
    else [];

  local flowTypeSpecs :: [String] = getSpecifiedSynsForNt(nt, flowEnv);
  
  local flowTypeAttrs :: [String] =
    filter(!contains(_, flowTypeSpecs),
      (if nonForwarding then [] else ["forward"]) ++ syns);
  
  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(filter(notRhsEqDep, fixedEdges));  -- deps on RHS.EQ don't matter in the regular graph
  local initialTileGraph :: g:Graph<FlowVertex> =
    createFlowGraph(suspectEdges ++ fixedEdges);

  return productionGraph(
    prod=prod, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialTileGraph, suspectEdges=suspectEdges,
    stitchPoints=stitchPoints, sigNtStitchPoints=sigNtStitchPoints);
}

{--
 - Constructs a function flow graph.
 -
 - NOTE: Not used as part of inference. Instead, only used as part of error checking.
 -
 - @param ns  The function signature
 - @param flowEnv  The LOCAL flow env where the function is. (n.b. for productions involved in inference, we get a global flow env)
 - @param realEnv  The LOCAL environment
 - @param prodEnv  The production flow graphs we've previously computed
 - @param ntEnv  The flow types we've previously computed
 -}
function constructFunctionGraph
ProductionGraph ::= ns::NamedSignature  flowEnv::FlowEnv  realEnv::Env  prodEnv::EnvTree<ProductionGraph>  ntEnv::EnvTree<FlowType>
{
  local prod :: String = ns.fullName;
  local nt :: NtName = "::nolhs"; -- the same hack we use elsewhere
  local defs :: [FlowDef] = getGraphContribsFor(prod, flowEnv);

  local normalEdges :: [(FlowVertex, FlowVertex)] =
    flatMap((.flowEdges), defs);
  
  local fixedEdges :: [(FlowVertex, FlowVertex)] =
    normalEdges ++
    flatMap(addSharingEqs(flowEnv, realEnv, _), defs);
  
  -- In functions, this is just `<-` contributions to local collections from aspects.
  local suspectEdges :: [(FlowVertex, FlowVertex)] =
    flatMap((.suspectFlowEdges), defs);
    
  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(filter(notRhsEqDep, fixedEdges)); -- deps on RHS.EQ don't matter in the regular graph
  -- TODO: functions shouldn't have a tile graph
  local initialTileGraph :: g:Graph<FlowVertex> =
    createFlowGraph(suspectEdges ++ fixedEdges); -- suspect edges included in tile graph initially

  -- Just included as regular stitch points.
  local sigNtStitchPoints :: [StitchPoint] = [];

  -- RHS and locals and forward.
  local stitchPoints :: [StitchPoint] =
    flatMap(rhsStitchPoints(realEnv, _), ns.inputElements) ++
    localStitchPoints(realEnv, defs) ++
    patternStitchPoints(realEnv, defs) ++
    subtermDecSiteStitchPoints(defs);

  local flowTypeAttrs :: [String] = []; -- Not used as part of inference.

  local g :: ProductionGraph = productionGraph(
    prod=prod, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialTileGraph, suspectEdges=suspectEdges,
    stitchPoints=stitchPoints, sigNtStitchPoints=sigNtStitchPoints);

  return fromMaybe(g, updateGraph(g, prodEnv, ntEnv));
}

{--
 - An anonymous graph is for a location where we have all `flowDefs` locally available,
 - and they're actually not propagated into an environment anywhere. (e.g. globals)
 -
 - NOTE: Not used as part of inference. Instead, only used as part of error checking.
 -
 -}
function constructAnonymousGraph
ProductionGraph ::= defs::[FlowDef]  realEnv::Env  prodEnv::EnvTree<ProductionGraph>  ntEnv::EnvTree<FlowType>
{
  -- Actually very unclear to me right now if these dummy names matter.
  -- Presently duplicating what appears in BlockContext
  local prod :: String = "_NULL_";
  local nt :: NtName = "::nolhs"; -- the same hack we use elsewhere

  local normalEdges :: [(FlowVertex, FlowVertex)] =
    flatMap((.flowEdges), defs);
  
  -- suspectEdges should always be empty! (No "aspects" where they could arise.)
  local suspectEdges :: [(FlowVertex, FlowVertex)] = [];

  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(normalEdges);

  -- There can still be anonEq, but there's no RHS anymore
  local stitchPoints :: [StitchPoint] =
    localStitchPoints(realEnv, defs) ++
    patternStitchPoints(realEnv, defs);
  local sigNtStitchPoints :: [StitchPoint] = [];

  local flowTypeAttrs :: [String] = []; -- Not used as part of inference.
  
  local g :: ProductionGraph = productionGraph(
    prod=prod, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialGraph, suspectEdges=suspectEdges,
    stitchPoints=stitchPoints, sigNtStitchPoints=sigNtStitchPoints);

  return fromMaybe(g, updateGraph(g, prodEnv, ntEnv));
}

{--
 - A graph for dependencies in default equations.
 -
 - This is used in checking, and also to handle dependencies of default equations during inference.
 -}
function constructDefaultProductionGraph
[ProductionGraph] ::= nt::NtName  flowEnv::FlowEnv  realEnv::Env
{
  -- The stand-in "full name" of this default production
  local prod :: String = nt ++ ":default";
  -- The flow defs for this default production
  local defs :: [FlowDef] = getGraphContribsFor(prod, flowEnv);
  -- Just synthesized attributes.
  local syns :: [String] = getSynAttrsOn(nt, realEnv);
  
  local normalEdges :: [(FlowVertex, FlowVertex)] =
    flatMap((.flowEdges), defs);
  
  -- suspectEdges should always be empty! (No "aspects" where they could arise.)
  local suspectEdges :: [(FlowVertex, FlowVertex)] = [];

  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(normalEdges);

  -- There can still be anonEq, but there's no RHS anymore
  -- However, we do behave like phantom graphs and create an LHS stitch point!
  local stitchPoints :: [StitchPoint] =
    nonterminalStitchPoints(realEnv, nt, lhsVertexType()) ++ 
    localStitchPoints(realEnv, defs) ++
    patternStitchPoints(realEnv, defs);
  local sigNtStitchPoints :: [StitchPoint] = [];

  local flowTypeSpecs :: [String] = getSpecifiedSynsForNt(nt, flowEnv);
  local flowTypeAttrs :: [String] =
    filter(!contains(_, flowTypeSpecs), syns);

  local g :: ProductionGraph = productionGraph(
    prod=prod, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialGraph, suspectEdges=suspectEdges,
    stitchPoints=stitchPoints, sigNtStitchPoints=sigNtStitchPoints);
  
  -- Optimization: omit the default graph if there are no default equations for the NT.
  return if null(defs) then [] else [g];
}

{--
 - Constructs "phantom graphs" to enforce 'ft(syn) >= ft(fwd)'.
 -
 - @param nt  The nonterminal for which we produce a phantom production.
 - @param flowEnv  A full flow environment (need to find out what syns are ext syns for this NT)
 - @param realEnv  A full real environment (need to find out what syns occurs on this NT)
 - @return A fixed up graph.
 -}
function constructPhantomProductionGraph
[ProductionGraph] ::= nt::String  flowEnv::FlowEnv  realEnv::Env
{
  -- Just synthesized attributes.
  local syns :: [String] = getSynAttrsOn(nt, realEnv);
  -- Those syns that are not part of the host, and so should have edges to fwdeq
  local extSyns :: [String] = removeAll(getHostSynsFor(nt, flowEnv), syns);

  -- The phantom edges: ext syn -> fwd
  local phantomEdges :: [(FlowVertex, FlowVertex)] =
    map(getPhantomEdge, extSyns);
  
  -- The stitch point: oddball. LHS stitch point. Normally, the LHS is not.
  local stitchPoints :: [StitchPoint] = nonterminalStitchPoints(realEnv, nt, lhsVertexType());
  local sigNtStitchPoints :: [StitchPoint] = [];
    
  local flowTypeAttrs :: [String] = syns;
  local initialGraph :: g:Graph<FlowVertex> = createFlowGraph(phantomEdges);
  local suspectEdges :: [(FlowVertex, FlowVertex)] = [];

  local g :: ProductionGraph = productionGraph(
    prod="Phantom for " ++ nt, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialGraph, suspectEdges=suspectEdges,
    stitchPoints=stitchPoints, sigNtStitchPoints=sigNtStitchPoints);
  
  -- Optimization: omit the phantom graph if there are no extension syns for the NT.
  return if null(extSyns) then [] else [g];
}


{--
 - Constructs a graph for a dispatch signature.
 -
 - @param ns  The dispatch signature
 - @param flowEnv  A full flow environment (need to find uses and impls of this sig)
 - @param realEnv  A full real environment (need to find out original signature and what inhs occur for stitch points)
 - @return A fixed up graph.
 -}
function constructDispatchGraph
ProductionGraph ::= ns::NamedSignature  flowEnv::FlowEnv  realEnv::Env
{
  local dispatch :: String = ns.fullName;
  local nt :: NtName = ns.outputElement.typerep.typeName;
  local defs :: [FlowDef] = getGraphContribsFor(dispatch, flowEnv);

  local normalEdges :: [(FlowVertex, FlowVertex)] =
    flatMap(addDispatchEqs(flowEnv, realEnv, ns, _), defs);

  local stitchPoints :: [StitchPoint] =
    sigSharingStitchPoints(realEnv, defs) ++  -- where this dispatch is applied
    dispatchStitchPoints(flowEnv, realEnv, ns, defs);  -- impls of this dispatch
  local sigNtStitchPoints :: [StitchPoint] = [];

  local flowTypeAttrs :: [String] = [];  -- Doesn't (directly) affect flow types
  local initialGraph :: g:Graph<FlowVertex> = createFlowGraph(normalEdges);
  local suspectEdges :: [(FlowVertex, FlowVertex)] = [];

  return productionGraph(
    prod=dispatch, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialGraph, suspectEdges=suspectEdges,
    stitchPoints=stitchPoints, sigNtStitchPoints=sigNtStitchPoints);
}

fun getPhantomEdge (FlowVertex, FlowVertex) ::= at::String =
  (lhsSynVertex(at), lhsSynVertex("forward"));

fun notRhsEqDep Boolean ::= e::(FlowVertex, FlowVertex) =
  case e of
  | (_, rhsEqVertex(_)) -> false
  | _ -> true
  end;

fun isSigEdge Boolean ::= edge::(FlowVertex, FlowVertex) =
  edge.1.isSigVertex && edge.2.isSigVertex;

synthesized attribute isSigVertex :: Boolean occurs on FlowVertex;
aspect isSigVertex on FlowVertex of
| lhsSynVertex(_) -> true
| lhsInhVertex(_) -> true
| rhsEqVertex(_) -> true
| rhsSynVertex(_, _) -> true
| rhsInhVertex(_, _) -> true
| _ -> false
end;

---- Begin helpers for fixing up graphs ----------------------------------------

{--
 - Introduces implicit 'lhs.syn -> forward.syn' (& forward.eq) equations.
 - Called twice: once for safe edges, later for SUSPECT edges!
 -}
fun addFwdSynEqs [(FlowVertex, FlowVertex)] ::= prod::ProdName syns::[String] flowEnv::FlowEnv =
  if null(syns) then []
  else (if null(lookupSyn(prod, head(syns), flowEnv))
    then [(lhsSynVertex(head(syns)), forwardSynVertex(head(syns))),
          (lhsSynVertex(head(syns)), forwardEqVertex())] else []) ++
    addFwdSynEqs(prod, tail(syns), flowEnv);
{--
 - Introduces implicit 'forward.inh = lhs.inh' equations.
 - Inherited equations are never suspect.
 -}
fun addFwdInhEqs [(FlowVertex, FlowVertex)] ::= prod::ProdName inhs::[String] flowEnv::FlowEnv =
  if null(inhs) then []
  else (if null(lookupFwdInh(prod, head(inhs), flowEnv)) then [(forwardInhVertex(head(inhs)), lhsInhVertex(head(inhs)))] else []) ++
    addFwdInhEqs(prod, tail(inhs), flowEnv);
{--
 - Introduces implicit 'fwrd.inh = lhs.inh' equations for forward production attributes.
 - Inherited equations are never suspect.
 -}
fun addFwdProdAttrInhEqs
[(FlowVertex, FlowVertex)] ::= prod::ProdName fName::String inhs::[String] flowEnv::FlowEnv =
  if null(inhs) then []
  else (if null(lookupLocalInh(prod, fName, head(inhs), flowEnv)) then [(localInhVertex(fName, head(inhs)), lhsInhVertex(head(inhs)))] else []) ++
    addFwdProdAttrInhEqs(prod, fName, tail(inhs), flowEnv);
fun allFwdProdAttrs [String] ::= d::[FlowDef] =
  case d of
  | [] -> []
  | localEq(_, fN, _, true, _) :: rest -> fN :: allFwdProdAttrs(rest)
  | _ :: rest -> allFwdProdAttrs(rest)
  end;
{--
 - Introduces default equations deps.
 -}
fun addDefEqs
[(FlowVertex, FlowVertex)] ::= prod::ProdName nt::NtName syns::[String] flowEnv :: FlowEnv =
  if null(syns) then []
  else (if null(lookupSyn(prod, head(syns), flowEnv)) 
        then let x :: [FlowDef] = lookupDef(nt, head(syns), flowEnv)
              in if null(x) then [] else head(x).flowEdges 
             end
        else []) ++
    addDefEqs(prod, nt, tail(syns), flowEnv);
{--
 - Introduce edges for inh/syn attributes on shared references to/from their decoration sites.
 -}
 fun addSharingEqs [(FlowVertex, FlowVertex)] ::= flowEnv::FlowEnv realEnv::Env d::FlowDef =
   case d of
   | refDecSiteEq(prod, nt, ref, decSite, isAlwaysDec) when
        case ref of
        | localVertexType(fName) -> !isForwardProdAttr(prod, fName, flowEnv)
        | _ -> true
        end ->
      cartProd(decSite.eqVertex, ref.eqVertex) ++
      filterMap(
        \ attr::String ->
          if vertexHasInhEq(prod, ref, attr, flowEnv)
          -- There is an override equation, so the attribute isn't supplied through sharing.
          -- Note that the reverse equation is introduced by the override eq.
          then nothing()
          else just((ref.inhVertex(attr), decSite.inhVertex(attr))),
        getInhAndInhOnTransAttrsOn(nt, realEnv)) ++
      map(
        \ attr::String -> (decSite.synVertex(attr), ref.synVertex(attr)),
        "forward" :: getSynAttrsOn(nt, realEnv))
   | _ -> []
   end;
{--
 - Introduce edges between lhs/rhs syn/inh and subterm vertices with tile deps.
 -}
fun addDispatchEqs
[(FlowVertex, FlowVertex)] ::= flowEnv::FlowEnv realEnv::Env dispatch::NamedSignature d::FlowDef =
  case d of
  | implFlowDef(_, prod, sigNames, _) -> concat(zipWith(
      \ ie::NamedSignatureElement sigName::String ->
        (rhsEqVertex(ie.elementName), subtermEqVertex(lhsVertexType(), prod, sigName)) ::
        (subtermEqVertex(lhsVertexType(), prod, sigName), rhsEqVertex(ie.elementName)) ::
        map(\ attr::String ->
          (subtermSynVertex(lhsVertexType(), prod, sigName, attr), rhsSynVertex(ie.elementName, attr)),
          "forward" :: getSynAttrsOn(ie.typerep.typeName, realEnv)) ++
        flatMap(\ attr::String ->
          [(rhsInhVertex(ie.elementName, attr), subtermInhVertex(lhsVertexType(), prod, sigName, attr)),
          -- We always include the subterm -> RHS inh dep, because we are trying to determine
          -- what RHS inh are allowable deps in dispatch impl override eqs.
           (subtermInhVertex(lhsVertexType(), prod, sigName, attr), rhsInhVertex(ie.elementName, attr))],
          getInhAndInhOnTransAttrsOn(ie.typerep.typeName, realEnv)),
      dispatch.inputElements, sigNames))
  | _ -> []
  end;

---- End helpers for fixing up graphs ------------------------------------------

---- Begin helpers for figuring out stitch points ------------------------------

{--
 - Stitch points for the flow type of 'nt', and the flow types of all translation attributes on 'nt'.
 -}
fun nonterminalStitchPoints [StitchPoint] ::= realEnv::Env  nt::NtName  vertexType::VertexType =
  nonterminalStitchPoint(nt, vertexType) ::
  flatMap(
    \ o::OccursDclInfo ->
      case getAttrDcl(o.attrOccurring, realEnv) of
      | at :: _ when at.isSynthesized && at.isTranslation ->
        nonterminalStitchPoints(
          realEnv, at.typeScheme.typeName,
          transAttrVertexType(vertexType, o.attrOccurring))
      | _ -> []
      end,
    getAttrOccursOn(nt, realEnv));
fun localStitchPoints [StitchPoint] ::= realEnv::Env  ds::[FlowDef] =
  flatMap(\ d::FlowDef ->
    case d of
    -- Add stitch points for holes that are nonterminal types
    | holeEq(_, tN, true, vt, _) -> nonterminalStitchPoints(realEnv, tN, vt)
    -- Ignore all other flow def info
    | _ -> []
    end, ds);
function rhsStitchPoints
[StitchPoint] ::= realEnv::Env  rhs::NamedSignatureElement
{
  return
    -- We want only NONTERMINAL stitch points!
    if rhs.typerep.isNonterminal
    then nonterminalStitchPoints(realEnv, rhs.typerep.typeName, rhsVertexType(rhs.elementName))
    else [];
}
fun patternStitchPoints [StitchPoint] ::= realEnv::Env  defs::[FlowDef] =
  case defs of
  | [] -> []
  | patternRuleEq(_, matchProd, scrutinee, vars) :: rest ->
      flatMap(patVarStitchPoints(matchProd, scrutinee, realEnv, _), vars) ++
      patternStitchPoints(realEnv, rest)
  | _ :: rest -> patternStitchPoints(realEnv, rest)
  end;
fun patVarStitchPoints [StitchPoint] ::= matchProd::String  scrutinee::VertexType  realEnv::Env  var::PatternVarProjection =
  case var of
  | patternVarProjection(child, typeName, patternVar) -> 
      projectionStitchPoint(
        matchProd, anonVertexType(patternVar), scrutinee, rhsVertexType(child),
        getInhAndInhOnTransAttrsOn(typeName, realEnv)) ::
      nonterminalStitchPoints(realEnv, typeName, anonVertexType(patternVar))
  end;
-- deps for subterm vertex of applied prod
fun subtermDecSiteStitchPoints [StitchPoint] ::= defs::[FlowDef] =
  flatMap(\ d::FlowDef ->
    case d of
    | subtermDecEq(_, parent, termProdName, _) ->
        [tileStitchPoint(termProdName, parent)]
    | _ -> []
    end,
    defs);
-- deps for prod/dispatch sig, from prods that forwarded to it
fun sigSharingStitchPoints [StitchPoint] ::= realEnv::Env  defs::[FlowDef] =
  flatMap(\ d::FlowDef ->
    case d of
    | sigShareSite(_, sigNt, sigName, sourceProd, vt) ->
        [projectionStitchPoint(
          sourceProd, rhsVertexType(sigName), lhsVertexType(), vt,
          getInhAndInhOnTransAttrsOn(sigNt, realEnv))]
    | _ -> []
    end,
    defs);
-- deps for child of prod, from dispatch sig that prod implements
fun implementedSigStitchPoints [StitchPoint] ::= realEnv::Env  nt::NtName  ie::NamedSignatureElement  dispatch::String se::NamedSignatureElement =
  if ie.elementShared || ie.typerep.isNonterminal
  then [projectionStitchPoint(
    dispatch, rhsVertexType(ie.elementName), lhsVertexType(), rhsVertexType(se.elementName),
    getInhAndInhOnTransAttrsOn(ie.typerep.typeName, realEnv))]
  else [];
-- deps for dispatch sig, from prods that implement it
fun dispatchStitchPoints [StitchPoint] ::= flowEnv::FlowEnv  realEnv::Env  dispatch::NamedSignature  defs::[FlowDef] =
  flatMap(\ d::FlowDef ->
    case d of
    | implFlowDef(_, prod, sigNames, extraSigNts) ->
        tileStitchPoint(prod, lhsVertexType()) ::
        concat(unzipWith(\ sigName::String nt::String ->
          nonterminalStitchPoints(realEnv, nt, subtermVertexType(lhsVertexType(), prod, sigName)),
          extraSigNts))
    | _ -> []
    end,
    defs);

---- End helpers for figuring our stitch points --------------------------------

fun prodGraphToEnv Pair<String ProductionGraph> ::= p::ProductionGraph = (p.prod, p);

---- Begin Suspect edge handling -----------------------------------------------

{--
 - This function finds edges that should be introduced from a suspect edge.
 -
 - Suspect edges themselves can never be introduced, because the interaction of
 - introducing two or more suspect edges can be undesirable.  (a,b) might be
 - introduced, followed by (b,c). But (b,c) might have prevented (a,b) from
 - appearing!
 -
 - Instead we introduce their ultimate dependencies of interest:
 - If (a,b) is introduced, we actually introduce (a, x) for x: an inherited
 - attribute that a does not already depend upon that is in a's flow type.
 - This way, after (b,c)'s edges are admitted, we come back to (a,b) and do not
 - admit the extra edges c introduced (through b) for a.
 -
 - A note on this being applied "in parallel:" it's okay not to update 'ft' and 'graph'
 - after each edge is introduced, as this is conservative: it just means we'll
 - potentially introduce an edge next iteration.
 -
 - The reason is that each edge is TO an lhsInh, which never gets edges from it.
 - So once valid that edge is valid, it is always valid. No additional edges or
 - flow type updates will change that.
 -
 - @param edge  A suspect edge. INVARIANT: edge.fst can always be looked up in the flow type.
 -              (currently, a syn or fwd)
 - @param graph  The current graph
 - @param ft  The current flow types for the nonterminal this graph belongs to.
 - @return  Edges to introduce. INVARIANT: .fst is always edge.fst, .snd is
 -          always an lhsInhVertex.
 -}
function findAdmissibleEdges
[(FlowVertex, FlowVertex)] ::= edge::(FlowVertex, FlowVertex)  graph::g:Graph<FlowVertex>  ft::FlowType
{
  local edgeSyn::String =
    case edge.1 of
    | lhsSynVertex(at) -> at
    | v -> error("Suspect edge source vertex is not lhsSynVertex: " ++ v.vertexName)
    end;

  -- The current flow type of the edge's source vertex (which is always a thing in the flow type)
  local currentDeps :: set:Set<String> =
    g:edgesFrom(edgeSyn, ft);
  
  local targetNotSource :: set:Set<FlowVertex> = 
    set:difference(
      g:edgesFrom(edge.snd, graph),
      g:edgesFrom(edge.fst, graph));
  
  -- ONLY those that ARE in current. i.e. dependencies that do not expand the flow type of this source vertex.
  local validDeps :: [FlowVertex] = 
    filter(isLhsInhSet(_, currentDeps), set:toList(targetNotSource));
  
  return if set:isEmpty(currentDeps) then [] -- just a quick optimization.
  else map(pair(fst=edge.fst, snd=_), validDeps);
}

