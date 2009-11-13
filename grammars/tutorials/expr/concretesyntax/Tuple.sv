grammar edu:umn:cs:melt:tutorial:expr:concretesyntax ;

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;

concrete production tupleTypeExpr_c
te::TypeExpr_c ::= '(' l::TypeExpr_c c::Comma_t r::TypeExpr_c ')'
{
 te.pp = "(" ++ l.pp ++ "," ++ r.pp ++ ")" ;
 te.ast_TypeExpr = tupleTypeExpr (l.ast_TypeExpr, c, r.ast_TypeExpr) ;
} 


concrete production tuple_c
e::Expr_c ::= '(' l::Expr_c ',' r::Expr_c ')'
{
 e.pp = "(" ++ l.pp ++ "," ++ r.pp ++ ")" ;
 e.ast_Expr = tuple(l.ast_Expr, r.ast_Expr) ;
}


concrete production fst_c
e::Expr_c ::= f::Fst_t '(' t::Expr_c ')'
{
 e.pp = "fst(" ++ t.pp ++ ")" ;
 e.ast_Expr = fst(f,t.ast_Expr);
}

concrete production snd_c
e::Expr_c ::= s::Snd_t '(' t::Expr_c ')'
{
 e.pp = "snd(" ++ t.pp ++ ")" ;
 e.ast_Expr = snd(s,t.ast_Expr);
}
