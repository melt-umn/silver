grammar silver:compiler:definition:flow:driver;

import silver:util:idcache as i;

type FlowType = rtm:Map<String InhDep>;

function findFlowType
FlowType ::= nt::String  e::EnvTree<FlowType>
{
  local lookup :: [FlowType] = searchEnvTree(nt, e);
  
  return if null(lookup) then rtm:empty() else head(lookup);
}
fun findProductionGraph ProductionGraph ::= n::String l::EnvTree<ProductionGraph> =
  case searchEnvTree(n, l) of
  | g :: _ -> g
  | _ -> error("Failed to find graph for " ++ n)
  end;

-- These two functions are used by Inh.sv:
function expandGraph
[FlowVertex] ::= v::[FlowVertex]  e::ProductionGraph
{
  -- look up each vertex, uniq it down.
  local initial :: set:Set<FlowVertex> =
    set:add(v, foldr(set:union, set:emptyWith(compareVertexId), map(e.edgeMap, v)));

  return set:toList(expandSuspectEdges(set:toList(initial), initial, e));
}
fun onlyLhsInh set:Set<InhDep> ::= s::[FlowVertex] = set:add(filterMap((.lhsInh), s), set:empty());

fun expandTileGraphSigDeps
set:Set<FlowVertex> ::= v::[FlowVertex] rhsNames::[String] g::ProductionGraph =
  set:filter(isSigVertex(rhsNames, _),
    set:add(v, flatMap(g.tileEdgeMap, v)));

fun isSigVertex Boolean ::= rhsNames::[String] v::FlowVertex =
  case v of
  | synVertex(lhsVertexType(), _) -> true
  | inhVertex(lhsVertexType(), _) -> true
  | eqVertex(rhsVertexType(sigName)) -> contains(sigName, rhsNames)
  | synVertex(rhsVertexType(sigName), _) -> contains(sigName, rhsNames)
  | inhVertex(rhsVertexType(sigName), _) -> contains(sigName, rhsNames)
  | _ -> false
  end;

-- suspect edges are not in the standard graph, so iteratively add them
-- call like expandSuspectEdges(p.edges.toList, p.edges, p)
function expandSuspectEdges
set:Set<FlowVertex> ::= todolist::[FlowVertex]  current::set:Set<FlowVertex>  p::ProductionGraph
{
  -- examine this flow vertex
  local thisvertex :: FlowVertex = head(todolist);
  -- get any suspect edges from this vertex
  local result :: [FlowVertex] = p.suspectEdgeMap(thisvertex);
  -- remove anything we're already considering/considered
  local filtered :: [FlowVertex] = filter(\v::FlowVertex -> !set:contains(v, current), result);
  
  return if null(todolist) then current
  else expandSuspectEdges(tail(todolist) ++ filtered, set:add(filtered, current), p);
}

{--
 - Look up flow types.
 - @param syn  A synthesized attribute's full name (or "forward", or trans.syn)
 - @param nt  The nonterminal to look up this attribute on
 - @param flow  The flow type environment (NOTE: TODO: this is currently 'myFlow' or something, NOT top.flowEnv)
 - @return A set of inherited attributes on this nonterminal, needed to compute this synthesized attribute.
 -}
fun inhDepsForSyn set:Set<InhDep> ::= syn::String  nt::String  flow::EnvTree<FlowType> =
  set:fromList(rtm:lookup(syn, findFlowType(nt, flow)));



fun isLhsInhSet Boolean ::= v::FlowVertex  inhSet::set:Set<InhDep> =
  case v of
  | inhVertex(lhsVertexType(), a) -> set:contains(inhDep(a), inhSet)
  | _ -> false
  end;

fun createFlowGraph g:Graph<FlowVertex> ::= l::[(FlowVertex, FlowVertex)] =
  g:transitiveClosure(g:add(l, g:emptyWith(compareVertexId)));


global vertexCache::i:IdCache<FlowVertex> = i:empty();
synthesized attribute vertexId::Integer occurs on FlowVertex;
aspect default production
top::FlowVertex ::=
{ top.vertexId = i:lookup(top, vertexCache); }

fun compareVertexId Integer ::= a::FlowVertex b::FlowVertex =
  a.vertexId - b.vertexId;
