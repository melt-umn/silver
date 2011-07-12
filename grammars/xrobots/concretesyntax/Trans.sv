grammar xrobots:concretesyntax ;


 import xrobots:terminals ;
 import xrobots:abstractsyntax ;

nonterminal Trans_c with pp;
synthesized attribute ast_Trans :: Trans 
					occurs on Trans_c ;


concrete production underCondApplyBehavior_c
ucab::Trans_c ::=  ut::Under_t 
                   ct::Condition_t 
		   e::Expr_c 
		   at::Apply_t 
		   bt::Behavior_t 
		   n::Id_t 
		   '(' arg::ExprList_c ')' ';'
{
	ucab.pp = e.pp ++ n.lexeme;
	ucab.ast_Trans = transition(ut, e.ast_Expr, n, arg.ast_ExprList);
}
