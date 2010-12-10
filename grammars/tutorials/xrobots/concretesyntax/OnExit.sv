grammar tutorials:xrobots:concretesyntax ;

-- import tutorials:xrobots:abstractsyntax ;

 import tutorials:xrobots:terminals;

nonterminal OnExit_c with pp ;

--synthesized attribute ast_OnExit :: OnExit occurs on OnExit_c ;

concrete production onExit_c
b::OnExit_c ::= e::Exit_t s::Stmt_c
{
   b.pp = s.pp;
--   b.ast_OnExit = makeExit(s.ast_Stmt);
}

concrete production onExit_empty_c
b::OnExit_c ::=
{
   b.pp = "";
  --Z b.ast_OnExit = onExit_empty();
}
