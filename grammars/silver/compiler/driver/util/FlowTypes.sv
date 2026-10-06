grammar silver:compiler:driver:util;

import silver:compiler:definition:flow:driver;
import silver:compiler:definition:flow:ast;
import silver:compiler:definition:flow:env;
import silver:compiler:analysis:uniqueness;
import silver:util:treemap as rtm;
import silver:util:graph as g;
import silver:util:cmdargs;

-- Hide all the flow type computation over here

aspect production compilation
top::Compilation ::= g::Grammars  r::Grammars  buildGrammars::[String]  a::Decorated CmdArgs  benv::BuildEnv
{
  -- aggregate all flow def information
  local allFlowDefs :: FlowDefs = foldr(consFlow, nilFlow(), flatMap((.flowDefs), top.allGrammars));
  local allSpecDefs :: [(String, String, [String], [String])] = flatMap((.specDefs), top.allGrammars);
  local allRefDefs :: [(String, [String])] = flatMap((.refDefs), top.allGrammars);
  local allSharedRefs :: [(String, SharedRefSite)] = flatMap((.sharedRefs), top.allGrammars);
  local allFlowEnv :: FlowEnv = flowEnv(allSpecDefs, allRefDefs, allSharedRefs, allFlowDefs);
  
  -- We need to know about all attributes and occurences on nonterminals.
  -- It's possible (likely) we could do better than using the overall env here.
  local allRealDefs :: [Def] = flatMap((.defs), top.allGrammars);
  local allRealOccursDefs :: [OccursDclInfo] = flatMap((.occursDefs), top.allGrammars);
  local allRealEnv :: Env = toEnv(allRealDefs, allRealOccursDefs);
  
  -- List of all productions
  local allProds :: [ValueDclInfo] = allRealEnv.prodDclList;
  local allNtTypes :: [Type] =
    nubBy(\ t1::Type t2::Type -> t1.typeName == t2.typeName,
      map(\ d::ValueDclInfo -> d.namedSignature.outputElement.typerep, allProds));
  local allNts :: [String] = map((.typeName), allNtTypes);
  local allDispatchSigs :: [NamedSignature] = map((.dispatchSignature), allRealEnv.dispatchDclList);
  
  -- Construct production graphs.
  production prodGraph :: [ProductionGraph] = 
    map(constructProductionGraph(_, allFlowEnv, allRealEnv), allProds) ++
    -- Add in phantom, default, tile and dispatch graphs
    flatMap(constructPhantomProductionGraph(_, allFlowEnv, allRealEnv), allNts) ++
    flatMap(constructDefaultProductionGraph(_, allFlowEnv, allRealEnv), allNts) ++
    map(constructDispatchGraph(_, allFlowEnv, allRealEnv), allDispatchSigs);
  
  local initialFT :: EnvTree<FlowType> =
    computeInitialFlowTypes(allSpecDefs);
  
  -- A cycle in translation attribute occurrences (a nonterminal that translates, through
  -- one or more translation attributes, to itself) is an error, reported by typechecking.
  -- Flow type inference would not terminate on it: stitching a production's tile at a
  -- translation attribute vertex type nests that vertex type without bound.  So don't
  -- attempt inference in that case, and leave the graphs unstitched.
  local transAttrOccursCycle :: Boolean =
    any(map(ntHasTransAttrOccursCycle(_, allRealEnv), allNtTypes));

  -- Now, solve for flow types!!
  local flowTypes1 :: (EnvTree<ProductionGraph>, EnvTree<FlowType>) =
    if transAttrOccursCycle
    then (directBuildTree(map(prodGraphToEnv, prodGraph)), initialFT)
    else runFlowTypeInference(prodGraph, initialFT);
  
  production finalGraphEnv :: EnvTree<ProductionGraph> = flowTypes1.fst;
  production flowTypes :: EnvTree<FlowType> = flowTypes1.snd;
  
  g.productionFlowGraphs = finalGraphEnv;
  g.grammarFlowTypes = flowTypes;
  
  r.productionFlowGraphs = finalGraphEnv;
  r.grammarFlowTypes = flowTypes;
}

