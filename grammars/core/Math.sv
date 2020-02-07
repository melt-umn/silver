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

{--
 - Returns the greatest common divisor of two numbers.
 -}
function gcd
Integer ::= l::Integer r::Integer
{
  return if r == 0 then l else gcd(r, l % r);
}

