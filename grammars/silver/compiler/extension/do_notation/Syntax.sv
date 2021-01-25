grammar silver:compiler:extension:do_notation;

concrete production do_c
top::Expr ::= 'do' '{' body::DoBody '}'
{
  top.unparse = s"do {${body.unparse}}";
  
  forwards to body.transform;
}

synthesized attribute transform::Expr;

nonterminal DoBody with location, unparse, transform;

concrete production bindExprDoBody
top::DoBody ::= n::Name DoDoubleColon_t t::TypeExpr '<-' e::Expr ';' rest::DoBody
{
  top.unparse = s"${n.unparse}::${t.unparse} <- ${e.unparse}; ${rest.unparse}";
  
  local cont :: Expr =
    lambdap(
      productionRHSCons(
        productionRHSElem(n, terminal(ColonColon_t, "::"), t, location=top.location),
        productionRHSNil(location=top.location),
        location=top.location),
      rest.transform,
      location=top.location);
  top.transform = mkStrFunctionInvocation(top.location, "silver:core:bind", [e, cont]);
}

concrete production sequenceDoBody
top::DoBody ::= e::Expr ';' rest::DoBody
{
  top.unparse = s"${e.unparse} ${rest.unparse}";
  top.transform = mkStrFunctionInvocation(top.location, "silver:core:applySecond", [e, rest.transform]);
}

concrete production letExprDoBody
top::DoBody ::= 'let' n::Name '::' t::TypeExpr '=' e::Expr ';' rest::DoBody
{
  top.unparse = s"let ${n.unparse}::${t.unparse} = ${e.unparse}; ${rest.unparse}";
  
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
  top.transform = e;
}

concrete production finalReturnDoBody
top::DoBody ::= 'return' e::Expr ';'
{
  top.unparse = s"return ${e.unparse};";
  forwards to finalExprDoBody(mkStrFunctionInvocation(top.location, "silver:core:pure", [e]), $3, location=top.location);
}
