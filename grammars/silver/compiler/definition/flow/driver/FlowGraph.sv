grammar silver:compiler:definition:flow:driver;

import silver:util:idcache as i;

type FlowType = g:Graph<String>;

function findFlowType
FlowType ::= prod::String  e::EnvTree<FlowType>
{
  local lookup :: [FlowType] = searchEnvTree(prod, e);
  
  return if null(lookup) then g:empty() else head(lookup);
}
fun findProductionGraph ProductionGraph ::= n::String l::EnvTree<ProductionGraph> =
  case searchEnvTree(n, l) of
  | g :: _ -> g
  | _ -> error("Failed to find graph for " ++ n)
  end;

-- These two functions are used by the flow checks:
function expandGraph
set:Set<FlowVertex> ::= v::[FlowVertex]  e::ProductionGraph
{
  -- look up each vertex, uniq it down.
  local initial :: set:Set<FlowVertex> =
    set:add(v, foldr(set:union, set:emptyWith(compareVertexId), map(e.edgeMap, v)));

  return expandSuspectEdges(set:toList(initial), initial, e);
}
fun onlyLhsInh set:Set<String> ::= s::set:Set<FlowVertex> = set:add(filterLhsInh(set:toList(s)), set:empty());

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
fun inhDepsForSyn set:Set<String> ::= syn::String  nt::String  flow::EnvTree<FlowType> =
  g:edgesFrom(syn, findFlowType(nt, flow));

{--
 - The direct dependencies of what a decoration site itself supplies for the inherited attribute attr, in the terms of
 - the production whose graph is given.  Unlike decSite.inhDeps(attr), these do not go through the tree shared there
 - (see addDecSiteTreeEqs).  They come from the production's own edges (see unstitchedGraph), including implicit copies,
 - and for a subterm, the edges of the production or dispatch signature applied there.  A translation attribute of the
 - LHS is supplied what the LHS is supplied.
 -}
fun decSiteOwnInhDeps
[FlowVertex] ::= graph::ProductionGraph  decSite::VertexType  attr::String  prodGraphs::EnvTree<ProductionGraph> =
  (if transRootVertex(decSite) == lhsVertexType() then [decSite.inhVertex(attr)] else []) ++
  set:toList(g:edgesFrom(decSite.inhVertex(attr), graph.unstitchedGraph)) ++
  case decSite of
  | subtermVertexType(parent, applied, sigName) ->
    map(fromSigVertex(applied, parent, _),
      filter(
        -- The decoration site's own vertex for attr is in the applied tile flow graph through cycles, such as
        -- those of addDispatchEqs, and would lead back to the tree shared there.
        \ v::FlowVertex -> v.isSigVertex && v != rhsInhVertex(sigName, attr),
        set:toList(findProductionGraph(applied, prodGraphs).tileEdgeMap(rhsInhVertex(sigName, attr)))))
  | _ -> []
  end;

-- The LHS inherited attributes of a dispatch signature that an equation for v, the inherited attribute of one of its
-- children, may depend on.  Implementations assume what the signature's normal graph allows (see
-- implementedSigStitchPoints), and an application that leaves the attribute to the implementation assumes what its
-- tile flow graph allows, so the bound is what both allow.
fun dispatchChildAllowedInhs set:Set<String> ::= dispatchGraph::ProductionGraph  v::FlowVertex =
  set:intersect(onlyLhsInh(dispatchGraph.edgeMap(v)), onlyLhsInh(dispatchGraph.tileEdgeMap(v)));


fun createFlowGraph g:Graph<FlowVertex> ::= l::[(FlowVertex, FlowVertex)] =
  g:transitiveClosure(g:add(l, g:emptyWith(compareVertexId)));


{--
 - Vertices are compared by ids given to their names, which are cached on each vertex.
 -}
global vertexCache::i:IdCache = i:empty();
synthesized attribute vertexId::Integer occurs on FlowVertex;
aspect default production
top::FlowVertex ::=
{ top.vertexId = i:lookup(top.vertexName, vertexCache); }

fun compareVertexId Integer ::= a::FlowVertex b::FlowVertex =
  a.vertexId - b.vertexId;
