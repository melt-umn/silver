grammar core:monad;

function bindList
[b] ::= l::[a] fn::([b] ::= a)
{
  return foldr(append, [], map(fn, l));
}

function returnList
[a] ::= x::a
{
  return [x];
}