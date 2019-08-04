grammar silver:extension:implicit_monads;

--import silver:definition:flow:ast only ExprVertexInfo, FlowVertex;

--- Concrete Syntax for lets
--------------------------------------------------------------------------------

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.merrors := la.merrors ++ e.merrors;
  la.mDownSubst = top.mDownSubst;
  e.mDownSubst = la.mUpSubst;
  top.mUpSubst = e.mUpSubst;

  top.mtyperep = e.mtyperep;

  top.monadRewritten = letp(la.monadRewritten, e.monadRewritten, location=top.location);
}


attribute merrors, monadRewritten<AssignExpr>, mDownSubst, mUpSubst occurs on AssignExpr;

aspect production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.merrors := a1.merrors ++ a2.merrors;
  a1.mDownSubst = top.mDownSubst;
  a2.mDownSubst = a1.mUpSubst;
  top.mUpSubst = a2.mUpSubst;

  top.monadRewritten = appendAssignExpr(a1.monadRewritten, a2.monadRewritten, location=top.location);
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  top.merrors := e.merrors;
  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;
  top.monadRewritten = assignExpr(id, '::', t, '=', e.monadRewritten, location=top.location);
}




aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName  fi::ExprVertexInfo  fd::[FlowVertex]
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typerep;
  top.monadRewritten = baseExpr(new(q), location=top.location);
}
