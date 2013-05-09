grammar silver:definition:flow:driver;

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
