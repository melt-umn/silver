grammar silver:extension:silverconstruction;

imports silver:langutil:pp;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type:syntax;
imports silver:extension:list;

concrete production quoteAGDcl
top::Expr ::= 'Silver_AGDcl' '{' ast::AGDcl '}'
{
  top.unparse = s"Silver_AGDcl {${ast.unparse}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production quoteProductionStmt
top::Expr ::= 'Silver_ProductionStmt' '{' ast::ProductionStmt '}'
{
  top.unparse = s"Silver_ProductionStmt {${ast.unparse}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production quoteExpr
top::Expr ::= 'Silver_Expr' '{' ast::Expr '}'
{
  top.unparse = s"Silver_Expr {${ast.unparse}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production antiquoteExpr
top::Expr ::= '$Expr' '{' e::Expr '}'
{
  top.unparse = s"$$Expr{${e.unparse}}";
  forwards to
    errorExpr(
      [err(top.location, "$Expr should not occur outside of Silver_Expr")],
      location=top.location);
}

concrete production antiquoteTypeExpr
top::TypeExpr ::= '$TypeExpr' '{' e::Expr '}'
{
  top.unparse = s"$$TypeExpr{${e.unparse}}";
  forwards to
    errorTypeExpr(
      [err(top.location, "$TypeExpr should not occur outside of Silver_Expr")],
      location=top.location);
}

concrete production antiquoteQName
top::QName ::= '$QName' '{' e::Expr '}'
{
  top.unparse = s"$$QName{${e.unparse}}";
  forwards to
    qNameError(
      [err(top.location, "$QName should not occur outside of Silver_Expr")],
      location=top.location);
}

concrete production antiquoteName
top::Name ::= '$Name' '{' e::Expr '}'
{
  top.unparse = s"$$Name{${e.unparse}}";
  -- TODO: [err(top.location, "$Name should not occur outside of Silver_Expr")]
  forwards to name("err", top.location);
}

concrete production antiquote_qName
top::QName ::= '$qName' '{' e::Expr '}'
{
  top.unparse = s"$$qName{${e.unparse}}";
  forwards to
    qNameError(
      [err(top.location, "$qName should not occur outside of Silver_Expr")],
      location=top.location);
}

concrete production antiquote_name
top::Name ::= '$name' '{' e::Expr '}'
{
  top.unparse = s"$$name{${e.unparse}}";
  -- TODO: [err(top.location, "$Name should not occur outside of Silver_Expr")]
  forwards to name("err", top.location);
}
