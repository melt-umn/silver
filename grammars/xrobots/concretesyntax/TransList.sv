grammar xrobots:concretesyntax ;


 import xrobots:terminals ;
 import xrobots:abstractsyntax ;

nonterminal TransList_c with pp;
synthesized attribute ast_TransList :: TransList occurs on TransList_c ;


concrete production ucabBlock_c
b::TransList_c ::= 
{
	b.pp = "";
	b.ast_TransList = translist_empty();
}

concrete production ucabBlock2_c
tl::TransList_c ::= t::Trans_c    tlIn::TransList_c
{
	tl.pp = tlIn.pp ++ t.pp;
        tl.ast_TransList = translist_cons(t.ast_Trans, tl.ast_TransList);
}
