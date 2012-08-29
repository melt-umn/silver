grammar silver:driver;

import silver:definition:flow:driver;
import silver:util:raw:treemap as rtm;

-- Hide all the flow type computation over here

aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  -- aggregate all flow def information, filtering out those that are not permitted to affect flow types
  local filteredFlowDefs :: FlowDefs = foldr(consFlow, nilFlow(), filter((.mayAffectFlowType), foldr(append, [], map((.flowDefs), grammars))));
  local allFlowEnv :: Decorated FlowEnv = fromFlowDefs(filteredFlowDefs);
  
  -- Look up tree for production info
  local prodTree :: EnvTree<FlowDef> = directBuildTree(filteredFlowDefs.prodGraphContribs);
  
  -- hack to allow us to look up certain info... TODO: maybe hack?
  local allRealDefs :: Defs = foldr(appendDefs, emptyDefs(), map((.defs), grammars));
  production allRealEnv :: Decorated Env = toEnv(allRealDefs);
  
  -- List of all productions (is this nub needed? TODO)
  production allProds :: [String] = nubBy(stringEq, map((.fullName), allRealDefs.prodDclList));
  
  -- Fix the production graph information from the flow defs TODO: some of this maybe should be fixed somehow
  production prodGraph :: [Pair<ProdName [Pair<FlowVertex FlowVertex>]>] = 
    fixupGraphs(allProds, prodTree, allFlowEnv, allRealEnv);
  
  -- Graph some random info about productions TODO: maybe hack?
  local prodinfos :: EnvTree<Pair<NtName [Pair<(FlowVertex ::= String) String>]>> =
    directBuildTree(makeProdLocalInfo(allProds, prodTree, allRealEnv));
  
  -- Now, solve for flow types!!
  production flowTypes_almost :: EnvTree<Pair<String String>> =
    fullySolveFlowTypes(prodinfos, prodGraph, allRealEnv, rtm:empty(compareString));
  
  -- Non-host syn patch the flow types! (Composition generates new equations
  -- that requires non-host syn to potentially need to evaluate forwards
  -- to be able to evaluate on new productions.)
  -- TODO: think about whether this is a bug or not! Do we need to propagate these
  -- constraints? Can they affect other flow info? Possibly... maybe we should somehow
  -- be generating edges instead in the graphs??
  production flowTypes :: EnvTree<Pair<String String>> =
    patchFlowTypes(flowTypes_almost, filteredFlowDefs.nonHostSynAttrs);
  

  unit.grammarFlowTypes = flowTypes;
  reUnit.grammarFlowTypes = flowTypes;

  -- Note: Nope! Not okay to use the filtered flowdefs for these. UGHHH
  -- Problem with 'fwd' nodes disappearing in some computations (checking known-generated fwd equations flow types)

  -- We'd like a final version of the stitched flow graphs to pass down
  unit.productionFlowGraphs = stitchAllGraphs(prodGraph, prodinfos, flowTypes);
  reUnit.productionFlowGraphs = unit.productionFlowGraphs;
  -- TODO: Turn these into trees prior to passing them down. (i.e. EnvTree<EnvTree<FlowVertex>>)
}


function getFst
a ::= v::Pair<a b>
{ return v.fst; }

function stitchAllGraphs
[Pair<ProdName [Pair<FlowVertex FlowVertex>]>] ::=
  graphs :: [Pair<ProdName [Pair<FlowVertex FlowVertex>]>]
  prodinfos :: EnvTree<Pair<NtName [Pair<(FlowVertex ::= String) String>]>>
  finalFlowTypes :: EnvTree<Pair<String String>>
{
  return map(stitchAProdGraph(_, prodinfos, finalFlowTypes), graphs);
}
function stitchAProdGraph
Pair<ProdName [Pair<FlowVertex FlowVertex>]> ::=
  graphs :: Pair<ProdName [Pair<FlowVertex FlowVertex>]>
  prodinfos :: EnvTree<Pair<NtName [Pair<(FlowVertex ::= String) String>]>>
  finalFlowTypes :: EnvTree<Pair<String String>>
{
  return pair(graphs.fst, stitchGraph(graphs.snd, head(searchEnvTree(graphs.fst, prodinfos)).snd, finalFlowTypes));
}

