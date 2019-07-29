grammar silver:extension:implicit_monads;

--import silver:definition:flow:ast only ExprVertexInfo, FlowVertex;

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

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  top.merrors := e.merrors;
  top.monadRewritten = assignExpr(id, '::', t, '=', e.monadRewritten, location=top.location);
}
