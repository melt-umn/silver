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

function failList
[a] ::= x::b
{
  return [];
}

function mplusList
[a] ::= l1::[a] l2::[a]
{
  return l1 ++ l2;
}
