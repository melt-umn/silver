grammar xrobots:concretesyntax ;

 import xrobots:abstractsyntax ;
 import xrobots:terminals;

nonterminal StmtList_c with pp, ast_Stmt;
--synthesized attribute ast_StmtList :: StmtList occurs on StmtList_c ;


concrete production statementList_c
e::StmtList_c ::=  r::Stmt_c
{
   e.pp =  r.pp;
   e.ast_Stmt =r.ast_Stmt;
}

concrete production statementList2_c
e::StmtList_c ::= l::StmtList_c    r::Stmt_c 
{
   e.pp = l.pp ++ r.pp;
   e.ast_Stmt = sequence(l.ast_Stmt, r.ast_Stmt);
}

