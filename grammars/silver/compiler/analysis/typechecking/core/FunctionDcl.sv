grammar silver:compiler:analysis:typechecking:core;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  body.downSubst = emptySubst();
}

aspect production functionLHS
top::FunctionLHS ::= t::TypeExpr
{
  top.errors <- t.errorsKindStar;
}
