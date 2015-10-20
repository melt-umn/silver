grammar monto:concretesyntax;

nonterminal Expr_c with ast<abs:Expr>;

concrete production intConstant
e::Expr_c ::= i::IntConst_t
{
 e.ast = abs:integerValue(toInt(i.lexeme));
}

concrete production parens
e::Expr_c ::= '(' e1::Expr_c ')'
{
 e.ast = e1.ast;
}

concrete production add
e::Expr_c ::= e1::Expr_c '+' e2::Expr_c
{
 e.ast = abs:add(e1.ast, e2.ast);
}

concrete production sub
e::Expr_c ::= e1::Expr_c '-' e2::Expr_c
{
 e.ast = abs:sub(e1.ast, e2.ast);
}

concrete production mul
e::Expr_c ::= e1::Expr_c '*' e2::Expr_c
{
 e.ast = abs:mul(e1.ast, e2.ast);
}

concrete production div
e::Expr_c ::= e1::Expr_c '/' e2::Expr_c
{
 e.ast = abs:div(e1.ast, e2.ast);
}

