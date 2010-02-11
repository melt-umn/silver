grammar tutorials:expr:concretesyntax ;

import tutorials:expr:terminals ;
import tutorials:expr:abstractsyntax ;

-- negation --
--------------

concrete production neg_c
e::Expr_c ::= op::Dash_t  n::Expr_c
operator = Star_t
{
 e.pp = op.lexeme ++ " " ++ n.pp ;
 e.ast_Expr = neg(op, n.ast_Expr );
}

