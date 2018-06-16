grammar silver:extension:silverconstruction;

imports silver:langutil;
imports silver:langutil:pp;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type:syntax;
imports silver:extension:list;

-- Silver-to-ableC bridge productions
concrete production silverExprLiteral
top::Expr ::= 'Silver_Expr' LCurly_t ast::Expr RCurly_t
{
  top.pp = s"Silver_Expr {${ast.pp}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production escapeExpr
top::Expr ::= '$Expr' silver:definition:core:LCurly_t e::Expr silver:definition:core:RCurly_t
{
  forwards to
    errorExpr(
      [err(top.location, "$Expr should not occur outside of Silver_Expr")],
      location=top.location);
}
