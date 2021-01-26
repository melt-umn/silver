grammar silver:core;

function id
a ::= x::a
{ return x; }

function compose
(c ::= a) ::= f1::(c ::= b) f2::(b ::= a)
{
  return \ x::a -> f1(f2(x));
}

function curry
((c ::= b) ::= a) ::= f::(c ::= a b)
{ return \x::a -> \y::b -> f(x, y); }

function curry3
(((d ::= c) ::= b) ::= a) ::= f::(d ::= a b c)
{ return \x::a -> \y::b -> \z::c -> f(x, y, z); }

function curry4
((((e ::= d) ::= c) ::= b) ::= a) ::= f::(e ::= a b c d)
{ return \x::a -> \y::b -> \z::c -> \p::d -> f(x, y, z, p); }

function curry5
(((((f ::= e) ::= d) ::= c) ::= b) ::= a) ::= f::(f ::= a b c d e)
{ return \x::a -> \y::b -> \z::c -> \p::d -> \q::e -> f(x, y, z, p, q); }

function uncurry
c ::= f::((c ::= b) ::= a)  x::a  y::b
{ return f(x)(y); }

function uncurry3
d ::= f::(((d ::= c) ::= b) ::= a)  x::a  y::b  z::c
{ return f(x)(y)(z); }

function uncurry4
e ::= f::((((e ::= d) ::= c) ::= b) ::= a)  x::a  y::b  z::c  p::d
{ return f(x)(y)(z)(p); }

function uncurry5
f ::= f::(((((f ::= e) ::= d) ::= c) ::= b) ::= a)  x::a  y::b  z::c  p::d  q::e
{ return f(x)(y)(z)(p)(q); }
