grammar silver:core;

@{- See [Wikipedia](https://en.wikipedia.org/wiki/Field_(mathematics)).
  -
  - All types that are both a `DivisionRing` and a `CommutativeRing` are
  - automatically `Field`s: the `EuclideanRing` instance can choose `leftDiv`
  - or `rightDiv` as its `div`, `const 0` as its `mod`, and `const 1` as its
  - `degree`. (TODO: This and `EuclideanRing` should be derivable based on that.)
  -
  - Laws:
  -
  - * Non-Zero Multiplicative Inverse: `mod(a, b) = zero`
  -}
class DivisionRing a, EuclideanRing a => Field a {}

instance Field Integer {}
instance Field Float {}