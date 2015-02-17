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
  
  -- List of all productions (is this nub needed? TODO)
  local allProds :: [String] = nubBy(stringEq, map((.fullName), foldr(consDefs, nilDefs(), allRealDefs).prodDclList));
  
  -- Fix the production graph information from the flow defs TODO: some of this maybe should be fixed somehow
  production prodGraph :: [ProductionGraph] = 
    computeAllProductionGraphs(allProds, prodTree, allFlowEnv, allRealEnv);
  
  -- Now, solve for flow types!!
  local flowTypes1 :: Pair<[ProductionGraph] EnvTree<g:Graph<String>>> =
    fullySolveFlowTypes(prodGraph, rtm:empty(compareString));
  
  -- Non-host syn patch the flow types! (Composition generates new equations
  -- that requires non-host syn to potentially need to evaluate forwards
  -- to be able to evaluate on new productions.) TODO: could this need to be iterated?? probably yes, so BUG!
  local flowTypes2 :: EnvTree<g:Graph<String>> =
    patchFlowTypes(flowTypes1.snd, allFlowDefs.nonHostSynAttrs);
    
  -- Iterate once more, to propagate the patch above across flow types!
  local flowTypes3 :: Pair<[ProductionGraph] EnvTree<g:Graph<String>>> =
    fullySolveFlowTypes(flowTypes1.fst, flowTypes2);
  
  production flowTypes :: EnvTree<g:Graph<String>> = flowTypes3.snd;
  production finalGraphs :: [ProductionGraph] = flowTypes3.fst;
  production finalGraphEnv :: EnvTree<ProductionGraph> = directBuildTree(map(prodGraphToEnv, finalGraphs));
  
  g.productionFlowGraphs = finalGraphEnv;
  g.grammarFlowTypes = flowTypes;
  
  r.productionFlowGraphs = finalGraphEnv;
  r.grammarFlowTypes = flowTypes;
}

