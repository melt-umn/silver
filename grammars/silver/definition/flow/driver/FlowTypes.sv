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

-- from explicit specifications
function computeInitialFlowTypes
EnvTree<FlowType> ::= flowEnv::Decorated FlowDefs
{
  local specs :: [Pair<NtName [Pair<String [String]>]>] =
    ntListCoalesce(groupBy(ntListEq, sortBy(ntListLte, flowEnv.specContribs)));
  
  return rtm:add(map(initialFlowType, specs), rtm:empty(compareString));
}
function initialFlowType
Pair<NtName FlowType> ::= x::Pair<NtName [Pair<String [String]>]>
{
  return pair(x.fst, g:add(flatMap(toFlatEdges, x.snd), g:empty(compareString)));
}
function ntListLte
Boolean ::= a::Pair<NtName a>  b::Pair<NtName b>
{
  return a.fst <= b.fst;
}
function ntListEq
Boolean ::= a::Pair<NtName a>  b::Pair<NtName b>
{
  return a.fst == b.fst;
}
function ntListCoalesce
[Pair<NtName [Pair<String [String]>]>] ::= l::[[Pair<NtName Pair<String [String]>>]]
{
  return if null(l) then []
  else pair(head(head(l)).fst, map(snd, head(l))) :: ntListCoalesce(tail(l));
}
function toFlatEdges
[Pair<String String>] ::= x::Pair<String [String]>
{
  return map(pair(x.fst, _), x.snd);
}


{--
 - Produces flow types for every nonterminal.
 - Iterates until convergence.
 -}
function fullySolveFlowTypes
Pair<[ProductionGraph] EnvTree<FlowType>> ::= 
  graphs::[ProductionGraph]
  ntEnv::EnvTree<FlowType>
{
  -- Each iteration, we rebuild this... :/
  -- TODO: consider a 'mapValuesWithMapState' function :: 'k1, v1, map<k1 v1>, map<k2 v2> -> v1, k2, v2'
  local prodEnv :: EnvTree<ProductionGraph> =
    directBuildTree(map(prodGraphToEnv, graphs));
  
  local iter :: Pair<Boolean Pair<[ProductionGraph] EnvTree<FlowType>>> =
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
          EnvTree<FlowType>>> ::=
  graphs::[ProductionGraph]
  prodEnv::EnvTree<ProductionGraph>
  ntEnv::EnvTree<FlowType>
{
  local graph :: ProductionGraph = head(graphs);
  graph.flowTypes = ntEnv;
  graph.prodGraphs = prodEnv;
  local stitchedGraph :: ProductionGraph = graph.stitchedGraph;
  stitchedGraph.flowTypes = ntEnv;
  local updatedGraph :: ProductionGraph = stitchedGraph.cullSuspect;

  local currentFlowType :: FlowType = findFlowType(graph.lhsNt, ntEnv);
  
  -- The New Improved Flow Type
  local synExpansion :: [Pair<String [String]>] =
    map(expandVertexFilterTo(_, updatedGraph), updatedGraph.flowTypeVertexes);
  
  -- Find what edges are NEW NEW NEW
  local brandNewEdges :: [Pair<String String>] =
    findBrandNewEdges(synExpansion, currentFlowType);
    
  local newFlowType :: FlowType =
    g:add(brandNewEdges, currentFlowType);
  -- TODO: we could just always "add everything unconditionally" but we also need to know if there were
  -- any new additions... so we'd need something added to graph to support that.
  
  local recurse :: Pair<Boolean Pair<[ProductionGraph] EnvTree<FlowType>>> =
    solveFlowTypes(tail(graphs), prodEnv, rtm:update(graph.lhsNt, [newFlowType], ntEnv));
    
  return if null(graphs) then pair(false, pair([], ntEnv))
  else pair(!null(brandNewEdges) || recurse.fst, pair(updatedGraph :: recurse.snd.fst, recurse.snd.snd));
}


function findBrandNewEdges
[Pair<String String>] ::= candidates::[Pair<String [String]>]  currentFlowType::FlowType
{
  local syn :: String = head(candidates).fst;
  local inhs :: [String] = head(candidates).snd;
  
  -- TODO: we might take '[Pair<String Set<String>>]' insteadof [String] and gain speed?
  local newinhs :: [String] = rem(inhs, set:toList(g:edgesFrom(syn, currentFlowType)));
  
  local newEdges :: [Pair<String String>] = map(pair(syn, _), newinhs);
  
  return if null(candidates) then [] else newEdges ++ findBrandNewEdges(tail(candidates), currentFlowType);
}

-- Expand 'ver' using 'graph', then filter down to just those in 'inhs'
function expandVertexFilterTo
Pair<String [String]> ::= ver::FlowVertex  graph::ProductionGraph
{
  -- TODO: we might return 'Pair<String Set<String>>' instead of [String] and gain speed?
  -- Have set:filter, don't have "set:map" yet... (FlowVertex->String)
  return pair(ver.flowTypeName, filterLhsInh(set:toList(graph.edgeMap(ver))));
}

{--
 - Filters vertexes down to just the names of inherited attributes on the LHS
 -}
function filterLhsInh
[String] ::= f::[FlowVertex]
{
  return foldr(collectInhs, [], f);
}

{--
 - Used to filter down to just the inherited attributes (on the LHS)
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
  top.flowTypeName = fName; -- secretly only ever "forward" when we actually demand flowTypeName
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

