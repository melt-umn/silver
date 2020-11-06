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
  local allFlowDefs :: FlowDefs = foldr(consFlow, nilFlow(), flatMap((.flowDefs), g.grammarList));
  local allFlowEnv :: Decorated FlowEnv = fromFlowDefs(allFlowDefs);
  
  -- Look up tree for production info
  local prodTree :: EnvTree<FlowDef> = directBuildTree(allFlowDefs.prodGraphContribs);
  
  -- We need to know about all attributes and occurences on nonterminals.
  -- It's possible (likely) we could do better than using the overall env here.
  local allRealDefs :: [Def] = flatMap((.defs), g.grammarList);
  local allRealOccursDefs :: [DclInfo] = flatMap((.occursDefs), g.grammarList);
  local allRealEnv :: Decorated Env = occursEnv(allRealOccursDefs, toEnv(allRealDefs));
  
  -- List of all productions
  local allProds :: [DclInfo] = foldr(consDefs, nilDefs(), allRealDefs).prodDclList;
  local allNts :: [String] = nubBy(stringEq, map(getProdNt, allProds));
  
  -- Construct production graphs.
  production prodGraph :: [ProductionGraph] = 
    computeAllProductionGraphs(allProds, prodTree, allFlowEnv, allRealEnv) ++
      -- Add in phantom graphs
      map(constructPhantomProductionGraph(_, allFlowEnv, allRealEnv), allNts);
  
  local initialFT :: EnvTree<FlowType> =
    computeInitialFlowTypes(allFlowDefs);
  
  -- Now, solve for flow types!!
  local flowTypes1 :: Pair<[ProductionGraph] EnvTree<FlowType>> =
    fullySolveFlowTypes(prodGraph, initialFT);
  
  production flowTypes :: EnvTree<FlowType> = flowTypes1.snd;
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
