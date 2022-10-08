grammar silver:core;

@{- This is the typeclass that provides the unary and binary `-` operators.
  -
  - Laws:
  -
  - * Left Inverse: `sub(x, x) = zero`
  - * Right Inverse: `add(sub(zero, x), x) = zero`
  - * Negate: `neg(x) = sub(zero, x)`
  -}
class Semiring a => Ring a {
  @{- The function corresponding to the binary `-` operator. -}
  sub :: (a ::= a a);

  @{- The function corresponding to the unary `-` operator. -}
  negate :: (a ::= a) = \x::a -> sub(zero, x);
}

instance Ring Integer {
  sub = \a::Integer b::Integer -> a - b;
}

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
