grammar silver:compiler:extension:do_notation;

concrete production do_c
top::Expr ::= 'do' '{' body::DoBody '}'
{
  top.unparse = s"do {${body.unparse}}";
  
  forwards to body.transform;
}

synthesized attribute bindingFreeVars::ts:Set<String>;
synthesized attribute isApplicative::Boolean;
synthesized attribute bindings::[ProductionRHSElem];
synthesized attribute exprs::[Expr];
synthesized attribute result::Expr;

synthesized attribute applicativeTransform::Expr;
synthesized attribute transform::Expr;

nonterminal DoBody with location, unparse, bindingFreeVars, isApplicative, bindings, exprs, result, applicativeTransform, transform;

aspect default production
top::DoBody ::=
{
  top.applicativeTransform =
    foldl(
      \ trans::Expr e::Expr -> mkStrFunctionInvocation(top.location, "silver:core:ap", [trans, e]),
      mkStrFunctionInvocation(top.location, "silver:core:map", [
        foldr(
          \ el::ProductionRHSElem trans::Expr ->
            lambdap(
              productionRHSCons(el, productionRHSNil(location=top.location), location=top.location),
              trans, location=top.location),
          top.result, top.bindings),
        head(top.exprs)]),
      tail(top.exprs));
}

concrete production bindExprDoBody
top::DoBody ::= n::Name DoDoubleColon_t t::TypeExpr '<-' e::Expr ';' rest::DoBody
{
  top.unparse = s"${n.unparse}::${t.unparse} <- ${e.unparse}; ${rest.unparse}";
  top.bindingFreeVars = e.freeVars ++ rest.bindingFreeVars;
  top.isApplicative = !ts:contains(n.name, rest.bindingFreeVars) && rest.isApplicative;
  top.bindings =
    productionRHSElem(n, terminal(ColonColon_t, "::"), t, location=top.location) :: rest.bindings;
  top.exprs = e :: rest.exprs;
  top.result = rest.result;
  
  local cont :: Expr =
    lambdap(
      productionRHSCons(
        productionRHSElem(n, terminal(ColonColon_t, "::"), t, location=top.location),
        productionRHSNil(location=top.location),
        location=top.location),
      rest.transform,
      location=top.location);
  top.transform =
    if top.isApplicative then top.applicativeTransform
    else mkStrFunctionInvocation(top.location, "silver:core:bind", [e, cont]);
}

concrete production sequenceDoBody
top::DoBody ::= e::Expr ';' rest::DoBody
{
  top.unparse = s"${e.unparse} ${rest.unparse}";
  top.bindingFreeVars = e.freeVars ++ rest.bindingFreeVars;
  top.isApplicative = rest.isApplicative;
  top.bindings =
    productionRHSElemType(typerepTypeExpr(freshType(), location=top.location), location=top.location) ::
    rest.bindings;
  top.exprs = e :: rest.exprs;
  top.result = rest.result;
  top.transform =
    if top.isApplicative then top.applicativeTransform
    else mkStrFunctionInvocation(top.location, "silver:core:applySecond", [e, rest.transform]);
}

concrete production letExprDoBody
top::DoBody ::= 'let' n::Name '::' t::TypeExpr '=' e::Expr ';' rest::DoBody
{
  top.unparse = s"let ${n.unparse}::${t.unparse} = ${e.unparse}; ${rest.unparse}";
  top.bindingFreeVars = e.freeVars ++ rest.bindingFreeVars;
  top.isApplicative = false;
  top.bindings = error("Not applicative");
  top.exprs = error("Not applicative");
  top.result = error("Not applicative");
  
  top.transform =
    letp(
      assignExpr(n, terminal(ColonColon_t, "::"), t, '=', e, location=top.location),
      rest.transform,
    location=top.location);
}

concrete production finalExprDoBody
top::DoBody ::= e::Expr ';'
{
  top.unparse = s"${e.unparse};";
  top.bindingFreeVars = mempty;
  top.isApplicative = false;
  top.bindings = error("Not applicative");
  top.exprs = error("Not applicative");
  top.result = error("Not applicative");
  top.transform = e;
}

concrete production finalReturnDoBody
top::DoBody ::= 'return' e::Expr ';'
{
  top.unparse = s"return ${e.unparse};";
  top.bindingFreeVars = mempty;
  top.isApplicative = true;
  top.bindings = [];
  top.exprs = [];
  top.result = e;
  top.transform = mkStrFunctionInvocation(top.location, "silver:core:pure", [e]);
}
