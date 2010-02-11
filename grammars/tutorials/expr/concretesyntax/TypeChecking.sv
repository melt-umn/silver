grammar tutorials:expr:concretesyntax ;

import tutorials:expr:terminals ;
import tutorials:expr:abstractsyntax ;

-- typed let --

concrete production let_expr_typed_c
l::Expr_c ::= 'let' n::Id_t ':' t::TypeExpr_c '=' v::Expr_c 'in' body::Expr_c 'end'
{
 l.pp = "let " ++ n.lexeme ++ ":" ++ t.pp ++ " = " ++ v.pp ++ " in " ++ body.pp ++ " end" ;
 l.ast_Expr = let_expr_typed(n, t.ast_TypeExpr, v.ast_Expr, body.ast_Expr);
}


-- type expression --

nonterminal TypeExpr_c ;

attribute pp occurs on TypeExpr_c ;

synthesized attribute ast_TypeExpr :: TypeExpr occurs on TypeExpr_c ;

concrete production intTypeExpr_c
te::TypeExpr_c ::= i::Int_t
{
 te.pp = i.lexeme ;
 te.ast_TypeExpr = intTypeExpr(i);
}

concrete production booleanTypeExpr_c
te::TypeExpr_c ::= b::Boolean_t
{
 te.pp = b.lexeme ;
 te.ast_TypeExpr = booleanTypeExpr(b);
}





