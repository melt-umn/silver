grammar silver:driver;

import silver:definition:flow:driver;
import silver:util:raw:treemap as rtm;

-- Hide all the flow type computation over here

aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  -- aggregate all flow def information, filtering out those that are not permitted to affect flow types
  local filteredFlowDefs :: FlowDefs = foldr(consFlow, nilFlow(), foldr(append, [], map((.flowDefs), grammars)));
  local allFlowEnv :: Decorated FlowEnv = fromFlowDefs(filteredFlowDefs);
  
  -- Look up tree for production info
  local prodTree :: EnvTree<FlowDef> = directBuildTree(filteredFlowDefs.prodGraphContribs);
  
  -- hack to allow us to look up certain info... TODO: maybe hack?
  local allRealDefs :: Defs = foldr(appendDefs, emptyDefs(), map((.defs), grammars));
  production allRealEnv :: Decorated Env = toEnv(allRealDefs);
  
  -- List of all productions (is this nub needed? TODO)
  production allProds :: [String] = nubBy(stringEq, map((.fullName), allRealDefs.prodDclList));
  
  -- Fix the production graph information from the flow defs TODO: some of this maybe should be fixed somehow
  production prodGraph :: [ProductionGraph] = 
    computeAllProductionGraphs(allProds, prodTree, allFlowEnv, allRealEnv);
  
  -- Now, solve for flow types!!
  production flowTypes_almost :: EnvTree<Pair<String String>> =
    fullySolveFlowTypes(prodGraph, allRealEnv, rtm:empty(compareString));
  
  -- Non-host syn patch the flow types! (Composition generates new equations
  -- that requires non-host syn to potentially need to evaluate forwards
  -- to be able to evaluate on new productions.)
  -- TODO: think about whether this is a bug or not! Do we need to propagate these
  -- constraints? YES WE DO. THIS IS A BUG. FIXME.
  production flowTypes :: EnvTree<Pair<String String>> =
    patchFlowTypes(flowTypes_almost, filteredFlowDefs.nonHostSynAttrs);
  

  unit.grammarFlowTypes = flowTypes;
  reUnit.grammarFlowTypes = flowTypes;

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
  unit.productionFlowGraphs = stitchAllGraphs(prodGraph, flowTypes);
  reUnit.productionFlowGraphs = unit.productionFlowGraphs;
  -- TODO: Turn these into trees prior to passing them down. (i.e. EnvTree<EnvTree<FlowVertex>>)
}



function stitchAllGraphs
[ProductionGraph] ::=
  graphs :: [ProductionGraph]
  finalFlowTypes :: EnvTree<Pair<String String>>
{
  return map(stitchAProdGraph(_, finalFlowTypes), graphs);
}
function stitchAProdGraph
ProductionGraph ::=
  graphs :: ProductionGraph
  finalFlowTypes :: EnvTree<Pair<String String>>
{
  graphs.flowTypes = finalFlowTypes;
  return graphs.stitchedGraph;
}

