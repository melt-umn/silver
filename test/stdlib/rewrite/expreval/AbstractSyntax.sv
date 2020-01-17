grammar stdlib:rewrite:expreval;

imports silver:langutil;
imports silver:langutil:pp;
imports silver:rewrite;

synthesized attribute needsParens::Boolean;

nonterminal Expr with pp, needsParens;

abstract production add
top::Expr ::= e1::Expr e2::Expr
{
  top.pp = pp"${if e1.needsParens then parens(e1.pp) else e1.pp} + ${if e2.needsParens then parens(e2.pp) else e2.pp}";
  top.needsParens = true;
}

abstract production sub
top::Expr ::= e1::Expr e2::Expr
{
  top.pp = pp"${if e1.needsParens then parens(e1.pp) else e1.pp} - ${if e2.needsParens then parens(e2.pp) else e2.pp}";
  top.needsParens = true;
}

abstract production mul
top::Expr ::= e1::Expr e2::Expr
{
  top.pp = pp"${if e1.needsParens then parens(e1.pp) else e1.pp} * ${if e2.needsParens then parens(e2.pp) else e2.pp}";
  top.needsParens = true;
}

abstract production div
top::Expr ::= e1::Expr e2::Expr
{
  top.pp = pp"${if e1.needsParens then parens(e1.pp) else e1.pp} / ${if e2.needsParens then parens(e2.pp) else e2.pp}";
  top.needsParens = true;
}

abstract production const
top::Expr ::= i::Integer
{
  top.pp = text(toString(i));
  top.needsParens = false;
}

function showExpr
String ::= e::Expr
{
  return show(80, e.pp);
}

global evalStep::Strategy =
  rule(
    prodCallASTExpr(
      "stdlib:rewrite:expreval:add",
      consASTExpr(
        prodCallASTExpr("stdlib:rewrite:expreval:const", consASTExpr(varASTExpr("a"), nilASTExpr()), nilNamedASTExpr()),
        consASTExpr(
          prodCallASTExpr("stdlib:rewrite:expreval:const", consASTExpr(varASTExpr("b"), nilASTExpr()), nilNamedASTExpr()),
          nilASTExpr())),
      nilNamedASTExpr()),
    prodCallASTExpr("stdlib:rewrite:expreval:const", consASTExpr(plusASTExpr(varASTExpr("a"), varASTExpr("b")), nilASTExpr()), nilNamedASTExpr()));

global eval::Strategy = innermost(evalStep);
