grammar tutorials:expr:abstractsyntax ;

import tutorials:expr:terminals ;

aspect production tuple
e::Expr ::= l::Expr r::Expr
{
 e.value = error ("value not computed on tuple types");
}

aspect production fst
e::Expr ::= f::Fst_t t::Expr
{
 e.value = error ("value not computed on tuple types");
}

aspect production snd
e::Expr ::= s::Snd_t t::Expr
{
 e.value = error ("value not computed on tuple types");
}
