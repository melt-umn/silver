grammar silver:definition:flow:driver;

type FlowType = g:Graph<String>;

function findFlowType
FlowType ::= prod::String  e::EnvTree<FlowType>
{
  local lookup :: [FlowType] = searchEnvTree(prod, e);
  
  return if null(lookup) then g:empty(compareString) else head(lookup);
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
  return set:toList(set:add(v, foldr(set:union, set:empty(compareFlowVertex), map(e.edgeMap, v))));
}
function onlyLhsInh
set:Set<String> ::= s::[FlowVertex]
{
  return set:add(filterLhsInh(s), set:empty(compareString));
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
  return g:add(l, g:empty(compareFlowVertex));
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

