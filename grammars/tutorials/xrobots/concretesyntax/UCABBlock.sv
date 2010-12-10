grammar tutorials:xrobots:concretesyntax ;


 import tutorials:xrobots:terminals ;
-- import tutorials:robots:abstractsyntax ;

nonterminal UCABBlock_c with pp;
--synthesized attribute ast_UCABBlock :: UCABBlock occurs on UCABBlock_c ;


concrete production ucabBlock_c
b::UCABBlock_c ::= 
{
	b.pp = "";
--	b.ast_UCABBlock = ucabBlock_empty();
}

concrete production ucabBlock2_c
b::UCABBlock_c ::= ucab::UnderCondApplyBehavior_c bIn::UCABBlock_c
{
	b.pp = bIn.pp ++ ucab.pp;
--	b.ast_UCABBlock = ucabBlock_snoc( ucab.ast_UnderCondApplyBehavior, bIn.ast_UCABBlock);
}
