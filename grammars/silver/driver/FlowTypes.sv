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
  production allProds :: [String] = nubBy(stringEq, map(getFst, rtm:toList(prodTree)));
  
  -- hack to allow us to look up certain info... TODO: maybe hack?
  production allRealEnv :: Decorated Env = toEnv(foldr(appendDefs, emptyDefs(), map((.defs), grammars)));
  
  -- Fix the production graph information from the flow defs TODO: some of this maybe should be fixed somehow
  production prodGraph :: [Pair<ProdName [Pair<FlowVertex FlowVertex>]>] = 
    fixupGraphs(allProds, prodTree, allFlowEnv, allRealEnv);
  
  -- Graph some random info about productions TODO: maybe hack?
  local prodinfos :: EnvTree<Pair<NtName [Pair<(FlowVertex ::= String) String>]>> =
    directBuildTree(makeProdLocalInfo(allProds, prodTree, allRealEnv));
  
  -- Now, solve for flow types!!
  production flowTypes :: EnvTree<Pair<String String>> =
    fullySolveFlowTypes(prodinfos, prodGraph, allRealEnv, rtm:empty(compareString));
  

  unit.grammarFlowTypes = flowTypes;
  reUnit.grammarFlowTypes = flowTypes;

  -- We'd like a version of the stiched flow graphs to pass down

  unit.productionFlowGraphs = prodGraph;
  reUnit.productionFlowGraphs = prodGraph;
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

