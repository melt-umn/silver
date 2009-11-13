grammar edu:umn:cs:melt:tutorial:expr:concretesyntax ;

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;

concrete production let_expr_c
l::Expr_c ::= 'let' n::Id_t '=' v::Expr_c 'in' body::Expr_c 'end'
{
 l.pp = "let " ++ n.lexeme ++ " = " ++ v.pp ++ 
        " in " ++ body.pp ++ " end" ;
 l.ast_Expr = let_expr(n,v.ast_Expr,body.ast_Expr);
}

concrete production idRef_c
ir::Expr_c ::= id::Id_t
{
 ir.pp = id.lexeme ;
 ir.ast_Expr = idRef(id);
}


