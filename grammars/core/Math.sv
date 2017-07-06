grammar core;

{--
 - Returns the maximum of two numbers.
 -}
function max
Integer ::= l::Integer r::Integer
{
  return if l < r then r else l;
}

{--
 - Returns the minimum of two numbers.
 -}
function min
Integer ::= l::Integer r::Integer
{
  return if l < r then l else r;
}

