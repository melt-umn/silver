grammar xrobots:concretesyntax ;

import xrobots:terminals ;
import xrobots:abstractsyntax ;

nonterminal Stmt_c with pp ;

synthesized attribute ast_Stmt :: Stmt occurs on Stmt_c ;

concrete production stmt_c
s::Stmt_c ::= n::Id_t ':=' e::Expr_c  ';'
{
  s.pp = n.lexeme ++ " := " ++ e.pp ;
  s.ast_Stmt = assignment(n,e.ast_Expr) ;
}
{---
concrete production collection_c
s::Stmt_c ::= sl::StmtList_c
{
 s.pp = sl.pp;
 s.ast_Stmt = collection(sl.ast_StmtList);
}	    
---}
  
{-----------
concrete production empty_sequence_c
s::StmtList_c ::= 
{
 s.pp = "Empty StatementList";
 s.ast_StmtList = skip();
}

concrete production sequence_c
slReturn::StmtList_c ::= s::Stmt_c sl::StmtList_c
{
 slReturn.pp = s.pp ++ "\n" ++ sl.pp;
 slReturn.ast_StmtList = sequence(s.ast_Stmt, sl.ast_StmtList);
}


concrete production arStmt_c
s::Stmt_c ::= n::Id_t '[' i::Expr_c ']' ':=' e::Expr_c  ';'
{
  s.pp = n.lexeme ++ " := " ++ e.pp ;
 -- s.ast_Stmt = arAssignment(n, i.ast_Expr, e.ast_Expr) ;
}

concrete production ar2dStmt_c
s::Stmt_c ::= n::Id_t '[' i::Expr_c ',' j::Expr_c ']' ':=' e::Expr_c  ';'
{
  s.pp = n.lexeme ++ " := " ++ e.pp ;
 -- s.ast_Stmt = ar2dAssignment(n, i.ast_Expr, j.ast_Expr, e.ast_Expr) ;
}

concrete production skip_c
s::Stmt_c ::= ';'
{
  s.pp = ";" ;
  s.ast_Stmt = skip() ;
}
----------------------}

