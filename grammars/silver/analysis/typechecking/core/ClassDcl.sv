grammar silver:analysis:typechecking:core;

aspect production defaultClassBodyItem
top::ClassBodyItem ::= id::Name '::' ty::TypeExpr '=' e::Expr ';'
{
  top.errors <- ty.errorsFullyApplied;

  local errCheck1::TypeCheck = check(ty.typerep, e.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(e.location, s"Member ${id.name} has expected type ${errCheck1.leftpp}, but the expression has actual type ${errCheck1.rightpp}")]
    else [];
  
  e.downSubst = emptySubst();
  errCheck1.downSubst = e.upSubst;
  e.finalSubst = e.upSubst;
  errCheck1.finalSubst = e.finalSubst;
}
