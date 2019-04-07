grammar silver:extension:astconstruction;

imports silver:langutil:pp;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type:syntax;
imports silver:extension:list;

exports silver:reflect:concretesyntax;

concrete production silverExprLiteral
top::Expr ::= 'AST' LCurly_t ast::AST_c RCurly_t
{
  top.unparse = s"AST {${ast.unparse}}";
  forwards to translate(top.location, reflect(new(ast)));
}

concrete production escapeAST
top::AST_c ::= '${' e::Expr silver:definition:core:RCurly_t
{
  top.unparse = s"$${${e.unparse}}";
  forwards to error("forward shouldn't be needed here");
}
