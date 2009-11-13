grammar edu:umn:cs:melt:tutorial:expr:exts:concretesyntax ;

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;

-- negation --
--------------

concrete production neg_c
e::Expr_c ::= op::Dash_t  n::Expr_c
precedence operator = Star_t
{
 e.pp = op.lexeme ++ " " ++ n.pp ;
 e.ast_Expr = neg(op, n.ast_Expr );
}

