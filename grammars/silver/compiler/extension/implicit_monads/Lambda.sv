grammar silver:compiler:extension:implicit_monads;

aspect production lambdap
top::Expr ::= params::LambdaRHS e::Expr
{
  top.merrors := e.merrors;
  propagate mDownSubst, mUpSubst;

  top.mtyperep = appTypes(functionType(length(params.inputElements), []), map((.typerep), params.inputElements) ++ [e.typerep]);

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.monadRewritten = lambdap(params, e.monadRewritten);
}



aspect production lambdaParamReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = q.lookupValue.typeScheme.monoType;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q))]
                     else [];
  top.monadRewritten = baseExpr(new(q));
}
