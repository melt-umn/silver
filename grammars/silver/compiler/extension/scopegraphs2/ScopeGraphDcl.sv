grammar silver:compiler:extension:scopegraphs2;

--

synthesized attribute sgFullName::String;
synthesized attribute labelSet::[String];
synthesized attribute labelDclTypeExpr::Maybe<TypeExpr>;

--

nonterminal ScopeGraphDclInfo with fullName, isEqual, compareTo, labelSet;

abstract production graphDcl
top::ScopeGraphDclInfo ::= fn::String labs::[String]
{
  top.fullName = fn;
  top.isEqual = case top.compareTo of
                | graphDcl(fn2, _) -> fn == fn2
                | _ -> false
                end;
  top.labelSet = labs;
}

instance Eq ScopeGraphDclInfo {
  eq = \l::ScopeGraphDclInfo r::ScopeGraphDclInfo -> l.fullName == r.fullName;
}

--

nonterminal ScopeLabelDclInfo with fullName, sgFullName, labelDclTypeExpr, isEqual, compareTo;

abstract production labelDcl
top::ScopeLabelDclInfo ::= sg::String fn::String datumTe::Maybe<TypeExpr>
{
  top.fullName = fn;
  top.sgFullName = sg;
  top.labelDclTypeExpr = datumTe;
  top.isEqual = case top.compareTo of
                | labelDcl(fn1, fn2, _) -> fn1 == sg && fn2 == fn
                | _ -> false
                end;
}

instance Eq ScopeLabelDclInfo {
  eq = \l::ScopeLabelDclInfo r::ScopeLabelDclInfo -> 
    l.sgFullName == r.sgFullName &&
    l.fullName   == r.fullName
  ;
}