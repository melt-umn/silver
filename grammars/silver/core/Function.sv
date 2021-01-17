grammar silver:core;

function id
a ::= x::a
{ return x; }

function compose
(c ::= a) ::= f1::(c ::= b) f2::(b ::= a)
{
  return \ x::a -> f1(f2(x));
}
