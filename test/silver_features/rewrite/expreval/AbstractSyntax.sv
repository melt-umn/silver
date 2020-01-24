grammar silver_features:rewrite:expreval;

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
top::Expr ::= n::Integer
{
  top.pp = text(toString(n));
  top.needsParens = false;
}

function showExpr
String ::= e::Expr
{
  return show(80, e.pp);
}

global evalStep::Strategy =
  rule on Expr of
  | add(const(a), const(b)) -> const(a + b)
  | sub(const(a), const(b)) -> const(a - b)
  | mul(const(a), const(b)) -> const(a * b)
  | div(const(a), const(b)) when b != 0 && a % b == 0 -> const(a / b)
  | div(const(a), const(b)) when b != 0 && gcd(a, b) > 1 ->
     let g::Integer = gcd(a, b) in div(const(a / g), const(b / g)) end
  end;

global simplifyFrac::Strategy =
  rule on Expr of
  | add(div(a, b), c) -> div(add(a, mul(b, c)), b)
  | sub(div(a, b), c) -> div(sub(a, mul(b, c)), b)
  | mul(div(a, b), c) -> div(mul(a, c), b)
  | div(div(a, b), c) -> div(a, mul(b, c))
  
  | add(a, div(b, c)) -> div(add(mul(a, c), b), c)
  | sub(a, div(b, c)) -> div(sub(mul(a, c), b), c)
  | mul(a, div(b, c)) -> div(mul(a, b), c)
  | div(a, div(b, c)) -> div(mul(a, c), b)
  
  | add(div(a, b), div(c, d)) -> div(add(mul(a, d), mul(c, b)), mul(b, d))
  | sub(div(a, b), div(c, d)) -> div(sub(mul(a, d), mul(c, b)), mul(b, d))
  | mul(div(a, b), div(c, d)) -> div(mul(a, c), mul(c, d))
  | div(div(a, b), div(c, d)) -> div(mul(a, d), mul(b, c))
  end;

global eval::Strategy = innermost(evalStep <+ simplifyFrac);
