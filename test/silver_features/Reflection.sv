
import core:reflect;
import silver:langutil;
import silver:langutil:pp;

nonterminal Expr;

abstract production addExpr
top::Expr ::= e1::Expr e2::Expr
{}

abstract production intConstExpr
top::Expr ::= i::Integer
{}

abstract production idExpr
top::Expr ::= id::String
{}

abstract production decExpr
top::Expr ::= d::Decorated Expr
{}

global testExpr::Expr = addExpr(intConstExpr(2), idExpr("asdf"));

equalityTest(
  show(80, reflectAST([testExpr, intConstExpr(5), decExpr(decorate testExpr with {})]).pp),
  s"""[silver_features:addExpr(silver_features:intConstExpr(2), silver_features:idExpr("asdf")), silver_features:intConstExpr(5), silver_features:decExpr(<FOREIGN>)]""",
  String, silver_tests);

