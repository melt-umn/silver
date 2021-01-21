grammar silver:core;

@{- This typeclass guarantees that a Heyting algebra behaves exactly like the
  - booleans. It doesn't provide any operators, but may be useful for algorithms.
  -
  - Laws:
  -
  - * Excluded Middle: `disj(x, not(x)) = true`
  -}
class HeytingAlgebra a => BooleanAlgebra a {}
