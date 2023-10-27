grammar silver:core;

@{- A typeclass for types where multiplication is guaranteed to be commutative.
  -
  - Laws:
  -
  - * Commutativity of Multiplication: `mul(x, y) = mul(y, x)`
  -}
class Ring a => CommutativeRing a {}

instance CommutativeRing Integer {}
instance CommutativeRing Float {}
