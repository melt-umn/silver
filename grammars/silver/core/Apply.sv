grammar silver:core;

@{-
Functors with an apply operation, that can be used to lift functions of two or more arguments to
work on values wrapped in the type constructor f.

Instances should satisfy the following:

Associative Composition
  ap(ap(map(compose, f), g), h) = ap(f, ap(g, h))
-}
class Functor f => Apply f {
  ap :: (f<b> ::= f<(b ::= a)> f<a>);
}

function applyFirst
Apply f => f<a> ::= a::f<a>  b::f<b>
{ return ap(map(\x::a -> \y::b -> x, a), b); }

function applySecond
Apply f => f<b> ::= a::f<a>  b::f<b>
{ return ap(map(\x::a -> \y::b -> y, a), b); }

function lift2
Apply f => f<c> ::= f::(c ::= a b)  x::f<a>  y::f<b>
{ return ap(map(curry(f), x), y); }

function lift3
Apply f => f<d> ::= f::(d ::= a b c)  x::f<a>  y::f<b>  z::f<c>
{ return ap(ap(map(curry3(f), x), y), z); }

function lift4
Apply f => f<e> ::= f::(e ::= a b c d)  x::f<a>  y::f<b>  z::f<c>  p::f<d>
{ return ap(ap(ap(map(curry4(f), x), y), z), p); }

function lift5
Apply f => f<g> ::= f::(g ::= a b c d e)  x::f<a>  y::f<b>  z::f<c>  p::f<d>  q::f<e>
{ return ap(ap(ap(ap(map(curry5(f), x), y), z), p), q); }
