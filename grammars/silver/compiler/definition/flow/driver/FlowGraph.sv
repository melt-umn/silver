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
 - The direct dependencies of what a decoration site itself supplies for the inherited attribute
 - attr, in the terms of the production whose graph is given.  Unlike decSite.inhDeps(attr), these
 - do not go through the tree shared there (see addDecSiteTreeEqs).  They come from the
 - production's own edges (see unstitchedGraph), including implicit copies; where the decoration
 - site has no equation for attr, from the decoration sites where it is shared in turn (see
 - sharedAtDecSites); and for a subterm, from the edges of the production or dispatch signature
 - applied there.  A translation attribute of the LHS is supplied what the LHS is supplied.
 -}
fun decSiteOwnInhDeps
[FlowVertex] ::=
  graph::ProductionGraph  decSite::VertexType  attr::String  prodGraphs::EnvTree<ProductionGraph>  flowEnv::FlowEnv =
  ownInhDepsSeen([], graph, decSite, attr, prodGraphs, flowEnv);
-- decSiteOwnInhDeps for vt, leaving out the trees and attributes in seen, visited on the way to vt.
function ownInhDepsSeen
[FlowVertex] ::=
  seen::[(VertexType, String)]  graph::ProductionGraph  vt::VertexType  attr::String
  prodGraphs::EnvTree<ProductionGraph>  flowEnv::FlowEnv
{
  local seenHere :: [(VertexType, String)] = (vt, attr) :: seen;
  return
    (if transRootVertex(vt) == lhsVertexType() then [vt.inhVertex(attr)] else []) ++
    set:toList(g:edgesFrom(vt.inhVertex(attr), graph.unstitchedGraph)) ++
    flatMap(
      \ d::(VertexType, String) ->
        if contains(d, seenHere) then [] else ownInhDepsSeen(seenHere, graph, d.1, d.2, prodGraphs, flowEnv),
      sharedAtDecSites(graph.prod, vt, attr, flowEnv)) ++
    appliedInhDeps(vt, attr, prodGraphs);
}
-- For a subterm vt, what the production or dispatch signature applied there supplies for attr.
fun appliedInhDeps [FlowVertex] ::= vt::VertexType  attr::String  prodGraphs::EnvTree<ProductionGraph> =
  case vt of
  | subtermVertexType(parent, applied, sigName) ->
    map(fromSigVertex(applied, parent, _),
      filter(
        -- The decoration site's own vertex for attr is in the applied tile flow graph through cycles, such as
        -- those of addDispatchEqs, and would lead back to the tree shared there.
        \ v::FlowVertex -> v.isSigVertex && v != rhsInhVertex(sigName, attr),
        set:toList(findProductionGraph(applied, prodGraphs).tileEdgeMap(rhsInhVertex(sigName, attr)))))
  | _ -> []
  end;
-- Where the tree at vt in prod has no equation for attr, the decoration sites where it is shared,
-- and those where the translation attribute of it that attr is on is shared, each with attr as
-- named there (see addSharingEqs).
fun sharedAtDecSites [(VertexType, String)] ::= prod::String  vt::VertexType  attr::String  flowEnv::FlowEnv =
  (if vertexHasInhEq(prod, vt, attr, flowEnv) then []
   else map(\ d::VertexType -> (d, attr), lookupRefPossibleDecSites(prod, vt, flowEnv))) ++
  case splitTransAttrInh(attr) of
  | just((transAttr, inh)) -> sharedAtDecSites(prod, transAttrVertexType(vt, transAttr), inh, flowEnv)
  | nothing() -> []
  end;

-- The dispatch contract.  For v, an inherited vertex of a child c of the dispatch signature whose
-- graph is dispatchGraph, implementations and applications of the signature assume different LHS
-- inherited attributes.  What implementations and their aspects assume: the normal graph, which
-- implementedSigStitchPoints projects onto each implementation's child at c's position.  It also
-- has what host sharing sites supply (see sigSharingStitchPoints).
fun dispatchImplAssumes set:Set<String> ::= dispatchGraph::ProductionGraph  v::FlowVertex =
  onlyLhsInh(dispatchGraph.edgeMap(v));
-- What applications assume: the tile flow graph, which every application stitches (see
-- tileStitchPoint).  It lacks what sharing sites supply, as an application does not see what other
-- applications supply, but has what an implementation not known here may copy from its forward (see
-- addDispatchUnknownImplSynEqs).
fun dispatchAppAssumes set:Set<String> ::= dispatchGraph::ProductionGraph  v::FlowVertex =
  onlyLhsInh(dispatchGraph.tileEdgeMap(v));
-- What an equation for v that implementations and applications both see may depend on, where that
-- equation takes the place of the implementations' equations: an extension implementation's own
-- equation for c, or an equation on a tree passed with '@' to c.
fun dispatchChildAllowedInhs set:Set<String> ::= dispatchGraph::ProductionGraph  v::FlowVertex =
  set:intersect(dispatchImplAssumes(dispatchGraph, v), dispatchAppAssumes(dispatchGraph, v));
-- May an implementation not known here supply v?  It may where the dispatch signature's graph has
-- the edge from v that addDispatchUnknownImplInhEqs adds for implSuppliedInhs.
fun unknownImplMaySupply Boolean ::= dispatchGraph::ProductionGraph  v::FlowVertex =
  set:contains(unknownImplForwardVertex, g:edgesFrom(v, dispatchGraph.unstitchedGraph));


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
