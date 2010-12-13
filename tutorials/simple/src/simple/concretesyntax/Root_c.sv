grammar simple:concretesyntax ;

{- "imports" statements import concstructs from the specified grammars
   in to this grammar and makes them visible in all files in this
   grammar.  Thus "imports" or "import" statements for these grammar
   are not needed (or allowed) in other files in thie grammar.
-}

imports simple:terminals ;
imports simple:abstractsyntax ;

nonterminal Root_c with pp ;
{- Complete programs are reprensented by tree which have nonterminal
   of type Root_c at the root of the concrete syntax trees.  
-}

synthesized attribute ast_Root :: Root occurs on Root_c ;

concrete production rootStmt_c
r::Root_c ::= 'main' '(' ')' '{' s::Stmts_c '}'
{
 r.pp = "main () { \n" ++ s.pp ++ "}\n\n}" ;
 r.ast_Root = rootStmt (s.ast_Stmt) ;
}
