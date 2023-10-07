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
  
  local checkNT::TypeCheck = checkNonterminal(top.env, false, t.typerep);
  checkNT.downSubst = emptySubst();
  checkNT.finalSubst = emptySubst();
  
  top.errors <-
    if checkNT.typeerror
    then [errFromOrigin(top, "Production LHS type must be a nonterminal.  Instead it is of type " ++ checkNT.leftpp)]
    else [];
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.errors <- t.errorsKindStar;
}
