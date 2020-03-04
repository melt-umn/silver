grammar silver:extension:silverconstruction;

imports silver:langutil:pp;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type:syntax;
imports silver:extension:list;

concrete production quoteExpr
top::Expr ::= 'Silver_Expr' LCurly_t ast::Expr RCurly_t
{
  top.unparse = s"Silver_Expr {${ast.unparse}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production antiquoteExpr
top::Expr ::= '$Expr' silver:definition:core:LCurly_t e::Expr silver:definition:core:RCurly_t
{
  top.unparse = s"$$Expr{${e.unparse}}";
  forwards to
    errorExpr(
      [err(top.location, "$Expr should not occur outside of Silver_Expr")],
      location=top.location);
}

concrete production antiquoteTypeExpr
top::TypeExpr ::= '$TypeExpr' silver:definition:core:LCurly_t e::Expr silver:definition:core:RCurly_t
{
  top.unparse = s"$$TypeExpr{${e.unparse}}";
  forwards to
    errorTypeExpr(
      [err(top.location, "$TypeExpr should not occur outside of Silver_Expr")],
      location=top.location);
}

concrete production antiquoteQName
top::QName ::= '$QName' silver:definition:core:LCurly_t e::Expr silver:definition:core:RCurly_t
{
  top.unparse = s"$$QName{${e.unparse}}";
  forwards to
    qNameError(
      [err(top.location, "$QName should not occur outside of Silver_Expr")],
      location=top.location);
}

concrete production antiquoteName
top::Name ::= '$Name' silver:definition:core:LCurly_t e::Expr silver:definition:core:RCurly_t
{
  top.unparse = s"$$Name{${e.unparse}}";
  -- TODO: [err(top.location, "$Name should not occur outside of Silver_Expr")]
  forwards to name("err", top.location);
}

concrete production antiquote_qName
top::QName ::= '$qName' silver:definition:core:LCurly_t e::Expr silver:definition:core:RCurly_t
{
  top.unparse = s"$$qName{${e.unparse}}";
  forwards to
    qNameError(
      [err(top.location, "$qName should not occur outside of Silver_Expr")],
      location=top.location);
}

concrete production antiquote_name
top::Name ::= '$name' silver:definition:core:LCurly_t e::Expr silver:definition:core:RCurly_t
{
  top.unparse = s"$$name{${e.unparse}}";
  -- TODO: [err(top.location, "$Name should not occur outside of Silver_Expr")]
  forwards to name("err", top.location);
}
