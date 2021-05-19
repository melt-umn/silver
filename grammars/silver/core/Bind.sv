grammar silver:core;

@{-
Extends the Apply type class with a "bind" operation that composes computations in sequence,
using the return value of one computation to determine the next computation.

Instances should satisfy the following:

Associativity
  bind(bind(x, f), g) = bind(x, \k -> bind(f(k), g))
-}
class Apply m => Bind m {
  bind :: (m<b> ::= m<a> (m<b> ::= a));
}

function bindFlipped
Bind m => m<b> ::= f::(m<b> ::= a)  x::m<a>
{ return bind(x, f); }

function join
Bind m => m<a> ::= x::m<m<a>>
{ return bind(x, \x::m<a> -> x); }

function composeKleisli
Bind m => m<c> ::= f::(m<b> ::= a)  g::(m<c> ::= b)  x::a
{ return bind(f(x), g); }

function composeKleisliFlipped
Bind m => m<c> ::= g::(m<c> ::= b)  f::(m<b> ::= a)  x::a
{ return composeKleisli(f, g, x); }

function ifM
Bind m => m<a> ::= c::m<Boolean>  t::m<a>  e::m<a>
{ return bind(c, \c::Boolean -> if c then t else e); }
