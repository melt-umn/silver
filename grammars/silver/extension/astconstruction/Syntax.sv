grammar silver:extension:astconstruction;

imports silver:langutil:pp;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type:syntax;
imports silver:extension:list;
imports silver:extension:patternmatching;

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

concrete production varAST_c
top::AST_c ::= '$' n::Name
{
  top.unparse = s"$${${n.unparse}}";
  top.ast = varAST(n);
  top.errors := [];
}

concrete production wildAST_c
top::AST_c ::= '$' '_'
{
  top.unparse = s"$$_";
  top.ast = wildAST();
  top.errors := [];
}

abstract production escapeAST
top::AST ::= e::Expr
{
  top.translation =
    errorExpr(
      [err(top.givenLocation, "${} should only occur inside AST { } expression")],
      location=top.givenLocation);
  top.patternTranslation =
    errorPattern(
      [err(top.givenLocation, "${} should only occur inside AST { } expression")],
      location=top.givenLocation);
  forwards to error("forward shouldn't be needed here");
}

abstract production varAST
top::AST ::= n::Name
{
  top.translation =
    errorExpr(
      [err(top.givenLocation, "$<name> should only occur inside AST { } pattern")],
      location=top.givenLocation);
  top.patternTranslation =
    errorPattern(
      [err(top.givenLocation, "$<name> should only occur inside AST { } pattern")],
      location=top.givenLocation);
  forwards to error("forward shouldn't be needed here");
}

abstract production wildAST
top::AST ::=
{
  top.translation =
    errorExpr(
      [err(top.givenLocation, "$_ should only occur inside AST { } pattern")],
      location=top.givenLocation);
  top.patternTranslation =
    errorPattern(
      [err(top.givenLocation, "$_ should only occur inside AST { } pattern")],
      location=top.givenLocation);
  forwards to error("forward shouldn't be needed here");
}
