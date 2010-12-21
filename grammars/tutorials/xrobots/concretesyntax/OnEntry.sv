grammar xrobots:concretesyntax ;

-- import tutorials:xrobots:abstractsyntax ;
 import xrobots:terminals;

nonterminal OnEntry_c with pp ;

--synthesized attribute ast_OnEntry :: OnEntry occurs on OnEntry_c ;

concrete production onEntry_c
b::OnEntry_c ::=  e::Entry_t s::Stmt_c
{
   b.pp = s.pp;
--   b.ast_OnEntry = makeEntry(s.ast_Stmt);
}


concrete production onEntry_empty_c
b::OnEntry_c ::= 
{
   b.pp = "";
  -- b.ast_OnEntry = onEntry_empty();
}

