grammar edu:umn:cs:melt:tutorial:expr:exts:full_exprs ;

import edu:umn:cs:melt:tutorial:expr:host ;


-- logical or --
----------------
terminal Or_t    '||' operator precedence = 6, association = none ;

concrete production or_c
e::Expr_c ::= l::Expr_c  op::Or_t  r::Expr_c
{
 e.pp = l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ;
 e.ast_Expr = or(l.ast_Expr, op, r.ast_Expr );
}

abstract production or
e::Expr ::= l::Expr op::Or_t r::Expr
{
 e.pp = "(" ++ l.pp ++ " " ++ op.lexeme ++ " " ++ r.pp ++ ")" ;

 forwards to not(not_op, and (not(not_op,l), and_op, not(not_op,r) ) ) ;

 local attribute not_op :: Not_t ;
 not_op = terminal (Not_t, "!", op.line, op.column) ;

 local attribute and_op :: And_t ;
 and_op = terminal (And_t, "&&", op.line, op.column) ;
}
