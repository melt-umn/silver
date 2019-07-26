grammar silver:extension:implicit_monads;

import silver:definition:flow:ast only ExprVertexInfo, FlowVertex;

--- Concrete Syntax for lets
--------------------------------------------------------------------------------

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.merrors := la.merrors ++ e.merrors;

  top.mtyperep = e.mtyperep;

  top.monadRewritten = letp(la.monadRewritten, e.monadRewritten, location=top.location);
}


attribute merrors, monadRewritten<AssignExpr> occurs on AssignExpr;

aspect production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.merrors := a1.merrors ++ a2.merrors;

  top.monadRewritten = appendAssignExpr(a1.monadRewritten, a2.monadRewritten, location=top.location);
}

-- TODO: Well, okay, so this isn't really abstract syntax...
aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  top.merrors := e.merrors;
{-
  -- Right now some things (pattern matching) abuse us by giving type variables
  -- for `t`. So we want to do a little inference before we stuff this into
  -- our DclInfo in `defs` because we expect variables in the env to have
  -- explicit types. We can't use `finalSubst` here because that requires
  -- having completed type inference which requires `defs` which we're defining.
  local semiTy :: Type = performSubstitution(t.typerep, top.upSubst);
  production fName :: String = toString(genInt()) ++ ":" ++ id.name;

  -- TODO: At present, this isn't working properly, because the local scope is
  -- whatever scope encloses the real local scope... hrmm!
  top.errors <- 
    if length(getValueDclInScope(id.name, top.env)) > 1
    then [err(id.location, "Value '" ++ id.name ++ "' is already bound.")]
    else [];

  top.errors <-
    if errCheck1.typeerror
    then [err(id.location, "Value " ++ id.name ++ " declared with type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
    else [];
  -}

  top.monadRewritten = assignExpr(id, '::', t, '=', e.monadRewritten, location=top.location);
}
