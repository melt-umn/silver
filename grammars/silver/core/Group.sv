grammar silver:core;

@{- They're from math. Yeah. See [Wikipedia](https://en.wikipedia.org/wiki/Group_(mathematics)).
  -
  - Laws:
  -
  - * Left Cancellation: `append(invert(x), x) = mempty`
  - * Right Cancellation: `append(x, invert(x)) = mempty`
  -}
class Monoid g => Group g {
  invert :: (g ::= g);
}

@{- Computes the integer exponent of some group (treating the semigroup as
  - multiplication, as is standard in algebra).
  -}
fun power Group g => g ::= x::g  n::Integer =
  if n < 0 then
    invert(powerHelper(x, -n))
  else
    powerHelper(x, n);

@{- @hide -}
function powerHelper
Group g => g ::= x::g  n::Integer
{
  local xPowN2 :: g = powerHelper(x, n / 2);
  return
    if n == 0 then
      mempty
    else if n % 2 == 0 then
      append(xPowN2, xPowN2)
    else
      append(x, append(xPowN2, xPowN2));
}
