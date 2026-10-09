grammar silver:compiler:definition:flow:driver;

import silver:compiler:definition:type only isNonterminal, typerep;

data nonterminal ProductionGraph with
  prod, lhsNt, flowTypeAttrs, graph, tileGraph, unstitchedGraph, suspectEdges,
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

-- The edges within this production before any stitching or transitive closure,
-- without edges from addDecSiteTreeEqs (see decSiteOwnInhDeps).
annotation unstitchedGraph::g:Graph<FlowVertex>;

-- Edges that are not permitted to affect their OWN flow types (but perhaps some unknown other flowtypes)
annotation suspectEdges::[(FlowVertex, FlowVertex)];

-- Places where current flow types need grafting to this graph to yield a full flow graph
annotation stitchPoints::[StitchPoint];

-- Stitch points that arise from signature nonterminals, only used in graph and not tileGraph.
annotation sigNtStitchPoints::[StitchPoint];

{--
 - Given a set of flow types, stitches those edges into the graph for
 - the stitch points (i.e. children, locals, forward) that satisfy a predicate.
 - Either just a new graph, or nothing if no new edges were added.
 -}
synthesized attribute stitchedGraph :: (Maybe<ProductionGraph> ::= EnvTree<FlowType> EnvTree<ProductionGraph> (Boolean ::= StitchPoint));

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
  top.stitchedGraph = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> include::(Boolean ::= StitchPoint) ->
    let
      edges :: [(FlowVertex, FlowVertex)] =
        flatMap(stitchEdgesFor(_, flowTypes, prodGraphs), filter(include, top.stitchPoints)),
      sigEdges :: [(FlowVertex, FlowVertex)] =
        flatMap(stitchEdgesFor(_, flowTypes, prodGraphs), filter(include, top.sigNtStitchPoints))
    in let
      newEdges :: [(FlowVertex, FlowVertex)] =
        filter(edgeIsNew(_, top.graph), filter(notSigEqDep, edges) ++ sigEdges),
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

  -- Only the edges from signature vertices are needed, so avoid listing all the edges of the tile graph.
  top.tileEdges =
    flatMap(
      \ vws::(FlowVertex, set:Set<FlowVertex>) ->
        if vws.1.isSigVertex
        then map(\ w::FlowVertex -> (vws.1, w), filter(\ w::FlowVertex -> w.isSigVertex, set:toList(vws.2)))
        else [],
      g:adjacency(top.tileGraph));

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
  updateChangedGraph(graph, prodEnv, ntEnv, \ _ -> true);

{--
 - Update a graph whose flow types and production graphs have changed only for some dependency keys
 - (see stitchDep) since it was last updated.  Only the stitch points depending on those can give new
 - edges, and the suspect edges need another look only if the graph or its nonterminal's flow type changed.
 -
 - @param changed  Whether a dependency key has changed since the graph was last updated
 -}
fun updateChangedGraph
Maybe<ProductionGraph> ::=
    graph::ProductionGraph
    prodEnv::EnvTree<ProductionGraph>
    ntEnv::EnvTree<FlowType>
    changed::(Boolean ::= String) =
  case graph.stitchedGraph(ntEnv, prodEnv, \ sp::StitchPoint -> changed(sp.stitchDep)) of
  | just(newGraph) -> alt(newGraph.cullSuspect(ntEnv), just(newGraph))
  | nothing() ->
    if changed(prodDep(graph.prod)) || changed(ntDep(graph.lhsNt))
    then graph.cullSuspect(ntEnv)
    else nothing()
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
  -- Only a production with a signature-shared child can reference its forward parent explicitly,
  -- so don't add the forward parent edges unless it has one.
  local hasSharedChild :: Boolean = any(map((.elementShared), dcl.namedSignature.inputElements));
  local fwdParentEdges :: [(FlowVertex, FlowVertex)] =
    if hasSharedChild then map(\ attr::String -> (forwardParentInhVertex(attr), lhsInhVertex(attr)), inhs) else [];
  
  -- Normal edges!
  local normalEdges :: [(FlowVertex, FlowVertex)] =
    flatMap((.flowEdges), defs);
  
  -- Insert implicit equations.
  local fixedEdges :: [(FlowVertex, FlowVertex)] =
    normalEdges ++
    (if nonForwarding
     then addDefEqs(prod, nt, syns, flowEnv)
     else addFwdSynEqs(prod, nt, synsBySuspicion.fst, flowEnv, realEnv) ++
          addFwdInhEqs(prod, forwardVertexType(), inhs, defs, flowEnv, realEnv)) ++
    addTransRootEqs(prod, syns, flowEnv, realEnv) ++
    flatMap(addFwdInhEqs(prod, _, inhs, defs, flowEnv, realEnv), map(localVertexType, allFwdProdAttrs(defs))) ++
    flatMap(addSharingEqs(flowEnv, realEnv, _), defs) ++
    map(addLhsEqRhsEq, dcl.namedSignature.inputElements);
  
  -- (safe, suspect)
  local synsBySuspicion :: Pair<[String] [String]> =
    partition(contains(_, getNonSuspectAttrsForProd(prod, flowEnv)), syns);
  
  -- No implicit equations here, just keep track.
  local suspectEdges :: [(FlowVertex, FlowVertex)] =
    flatMap((.suspectFlowEdges), defs) ++
    -- If it's forwarding .snd is attributes not known at forwarding time. If it's non, then actually .snd is all attributes. Ignore.
    if nonForwarding then [] else addFwdSynEqs(prod, nt, synsBySuspicion.snd, flowEnv, realEnv);

  -- Translation attributes this production does not define, whose translation it cannot see.
  local undefinedTransAttrs :: [String] =
    filter(
      \ attr::String -> isTranslationAttr(attr, realEnv) && null(lookupSyn(prod, attr, flowEnv)),
      syns);
  -- One supplied by a default equation.  The default's own tiles are not among this production's defs,
  -- so the stitch point is needed in the tile graph too.
  local defaultTransAttrs :: [String] =
    if nonForwarding then filter(\ attr::String -> !null(lookupDef(nt, attr, flowEnv)), undefinedTransAttrs) else [];
  -- One taken from the forward whose implicit copy is suspect.  The dotted copies of its synthesized
  -- attributes are never admitted into the graph (see findAdmissibleEdges), but they are exact in the
  -- tile graph, so the stitch point is kept out of it.
  local suspectTransAttrs :: [String] =
    if nonForwarding then [] else filter(contains(_, synsBySuspicion.snd), undefinedTransAttrs);

  -- Stitch points only in the normal graph: the flow types of the trees this production is given (its children and
  -- forward parent), and what applications of it, or of the dispatch signature it implements, may supply to its
  -- children.  Wherever the tile flow graph is stitched in, the stitching graph already has these more precisely.
  local sigNtStitchPoints :: [StitchPoint] =
    flatMap(rhsStitchPoints(realEnv, _), dcl.namedSignature.inputElements) ++
    lhsTransStitchPoints(realEnv, nt, suspectTransAttrs) ++
    (if hasSharedChild then nonterminalStitchPoints(realEnv, nt, forwardParentVertexType()) else []) ++
    sigSharingStitchPoints(flowEnv, realEnv, prod, dcl.namedSignature.inputNames, dcl.namedSignature.inputElements) ++
    case dcl.implementedSignature of
    | just(sig) ->
      concat(zipWith(
        implementedSigStitchPoints(realEnv, _, sig.fullName, _),
        dcl.namedSignature.inputElements,
        sig.inputElements))
    | nothing() -> []
    end;

  -- Stitch points in both graphs.
  local stitchPoints :: [StitchPoint] =
    lhsTransStitchPoints(realEnv, nt, defaultTransAttrs) ++
    localStitchPoints(realEnv, defs) ++
    patternStitchPoints(realEnv, defs) ++
    subtermDecSiteStitchPoints(defs);

  local flowTypeSpecs :: [String] = getSpecifiedSynsForNt(nt, flowEnv);
  
  local flowTypeAttrs :: [String] =
    filter(!contains(_, flowTypeSpecs),
      (if nonForwarding then [] else ["forward"]) ++ syns);
  
  -- Edges to trees decorated before this production got them (see addDecSiteTreeEqs), left out of unstitchedGraph.
  local decSiteTreeEdges :: [(FlowVertex, FlowVertex)] =
    flatMap(addDecSiteTreeEqs(realEnv, dcl.namedSignature, dcl.implementedSignature, _), defs);
  -- deps on LHS/RHS.EQ don't matter in the regular graph
  local ownEdges :: [(FlowVertex, FlowVertex)] = filter(notSigEqDep, fixedEdges) ++ fwdParentEdges;
  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(ownEdges ++ decSiteTreeEdges);
  local initialTileGraph :: g:Graph<FlowVertex> =
    createFlowGraph(suspectEdges ++ fixedEdges ++ decSiteTreeEdges);
  local unstitchedGraph :: g:Graph<FlowVertex> =
    g:add(ownEdges, g:emptyWith(compareVertexId));

  return productionGraph(
    prod=prod, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialTileGraph, unstitchedGraph=unstitchedGraph,
    suspectEdges=suspectEdges, stitchPoints=stitchPoints, sigNtStitchPoints=sigNtStitchPoints);
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
    createFlowGraph(filter(notSigEqDep, fixedEdges)); -- deps on LHS/RHS.EQ don't matter in the regular graph
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
    graph=initialGraph, tileGraph=initialTileGraph, unstitchedGraph=initialGraph, suspectEdges=suspectEdges,
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
    patternStitchPoints(realEnv, defs) ++
    subtermDecSiteStitchPoints(defs);
  local sigNtStitchPoints :: [StitchPoint] = [];

  local flowTypeAttrs :: [String] = []; -- Not used as part of inference.
  
  local g :: ProductionGraph = productionGraph(
    prod=prod, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialGraph, unstitchedGraph=initialGraph, suspectEdges=suspectEdges,
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
    patternStitchPoints(realEnv, defs) ++
    subtermDecSiteStitchPoints(defs);
  local sigNtStitchPoints :: [StitchPoint] = [];

  local flowTypeSpecs :: [String] = getSpecifiedSynsForNt(nt, flowEnv);
  local flowTypeAttrs :: [String] =
    filter(!contains(_, flowTypeSpecs), syns);

  local g :: ProductionGraph = productionGraph(
    prod=prod, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialGraph, unstitchedGraph=initialGraph, suspectEdges=suspectEdges,
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
    graph=initialGraph, tileGraph=initialGraph, unstitchedGraph=initialGraph, suspectEdges=suspectEdges,
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
    flatMap(addDispatchEqs(flowEnv, realEnv, ns, _), defs) ++
    addDispatchUnknownImplInhEqs(flowEnv, realEnv, ns);

  local stitchPoints :: [StitchPoint] =
    dispatchStitchPoints(flowEnv, realEnv, ns, defs) ++  -- impls of this dispatch
    -- The forward of an implementation from an independent extension: see addDispatchUnknownImplInhEqs.
    nonterminalStitchPoints(realEnv, nt, forwardVertexType());
  -- Where this dispatch, or one of its implementations by name, is applied in the host language, for the inherited
  -- attributes supplied to the children of implementations.  These are not in the tile flow graph, as each application
  -- knows what it supplies.
  local sigNtStitchPoints :: [StitchPoint] =
    flatMap(
      \ p::(String, [String]) -> sigSharingStitchPoints(flowEnv, realEnv, p.1, p.2, ns.inputElements),
      (dispatch, ns.inputNames) :: getImplementingProds(dispatch, flowEnv));

  local flowTypeAttrs :: [String] = [];  -- Doesn't (directly) affect flow types
  local initialGraph :: g:Graph<FlowVertex> = createFlowGraph(normalEdges);
  local initialTileGraph :: g:Graph<FlowVertex> =
    createFlowGraph(normalEdges ++ addDispatchUnknownImplSynEqs(realEnv, ns));
  local suspectEdges :: [(FlowVertex, FlowVertex)] = [];

  return productionGraph(
    prod=dispatch, lhsNt=nt, flowTypeAttrs=flowTypeAttrs,
    graph=initialGraph, tileGraph=initialTileGraph, unstitchedGraph=initialGraph, suspectEdges=suspectEdges,
    stitchPoints=stitchPoints, sigNtStitchPoints=sigNtStitchPoints);
}

fun getPhantomEdge (FlowVertex, FlowVertex) ::= at::String =
  (lhsSynVertex(at), forwardEqVertex);

fun notSigEqDep Boolean ::= e::(FlowVertex, FlowVertex) =
  case e of
  | (_, lhsEqVertex()) -> false
  | (_, rhsEqVertex(_)) -> false
  | (_, rhsOuterEqVertex(_)) -> false
  | _ -> true
  end;

synthesized attribute isSigVertex :: Boolean occurs on FlowVertex;
aspect isSigVertex on FlowVertex of
-- Note that deps on lhsEqVertex are not included in tile stitch points,
-- as it is only used to locally collect the deps for taking a reference to the LHS.
| lhsSynVertex(_) -> true
| lhsInhVertex(_) -> true
| rhsEqVertex(_) -> true
| rhsOuterEqVertex(_) -> true
| rhsSynVertex(_, _) -> true
| rhsInhVertex(_, _) -> true
| forwardParentEqVertex() -> true
| forwardParentSynVertex(_) -> true
| forwardParentInhVertex(_) -> true
| _ -> false
end;

---- Begin helpers for fixing up graphs ----------------------------------------

{--
 - Introduces implicit 'lhs.syn -> forward.syn' (& forward.outerEq) equations.
 - Called twice: once for safe edges, later for SUSPECT edges!
 -}
fun addFwdSynEqs [(FlowVertex, FlowVertex)] ::= prod::ProdName nt::NtName syns::[String] flowEnv::FlowEnv realEnv::Env =
  flatMap(
    \ syn::String ->
      if null(lookupSyn(prod, syn, flowEnv))
      then (lhsSynVertex(syn), forwardSynVertex(syn)) ::
           (lhsSynVertex(syn), forwardOuterEqVertex()) ::
           -- A translation attribute taken from the forward is the forward's translation,
           -- so the synthesized attributes of the translation are copied as well.
           -- (When suspect, these dotted copies reach only the tile graph; see findAdmissibleEdges.)
           map(
             \ transSyn::String ->
               (lhsSynVertex(s"${syn}.${transSyn}"), forwardSynVertex(s"${syn}.${transSyn}")),
             getSynOnTransAttr(syn, nt, realEnv))
      else [],
    syns);
{--
 - A translation attribute that the production does not define (it is taken from the forward, or
 - from a default equation) is built elsewhere, so the root of the LHS's translation depends on the
 - whole value of the attribute.  Never suspect: suspicion is kept on the edges out of the attribute's
 - own vertex.
 -}
fun addTransRootEqs [(FlowVertex, FlowVertex)] ::= prod::ProdName syns::[String] flowEnv::FlowEnv realEnv::Env =
  flatMap(
    \ syn::String ->
      if isTranslationAttr(syn, realEnv) && null(lookupSyn(prod, syn, flowEnv))
      then [(transAttrOuterEqVertex(lhsVertexType(), syn), lhsSynVertex(syn))]
      else [],
    syns);

fun isTranslationAttr Boolean ::= attr::String realEnv::Env =
  case getAttrDcl(attr, realEnv) of
  | at :: _ -> at.isTranslation
  | [] -> false
  end;

{--
 - Nonterminal stitch points for the LHS's translations of the given translation attributes on 'nt'.
 -}
fun lhsTransStitchPoints [StitchPoint] ::= realEnv::Env  nt::NtName  attrs::[String] =
  flatMap(
    \ attr::String ->
      case getOccursDcl(attr, nt, realEnv) of
      | o :: _ -> nonterminalStitchPoints(realEnv, o.attrTypeName, transAttrVertexType(lhsVertexType(), attr))
      | [] -> []
      end,
    attrs);
{--
 - Introduces implicit 'vt.inh = lhs.inh' equations for vt, the forward or a forward production attribute, for the
 - inherited attributes that have no direct equation on vt.
 - This production is the forward parent of the tree at vt.  If vt's defining expression may build that tree by
 - applying a production or dispatch signature that can record a dependency on its forward parent's inherited
 - attribute i as a dependency on its own i (see dependsOnFwdParentViaLhs), a dependency on vt.i may really be on
 - this production's i, so the edge is kept even where vt.i has an equation.
 - Inherited equations are never suspect.
 -}
fun addFwdInhEqs
[(FlowVertex, FlowVertex)] ::=
  prod::ProdName  vt::VertexType  inhs::[String]  defs::[FlowDef]  flowEnv::FlowEnv  realEnv::Env =
  map(
    \ attr::String -> (vt.inhVertex(attr), lhsInhVertex(attr)),
    if any(map(
         \ d::FlowDef ->
           case d of
           | subtermDecEq(_, _, parent, termProd) ->
             parent.vertexName == vt.vertexName && dependsOnFwdParentViaLhs(termProd, realEnv)
           | _ -> false
           end,
         defs))
    then inhs
    else filter(
      \ attr::String ->
        null(case vt of
             | localVertexType(fName) -> lookupLocalInh(prod, fName, attr, flowEnv)
             | _ -> lookupFwdInh(prod, attr, flowEnv)
             end),
      inhs));
-- Can the production or dispatch signature termProd record a dependency on its forward parent's inherited attribute i
-- as a dependency on its own i?  A dispatch signature (which has no value declaration) and its implementations can
-- (see implementedSigStitchPoints), and so can a production with a signature-shared child (see sigSharingStitchPoints).
fun dependsOnFwdParentViaLhs Boolean ::= termProd::String  realEnv::Env =
  case getValueDcl(termProd, realEnv) of
  | [] -> true
  | dcl :: _ -> dcl.implementedSignature.isJust || any(map((.elementShared), dcl.namedSignature.inputElements))
  end;
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
      filterMap(
        \ attr::String ->
          if vertexHasInhEq(prod, ref, attr, flowEnv)
          -- There is an override equation, so the attribute isn't supplied through sharing.
          -- Note that the reverse equation is introduced by the override eq.
          then nothing()
          else just((ref.inhVertex(attr), decSite.inhVertex(attr))),
        getInhAndInhOnTransAttrsOn(nt, realEnv)) ++
      -- The shared tree's translations are the decoration site's translations,
      -- so their synthesized attributes are related too.
      map(
        \ attr::String -> (decSite.synVertex(attr), ref.synVertex(attr)),
        "forward" :: getSynAndSynOnTransAttrsOn(nt, realEnv))
   | _ -> []
   end;
{--
 - Introduce edges from the inherited attributes of a decoration site to those of the tree shared there, if the tree was
 - decorated before this production got it.  What was supplied to the tree then takes precedence over the decoration
 - site's equations.  A decoration site that is a translation attribute of the LHS gets none: its inherited vertices
 - are LHS inherited vertices (lhs.t.k), which must have no out-edges (see findAdmissibleEdges.)
 -}
fun addDecSiteTreeEqs
[(FlowVertex, FlowVertex)] ::= realEnv::Env  ns::NamedSignature  implSig::Maybe<NamedSignature>  d::FlowDef =
  case d of
  | refDecSiteEq(_, nt, ref, decSite, _)
      when decoratedBefore(ns, implSig, ref) && transRootVertex(decSite) != lhsVertexType() ->
    map(\ attr::String -> (decSite.inhVertex(attr), ref.inhVertex(attr)), getInhAndInhOnTransAttrsOn(nt, realEnv))
  | _ -> []
  end;
{--
 - Was the tree vt decorated before the production with signature ns got it?  A child shared through the signature
 - was, and so may be a nonterminal child of an implementation at a position of the dispatch signature implSig that it
 - implements, as an application may pass a tree with '@x' to that child even where implSig does not share it (see
 - implementedSigStitchPoints.)  So may the translation attributes of these.
 -}
fun decoratedBefore Boolean ::= ns::NamedSignature  implSig::Maybe<NamedSignature>  vt::VertexType =
  case transRootVertex(vt) of
  | rhsVertexType(sigName) ->
    lookupSignatureInputElem(sigName, ns).elementShared ||
    case implSig of
    | just(sig) ->
      positionOf(sigName, ns.inputNames) < length(sig.inputElements) &&
      lookupSignatureInputElem(sigName, ns).typerep.isNonterminal
    | nothing() -> false
    end
  | _ -> false
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
          "forward" :: getSynAndSynOnTransAttrsOn(ie.typerep.typeName, realEnv)) ++
        flatMap(\ attr::String ->
          [(rhsInhVertex(ie.elementName, attr), subtermInhVertex(lhsVertexType(), prod, sigName, attr)),
          -- We always include the subterm -> RHS inh dep, because we are trying to determine
          -- what RHS inh are allowable deps in dispatch impl override eqs.
           (subtermInhVertex(lhsVertexType(), prod, sigName, attr), rhsInhVertex(ie.elementName, attr))],
          getInhAndInhOnTransAttrsOn(ie.typerep.typeName, realEnv)),
      dispatch.inputElements, sigNames))
  | _ -> []
  end;
{--
 - Introduce edges for what an implementation of a dispatch signature in an independent extension may supply.
 - Such implementations are not known here, but must forward to an application of the dispatch signature
 - (see OrphanedProduction.sv).  The forward vertex type stands for that forward.  The graph of a dispatch signature
 - does not otherwise use the forward vertex type.
 - * The forward has the inherited attributes of the LHS.
 - * Some application in the host language might not supply an inherited attribute to an argument
 -   (see sigShareSitesHaveInhEq).  The implementation's equation for that attribute may then depend on anything in the
 -   forward flow type.
 -}
fun addDispatchUnknownImplInhEqs
[(FlowVertex, FlowVertex)] ::= flowEnv::FlowEnv  realEnv::Env  dispatch::NamedSignature =
  map(
    \ attr::String -> (forwardInhVertex(attr), lhsInhVertex(attr)),
    getInhAndInhOnTransAttrsOn(dispatch.outputElement.typerep.typeName, realEnv)) ++
  flatMap(
    \ ie::NamedSignatureElement ->
      map(
        \ attr::String -> (rhsInhVertex(ie.elementName, attr), unknownImplForwardVertex),
        implSuppliedInhs(flowEnv, realEnv, dispatch.fullName, ie)),
    dispatch.inputElements);
{--
 - Introduce edges for the synthesized attributes and the forward of an implementation of a dispatch signature in an
 - independent extension (see addDispatchUnknownImplInhEqs).  These edges are only in the signature's tile flow graph.
 - * An attribute of the LHS is copied from the forward, or else defined within its own flow type, as the flow-type
 -   check on implementations accounts for anything an application or earlier implementation could supply to their
 -   children (see implementedSigStitchPoints and dispatchChildAllowedInhs).  So the attribute depends on the same
 -   attribute of the forward.
 -   A nonterminal stitch point on the forward vertex type relates that attribute to its flow type.
 - * The forward may be built from the values of the arguments.
 -}
fun addDispatchUnknownImplSynEqs
[(FlowVertex, FlowVertex)] ::= realEnv::Env  dispatch::NamedSignature =
  flatMap(
    \ attr::String -> [(lhsSynVertex(attr), forwardSynVertex(attr)), (lhsSynVertex(attr), forwardOuterEqVertex())],
    "forward" :: getSynAndSynOnTransAttrsOn(dispatch.outputElement.typerep.typeName, realEnv)) ++
  (unknownImplForwardVertex, forwardOuterEqVertex()) ::
  flatMap(
    \ ie::NamedSignatureElement ->
      [(forwardOuterEqVertex(), rhsEqVertex(ie.elementName)),
       (forwardOuterEqVertex(), rhsOuterEqVertex(ie.elementName))],
    dispatch.inputElements);
-- The inherited attributes of the child de of a dispatch signature that an implementation may supply: those that some
-- application in the host language does not supply (see sigShareSitesHaveInhEq), or all of an unshared child's, as
-- sharing sites are only recorded for children shared through the signature.  A child that is not a tree has none.
fun implSuppliedInhs [String] ::= flowEnv::FlowEnv  realEnv::Env  dispatch::String  de::NamedSignatureElement =
  if de.elementShared || de.typerep.isNonterminal
  then filter(
    \ attr::String -> !de.elementShared || !sigShareSitesHaveInhEq(dispatch, de.elementName, attr, flowEnv),
    getInhAndInhOnTransAttrsOn(de.typerep.typeName, realEnv))
  else [];
-- The forward of an implementation from an independent extension: see addDispatchUnknownImplInhEqs.
global unknownImplForwardVertex :: FlowVertex = forwardSynVertex("forward");
{--
 - Introduce 'lhs.eq -> rhs.eq' edges, to capture the deps of taking a reference to the LHS.
 -}
fun addLhsEqRhsEq (FlowVertex, FlowVertex) ::= ne::NamedSignatureElement =
  (lhsEqVertex(), rhsEqVertex(ne.elementName));

---- End helpers for fixing up graphs ------------------------------------------

---- Begin helpers for figuring out stitch points ------------------------------

{--
 - Stitch points for the flow type of 'nt', and the flow types of all translation attributes on 'nt'.
 - A cycle in translation attribute occurrences is an error (checked in typechecking), but the
 - 'seen' list guarantees that this is finite regardless, since these graphs are also constructed
 - during error checking.
 -}
fun nonterminalStitchPoints [StitchPoint] ::= realEnv::Env  nt::NtName  vertexType::VertexType =
  nonterminalStitchPointsSeen([], realEnv, nt, vertexType);
fun nonterminalStitchPointsSeen [StitchPoint] ::= seen::[NtName]  realEnv::Env  nt::NtName  vertexType::VertexType =
  if contains(nt, seen) then []
  else nonterminalStitchPoint(nt, vertexType) ::
  flatMap(
    \ o::OccursDclInfo ->
      case getAttrDcl(o.attrOccurring, realEnv) of
      | at :: _ when at.isSynthesized && at.isTranslation ->
        nonterminalStitchPointsSeen(
          nt :: seen, realEnv, o.attrTypeName,
          transAttrVertexType(vertexType, o.attrOccurring))
      | _ -> []
      end,
    getAttrOccursOn(nt, realEnv));
fun localStitchPoints [StitchPoint] ::= realEnv::Env  ds::[FlowDef] =
  flatMap(\ d::FlowDef ->
    case d of
    -- Add stitch points for holes that are nonterminal types
    | holeEq(_, tN, true, vt, _) -> nonterminalStitchPoints(realEnv, tN, vt)
    | anonScrutineeEq(_, x, tN, true, _, gram, l, _) ->
      nonterminalStitchPoints(realEnv, tN, anonScrutineeVertexType(x, gram, l))
    -- Ignore all other flow def info
    | _ -> []
    end, ds);
fun rhsStitchPoints [StitchPoint] ::= realEnv::Env  rhs::NamedSignatureElement =
  -- We want only NONTERMINAL stitch points!
  -- A child shared through the signature has a reference type, but is still a tree of its nonterminal.
  if rhs.elementShared || rhs.typerep.isNonterminal
  then nonterminalStitchPoints(realEnv, rhs.typerep.typeName, rhsVertexType(rhs.elementName))
  else [];
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
  | patternVarProjection(child, typeName) -> 
      projectionStitchPoint(
        matchProd, subtermVertexType(scrutinee, matchProd, child), scrutinee, rhsVertexType(child),
        getInhAndInhOnTransAttrsOn(typeName, realEnv)) ::
      nonterminalStitchPoints(realEnv, typeName, subtermVertexType(scrutinee, matchProd, child))
  end;
-- deps for subterm vertex of applied prod
fun subtermDecSiteStitchPoints [StitchPoint] ::= defs::[FlowDef] =
  flatMap(\ d::FlowDef ->
    case d of
    | subtermDecEq(_, _, parent, termProdName) ->
        [tileStitchPoint(termProdName, parent)]
    | _ -> []
    end,
    defs);
-- Stitch points for what host-language applications of prod (a production, dispatch signature or implementation)
-- supply to the children it shares through its signature.  sigNames are prod's children, and elems those at the same
-- positions in the signature this graph is for: in a dispatch signature's graph, the signature's, as an
-- implementation applied by name may pass the tree on to another implementation (see implementedSigStitchPoints).
fun sigSharingStitchPoints
[StitchPoint] ::= flowEnv::FlowEnv  realEnv::Env  prod::String  sigNames::[String]  elems::[NamedSignatureElement] =
  concat(zipWith(
    \ sigName::String e::NamedSignatureElement ->
      map(
        \ sharingSite::(String, VertexType) ->
          projectionStitchPoint(
            sharingSite.1, rhsVertexType(e.elementName), lhsVertexType(), sharingSite.2,
            filter(
              vertexHasInhEq(sharingSite.1, sharingSite.2, _, flowEnv),
              getInhAndInhOnTransAttrsOn(e.typerep.typeName, realEnv))),
        lookupSigShareSites(prod, sigName, flowEnv)),
    sigNames, elems));
{--
 - Stitch points for a child of an implementation at a position of the dispatch signature it implements, for what may
 - be supplied to the child before the implementation gets it: by an application of the signature (see
 - DispatchSites.sv), by an earlier implementation in a chain of forwards, or by an implementation not known here (see
 - addDispatchUnknownImplInhEqs).  This projects the signature's normal graph onto the implementation's own inherited
 - attributes, as for its forward parent.
 -}
fun implementedSigStitchPoints
[StitchPoint] ::= realEnv::Env  ie::NamedSignatureElement  dispatch::String  se::NamedSignatureElement =
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

---- End helpers for figuring out stitch points --------------------------------

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
 - @param edge  A suspect edge. INVARIANT: edge.fst is an lhsSynVertex, looked up in the flow type.
 -              For the dotted synthesized attributes of a translation ("a.s"), which are never
 -              flow type keys, nothing is admitted: those copies only feed the tile graph, and the
 -              graph gets a nonterminal stitch point for the translation instead (suspectTransAttrs).
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
  
  local targetDeps :: set:Set<FlowVertex> = g:edgesFrom(edge.snd, graph);
  local sourceDeps :: set:Set<FlowVertex> = g:edgesFrom(edge.fst, graph);
  
  -- ONLY those that ARE in current. i.e. dependencies that do not expand the flow type of this source vertex.
  -- (Look these up, rather than listing everything the target depends on, which can be much more.)
  local validDeps :: [FlowVertex] = 
    filter(
      \ v::FlowVertex -> set:contains(v, targetDeps) && !set:contains(v, sourceDeps),
      map(lhsInhVertex, set:toList(currentDeps)));
  
  return if set:isEmpty(currentDeps) then [] -- just a quick optimization.
  else zipFst(edge.fst, validDeps);
}

