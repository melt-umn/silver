grammar silver:compiler:definition:flow:driver;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:flow:env;
imports silver:compiler:definition:flow:ast;
imports silver:compiler:analysis:warnings:flow only isOccursSynthesized;
imports silver:compiler:analysis:uniqueness;

imports silver:util:treemap as rtm;
imports silver:util:graph as g;
imports silver:util:treeset as set;

-- Help some type signatures suck a little less
type ProdName = String;
type NtName = String;

-- from explicit specifications and initial flow graphs
function computeInitialFlowTypes
EnvTree<FlowType> ::= specDefs::[(String, String, [String], [String])]
{
  -- We don't care what flow specs reference what.
  -- Also, exclude specs for 'decorate' which isn't a real attribute.
  local dropRefs::[(String, String, [String])] =
    filterMap(\ d::(String, String, [String], [String]) ->
      if d.2 == "decorate" then nothing() else just((d.1, d.2, d.3)),
      specDefs);

  local specs :: [(NtName, [(String, [String])])] =
    ntListCoalesce(groupBy(ntListEq, sortBy(ntListLte, dropRefs)));
  
  return rtm:add(map(initialFlowType, specs), rtm:empty());
}
fun initialFlowType Pair<NtName FlowType> ::= x::(NtName, [(String, [String])]) =
  (x.fst, g:add(concat(unzipWith(zipFst, x.snd)), g:empty()));
fun ntListLte Boolean ::= a::Pair<NtName a>  b::Pair<NtName b> = a.fst <= b.fst;
fun ntListEq Boolean ::= a::Pair<NtName a>  b::Pair<NtName b> = a.fst == b.fst;
fun ntListCoalesce [(NtName, [(String, [String])])] ::= l::[[(NtName, String, [String])]] =
  if null(l) then []
  else (head(head(l)).fst, map(snd, head(l))) :: ntListCoalesce(tail(l));

fun runFlowTypeInference
(EnvTree<ProductionGraph>, EnvTree<FlowType>) ::=
    graphs::[ProductionGraph] ntEnv::EnvTree<FlowType> =
  let final::InferStateVal =
    runState(
      fullySolveFlowTypes(map((.prod), graphs)),
      inferStateVal(
        inferGraphs=directBuildTree(map(prodGraphToEnv, graphs)), inferFlowTypes=ntEnv,
        changedLastRound=set:empty(), changedThisRound=set:empty())).1
  in (final.inferGraphs, final.inferFlowTypes)
  end;

{--
 - The state of flow type inference.
 -}
data InferStateVal = inferStateVal with
  inferGraphs,       -- The production graphs
  inferFlowTypes,    -- The flow types
  changedLastRound,  -- The dependency keys (see stitchDep) of the graphs and flow types that changed in the last round of updates
  changedThisRound;  -- The same, for those that have changed so far in this round

annotation inferGraphs::EnvTree<ProductionGraph>;
annotation inferFlowTypes::EnvTree<FlowType>;
annotation changedLastRound::set:Set<String>;
annotation changedThisRound::set:Set<String>;

type InferState = State<InferStateVal _>;

{--
 - Produces flow types for every nonterminal.
 - Iterates until convergence.
 -}
fun fullySolveFlowTypes InferState<()> ::= prods::[ProdName] = do {
  -- Update the flow types from all the initial production graphs
  traverse_(updateFlowType, prods);

  -- Stitch every graph in full once, then just iterate until no new edges are added,
  -- updating only the graphs with dependencies that changed since they were last updated.
  -- A graph is updated once in each round, so these changed in the previous round or earlier in this one.
  changed :: Boolean <- solveRound(true, prods);
  when_(changed, doWhile_(solveRound(false, prods)));
};

fun solveRound InferState<Boolean> ::= all::Boolean prods::[ProdName] = do {
  modifyState(\ s::InferStateVal -> s(changedLastRound=s.changedThisRound, changedThisRound=set:empty()));
  map(any, traverseA(
    \ prod::ProdName -> do {
      -- Update the production graph
      graphUpdated :: Boolean <- updateProdGraph(all, prod);

      -- Only update the flow types for the prod's NT if the prod graph changed
      when_(graphUpdated, updateFlowType(prod));
      return graphUpdated;
    },
    prods));
};

{--
 - Update a production graph using the current flow types and graphs,
 - including tile graphs and stitch points.
 -
 - @param all  Whether to consider every dependency changed
 -}
production updateProdGraph
top::InferState<Boolean> ::= all::Boolean prod::ProdName
{
  local graph :: ProductionGraph = findProductionGraph(prod, top.stateIn.inferGraphs);
  local updatedGraph :: Maybe<ProductionGraph> =
    updateChangedGraph(graph, top.stateIn.inferGraphs, top.stateIn.inferFlowTypes,
      \ dep::String ->
        all || set:contains(dep, top.stateIn.changedLastRound) || set:contains(dep, top.stateIn.changedThisRound));
  top.stateOut =
    case updatedGraph of
    | just(g) ->
      top.stateIn(
        inferGraphs=rtm:update(prod, [g], top.stateIn.inferGraphs),
        changedThisRound=set:add([prodDep(prod)], top.stateIn.changedThisRound))
    | nothing() -> top.stateIn
    end;
  top.stateVal = updatedGraph.isJust;
}

{--
 - Update flow types for a nonterminal based on a single production graph.
 -}
production updateFlowType
top::InferState<()> ::= prod::ProdName
{
  local graph :: ProductionGraph = findProductionGraph(prod, top.stateIn.inferGraphs);
  local currentFlowType :: FlowType = findFlowType(graph.lhsNt, top.stateIn.inferFlowTypes);
  local newEdges :: [(String, String)] =
    filter(
      \ e::(String, String) -> !g:contains(e, currentFlowType),
      flatMap(expandVertexFilterTo(_, graph), graph.flowTypeAttrs));
  top.stateOut =
    top.stateIn(
      inferFlowTypes=rtm:update(graph.lhsNt, [g:add(newEdges, currentFlowType)], top.stateIn.inferFlowTypes),
      changedThisRound=
        if null(newEdges) then top.stateIn.changedThisRound
        else set:add([ntDep(graph.lhsNt)], top.stateIn.changedThisRound));
  top.stateVal = ();
}

-- Expand 'lhsSynVertex(syn)' using 'graph', then filter down to just those in 'inhs'
fun expandVertexFilterTo [(String, String)] ::= syn::String  graph::ProductionGraph =
  zipFst(syn, filterLhsInh(set:toList(graph.edgeMap(lhsSynVertex(syn)))));

{--
 - Filters vertexes down to just the names of inherited attributes on the LHS
 -}
global filterLhsInh :: ([String] ::= [FlowVertex]) = flatMap(collectInhs, _);

{--
 - Used to filter down to just the inherited attributes (on the LHS)
 - 
 - @param f  The flow vertex in question
 - @return  {f} if f is an LHS Inh vertex, otherwise {}
 -}
fun collectInhs [String] ::= f::FlowVertex =
  case f of
  | lhsInhVertex(a) -> [a]
  | _ -> []
  end;
