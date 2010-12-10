grammar tutorials:xrobots:concretesyntax ;

-- import tutorials:xrobots:abstractsyntax ;
 import tutorials:xrobots:terminals;

nonterminal StmtList_c with pp;
--synthesized attribute ast_StmtList :: StmtList occurs on StmtList_c ;


concrete production statementList_c
e::StmtList_c ::=  r::Stmt_c
{
   e.pp =  r.pp;
   --e.ast_StmtList = stmtList_one(r.ast_Stmt);
}

concrete production statementList2_c
e::StmtList_c ::= l::StmtList_c    r::Stmt_c 
{
   e.pp = l.pp ++ r.pp;
   --e.ast_StmtList = stmtList_cons(l.ast_StmtList, r.ast_Stmt);
}

