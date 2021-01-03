
abstract production bindExpr
top::Expr ::= n::Name t::TypeExpr e::Expr rest::Expr bindFn::QName
{
  --propagate downSubst, upSubst;

  local cont :: Expr =
    lambdap(
      productionRHSCons(
        productionRHSElem(
          n, '::', t, location=top.location),
        productionRHSNil(location=top.location),
        location=top.location),
      rest,
      location=top.location);

  forwards to
    mkFunctionInvocation(top.location, baseExpr(bindFn, location=top.location), [e, cont]);
}

abstract production returnExpr
top::Expr ::= e::Expr isFinalVal::Boolean returnFn::QName
{
  -- TODO: This is interfering...
  top.errors <-
    if !isFinalVal
    then [wrn(top.location, "Return is not final value, and does not have type Unit")]
    else [];

  forwards to
    mkFunctionInvocation(top.location, baseExpr(returnFn, location=top.location), [e]);
}

abstract production returnUnitExpr
top::Expr ::= returnFn::QName
{
  forwards to
    returnExpr(
      mkStrFunctionInvocation(top.location, "silver:core:unit", []),
      true, returnFn, location=top.location);
}
