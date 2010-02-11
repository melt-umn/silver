grammar tutorials:expr:concretesyntax ;

import tutorials:expr:terminals ;
import tutorials:expr:abstractsyntax ;


nonterminal Root_c with pp ;

synthesized attribute ast_Root :: Root occurs on Root_c ;

concrete production root_c
r::Root_c ::= e::Expr_c 
{
 r.pp = e.pp ;
 r.ast_Root = root(e.ast_Expr);
}

