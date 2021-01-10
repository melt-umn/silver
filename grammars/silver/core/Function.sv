grammar silver:core;

function id
a ::= x::a
{ return x; }

function compose
(c ::= a) ::= f1::(b ::= a) f2::(c ::= b)
{
  return \ x::a -> f2(f1(x));
}
