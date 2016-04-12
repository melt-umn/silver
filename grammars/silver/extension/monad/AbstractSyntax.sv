
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