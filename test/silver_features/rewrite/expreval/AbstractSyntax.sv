grammar silver_features:rewrite:expreval;

imports silver:core hiding add, sub, mul, div;
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

abstract production letE
top::Expr ::= n::String e1::Expr e2::Expr
{
  top.pp = pp"let ${text(n)} = ${e1.pp} in ${e2.pp}";
  top.needsParens = true;
}

abstract production const
top::Expr ::= n::Integer
{
  top.pp = text(toString(n));
  top.needsParens = false;
}

abstract production var
top::Expr ::= n::String
{
  top.pp = text(n);
  top.needsParens = false;
}

function showExpr
String ::= e::Expr
{
  return show(80, e.pp);
}

-- Term rewriting library/extension
function subst
Strategy ::= n::String e::Expr
{
  return bottomUp(try(
    rule on Expr of
    | var(n1) when n == n1 -> e
    end));
}

global evalStep::Strategy =
  rule on Expr of
  | add(const(a), const(b)) -> const(a + b)
  | sub(const(a), const(b)) -> const(a - b)
  | mul(const(a), const(b)) -> const(a * b)
  | div(const(a), const(b)) when b != 0 && a % b == 0 -> const(a / b)
  | div(const(a), const(b)) when b != 0 && gcd(a, b) > 1 ->
     let g::Integer = gcd(a, b) in div(const(a / g), const(b / g)) end
  -- This rule does not respect lexical shadowing;
  -- it is assumed that the overall rewrite will be done in an innermost order.
  | letE(n, e1, e2) -> rewriteWith(subst(n, e1), e2).fromJust
  end;

global simplifyConstIdent::Strategy =
  rule on Expr of
  | add(a, const(0)) -> a
  | add(const(0), a) -> a
  
  | sub(a, const(0)) -> a
  
  | mul(_, const(0)) -> const(0)
  | mul(const(0), _) -> const(0)
  | mul(a, const(1)) -> a
  | mul(const(1), a) -> a
  
  | div(const(0), _) -> const(0)
  | div(a, const(1)) -> a
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

global eval::Strategy = innermost(evalStep <+ simplifyConstIdent <+ simplifyFrac);

-- Strategy attributes
autocopy attribute substName::String;
autocopy attribute substExpr::Expr;
strategy attribute substRes =
  allTopDown(
    rule on top::Expr of
    | var(n1) when top.substName == n1 -> top.substExpr
    end);
attribute substName, substExpr, substRes occurs on Expr;
propagate substRes on Expr;

partial strategy attribute evalStep =
  rule on Expr of
  | add(const(a), const(b)) -> const(a + b)
  | sub(const(a), const(b)) -> const(a - b)
  | mul(const(a), const(b)) -> const(a * b)
  | div(const(a), const(b)) when b != 0 && a % b == 0 -> const(a / b)
  | div(const(a), const(b)) when b != 0 && gcd(a, b) > 1 ->
     let g::Integer = gcd(a, b) in div(const(a / g), const(b / g)) end
  -- This rule does not respect lexical shadowing;
  -- it is assumed that the overall rewrite will be done in an innermost order.
  | letE(n, e1, e2) -> decorate e2 with {substName = n; substExpr = e1;}.substRes
  end;

partial strategy attribute simplifyConstIdent =
  rule on Expr of
  | add(a, const(0)) -> a
  | add(const(0), a) -> a
  
  | sub(a, const(0)) -> a
  
  | mul(_, const(0)) -> const(0)
  | mul(const(0), _) -> const(0)
  | mul(a, const(1)) -> a
  | mul(const(1), a) -> a
  
  | div(const(0), _) -> const(0)
  | div(a, const(1)) -> a
  end;

partial strategy attribute simplifyFrac =
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

strategy attribute eval = innermost(evalStep <+ simplifyConstIdent <+ simplifyFrac);

attribute evalStep, simplifyConstIdent, simplifyFrac, eval occurs on Expr;
propagate evalStep, simplifyConstIdent, simplifyFrac, eval on Expr;

