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
  
  top.errors <-
    if !t.typerep.isNonterminal
    then [err(top.location, "Production LHS type must be a nonterminal.  Instead it is of type " ++ prettyType(t.typerep))]
    else [];
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.errors <- t.errorsKindStar;
}
