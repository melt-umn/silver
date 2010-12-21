grammar xrobots:concretesyntax ;


 import xrobots:terminals ;
-- import tutorials:xrobots:abstractsyntax ;

--nonterminal BStmtRS_c with pp, ast_Stmt;
--nonterminal BStmtLS_c with pp, ast_Stmt;



concrete production RightSpeed_c
b::Stmt_c ::= s::SetRSpeed_t l::LBrace_t e::Expr_c '}'
{
	b.pp = "setting right speed to " ++ e.pp;
--	b.ast_Stmt = setBStmt(l, e.ast_Expr, 1);
}	

concrete production LeftSpeed_c
b::Stmt_c ::= s::SetLSpeed_t l::LBrace_t e::Expr_c '}'
{
	b.pp = "setting left speed to " ++ e.pp;
--	b.ast_Stmt = setBStmt(l, e.ast_Expr, 0);
}	


