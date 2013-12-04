grammar core;

{--
 - The basic sum type, counterpart to Pair.
 -
 - Occasionally used as a poor-quality "result or error" type.
 - By convention, the error type is the FIRST type, and the 
 - expected return value is the second.
 - e.g. Either<String Tree>
 -}
nonterminal Either<a b>;


abstract production left
top::Either<a b> ::= value::a
{
}

abstract production right
top::Either<a b> ::= value::b
{
}


