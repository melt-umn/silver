grammar silver:compiler:definition:flow:driver;

type FlowType = g:Graph<String>;

function findFlowType
FlowType ::= prod::String  e::EnvTree<FlowType>
{
  local lookup :: [FlowType] = searchEnvTree(prod, e);
  
  return if null(lookup) then g:empty() else head(lookup);
}
function findProductionGraph
ProductionGraph ::= n::String  l::EnvTree<ProductionGraph>
{
  local lookup :: [ProductionGraph] = searchEnvTree(n, l);
  
  -- TODO: so apparently this should never fail?
  return head(lookup);
}

-- These two functions are used by Inh.sv:
function expandGraph
[FlowVertex] ::= v::[FlowVertex]  e::ProductionGraph
{
  -- look up each vertex, uniq it down.
  local initial :: set:Set<FlowVertex> =
    set:add(v, foldr(set:union, set:empty(), map(e.edgeMap, v)));

  return set:toList(expandSuspectEdges(set:toList(initial), initial, e));
}
function onlyLhsInh
set:Set<String> ::= s::[FlowVertex]
{
  return set:add(filterLhsInh(s), set:empty());
}

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
 - @param syn  A synthesized attribute's full name (or "forward")
 - @param nt  The nonterminal to look up this attribute on
 - @param flow  The flow type environment (NOTE: TODO: this is currently 'myFlow' or something, NOT top.flowEnv)
 - @return A set of inherited attributes on this nonterminal, needed to compute this synthesized attribute.
 -}
function inhDepsForSyn
set:Set<String> ::= syn::String  nt::String  flow::EnvTree<FlowType>
{
  return g:edgesFrom(syn, findFlowType(nt, flow));
}



function isLhsInhSet
Boolean ::= v::FlowVertex  inhSet::set:Set<String>
{
  return case v of
  | lhsInhVertex(a) -> set:contains(a, inhSet)
  | _ -> false
  end;
}

function createFlowGraph
g:Graph<FlowVertex> ::= l::[Pair<FlowVertex FlowVertex>]
{
  return g:add(l, g:empty());
}

function extendFlowGraph
g:Graph<FlowVertex> ::= l::[Pair<FlowVertex FlowVertex>]  g::g:Graph<FlowVertex>
{
  return g:add(l, g);
}

function transitiveClose
g:Graph<FlowVertex> ::=
  graph::g:Graph<FlowVertex>
{
  return g:transitiveClosure(graph);
}

function repairClosure
g:Graph<FlowVertex> ::=
  newEdges::[Pair<FlowVertex FlowVertex>]
  graph::g:Graph<FlowVertex>
{
  return g:repairClosure(newEdges, graph);
}

