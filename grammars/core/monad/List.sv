grammar core:monad;

function bindList
[b] ::= l::[a] fn::([b] ::= a)
{
  return flatMap(fn, l);
}

function returnList
[a] ::= x::a
{
  return [x];
}
