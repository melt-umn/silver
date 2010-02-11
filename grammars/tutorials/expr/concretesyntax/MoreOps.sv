grammar tutorials:expr:concretesyntax ;

import tutorials:expr:terminals ;
import tutorials:expr:abstractsyntax ;

concrete production lessthan_c
e::Expr_c ::= l::Expr_c  op::LT_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 e.ast_Expr = lessthan(l.ast_Expr, op, r.ast_Expr );
}

concrete production greaterthan_c
e::Expr_c ::= l::Expr_c  op::GT_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 e.ast_Expr = greaterthan(l.ast_Expr, op, r.ast_Expr );
}

concrete production equals_c
e::Expr_c ::= l::Expr_c  op::EqEq_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 e.ast_Expr = equals(l.ast_Expr, op, r.ast_Expr );
}

concrete production and_c
e::Expr_c ::= l::Expr_c  op::And_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 e.ast_Expr = and(l.ast_Expr, op, r.ast_Expr );
}

concrete production not_c
e::Expr_c ::=  op::Not_t  n::Expr_c
{
 e.pp = op.lexeme ++ " " ++ n.pp ;
 e.ast_Expr = not(op, n.ast_Expr);
}

concrete production cond_c
e::Expr_c ::= c::Expr_c  q::Quest_t then_expr::Expr_c  cln::Colon_t  else_expr::Expr_c
{
 e.pp = c.pp ++ " ? " ++ then_expr.pp ++ " : " ++ else_expr.pp ;
 e.ast_Expr = cond(c.ast_Expr, q, then_expr.ast_Expr, cln, else_expr.ast_Expr);
}

concrete production trueConstant_c
bc::Expr_c::= t::True_t
{
 bc.pp = t.lexeme ;
 bc.ast_Expr = trueConstant(t);
}

concrete production falseConstant_c
bc::Expr_c ::= f::False_t
{
 bc.pp = f.lexeme ;
 bc.ast_Expr = falseConstant(f);
}
