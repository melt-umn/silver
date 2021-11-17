grammar silver:core;

@{- Provides the `/` and `%` operators.
  -
  - Laws:
  -
  - * Integral Domain (TODO: are these equivalent?):
  -   * `oneSR != zero`
  -   * `(a != zero && b != zero) -> mul(a, b) != zero`
  - * Non-negativity of `degree`: `degree(x) >= 0`
  - * Quotient: `(b != zero) -> (add(mul(div(a, b), b), mod(a, b)) = a)`
  - * `degree`-`mod` interaction: `(b != zero) -> (mod(a, b) = 0 || degree(mod(a, b)) < degree(b))`
  - * `degree`-`mul` interaction: `(a != zero && b != zero) -> (degree(a) <= degree(mul(a, b)))`
  -}
class CommutativeRing a => EuclideanRing a {
  degree :: (Integer ::= a);
  div :: (a ::= a a);
  mod :: (a ::= a a);
}

@{- Computes the greatest common divisor of two numbers. -}
function gcd
Eq a, EuclideanRing a => a ::= a::a  b::a
{
  return
    if b == zero then
      a
    else
      gcd(b, mod(a, b));
}

@{- Computes the least common multiple of two numbers. -}
function lcm
Eq a, EuclideanRing a => a ::= a::a  b::a
{
  return
    if a == zero || b == zero then
      zero
    else
      div(mul(a, b), gcd(a, b));
}
