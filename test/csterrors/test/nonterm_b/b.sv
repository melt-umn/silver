grammar test:nonterm_b;

nonterminal B;

terminal Mangle_t 'nonterm_b_mangle';

concrete production nonterm_b_cnc
top::B ::= Mangle_t
{}