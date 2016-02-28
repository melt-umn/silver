grammar silver:driver:util;

import silver:definition:flow:driver;
import silver:definition:flow:ast;
import silver:definition:flow:env;
import silver:util:raw:treemap as rtm;
import silver:util:raw:graph as g;

-- Hide all the flow type computation over here

aspect production compilation
top::Compilation ::= g::Grammars  r::Grammars  buildGrammar::String  benv::BuildEnv
{
  -- aggregate all flow def information
  local allFlowDefs :: FlowDefs = foldr(consFlow, nilFlow(), foldr(append, [], map((.flowDefs), g.grammarList)));
  local allFlowEnv :: Decorated FlowEnv = fromFlowDefs(allFlowDefs);
  
  -- Look up tree for production info
  local prodTree :: EnvTree<FlowDef> = directBuildTree(allFlowDefs.prodGraphContribs);
  
  -- hack to allow us to look up certain info... TODO: maybe hack?
  local allRealDefs :: [Def] = foldr(append, [], map((.defs), g.grammarList));
  local allRealEnv :: Decorated Env = toEnv(allRealDefs);
  
  -- List of all productions
  local allProds :: [DclInfo] = foldr(consDefs, nilDefs(), allRealDefs).prodDclList;
  local allNts :: [String] = nubBy(stringEq, map(getProdNt, allProds));
  
  -- Fix the production graph information from the flow defs TODO: some of this maybe should be fixed somehow
  production prodGraph :: [ProductionGraph] = 
    computeAllProductionGraphs(allProds, prodTree, allFlowEnv, allRealEnv) ++
      -- Add in phantom graphs
      map(constructPhantomProductionGraph(_, allFlowEnv, allRealEnv), allNts);
  
  -- Now, solve for flow types!!
  local flowTypes1 :: Pair<[ProductionGraph] EnvTree<g:Graph<String>>> =
    fullySolveFlowTypes(prodGraph, rtm:empty(compareString));
  
  production flowTypes :: EnvTree<g:Graph<String>> = flowTypes1.snd;
  production finalGraphs :: [ProductionGraph] = flowTypes1.fst;
  production finalGraphEnv :: EnvTree<ProductionGraph> = directBuildTree(map(prodGraphToEnv, finalGraphs));
  
  g.productionFlowGraphs = finalGraphEnv;
  g.grammarFlowTypes = flowTypes;
  
  r.productionFlowGraphs = finalGraphEnv;
  r.grammarFlowTypes = flowTypes;
}

function getProdNt
String ::= d::DclInfo
{
  return d.namedSignature.outputElement.typerep.typeName;
}
