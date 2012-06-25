grammar silver:driver;

import silver:definition:flow:driver;
import silver:util:raw:treemap as rtm;

-- Hide all the flow type computation over here

aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  -- aggregate all flow def information
  local allFlowDefs :: FlowDefs = foldr(consFlow, nilFlow(), foldr(append, [], map((.flowDefs), grammars)));
  local allFlowEnv :: Decorated FlowEnv = fromFlowDefs(allFlowDefs);
  
  -- Look up tree for production info
  local prodTree :: EnvTree<FlowDef> = directBuildTree(allFlowDefs.prodGraphContribs);
  production allProds :: [String] = nubBy(stringEq, map(getFst, rtm:toList(prodTree)));
  
  -- hack to allow us to look up certain info... TODO: maybe hack?
  production allRealEnv :: Decorated Env = toEnv(foldr(appendDefs, emptyDefs(), map((.defs), grammars)));
  
  -- Fix the production graph information from the flow defs TODO: some of this maybe should be fixed somehow
  production prodGraph :: [Pair<String [Pair<FlowVertex FlowVertex>]>] = 
    fixupGraphs(allProds, prodTree, allFlowEnv, allRealEnv);
  
  -- Graph some random info about productions TODO: maybe hack?
  local prodinfos :: EnvTree<Pair<NamedSignature [Pair<String String>]>> =
    directBuildTree(makeProdLocalInfo(allProds, prodTree, allRealEnv));
  
  -- Now, solve for flow types!!
  production flowTypes :: EnvTree<Pair<String String>> =
    fullySolveFlowTypes(prodinfos, prodGraph, allRealEnv, rtm:empty(compareString));
  

  unit.flowEnv = allFlowEnv;
  unit.productionFlowGraphs = prodGraph;
  unit.grammarFlowTypes = flowTypes;

  reUnit.flowEnv = allFlowEnv;
  reUnit.productionFlowGraphs = prodGraph;
  reUnit.grammarFlowTypes = flowTypes;
}


function getFst
a ::= v::Pair<a b>
{ return v.fst; }

