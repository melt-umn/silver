grammar silver:extension:astconstruction;

imports silver:langutil:pp;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type:syntax;
imports silver:extension:list;

exports silver:reflect:concretesyntax;

concrete production silverExprLiteral
top::Expr ::= 'AST' '{' ast::AST_c '}'
{
  top.unparse = s"AST {${ast.unparse}}";
  forwards to translate(top.location, reflect(ast.ast));
}

concrete production escapeAST_c
top::AST_c ::= '$' '{' e::Expr '}'
{
  top.unparse = s"$${${e.unparse}}";
  top.ast = escapeAST(e);
  top.errors := [];
}

abstract production escapeAST
top::AST ::= e::Expr
{
  forwards to error("forward shouldn't be needed here");
}
