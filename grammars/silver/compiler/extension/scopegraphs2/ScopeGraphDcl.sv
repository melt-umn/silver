grammar silver:compiler:extension:scopegraphs2;

--

synthesized attribute labelSet::[(String, Maybe<TypeExpr>)];
synthesized attribute labelDclTypeExpr::Maybe<TypeExpr>;
synthesized attribute combinedTe::TypeExpr;

--

nonterminal ScopeGraphDclInfo with fullName, isEqual, compareTo, labelSet, combinedTe;

abstract production graphDcl
top::ScopeGraphDclInfo ::= fn::String labs::[(String, Maybe<TypeExpr>)]
{
  top.fullName = fn;
  top.isEqual = ^top.compareTo == ^top;
  top.labelSet = labs;

  top.combinedTe = foldrLastElem(
    \te::TypeExpr acc::TypeExpr -> Silver_TypeExpr{Either<$TypeExpr{te} $TypeExpr{acc}>},
    \te::TypeExpr -> te,
    Silver_TypeExpr{Unit}::filterMap(snd(_), labs)
  );
}

instance Eq ScopeGraphDclInfo {
  eq = \l::ScopeGraphDclInfo r::ScopeGraphDclInfo -> l.fullName == r.fullName;
}

--

nonterminal ScopeLabelDclInfo with fullName, labelDclTypeExpr, isEqual, compareTo;

abstract production labelDcl
top::ScopeLabelDclInfo ::= fn::String datumTe::Maybe<TypeExpr>
{
  top.fullName = fn;
  top.labelDclTypeExpr = datumTe;
  top.isEqual = ^top.compareTo == ^top;
}

instance Eq ScopeLabelDclInfo {
  eq = \l::ScopeLabelDclInfo r::ScopeLabelDclInfo -> 
    l.fullName   == r.fullName
  ;
}
