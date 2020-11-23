grammar silver:extension:implicit_monads;

aspect production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  top.merrors := e.merrors;
  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;

  e.expectedMonad = top.expectedMonad;

  top.mtyperep = functionType(e.mtyperep, map((.typerep), params.inputElements), []);

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.monadRewritten = lambdap(params, e.monadRewritten, location=top.location);
}



aspect production lambdaParamReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typerep;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}
