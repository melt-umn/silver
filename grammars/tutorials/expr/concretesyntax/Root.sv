grammar edu:umn:cs:melt:tutorial:expr:concretesyntax ;
 export edu:umn:cs:melt:tutorial:expr:concretesyntax ;

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;


start nonterminal Root_c with pp ;

synthesized attribute ast_Root :: Root occurs on Root_c ;

concrete production root_c
r::Root_c ::= e::Expr_c 
{
 r.pp = e.pp ;
 r.ast_Root = root(e.ast_Expr);
}

