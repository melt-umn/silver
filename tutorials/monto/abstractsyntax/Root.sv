grammar monto:abstractsyntax;

{- Definition of Root. -}

nonterminal Root with value, errors;

abstract production root
r::Root ::= e::Expr
{
 r.value = e.value;
 r.errors = e.errors;
}
