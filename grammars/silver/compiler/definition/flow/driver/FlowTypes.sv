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
EnvTree<FlowType> ::= specDefs::[(String, String, [InhDep], [String])]
{
  -- We don't care what flow specs reference what.
  -- Also, exclude specs for 'decorate' which isn't a real attribute.
  local dropRefs::[(String, String, [InhDep])] =
    filterMap(\ d::(String, String, [InhDep], [String]) ->
      if d.2 == "decorate" then nothing() else just((d.1, d.2, d.3)),
      specDefs);

  local specs :: [(NtName, [(String, [InhDep])])] =
    ntListCoalesce(groupBy(ntListEq, sortBy(ntListLte, dropRefs)));
  
  return rtm:add(map(initialFlowType, specs), rtm:empty());
}
fun initialFlowType Pair<NtName FlowType> ::= x::(NtName, [(String, [InhDep])]) =
  (x.fst, rtm:fromList(flatMap(toFlatEdges, x.snd)));
fun ntListLte Boolean ::= a::Pair<NtName a>  b::Pair<NtName b> = a.fst <= b.fst;
fun ntListEq Boolean ::= a::Pair<NtName a>  b::Pair<NtName b> = a.fst == b.fst;
fun ntListCoalesce [(NtName, [(String, [InhDep])])] ::= l::[[(NtName, String, [InhDep])]] =
  if null(l) then []
  else (head(head(l)).fst, map(snd, head(l))) :: ntListCoalesce(tail(l));
fun toFlatEdges [Pair<String InhDep>] ::= x::Pair<String [InhDep]> =
  map(pair(fst=x.fst, snd=_), x.snd);

fun runFlowTypeInference
(EnvTree<ProductionGraph>, EnvTree<FlowType>) ::=
    graphs::[ProductionGraph] ntEnv::EnvTree<FlowType> =
  runState(
    fullySolveFlowTypes(map((.prod), graphs)),
    (directBuildTree(map(prodGraphToEnv, graphs)), ntEnv)).1;

type InferState = State<(EnvTree<ProductionGraph>, EnvTree<FlowType>) _>;

{--
 - Produces flow types for every nonterminal.
 - Iterates until convergence.
 -}
fun fullySolveFlowTypes InferState<()> ::= prods::[ProdName] = do {
  -- Update the flow types from all the initial production graphs
  traverse_(updateFlowType, prods);

  -- Just iterate until no new edges are added
  doWhile_(
    map(any, traverseA(
      \ prod::ProdName -> do {
        -- Update the production graph
        graphUpdated :: Boolean <- updateProdGraph(prod);

        -- Only update the flow types for the prod's NT if the prod graph changed
        when_(graphUpdated, updateFlowType(prod));
        return graphUpdated;
      },
      prods)));
};

{--
 - Update a production graph using the current flow types and graphs,
 - including tile graphs and stitch points.
 -}
production updateProdGraph
top::InferState<Boolean> ::= prod::ProdName
{
  local graph :: ProductionGraph = findProductionGraph(prod, top.stateIn.1);
  local updatedGraph :: Maybe<ProductionGraph> =
    updateGraph(graph, top.stateIn.1, top.stateIn.2);
  top.stateOut =
    case updatedGraph of
    | just(g) -> (rtm:update(prod, [g], top.stateIn.1), top.stateIn.2)
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
  local graph :: ProductionGraph = findProductionGraph(prod, top.stateIn.1);
  local currentFlowType :: FlowType = findFlowType(graph.lhsNt, top.stateIn.2);
  local newFlowType :: FlowType = rtm:add(
    flatMap(expandVertexFilterTo(_, graph), graph.flowTypeAttrs),
    currentFlowType);
  top.stateOut = (top.stateIn.1, rtm:update(graph.lhsNt, [newFlowType], top.stateIn.2));
  top.stateVal = ();
}

-- Expand 'lhsSynVertex(syn)' using 'graph', then filter down to just those in 'inhs'
fun expandVertexFilterTo [(String, InhDep)] ::= syn::String  graph::ProductionGraph =
  map(pair(fst=syn, snd=_), filterMap((.lhsInh), set:toList(graph.edgeMap(lhsSynVertex(syn)))));
