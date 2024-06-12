grammar silver:compiler:analysis:typechecking:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  top.errors <- t.errorsKindStar;

  local attribute errCheck1 :: TypeCheck;

  e.downSubst = emptySubst();
  errCheck1.downSubst = e.upSubst;
  e.downSubst2 = errCheck1.upSubst;

  e.finalSubst = e.upSubst2;
  errCheck1.finalSubst = e.finalSubst;

  errCheck1 = check(e.typerep, t.typerep);
  top.errors <- if errCheck1.typeerror
                then [errFromOrigin(top, "Declaration of global " ++ id.name ++ " with type " ++ errCheck1.rightpp ++ " has initialization expression with type " ++ errCheck1.leftpp)]
                else [];
}

