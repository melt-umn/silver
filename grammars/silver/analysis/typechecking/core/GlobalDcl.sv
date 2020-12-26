grammar silver:analysis:typechecking:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
  top.errors <-
    if t.typerep.kindArity > 0
    then [err(t.location, s"Type ${t.unparse} is not fully applied")]
    else [];

  local attribute errCheck1 :: TypeCheck;

  e.downSubst = emptySubst();
  errCheck1.downSubst = e.upSubst;

  errCheck1.finalSubst = errCheck1.upSubst;
  e.finalSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, t.typerep);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, "Declaration of global " ++ id.name ++ " with type " ++ errCheck1.rightpp ++ " has initialization expression with type " ++ errCheck1.leftpp)]
                else [];
}

