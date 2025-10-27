grammar silver:core;

@{-
A type f is a Functor if it provides a function map which, given any types a and b lets you apply
any function from (a -> b) to turn an f<a> into an f<b>, preserving the structure of f. 
Furthermore f needs to adhere to the following:

Identity
  map(id, x) == x
Composition
  map(compose(f, g), x) == map(f, map(g, x))
-}
class Functor f {
  map :: (f<b> ::= (b ::= a) f<a>); 
}

fun mapFlipped Functor f => f<b> ::= x::f<a>  f::(b ::= a) = map(f, x);

fun void Functor f => f<Unit> ::= x::f<a> = map(\y::a -> unit(), x);

fun voidLeft Functor f => f<b> ::= x::f<a>  y::b = map(\z::a -> y, x);

fun voidRight Functor f => f<a> ::= x::a  y::f<b> = map(\z::b -> x, y);

fun flap Functor f => f<b> ::= fs::f<(b ::= a)>  x::a = map(\f::(b ::= a) -> f(x), fs);
