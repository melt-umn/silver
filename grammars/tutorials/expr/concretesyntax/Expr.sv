grammar edu:umn:cs:melt:tutorial:expr:concretesyntax ;

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;

nonterminal Expr_c with pp ; 

synthesized attribute ast_Expr :: Expr occurs on Expr_c; --, Term_c, Factor_c ;

concrete production add_c
sum::Expr_c ::= e::Expr_c  op::Plus_t  t::Expr_c
{
 sum.pp = e.pp ++ " " ++ op.lexeme ++ " " ++ t.pp ;
 sum.ast_Expr = add(e.ast_Expr, op, t.ast_Expr );
}

concrete production sub_c
dff::Expr_c ::= e::Expr_c  op::Dash_t  t::Expr_c
{
 dff.pp = e.pp ++ " " ++ op.lexeme ++ " " ++ t.pp ;
 dff.ast_Expr = sub(e.ast_Expr, op, t.ast_Expr);
}

concrete production mul_c
prd::Expr_c ::= t::Expr_c  op::Star_t  f::Expr_c
{
 prd.pp = t.pp ++ " " ++ op.lexeme ++ " " ++ f.pp ;
 prd.ast_Expr = mul(t.ast_Expr, op, f.ast_Expr);
}

concrete production div_c
d::Expr_c ::= t::Expr_c  op::Slash_t  f::Expr_c
{
 d.pp = t.pp ++ " " ++ op.lexeme ++ " " ++ f.pp ;
 d.ast_Expr = div(t.ast_Expr, op, f.ast_Expr);
}

concrete production nested_c
e::Expr_c ::= '(' inner::Expr_c ')'
{
 e.pp = "(" ++ inner.pp ++ ")" ;
 e.ast_Expr = inner.ast_Expr ;
}

concrete production integerConstant_c
ic::Expr_c ::= i::IntLit_t
{
 ic.pp = i.lexeme ;
 ic.ast_Expr = integerConstant(i);
}
