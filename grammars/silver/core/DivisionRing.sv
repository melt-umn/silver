grammar silver:core;

@{- A typeclass for rings that have a reciprocal operation, which gives the
  - multiplicative inverse of non-zero elements.
  -
  - Laws:
  -
  - * Non-Zero Ring: `one != zero`
  - * Non-Zero Multiplicative Left Inverse: `(a != 0) -> mul(recip(a), a) = one`
  - * Non-Zero Multiplicative Right Inverse: `(a != 0) -> mul(a, recip(a)) = one`
  -}
class Ring a => DivisionRing a {
  @{- The reciprocal, i.e. the inverse for the multiplicative group. -}
  recip :: (a ::= a);
}

instance DivisionRing Float {
  recip = \ a -> 1.0 / a;
}

@{- Division implemented as `(1/r) * l`.
  -
  - Iff the type is a commutative ring, this is equivalent to `rightDiv`.
  -}
fun leftDiv DivisionRing a => a ::= l::a  r::a = mul(recip(r), l);

@{- Division implemented as `l * (1/r)`.
  -
  - Iff the type is a commutative ring, this is equivalent to `leftDiv`.
  -}
fun rightDiv DivisionRing a => a ::= l::a  r::a = mul(l, recip(r));
