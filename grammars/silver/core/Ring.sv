grammar silver:core;

@{- This is the typeclass that provides the unary and binary `-` operators.
  -
  - Laws:
  -
  - * Left Inverse: `sub(x, x) = zero`
  - * Right Inverse: `add(sub(zero, x), x) = zero`
  -}
class Semiring a => Ring a {
  @{- The function corresponding to the binary `-` operator. -}
  sub :: (a ::= a a);
}

instance Ring Integer {
  sub = \a::Integer b::Integer -> a - b;
}

@{- The function corresponding to the unary `-` operator. -}
function negate
Ring a => a ::= x::a
{ return sub(zero, x); }

@{- Converts an integer into an arbitrary ring. -}
function fromInteger
Ring a => a ::= n::Integer
{
  return
    if n < 0 then
      negate(fromNonnegativeInteger(-n))
    else
      fromNonnegativeInteger(n);
}
