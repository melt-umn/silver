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

function mapFlipped
Functor f => f<b> ::= x::f<a>  f::(b ::= a)
{ return map(f, x); }

function void
Functor f => f<Unit> ::= x::f<a>
{ return map(\y::a -> unit(), x); }

function voidLeft
Functor f => f<b> ::= x::f<a>  y::b
{ return map(\z::a -> y, x); }

function voidRight
Functor f => f<a> ::= x::a  y::f<b>
{ return map(\z::b -> x, y); }

function flap
Functor f => f<b> ::= fs::f<(b ::= a)>  x::a
{ return map(\f::(b ::= a) -> f(x), fs); }
