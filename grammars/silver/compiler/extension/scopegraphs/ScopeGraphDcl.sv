grammar silver:compiler:extension:scopegraphs;

--

synthesized attribute labelSet::[String];

--

nonterminal ScopeGraphDclInfo with fullName, isEqual, compareTo, labelSet;

abstract production graphDcl
top::ScopeGraphDclInfo ::= fn::String labs::[String]
{
  top.fullName = fn;
  top.isEqual = ^top.compareTo == ^top;
  top.labelSet = labs;
}

instance Eq ScopeGraphDclInfo {
  eq = \l::ScopeGraphDclInfo r::ScopeGraphDclInfo -> l.fullName == r.fullName;
}
