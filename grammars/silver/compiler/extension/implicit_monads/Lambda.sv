grammar silver:compiler:extension:implicit_monads;

aspect production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  top.merrors := e.merrors;
  propagate mDownSubst, mUpSubst;

  top.mtyperep = appTypes(functionType(length(params.inputElements), []), map((.typerep), params.inputElements) ++ [e.typerep]);

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.monadRewritten = lambdap(params, e.monadRewritten, location=top.location);
}



aspect production lambdaParamReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = q.lookupValue.typeScheme.monoType;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}
