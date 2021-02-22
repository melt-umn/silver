grammar silver:compiler:analysis:typechecking:core;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  body.downSubst = emptySubst();
}

aspect production productionLHS
top::ProductionLHS ::= id::Name '::' t::TypeExpr
{
  top.errors <- t.errorsKindStar;
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.errors <- t.errorsKindStar;
}
