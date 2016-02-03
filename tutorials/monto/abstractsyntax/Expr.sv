grammar monto:abstractsyntax;

{- This file holds the productions for abstract expressions. -}

nonterminal Expr with value, errors;

abstract production integerValue
e::Expr ::= i::Integer
{
 e.value = i;
 e.errors = [];
}

abstract production add
e::Expr ::= e1::Expr e2::Expr
{
 e.value = e1.value + e2.value;
 e.errors = e1.errors ++ e2.errors;
}

abstract production sub
  e::Expr ::= e1::Expr e2::Expr
{
 e.value = e1.value - e2.value;
 e.errors = e1.errors ++ e2.errors;
}

abstract production mul
  e::Expr ::= e1::Expr e2::Expr
{
 e.value = e1.value * e2.value;
 e.errors = e1.errors ++ e2.errors;
}

abstract production div
  e::Expr ::= e1::Expr e2::Expr
{
 e.value = e1.value / e2.value;
 e.errors = case e2.value of 0 -> ["Divide by zero error"] | _ -> e1.errors ++ e2.errors end;
}
