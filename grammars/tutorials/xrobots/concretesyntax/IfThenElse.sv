grammar tutorials:xrobots:concretesyntax ;

import tutorials:xrobots:terminals ;
--import tutorials:xrobots:abstractsyntax ;


concrete production ifThenElse_C
s::Stmt_c ::= ift::If_t eTest::Expr_c tt::Then_t  thenStmt::Stmt_c  et::Else_t  elseStmt::Stmt_c
{
	s.pp = "if " ++ eTest.pp ++ " then " ++ thenStmt.pp;
--	s.ast_Stmt = ifThenElse(ift, eTest.ast_Expr, thenStmt.ast_Stmt, elseStmt.ast_Stmt);
}


