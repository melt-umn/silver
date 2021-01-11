grammar silver:compiler:extension:silverconstruction;

imports silver:langutil:pp;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:extension:list;
imports silver:compiler:extension:patternmatching;

concrete production quoteAGDcl
top::Expr ::= 'Silver_AGDcl' '{' ast::AGDcl '}'
{
  top.unparse = s"Silver_AGDcl " ++ substitute("\n"," ", "{${ast.unparse}}");
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production quoteProductionStmt
top::Expr ::= 'Silver_ProductionStmt' '{' ast::ProductionStmt '}'
{
  top.unparse = s"Silver_ProductionStmt" ++ substitute("\n"," ", "{${ast.unparse}}");
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production quoteExpr
top::Expr ::= 'Silver_Expr' '{' ast::Expr '}'
{
  top.unparse = s"Silver_Expr {${ast.unparse}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production quoteExprInh
top::Expr ::= 'Silver_ExprInh' '{' ast::ExprInh '}'
{
  top.unparse = s"Silver_ExprInh {${ast.unparse}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production quotePattern
top::Expr ::= 'Silver_Pattern' '{' ast::Pattern '}'
{
  top.unparse = s"Silver_Pattern {${ast.unparse}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production quoteTypeExpr
top::Expr ::= 'Silver_TypeExpr' '{' ast::TypeExpr '}'
{
  top.unparse = s"Silver_TypeExpr {${ast.unparse}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production antiquoteExpr
top::Expr ::= '$Expr' '{' e::Expr '}'
{
  top.unparse = s"$$Expr{${e.unparse}}";
  forwards to
    errorExpr(
      [err(top.location, "$Expr should not occur outside of quoted Silver literal")],
      location=top.location);
}

concrete production antiquoteExprInhs
top::ExprInhs ::= '$ExprInhs' '{' e::Expr '}'
{
  top.unparse = s"$$ExprInhs{${e.unparse}}";
  -- TODO: [err(top.location, "$ExprInhs should not occur outside of quoted Silver literal")]
  forwards to exprInhsEmpty(location=top.location);
}

concrete production antiquoteTypeExpr
top::TypeExpr ::= '$TypeExpr' '{' e::Expr '}'
{
  top.unparse = s"$$TypeExpr{${e.unparse}}";
  forwards to
    errorTypeExpr(
      [err(top.location, "$TypeExpr should not occur outside of quoted Silver literal")],
      location=top.location);
}

concrete production antiquotePattern
top::Pattern ::= '$Pattern' '{' e::Expr '}'
{
  top.unparse = s"$$Pattern{${e.unparse}}";
  forwards to
    errorPattern(
      [err(top.location, "$Pattern should not occur outside of quoted Silver literal")],
      location=top.location);
}

concrete production antiquoteQName
top::QName ::= '$QName' '{' e::Expr '}'
{
  top.unparse = s"$$QName{${e.unparse}}";
  forwards to
    qNameError(
      [err(top.location, "$QName should not occur outside of quoted Silver literal")],
      location=top.location);
}

concrete production antiquoteQNameAttrOccur
top::QNameAttrOccur ::= '$QNameAttrOccur' '{' e::Expr '}'
{
  top.unparse = s"$$QNameAttrOccur{${e.unparse}}";
  forwards to
    qNameAttrOccur(
      qNameError(
        [err(top.location, "$QNameAttrOccur should not occur outside of quoted Silver literal")],
        location=top.location),
      location=top.location);
}

concrete production antiquoteName
top::Name ::= '$Name' '{' e::Expr '}'
{
  top.unparse = s"$$Name{${e.unparse}}";
  -- TODO: [err(top.location, "$Name should not occur outside of quoted Silver literal")]
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
  -- TODO: [err(top.location, "$Name should not occur outside of quoted Silver literal")]
  forwards to name("err", top.location);
}
