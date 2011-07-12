grammar xrobots:concretesyntax ;

 import xrobots:terminals ;
 import xrobots:abstractsyntax ;


nonterminal Root_c with pp ;

synthesized attribute ast_Root :: Root occurs on Root_c ;

concrete production root_behaviorlist_c
r::Root_c ::= b::Behavior_c
{
 r.pp = b.pp;
 r.ast_Root = root(b.ast_Behavior);
}	



