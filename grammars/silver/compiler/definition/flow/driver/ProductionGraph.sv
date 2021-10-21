grammar silver:compiler:definition:flow:driver;

import silver:compiler:definition:type only isNonterminal, typerep;

nonterminal ProductionGraph with flowTypes, stitchedGraph, prod, lhsNt, transitiveClosure, edgeMap, suspectEdgeMap, cullSuspect, flowTypeVertexes, prodGraphs;

inherited attribute flowTypes :: EnvTree<FlowType>;
inherited attribute prodGraphs :: EnvTree<ProductionGraph>;

-- TODO: future me note: these are good candidates to be "static attributes" maybe?
{--
 - Given a set of flow types, stitches those edges into the graph for
 - all stitch points (i.e. children, locals, forward)
 -}
synthesized attribute stitchedGraph :: ProductionGraph;
{--
 - Just compute the transitive closure of the edge set
 -}
synthesized attribute transitiveClosure :: ProductionGraph;
{--
 - Edge mapper
 -}
synthesized attribute edgeMap :: (set:Set<FlowVertex> ::= FlowVertex);
synthesized attribute suspectEdgeMap :: ([FlowVertex] ::= FlowVertex);

synthesized attribute cullSuspect :: ProductionGraph;

-- This is, apparently, only used to look up production by name
synthesized attribute prod::String;
-- Only used by solveFlowTypes()
synthesized attribute lhsNt::String;
-- Used in solveFlowTypes
synthesized attribute flowTypeVertexes::[FlowVertex];
-- I'd prefer this not exist, but...

{--
 - An object for representing a production's flow graph.
 - Should ALWAYS be a transitive closure over the edges for 'vertexes'.
 -
 - @param prod  The full name of this production
 - @param lhsNt  The full name of the nonterminal this production constructs
 - @param flowTypeVertexes  The vertexes that we are inferring the flow types of.
 -                          (Syns and optionally fwd, minus those that are specified.)
 - @param graph  The edges within this production
 - @param suspectEdges  Edges that are not permitted to affect their OWN flow types (but perhaps some unknown other flowtypes)
 - @param stitchPoints  Places where current flow types need grafting to this graph to yield a full flow graph
 -
 - @see constructProductionGraph for how to go about getting an object of this type
 -}
abstract production productionGraph
top::ProductionGraph ::=
  prod::String
  lhsNt::String
  flowTypeVertexes::[FlowVertex]
  graph::g:Graph<FlowVertex>
  suspectEdges::[Pair<FlowVertex FlowVertex>]
  stitchPoints::[StitchPoint]
{
  top.prod = prod;
  top.lhsNt = lhsNt;
  top.flowTypeVertexes = flowTypeVertexes;
  
  top.stitchedGraph = 
    let newEdges :: [Pair<FlowVertex FlowVertex>] =
          filter(edgeIsNew(_, graph),
            flatMap(stitchEdgesFor(_, top.flowTypes, top.prodGraphs), stitchPoints))
    in let repaired :: g:Graph<FlowVertex> =
             repairClosure(newEdges, graph)
    in if null(newEdges) then top else
         productionGraph(prod, lhsNt, flowTypeVertexes, repaired, suspectEdges, stitchPoints)
    end end;
  
  top.transitiveClosure =
    let transitiveClosure :: g:Graph<FlowVertex> =
          transitiveClose(graph)
    in
      productionGraph(prod, lhsNt, flowTypeVertexes, transitiveClosure, suspectEdges, stitchPoints) end;
    
  top.edgeMap = g:edgesFrom(_, graph);
  top.suspectEdgeMap = lookupAll(_, suspectEdges);
  
  top.cullSuspect = 
    -- this potentially introduces the same edge twice, but that's a nonissue
    let newEdges :: [Pair<FlowVertex FlowVertex>] =
          flatMap(findAdmissibleEdges(_, graph, findFlowType(lhsNt, top.flowTypes)), suspectEdges)
    in let repaired :: g:Graph<FlowVertex> =
             repairClosure(newEdges, graph)
    in if null(newEdges) then top else
         productionGraph(prod, lhsNt, flowTypeVertexes, repaired, suspectEdges, stitchPoints)
    end end;
}

function updateGraph
ProductionGraph ::=
  graph::ProductionGraph
  prodEnv::EnvTree<ProductionGraph>
  ntEnv::EnvTree<FlowType>
{
  graph.flowTypes = ntEnv;
  graph.prodGraphs = prodEnv;

  local stitchedGraph :: ProductionGraph = graph.stitchedGraph;
  stitchedGraph.flowTypes = ntEnv;

  return stitchedGraph.cullSuspect;
}

-- construct a production graph for each production
function computeAllProductionGraphs
[ProductionGraph] ::= prods::[ValueDclInfo]  prodTree::EnvTree<FlowDef>  flowEnv::Decorated FlowEnv  realEnv::Decorated Env
{
  return if null(prods) then []
  else constructProductionGraph(head(prods), searchEnvTree(head(prods).fullName, prodTree), flowEnv, realEnv) ::
    computeAllProductionGraphs(tail(prods), prodTree, flowEnv, realEnv);
}


--------------------------------------------------------------------------------
-- Below, we have various means of constructing a production graph.
-- Two types are used as part of inference:
--  1. `constructProductionGraph` builds a graph for a normal production.
--  2. `constructPhantomProductionGraph` builds a "phantom graph" to guide inference.
--
-- There are more types of "production" graphs, used NOT for inference, but
-- for error checking behaviors:
--  1. `constructFunctionGraph` builds a graph for a function.
--       (the key here: `aspect function` contributions `<-` needs to be handled.)
--  2. `constructAnonymousGraph` builds a graph for a global expression. (also action blocks)
--       (key: decorate/patterns create stitch points and things that need to be handled.)
--  3. `constructDefaultProductionGraph` builds a graph used locally in a default production.
--       (key: like phantom, LHS is stitch point, to make dependencies clear.)
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
 - 4. All autocopy attributes not supplied to childred have copies.
 -
 - @param dcl  The DclInfo of the production
 - @param defs  The set of defs from prodGraphContribs
 - @param flowEnv  A full flow environment
 -         (used to discover what explicit equations exist, find info on nonterminals)
 - @param realEnv  A full real environment
 -         (used to discover attribute occurrences, whether inh/syn/auto)
 - @return A fixed up graph.
 -}
function constructProductionGraph
ProductionGraph ::= dcl::ValueDclInfo  defs::[FlowDef]  flowEnv::Decorated FlowEnv  realEnv::Decorated Env
{
  -- The name of this production
  local prod :: String = dcl.fullName;
  -- The LHS nonterminal full name
  local nt :: NtName = dcl.namedSignature.outputElement.typerep.typeName;
  -- All attributes occurrences
  local attrs :: [OccursDclInfo] = getAttrsOn(nt, realEnv);
  -- Just synthesized attributes.
  local syns :: [String] = map((.attrOccurring), filter(isOccursSynthesized(_, realEnv), attrs));
  -- Just inherited.
  local inhs :: [String] = map((.attrOccurring), filter(isOccursInherited(_, realEnv), attrs));
  -- Autocopy.
  local autos :: [String] = filter(isAutocopy(_, realEnv), inhs);
  -- Does this production forward?
  local nonForwarding :: Boolean = null(lookupFwd(prod, flowEnv));
    
  -- Normal edges!
  local normalEdges :: [Pair<FlowVertex FlowVertex>] =
    flatMap((.flowEdges), defs);
  
  -- Insert implicit equations.
  local fixedEdges :: [Pair<FlowVertex FlowVertex>] =
    normalEdges ++
    (if nonForwarding
     then addDefEqs(prod, nt, syns, flowEnv)
     else -- This first pair is used sometimes as an alias:
          (lhsSynVertex("forward"), forwardEqVertex()) ::
          addFwdSynEqs(prod, synsBySuspicion.fst, flowEnv) ++ 
          addFwdInhEqs(prod, inhs, flowEnv)) ++
    addAllAutoCopyEqs(prod, dcl.namedSignature.inputElements, autos, flowEnv, realEnv);
  
  -- (safe, suspect)
  local synsBySuspicion :: Pair<[String] [String]> =
    partition(contains(_, getNonSuspectAttrsForProd(prod, flowEnv)), syns);
  
  -- No implicit equations here, just keep track.
  local suspectEdges :: [Pair<FlowVertex FlowVertex>] =
    flatMap((.suspectFlowEdges), defs) ++
    -- If it's forwarding .snd is attributes not known at forwarding time. If it's non, then actually .snd is all attributes. Ignore.
    if nonForwarding then [] else addFwdSynEqs(prod, synsBySuspicion.snd, flowEnv);

  -- RHS and locals and forward.
  local stitchPoints :: [StitchPoint] =
    flatMap(rhsStitchPoints, dcl.namedSignature.inputElements) ++
    localStitchPoints(nt, defs) ++
    patternStitchPoints(realEnv, defs);
  
  local flowTypeVertexesOverall :: [FlowVertex] =
    (if nonForwarding then [] else [forwardEqVertex()]) ++
      map(lhsSynVertex, syns);
  local flowTypeSpecs :: [String] = getSpecifiedSynsForNt(nt, flowEnv);
  
  local flowTypeVertexes :: [FlowVertex] =
    filter(\x::FlowVertex -> !contains(x.flowTypeName, flowTypeSpecs), flowTypeVertexesOverall);
  
  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(fixedEdges);

  return productionGraph(prod, nt, flowTypeVertexes, initialGraph, suspectEdges, stitchPoints).transitiveClosure;
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
ProductionGraph ::= ns::NamedSignature  flowEnv::Decorated FlowEnv  realEnv::Decorated Env  prodEnv::EnvTree<ProductionGraph>  ntEnv::EnvTree<FlowType>
{
  local prod :: String = ns.fullName;
  local nt :: NtName = "::nolhs"; -- the same hack we use elsewhere
  local defs :: [FlowDef] = getGraphContribsFor(prod, flowEnv);

  local normalEdges :: [Pair<FlowVertex FlowVertex>] =
    flatMap((.flowEdges), defs);
  
  -- In functions, this is just `<-` contributions to local collections from aspects.
  local suspectEdges :: [Pair<FlowVertex FlowVertex>] =
    flatMap((.suspectFlowEdges), defs);
    
  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(normalEdges);

  -- RHS and locals and forward.
  local stitchPoints :: [StitchPoint] =
    flatMap(rhsStitchPoints, ns.inputElements) ++
    localStitchPoints(error("functions shouldn't have a forwarding equation?"), defs) ++
    patternStitchPoints(realEnv, defs);

  local flowTypeVertexes :: [FlowVertex] = []; -- Not used as part of inference.

  local g :: ProductionGraph =
    productionGraph(prod, nt, flowTypeVertexes, initialGraph, suspectEdges, stitchPoints).transitiveClosure;

  return updateGraph(g, prodEnv, ntEnv);
}

{--
 - An anonymous graph is for a location where we have all `flowDefs` locally available,
 - and they're actually not propagated into an environment anywhere. (e.g. globals)
 -
 - NOTE: Not used as part of inference. Instead, only used as part of error checking.
 -
 -}
function constructAnonymousGraph
ProductionGraph ::= defs::[FlowDef]  realEnv::Decorated Env  prodEnv::EnvTree<ProductionGraph>  ntEnv::EnvTree<FlowType>
{
  -- Actually very unclear to me right now if these dummy names matter.
  -- Presently duplicating what appears in BlockContext
  local prod :: String = "_NULL_";
  local nt :: NtName = "::nolhs"; -- the same hack we use elsewhere

  local normalEdges :: [Pair<FlowVertex FlowVertex>] =
    flatMap((.flowEdges), defs);
  
  -- suspectEdges should always be empty! (No "aspects" where they could arise.)
  local suspectEdges :: [Pair<FlowVertex FlowVertex>] = [];

  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(normalEdges);

  -- There can still be anonEq, but there's no RHS anymore
  local stitchPoints :: [StitchPoint] =
    localStitchPoints(error("global expressions shouldn't have a forwarding equation?"), defs) ++
    patternStitchPoints(realEnv, defs);

  local flowTypeVertexes :: [FlowVertex] = []; -- Not used as part of inference.
  
  local g :: ProductionGraph =
    productionGraph(prod, nt, flowTypeVertexes, initialGraph, suspectEdges, stitchPoints).transitiveClosure;

  return updateGraph(g, prodEnv, ntEnv);
}

{--
 - An graph for checking dependencies in default equations.
 -
 - NOTE: Not used as part of inference. Instead, only used as part of error checking.
 -
 -}
function constructDefaultProductionGraph
ProductionGraph ::= ns::NamedSignature  defs::[FlowDef]  realEnv::Decorated Env  prodEnv::EnvTree<ProductionGraph>  ntEnv::EnvTree<FlowType>
{
  local prod :: String = ns.fullName;
  local nt :: NtName = ns.outputElement.typerep.typeName;
  
  local normalEdges :: [Pair<FlowVertex FlowVertex>] =
    flatMap((.flowEdges), defs);
  
  -- suspectEdges should always be empty! (No "aspects" where they could arise.)
  local suspectEdges :: [Pair<FlowVertex FlowVertex>] = [];
    
  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(normalEdges);

  -- There can still be anonEq, but there's no RHS anymore
  -- However, we do behave like phantom graphs and create an LHS stitch point!
  local stitchPoints :: [StitchPoint] =
    [nonterminalStitchPoint(nt, lhsVertexType)] ++ 
    localStitchPoints(error("default production shouldn't have a forwarding equation?"), defs) ++
    patternStitchPoints(realEnv, defs);

  local flowTypeVertexes :: [FlowVertex] = []; -- Not used as part of inference.

  local g :: ProductionGraph =
    productionGraph(prod, nt, flowTypeVertexes, initialGraph, suspectEdges, stitchPoints).transitiveClosure;

  return updateGraph(g, prodEnv, ntEnv);
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
ProductionGraph ::= nt::String  flowEnv::Decorated FlowEnv  realEnv::Decorated Env
{
  -- All attributes occurrences
  local attrs :: [OccursDclInfo] = getAttrsOn(nt, realEnv);
  -- Just synthesized attributes.
  local syns :: [String] = map((.attrOccurring), filter(isOccursSynthesized(_, realEnv), attrs));
  -- Those syns that are not part of the host, and so should have edges to fwdeq
  local extSyns :: [String] = removeAll(getHostSynsFor(nt, flowEnv), syns);

  -- The phantom edges: ext syn -> fwd.eq
  local phantomEdges :: [Pair<FlowVertex FlowVertex>] =
    -- apparently this alias may sometimes be used. we should get rid of this by making good use of vertex types
    (lhsSynVertex("forward"), forwardEqVertex()) ::
    map(getPhantomEdge, extSyns);
  
  -- The stitch point: oddball. LHS stitch point. Normally, the LHS is not.
  local stitchPoints :: [StitchPoint] = [nonterminalStitchPoint(nt, lhsVertexType)];
    
  local flowTypeVertexes :: [FlowVertex] = [forwardEqVertex()] ++ map(lhsSynVertex, syns);
  local initialGraph :: g:Graph<FlowVertex> = createFlowGraph(phantomEdges);
  local suspectEdges :: [Pair<FlowVertex FlowVertex>] = [];

  return productionGraph("Phantom for " ++ nt, nt, flowTypeVertexes, initialGraph, suspectEdges, stitchPoints).transitiveClosure;
}

function getPhantomEdge
Pair<FlowVertex FlowVertex> ::= at::String
{
  return (lhsSynVertex(at), forwardEqVertex());
}

---- Begin helpers for fixing up graphs ----------------------------------------

{--
 - Introduces implicit 'lhs.syn -> forward.syn' (& forward.eq) equations.
 - Called twice: once for safe edges, later for SUSPECT edges!
 -}
function addFwdSynEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName syns::[String] flowEnv::Decorated FlowEnv
{
  return if null(syns) then []
  else (if null(lookupSyn(prod, head(syns), flowEnv))
    then [(lhsSynVertex(head(syns)), forwardVertex(head(syns))),
          (lhsSynVertex(head(syns)), forwardEqVertex())] else []) ++
    addFwdSynEqs(prod, tail(syns), flowEnv);
}
{--
 - Introduces implicit 'forward.inh = lhs.inh' equations.
 - Inherited equations are never suspect.
 -}
function addFwdInhEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName inhs::[String] flowEnv::Decorated FlowEnv
{
  return if null(inhs) then []
  else (if null(lookupFwdInh(prod, head(inhs), flowEnv)) then [(forwardVertex(head(inhs)), lhsInhVertex(head(inhs)))] else []) ++
    addFwdInhEqs(prod, tail(inhs), flowEnv);
}
{--
 - Introduces default equations deps. Realistically, should be empty, always.
 -}
function addDefEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName nt::NtName syns::[String] flowEnv :: Decorated FlowEnv
{
  return if null(syns) then []
  else (if null(lookupSyn(prod, head(syns), flowEnv)) 
        then let x :: [FlowDef] = lookupDef(nt, head(syns), flowEnv)
              in if null(x) then [] else head(x).flowEdges 
             end
        else []) ++
    addDefEqs(prod, nt, tail(syns), flowEnv);
}
{--
 - Introduces 'rhs.inh = lhs.inh' wherever not present.
 - Inherited equations are never suspect.
 -}
function addAllAutoCopyEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName sigNames::[NamedSignatureElement] inhs::[String] flowEnv::Decorated FlowEnv realEnv::Decorated Env
{
  return if null(sigNames) then []
  else addAutocopyEqs(prod, head(sigNames), inhs, flowEnv, realEnv) ++ addAllAutoCopyEqs(prod, tail(sigNames), inhs, flowEnv, realEnv);
}
-- Helper for above.
function addAutocopyEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName sigName::NamedSignatureElement inhs::[String] flowEnv::Decorated FlowEnv realEnv::Decorated Env
{
  return if null(inhs) then []
  else (if null(lookupInh(prod, sigName.elementName, head(inhs), flowEnv))  -- no equation
        && !null(getOccursDcl(head(inhs), sigName.typerep.typeName, realEnv)) -- and it occurs on this type
        then [(rhsVertex(sigName.elementName, head(inhs)), lhsInhVertex(head(inhs)))]
        else []) ++
    addAutocopyEqs(prod, sigName, tail(inhs), flowEnv, realEnv);
}

---- End helpers for fixing up graphs ------------------------------------------

---- Begin helpers for figuring out stitch points ------------------------------

function localStitchPoints
[StitchPoint] ::= nt::NtName  d::[FlowDef]
{
  return case d of
  | [] -> []
  -- We add the forward stitch point here, too!
  | fwdEq(_, _, _) :: rest -> nonterminalStitchPoint(nt, forwardVertexType) :: localStitchPoints(nt, rest)
  -- Add locals that are nonterminal types.
  | localEq(_, fN, tN, true, _) :: rest -> nonterminalStitchPoint(tN, localVertexType(fN)) :: localStitchPoints(nt, rest)
  -- Add anon decoration sites that are nonterminal types
  | anonEq(_, fN, tN, true, _, _) :: rest -> nonterminalStitchPoint(tN, anonVertexType(fN)) :: localStitchPoints(nt, rest)
  -- Ignore all other flow def info
  | _ :: rest -> localStitchPoints(nt, rest)
  end;
}
function rhsStitchPoints
[StitchPoint] ::= rhs::NamedSignatureElement
{
  return
    -- We want only NONTERMINAL stitch points!
    if rhs.typerep.isNonterminal
    then [nonterminalStitchPoint(rhs.typerep.typeName, rhsVertexType(rhs.elementName))]
    else [];
}
function patternStitchPoints
[StitchPoint] ::= realEnv::Decorated Env  defs::[FlowDef]
{
  return case defs of
  | [] -> []
  | patternRuleEq(_, matchProd, scrutinee, vars) :: rest ->
      flatMap(patVarStitchPoints(matchProd, scrutinee, realEnv, _), vars) ++
      patternStitchPoints(realEnv, rest)
  | _ :: rest -> patternStitchPoints(realEnv, rest)
  end;
}
function patVarStitchPoints
[StitchPoint] ::= matchProd::String  scrutinee::VertexType  realEnv::Decorated Env  var::PatternVarProjection
{
  return case var of
  | patternVarProjection(child, typeName, patternVar) -> 
      [nonterminalStitchPoint(typeName, anonVertexType(patternVar)),
       projectionStitchPoint(matchProd, anonVertexType(patternVar), scrutinee, rhsVertexType(child), getInhsForNtForPatternVars(typeName, realEnv))]
  end;
}

-- fudge :(
-- This is an annoying thing to have to write here.
-- I wish for a better way to discover this info.
function getInhsForNtForPatternVars
[String] ::= nt::String  realEnv::Decorated Env
{
  return map((.attrOccurring), filter(isOccursInherited(_, realEnv), getAttrsOn(nt, realEnv)));
}

---- End helpers for figuring our stitch points --------------------------------

function prodGraphToEnv
Pair<String ProductionGraph> ::= p::ProductionGraph
{
  return (p.prod, p);
}
function isOccursInherited
Boolean ::= occs::OccursDclInfo  e::Decorated Env
{
  return case getAttrDcl(occs.attrOccurring, e) of
         | at :: _ -> at.isInherited
         | _ -> false
         end;
}

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
[Pair<FlowVertex FlowVertex>] ::= edge::Pair<FlowVertex FlowVertex>  graph::g:Graph<FlowVertex>  ft::FlowType
{
  -- The current flow type of the edge's source vertex (which is always a thing in the flow type)
  local currentDeps :: set:Set<String> =
    g:edgesFrom(edge.fst.flowTypeName, ft);
  
  local targetNotSource :: set:Set<FlowVertex> = 
    set:difference(
      g:edgesFrom(edge.snd, graph),
      g:edgesFrom(edge.fst, graph));
  
  -- ONLY those that ARE in current. i.e. dependencies that do not expand the flow type of this source vertex.
  local validDeps :: [FlowVertex] = 
    filter(isLhsInhSet(_, currentDeps), set:toList(targetNotSource));
  
  return if set:isEmpty(currentDeps) then [] -- just a quick optimization.
  else map((edge.fst, _), validDeps);
}

