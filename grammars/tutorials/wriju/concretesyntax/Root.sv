grammar tutorials:wriju:concretesyntax;

import tutorials:wriju:terminals;

nonterminal Root_c with pp;

synthesized attribute pp::String;

concrete production root
r::Root_c ::= t::IntLit_t
{
	r.pp = "Root: " ++ t.lexeme;
}
