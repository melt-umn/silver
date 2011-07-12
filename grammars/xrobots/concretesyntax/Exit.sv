grammar xrobots:concretesyntax ;

 import xrobots:abstractsyntax ;
 import xrobots:terminals;

nonterminal Exit_c with pp ;

synthesized attribute ast_Exit :: Exit occurs on Exit_c ;

concrete production onExit_c
b::Exit_c ::=  e::Exit_t '{' s::StmtList_c '}'
{
   b.pp = s.pp;
   b.ast_Exit = makeExit(s.ast_Stmt);
}


concrete production onExit_empty_c
b::Exit_c ::= 
{
   b.pp = "";
   b.ast_Exit = emptyExit();
}

