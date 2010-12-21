grammar xrobots:concretesyntax ;


 import xrobots:terminals ;
-- import tutorials:xrobots:abstractsyntax ;

nonterminal UnderCondApplyBehavior_c with pp;
--synthesized attribute ast_UnderCondApplyBehavior :: 
					--UnderCondApplyBehavior 
					--occurs on UnderCondApplyBehavior_c ;


concrete production underCondApplyBehavior_c
ucab::UnderCondApplyBehavior_c ::=  u::Under_t c::Condition_t e::Expr_c at::Apply_t bt::Behavior_t n::Id_t '(' arg::ArgList_c ')'
{
	ucab.pp = e.pp ++ n.lexeme;
--	ucab.ast_UnderCondApplyBehavior = 
--					underCondApplyBehavior(c, e.ast_Expr, bt, n, arg.ast_ArgList);
}

concrete production underCondApplyBehaviorDotted_c
ucab::UnderCondApplyBehavior_c ::=  u::Under_t c::Condition_t e::Expr_c at::Apply_t bt::Behavior_t dd::DottedAccess '(' arg::ArgList_c ')'
{
	ucab.pp = e.pp ++ dd.pp;
--	ucab.ast_UnderCondApplyBehavior = 
--					underCondApplyBehaviorList(c, e.ast_Expr, bt, dd.ids,
--													 arg.ast_ArgList);
}


nonterminal DottedAccess with pp, ids ;
synthesized attribute ids::[Id_t];

concrete production access
d::DottedAccess ::= n::Id_t '.' m::Id_t
{
	d.pp = n.lexeme ++ m.lexeme;
	d.ids = [ n, m];
}

concrete production access2
d::DottedAccess ::= dIn::DottedAccess '.' m::Id_t
{
	d.pp = dIn.pp ++ m.lexeme;
	d.ids = dIn.ids ++ [m];
}

concrete production underCondExit_c
ucab::UnderCondApplyBehavior_c ::=  u::Under_t c::Condition_t e::Expr_c et::ExitTo_t '(' n::Id_t ')'
{
	ucab.pp = e.pp ++ ":ExitBehavior"	;
--	ucab.ast_UnderCondApplyBehavior = underCondExitTo(c, e.ast_Expr, et, n);
}
