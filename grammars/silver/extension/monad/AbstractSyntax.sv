
abstract production bindExpr
top::Expr ::= n::Name t::Type e::Expr rest::Expr bindFn::QName
{
  {-e.downSubst = top.downSubst;
  rest.downSubst = e.upSubst;
  forward.downSubst = rest.upSubst;-}

  forwards to
    applicationExpr(
      baseExpr(bindFn, location=top.location),
      '(',
      snocAppExprs(
        oneAppExprs(
          presentAppExpr(e, location=top.location),
          location=top.location),
        ',',
        presentAppExpr(
          lambdap(
            productionRHSCons(
              productionRHSElem(
                n, '::', t,
                location=top.location),
              productionRHSNil(location=top.location),
              location=top.location),
            rest,
            location=top.location),
          location=top.location),
        location=top.location),
      ')',
      location=top.location);
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
    applicationExpr(
      baseExpr(returnFn, location=top.location), '(',
      oneAppExprs(presentAppExpr(e, location=top.location), location=top.location),
      ')',
      location=top.location);
}

abstract production returnUnitExpr
top::Expr ::= returnFn::QName
{
  forwards to
    returnExpr(
      applicationEmpty(
        baseExpr(qName(top.location, "unit"), location=top.location), '(', ')',
        location=top.location),
      true, returnFn, location=top.location);
}