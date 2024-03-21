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

fun applyFirst Apply f => f<a> ::= a::f<a>  b::f<b> = ap(map(\x::a -> \y::b -> x, a), b);

fun applySecond Apply f => f<b> ::= a::f<a>  b::f<b> = ap(map(\x::a -> \y::b -> y, a), b);

fun lift2 Apply f => f<c> ::= f::(c ::= a b)  x::f<a>  y::f<b> = ap(map(curry(f), x), y);

fun lift3 Apply f => f<d> ::= f::(d ::= a b c)  x::f<a>  y::f<b>  z::f<c> =
  ap(ap(map(curry3(f), x), y), z);

fun lift4 Apply f => f<e> ::= f::(e ::= a b c d)  x::f<a>  y::f<b>  z::f<c>  p::f<d> =
  ap(ap(ap(map(curry4(f), x), y), z), p);

fun lift5 Apply f => f<g> ::= f::(g ::= a b c d e)  x::f<a>  y::f<b>  z::f<c>  p::f<d>  q::f<e> =
  ap(ap(ap(ap(map(curry5(f), x), y), z), p), q);
