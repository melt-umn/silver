grammar silver:definition:flow:driver;

function findFlowType
g:Graph<String> ::= prod::String  e::EnvTree<g:Graph<String>>
{
  local lookup :: [g:Graph<String>] = searchEnvTree(prod, e);
  
  return if null(lookup) then g:empty(compareString) else head(lookup);
}

{--
 - Look up flow types.
 - @param syn  A synthesized attribute's full name (or "forward")
 - @param nt  The nonterminal to look up this attribute on
 - @param flow  The flow type environment (NOTE: TODO: this is currently 'myFlow' or something, NOT top.flowEnv)
 - @return A set of inherited attributes on this nonterminal, needed to compute this synthesized attribute.
 -}
function inhDepsForSyn
[String] ::= syn::String  nt::String  flow::EnvTree<FlowType>
{
  return set:toList(g:edgesFrom(syn, findFlowType(nt, flow))); -- TODO: return a set!! faster!
}


function isLhsInhSet
Boolean ::= v::FlowVertex  inhSet::set:Set<String>
{
  return case v of
  | lhsInhVertex(a) -> set:contains(a, inhSet)
  | _ -> false
  end;
}

function compareFlowVertex
Integer ::= a::FlowVertex  b::FlowVertex
{
  local astr :: String = a.unparse;
  local bstr :: String = b.unparse;
  return if astr < bstr then -1 else if astr == bstr then 0 else 1;
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

function searchGraphEnv
[FlowVertex] ::= v::FlowVertex g::g:Graph<FlowVertex>
{
  return set:toList(g:edgesFrom(v, g));
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

