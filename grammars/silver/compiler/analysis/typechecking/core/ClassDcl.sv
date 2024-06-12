grammar silver:compiler:analysis:typechecking:core;

aspect production defaultConstraintClassBodyItem
top::ClassBodyItem ::= id::Name '::' cl::ConstraintList '=>' ty::TypeExpr '=' e::Expr ';'
{
  top.errors <- ty.errorsKindStar;

  local errCheck1::TypeCheck = check(ty.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [errFromOrigin(e, s"Member ${id.name} has expected type ${errCheck1.leftpp}, but the expression has actual type ${errCheck1.rightpp}")]
    else [];
  
  e.downSubst = emptySubst();
  errCheck1.downSubst = e.upSubst;
  e.downSubst2 = errCheck1.upSubst;
  e.finalSubst = e.upSubst2;
  errCheck1.finalSubst = e.finalSubst;
}
