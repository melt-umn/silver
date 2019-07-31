grammar silver:extension:implicit_monads;

--import silver:definition:flow:ast only ExprVertexInfo, FlowVertex;

--- Concrete Syntax for lambdas
--------------------------------------------------------------------------------

aspect production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = functionType(e.mtyperep, map((.typerep), params.inputElements), []);

  top.monadRewritten = lambdap(params, e.monadRewritten, location=top.location);
}



aspect production lambdaParamReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = q.lookupValue.typerep;
  top.monadRewritten = baseExpr(new(q), location=top.location);
}
