grammar silver:extension:astconstruction;

imports silver:langutil:pp;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type:syntax;
imports silver:extension:list;
imports silver:extension:patternmatching;

exports silver:reflect:concretesyntax;

concrete production quoteAST
top::Expr ::= 'AST' '{' ast::AST_c '}'
layout {silver:reflect:concretesyntax:WhiteSpace}
{
  top.unparse = s"AST {${ast.unparse}}";
  forwards to translate(top.location, reflect(ast.ast));
}

concrete production quoteASTPattern
top::Pattern ::= 'AST' '{' ast::AST_c '}'
layout {silver:reflect:concretesyntax:WhiteSpace}
{
  top.unparse = s"AST {${ast.unparse}}";
  forwards to translatePattern(top.location, reflect(ast.ast));
}

concrete production antiquoteAST_c
top::AST_c ::= '$' '{' e::Expr '}'
layout {silver:definition:core:WhiteSpace}
{
  top.unparse = s"$${${e.unparse}}";
  top.ast = antiquoteAST(e);
  top.errors := [];
}

concrete production varAST_c
top::AST_c ::= n::QName_t
{
  top.unparse = n.lexeme;
  top.ast = antiquotePatternAST(varPattern(name(n.lexeme, n.location), location=top.location));
  top.errors :=
    if indexOf(":", n.lexeme) != -1
    then [err(n.location, "Pattern variable name must be unqualified")]
    else [];
}

concrete production wildAST_c
top::AST_c ::= '_'
{
  top.unparse = "_";
  top.ast = antiquotePatternAST(wildcPattern('_', location=top.location));
  top.errors := [];
}

abstract production antiquoteAST
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

abstract production antiquotePatternAST
top::AST ::= p::Pattern
{
  top.translation =
    errorExpr(
      [err(top.givenLocation, "Variable and wildcard patterns should only occur inside AST { } pattern")],
      location=top.givenLocation);
  top.patternTranslation =
    errorPattern(
      [err(top.givenLocation, "Variable and wildcard patterns should only occur inside AST { } pattern")],
      location=top.givenLocation);
  forwards to error("forward shouldn't be needed here");
}
