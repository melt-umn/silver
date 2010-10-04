grammar silver:analysis:typechecking:core;

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::Type '=' e::Expr ';'
{
  local attribute errCheck1 :: TypeCheck; errCheck1.downSubst = emptySubst();
  errCheck1 = check(e.typerep, t.typerep);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, "Declaration of global " ++ id.name ++ " with type " ++ errCheck1.rightpp ++ " has initialization expression with type " ++ errCheck1.leftpp)]
                else [];

  e.downSubst = emptySubst();
  e.finalSubst = e.upSubst;
}

