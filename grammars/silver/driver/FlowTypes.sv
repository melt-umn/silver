grammar silver:driver;

imports silver:definition:flow:driver;
import silver:definition:flow:ast;
import silver:util:raw:treemap as rtm;

-- Hide all the flow type computation over here

aspect production compilation
top::Compilation ::= g::Grammars buildGrammar::String silverHome::String silverGen::String
{
  -- aggregate all flow def information, filtering out those that are not permitted to affect flow types
  local allFlowDefs :: FlowDefs = foldr(consFlow, nilFlow(), foldr(append, [], map((.flowDefs), top.grammarList)));
  local allFlowEnv :: Decorated FlowEnv = fromFlowDefs(allFlowDefs);
  
  -- Look up tree for production info
  local prodTree :: EnvTree<FlowDef> = directBuildTree(allFlowDefs.prodGraphContribs);
  
  -- hack to allow us to look up certain info... TODO: maybe hack?
  local allRealDefs :: [Def] = foldr(append, [], map((.defs), top.grammarList));
  local allRealEnv :: Decorated Env = toEnv(allRealDefs);
  
  -- List of all productions (is this nub needed? TODO)
  local allProds :: [String] = nubBy(stringEq, map((.fullName), foldr(consDefs, nilDefs(), allRealDefs).prodDclList));
  
  -- Fix the production graph information from the flow defs TODO: some of this maybe should be fixed somehow
  local prodGraph :: [ProductionGraph] = 
    computeAllProductionGraphs(allProds, prodTree, allFlowEnv, allRealEnv);
  
  -- Now, solve for flow types!!
  local flowTypes1 :: Pair<[ProductionGraph] EnvTree<Pair<String String>>> =
    fullySolveFlowTypes(prodGraph, allRealEnv, rtm:empty(compareString));
  
  -- Non-host syn patch the flow types! (Composition generates new equations
  -- that requires non-host syn to potentially need to evaluate forwards
  -- to be able to evaluate on new productions.)
  local flowTypes2 :: EnvTree<Pair<String String>> =
    patchFlowTypes(flowTypes1.snd, allFlowDefs.nonHostSynAttrs);
    
  -- Iterate once more, to propagate the patch above across flow types!
  local flowTypes3 :: Pair<[ProductionGraph] EnvTree<Pair<String String>>> =
    fullySolveFlowTypes(flowTypes1.fst, allRealEnv, flowTypes2);
  
  production flowTypes :: EnvTree<Pair<String String>> = flowTypes3.snd;
  production finalGraphs :: [ProductionGraph] = flowTypes3.fst;
  

  -- Note: Nope! Not okay to use the filtered flowdefs for these. UGHHH
  -- Problem with 'fwd' nodes disappearing in some computations (checking known-generated fwd equations flow types)
  -- And the possibility (not sure) that the production flow graphs are assumed to be "fully there"
  
  -- TODO: perhaps we should be constructing these graphs more locally?
  -- If we need to NOT filter, then they're kinda local things right?
  -- Maybe the production/function/aspect itself should gather up
  -- the info and stitch their own flow graphs? TODO consider doing this instead!!
  
  -- So, what do we need? prodGraph and prodinfos.
  -- What do we need for prodGraph? allProds prodTree allFlowEnv allRealEnv
  -- allProds and allRealEnv are okay.  prodTree and allFlowEnv are suspect.
  -- Both come straight from the flowdefs.
  -- What about prodinfos? allPRods prodTree allRealEnv.
  -- Again, only prodTree is suspect.
  -- So, all flow defs, prodtree, allflowenv, 
  -- TODO: problem: we're filtering out some fwdDefs and then using the existence of them to know if
  -- a prod forwards.  Further, we're removing those deps, not just for fwd but for syn too,
  -- when they maybe SHOULD affect flow types of other things. Problem is there's no way to say
  -- "these edges, IFF they appear in the flow type for this equation" in the graph computation...
  -- What to do?
  -- Run the graph computation. Get result, add appropriate edges that we previously filtered out,
  -- iterate until we converge on no new admitted edges? I suppose...
  

  -- We'd like a final version of the stitched flow graphs to pass down
  -- TODO: Turn these into trees prior to passing them down. (i.e. EnvTree<EnvTree<FlowVertex>>)
}

