grammar xrobots:concretesyntax ;

--export edu:umn:cs:melt:sandbox:steve_demos:xrobots:concretesyntax ;

 import xrobots:terminals ;
 --import tutorials:xrobots:abstractsyntax ;


nonterminal Root_c with pp ;

--synthesized attribute ast_Root :: Root occurs on Root_c ;
synthesized attribute pp :: String;

concrete production root_behaviorlist_c
r::Root_c ::= b::Dec_c
{
	r.pp = b.pp;
--Z	r.ast_Root = root_behaviorlist(b.ast_Dec);
}	



