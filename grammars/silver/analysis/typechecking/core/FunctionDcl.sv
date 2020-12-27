grammar silver:analysis:typechecking:core;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  body.downSubst = emptySubst();
}

aspect production functionLHS
top::FunctionLHS ::= t::TypeExpr
{
  top.errors <-
    if t.typerep.kindArity > 0
    then [err(t.location, s"Type ${t.unparse} is not fully applied, it has kind arity ${toString(t.typerep.kindArity)}")]
    else [];
}
