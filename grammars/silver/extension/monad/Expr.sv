
concrete production do_c
top::Expr ::= 'do' '(' bindFn::QName ',' returnFn::QName ')' '{' body::DoBody '}'
{
  top.pp = s"do (${bindFn.pp}, ${returnFn.pp}) {${body.pp}}";

  -- TODO: quit reporting errors about undefined bind and lookup functions repeatedly
  top.errors <- bindFn.lookupValue.errors ++ returnFn.lookupValue.errors;
  --top.errors <- body.errors; 
  
  body.bindFn = bindFn;
  body.returnFn = returnFn;
  forwards to body.transform;
}

nonterminal DoBody with location, pp, transform, bindFn, returnFn;

autocopy attribute bindFn::QName;
autocopy attribute returnFn::QName;
synthesized attribute transform::Expr;

concrete production bindExprDoBody
top::DoBody ::= n::Name '<-' e::Expr ';' rest::DoBody
{
  top.pp = s"${n.pp} <- ${e.pp}; ${rest.pp}";
  --top.errors := e.errors ++ rest.errors;
  
  top.transform =
    applicationExpr(
      baseExpr(top.bindFn, location=top.location),
      '(',
      snocAppExprs(
        oneAppExprs(presentAppExpr(e, location=top.location), location=top.location),
        ',',
        presentAppExpr(
          lambdap(
            productionRHSCons(
              productionRHSElem(n, '::', typerepType(e.typerep, location=top.location), location=top.location),
              productionRHSNil(location=top.location),
              location=top.location),
            rest.transform,
            location=top.location),
          location=top.location),
        location=top.location),
      ')',
      location=top.location);
}

concrete production sequenceExprDoBody
top::DoBody ::= e::Expr ';' rest::DoBody
{
  top.pp = s"${e.pp}; ${rest.pp}";
  forwards to bindExprDoBody(name("_", top.location), '<-', e, ';', rest, location=top.location);
}

concrete production exprDoBody
top::DoBody ::= e::Expr ';'
{
  top.pp = s"${e.pp};";
  --top.errors := e.errors;
  top.transform = e;
}

concrete production returnDoBody
top::DoBody ::= 'return' e::Expr ';' rest::DoBody
{
  forwards to
    exprDoBody(
      applicationExpr(
        baseExpr(top.returnFn, location=top.location), '(',
        oneAppExprs(presentAppExpr(e, location=top.location), location=top.location),
        ')',
        location=top.location), ';',
      location=top.location);
}
