grammar xrobots:concretesyntax ;


 import xrobots:terminals ;
 import xrobots:abstractsyntax ;

nonterminal ExprList_c with pp;
synthesized attribute ast_ExprList :: [Expr] occurs on ExprList_c;


concrete production exprList_empty
el::ExprList_c ::= 
{
 el.pp = "Empty_ExprList";
 el.ast_ExprList = [];
}


concrete production exprList_cons
el::ExprList_c ::= e::Expr_c c::Comma_t elIn::ExprList_c
{
 el.pp = if elIn.pp == "Empty_ExprList"
         then e.pp
         else e.pp ++ elIn.pp;
 el.ast_ExprList = [e.ast_Expr] ++ elIn.ast_ExprList;
}
