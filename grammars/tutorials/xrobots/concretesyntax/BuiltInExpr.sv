grammar tutorials:xrobots:concretesyntax ;


 import tutorials:xrobots:terminals ;
-- import tutorials:xrobots:abstractsyntax ;

--nonterminal BStmtRS_c with pp, ast_Stmt;
--nonterminal BStmtLS_c with pp, ast_Stmt;



concrete production RightBump_c
b::Expr_c ::= s::RightBump_t
{
	b.pp = "accessing right Bump";
--	b.ast_Expr = bumpRightExpr(s);
}	

concrete production LeftBump_c
b::Expr_c ::= s::LeftBump_t 
{
	b.pp = "accessing left Bump";
--0	b.ast_Expr = bumpLeftExpr(s);
}	

--concrete production clock_c
--b::Expr_c ::= s::Clock_t 
--{
--	b.pp = "accessing Clock";
--	b.ast_Expr = clockExpr(s);
--}	
