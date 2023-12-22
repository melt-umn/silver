grammar silver:core;

fun id a ::= x::a = x;

fun compose (c ::= a) ::= f1::(c ::= b) f2::(b ::= a) = \ x::a -> f1(f2(x));

fun curry ((c ::= b) ::= a) ::= f::(c ::= a b) = \x::a -> \y::b -> f(x, y);

fun curry3 (((d ::= c) ::= b) ::= a) ::= f::(d ::= a b c) = \x::a -> \y::b -> \z::c -> f(x, y, z);

fun curry4 ((((e ::= d) ::= c) ::= b) ::= a) ::= f::(e ::= a b c d) =
  \x::a -> \y::b -> \z::c -> \p::d -> f(x, y, z, p);

fun curry5 (((((f ::= e) ::= d) ::= c) ::= b) ::= a) ::= f::(f ::= a b c d e) =
  \x::a -> \y::b -> \z::c -> \p::d -> \q::e -> f(x, y, z, p, q);

fun uncurry c ::= f::((c ::= b) ::= a)  x::a  y::b = f(x)(y);

fun uncurry3 d ::= f::(((d ::= c) ::= b) ::= a)  x::a  y::b  z::c = f(x)(y)(z);

fun uncurry4 e ::= f::((((e ::= d) ::= c) ::= b) ::= a)  x::a  y::b  z::c  p::d = f(x)(y)(z)(p);

fun uncurry5 f ::= f::(((((f ::= e) ::= d) ::= c) ::= b) ::= a)  x::a  y::b  z::c  p::d  q::e =
  f(x)(y)(z)(p)(q);
