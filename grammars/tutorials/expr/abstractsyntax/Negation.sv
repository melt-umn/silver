grammar tutorials:expr:abstractsyntax ;

import tutorials:expr:terminals ;


-- negation --
-------------
abstract production neg
e::Expr ::= op::Dash_t  n::Expr
{
 e.pp = op.lexeme ++ " " ++ n.pp ;

 forwards to sub( integerConstant(zero), sub_op, n) ;

 local attribute zero :: IntLit_t ;
 zero = terminal (IntLit_t, "0", op.line, op.column) ;

 local attribute sub_op :: Dash_t ;
 sub_op = terminal (Dash_t, "-", op.line, op.column) ;
}
