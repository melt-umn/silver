grammar tutorials:xrobots:concretesyntax ;

import tutorials:xrobots:terminals ;
--import tutorials:xrobots:abstractsyntax ;

concrete production lessthan_c
e::Expr_c ::= l::Expr_c  op::LT_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
-- e.ast_Expr = lessthan(l.ast_Expr, op, r.ast_Expr );
}

concrete production greaterthan_c
e::Expr_c ::= l::Expr_c  op::GT_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
-- e.ast_Expr = greaterthan(l.ast_Expr, op, r.ast_Expr );
}

concrete production equals_c
e::Expr_c ::= l::Expr_c  op::EqEq_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
-- e.ast_Expr = equals(l.ast_Expr, op, r.ast_Expr );
}

concrete production and_c
e::Expr_c ::= l::Expr_c  op::And_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 --e.ast_Expr = and(l.ast_Expr, op, r.ast_Expr );
}

concrete production or_c
e::Expr_c ::= l::Expr_c  op::Or_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 --e.ast_Expr = or(l.ast_Expr, op, r.ast_Expr );
}

concrete production not_c
e::Expr_c ::=  op::Not_t  n::Expr_c
{
 e.pp = op.lexeme ++ " " ++ n.pp ;
 --e.ast_Expr = not(op, n.ast_Expr);
}


