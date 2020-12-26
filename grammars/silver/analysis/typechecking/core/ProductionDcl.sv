grammar silver:analysis:typechecking:core;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  body.downSubst = emptySubst();
}

aspect production productionLHS
top::ProductionLHS ::= id::Name '::' t::TypeExpr
{
  top.errors <-
    if t.typerep.kindArity > 0
    then [err(t.location, s"Type ${t.unparse} is not fully applied")]
    else [];
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  top.errors <-
    if t.typerep.kindArity > 0
    then [err(t.location, s"Type ${t.unparse} is not fully applied")]
    else [];
}
