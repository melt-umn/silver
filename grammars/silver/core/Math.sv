grammar silver:core;

{--
 - Returns the greatest common divisor of two numbers.
 -}
function gcd
Integer ::= l::Integer r::Integer
{
  return if r == 0 then l else gcd(r, l % r);
}

