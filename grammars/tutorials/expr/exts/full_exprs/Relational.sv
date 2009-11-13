grammar edu:umn:cs:melt:tutorial:expr:exts:full_exprs ;
 export edu:umn:cs:melt:tutorial:expr:exts:full_exprs ;

import edu:umn:cs:melt:tutorial:expr:host ;

-- greater than or equal to--
-----------------------------
terminal GtEq_t   '>=' operator precedence = 8, association = none ;

concrete production greaterthaneq_c
e::Expr_c ::= l::Expr_c  op::GtEq_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 e.ast_Expr = greaterthaneq(l.ast_Expr, op, r.ast_Expr );
}

abstract production greaterthaneq
e::Expr ::= l::Expr op::GtEq_t r::Expr
{
 e.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;

 forwards to or(greaterthan(l,gt_op,r), or_op, equals(l,eq_op,r) ) ;

 local attribute gt_op :: GT_t ;
 gt_op = terminal (GT_t, ">", op.line, op.column) ;

 local attribute or_op :: Or_t ;
 or_op = terminal (Or_t, "||", op.line, op.column) ;

 local attribute eq_op :: EqEq_t ;
 eq_op = terminal (EqEq_t, "==", op.line, op.column) ;
}


-- less than or equal to--
-----------------------------
terminal LtEq_t   '<=' operator precedence = 8, association = none ;

concrete production lessthaneq_c
e::Expr_c ::= l::Expr_c  op::LtEq_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 e.ast_Expr = lessthaneq(l.ast_Expr, op, r.ast_Expr );
}

abstract production lessthaneq
e::Expr ::= l::Expr op::LtEq_t r::Expr
{
 e.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;

 forwards to or(lessthan(l,lt_op,r), or_op, equals(l,eq_op,r) ) ;

 local attribute lt_op :: LT_t ;
 lt_op = terminal (LT_t, ">", op.line, op.column) ;

 local attribute or_op :: Or_t ;
 or_op = terminal (Or_t, "||", op.line, op.column) ;

 local attribute eq_op :: EqEq_t ;
 eq_op = terminal (EqEq_t, "==", op.line, op.column) ;
}


-- not equal to--
-----------------------------
terminal NotEq_t   '!=' operator precedence = 8, association = none ;

concrete production noteq_c
e::Expr_c ::= l::Expr_c  op::NotEq_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 e.ast_Expr = noteq(l.ast_Expr, op, r.ast_Expr );
}

abstract production noteq
e::Expr ::= l::Expr op::NotEq_t r::Expr
{
 e.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;

 forwards to not(not_op, equals(l,eq_op,r) ) ;

 local attribute not_op :: Not_t ;
 not_op = terminal (Not_t, "!", op.line, op.column) ;

 local attribute eq_op :: EqEq_t ;
 eq_op = terminal (EqEq_t, "==", op.line, op.column) ;
}



