grammar silver:definition:flow:driver;

imports silver:definition:core;
imports silver:definition:env;
--import silver:definition:flow:env;
imports silver:definition:flow:ast;
imports silver:analysis:warnings:defs only isOccursSynthesized, isAutocopy;

imports silver:modification:autocopyattr;

imports silver:util:raw:treemap as rtm;
imports silver:util:raw:graph as g;
imports silver:util:raw:treeset as set;

import silver:util only rem;


-- Help some type signatures suck a little less
type ProdName = String;
type NtName = String;

-- construct a production graph for each production
function computeAllProductionGraphs
[ProductionGraph] ::= prods::[String]  prodTree::EnvTree<FlowDef>  flowEnv::Decorated FlowEnv  realEnv::Decorated Env
{
  return if null(prods) then []
  else constructProductionGraph(head(prods), searchEnvTree(head(prods), prodTree), flowEnv, realEnv) ::
    computeAllProductionGraphs(tail(prods), prodTree, flowEnv, realEnv);
}


{--
 - Produces flow types for every nonterminal.
 - Iterates until convergence.
 -}
function fullySolveFlowTypes
Pair<[ProductionGraph] EnvTree<g:Graph<String>>> ::= 
  graphs::[ProductionGraph]
  ntEnv::EnvTree<g:Graph<String>>
{
  -- Each iteration, we rebuild this... :/
  -- TODO: consider a 'mapValuesWithMapState' function :: 'k1, v1, map<k1 v1>, map<k2 v2> -> v1, k2, v2'
  local prodEnv :: EnvTree<ProductionGraph> =
    directBuildTree(map(prodGraphToEnv, graphs));
  
  local iter :: Pair<Boolean Pair<[ProductionGraph] EnvTree<g:Graph<String>>>> =
    solveFlowTypes(graphs, prodEnv, ntEnv);
  
  -- Just iterate until no new edges are added
  return if !iter.fst then iter.snd
  else fullySolveFlowTypes(iter.snd.fst, iter.snd.snd);
}

{--
 - One iteration of solving flow type equations. Goes through each production once.
 -}
function solveFlowTypes
Pair<Boolean
     Pair<[ProductionGraph]
          EnvTree<g:Graph<String>>>> ::=
  graphs::[ProductionGraph]
  prodEnv::EnvTree<ProductionGraph>
  ntEnv::EnvTree<g:Graph<String>>
{
  local graph :: ProductionGraph = head(graphs);
  graph.flowTypes = ntEnv;
  graph.prodGraphs = prodEnv;
  local stitchedGraph :: ProductionGraph = graph.stitchedGraph;
  stitchedGraph.flowTypes = ntEnv;
  local updatedGraph :: ProductionGraph = stitchedGraph.cullSuspect;

  local currentFlowType :: g:Graph<String> = findFlowType(graph.lhsNt, ntEnv);
  
  -- The New Improved Flow Type
  local synExpansion :: [Pair<String [String]>] =
    map(expandVertexFilterTo(_, updatedGraph), updatedGraph.flowTypeVertexes);
  
  -- Find what edges are NEW NEW NEW
  local brandNewEdges :: [Pair<String String>] =
    findBrandNewEdges(synExpansion, currentFlowType);
    
  local newFlowType :: g:Graph<String> =
    g:add(brandNewEdges, currentFlowType); -- TODO: faster?
  
  local recurse :: Pair<Boolean Pair<[ProductionGraph] EnvTree<g:Graph<String>>>> =
    solveFlowTypes(tail(graphs), prodEnv, rtm:update(graph.lhsNt, [newFlowType], ntEnv));
    
  return if null(graphs) then pair(false, pair([], ntEnv))
  else pair(!null(brandNewEdges) || recurse.fst, pair(updatedGraph :: recurse.snd.fst, recurse.snd.snd));
}


function findBrandNewEdges
[Pair<String String>] ::= candidates::[Pair<String [String]>]  currentFlowType::g:Graph<String>
{
  local syn :: String = head(candidates).fst;
  local inhs :: [String] = head(candidates).snd;
  
  local newinhs :: [String] = rem(inhs, set:toList(g:edgesFrom(syn, currentFlowType))); -- TODO faster?
  
  local newEdges :: [Pair<String String>] = map(pair(syn, _), newinhs);
  
  return if null(candidates) then [] else newEdges ++ findBrandNewEdges(tail(candidates), currentFlowType);
}




-- Expand 'ver' using 'graph', then filter down to just those in 'inhs'
function expandVertexFilterTo
Pair<String [String]> ::= ver::FlowVertex  graph::ProductionGraph
{
  return pair(ver.flowTypeName, foldr(collectInhs, [], set:toList(graph.edgeMap(ver)))); -- TODO: faster? using sets
}


{--
 - Used to filter down to just the inherited attributes
 - 
 - @param f  The flow vertex in question
 - @param l  The current set of inherited attribute dependencies
 - @return  {l} with {f} added to it
 -}
function collectInhs
[String] ::= f::FlowVertex  l::[String]
{
  return case f of
  | lhsInhVertex(a) -> a::l
  | _ -> l
  end;
}

{--
 - Used to add the 'minimum' flow type for non-host synthesized attributes.
 - These attributes need to be able to evaluate the forwards of productions
 - to be able to be evaluated.
 - @param initial  the results from fullySolveFlowTypes
 - @param edits  the list of non-host synthesized attribute occurrences
 - @return the modified flow types
 -}
function patchFlowTypes
EnvTree<g:Graph<String>> ::= initial::EnvTree<g:Graph<String>>  edits::[FlowDef]
{
  return foldr(patchEditPair, initial, edits);
}
function patchEditPair
EnvTree<g:Graph<String>> ::= edit::FlowDef  current::EnvTree<g:Graph<String>>
{
  return case edit of
  | nonHostSynDef(attr, nt) -> 
      let ft :: g:Graph<String> = findFlowType(nt, current)
       in
      let fwdInhs :: set:Set<String> = g:edgesFrom("forward", ft),
          alreadyInhs :: set:Set<String> = g:edgesFrom(attr, ft)
       in
          rtm:update(nt, [
            g:add(map(pair(attr, _), set:toList(set:difference(fwdInhs, alreadyInhs))), ft)
            ], current)
      end
      end
  end; -- for everything found under nt->forward, add something under nt->attr, if it doesn't exist already
}







function flowVertexEq
Boolean ::= a::FlowVertex  b::FlowVertex
{
  -- eh, good enough TODO
  return a.dotName == b.dotName;
}




{--
 - Flow type lookup names for vertices
 -}
synthesized attribute flowTypeName :: String occurs on FlowVertex;

aspect production lhsSynVertex
top::FlowVertex ::= attrName::String
{
  top.flowTypeName = attrName;
}
aspect production lhsInhVertex
top::FlowVertex ::= attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for inherited attributes?");
}
aspect production rhsVertex
top::FlowVertex ::= sigName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for child attributes?");
}
aspect production localEqVertex
top::FlowVertex ::= fName::String
{
  top.flowTypeName = fName; -- secretly only ever "forward"
}
aspect production localVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for local inherited attributes?");
}
aspect production anonEqVertex
top::FlowVertex ::= fName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for anon equations?");
}
aspect production anonVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for anon inherited attributes?");
}

