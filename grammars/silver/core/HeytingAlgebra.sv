grammar silver:core;

@{- This is the typeclass that provides the `true` and `false` constants, as
  - well as the `&&`, `||`, and `!` operators.
  -
  - Laws:
  -
  - * Associativity:
  -   * `conj(x, conj(y, z)) = conj(conj(x, y), z)`
  -   * `disj(x, disj(y, z)) = disj(disj(x, y), z)`
  - * Commutativity:
  -   * `conj(x, y) = conj(y, x)`
  -   * `disj(x, y) = disj(y, x)`
  - * Absorption:
  -   * `conj(x, disj(x, y)) = x`
  -   * `disj(x, conj(x, y)) = x`
  - * Idempotence:
  -   * `conj(x, x) = x`
  -   * `disj(x, x) = x`
  - * Identity:
  -   * `conj(x, true) = x`
  -   * `disj(x, false) = x`
  - * Implication:
  -   * `implies(x, x) = true`
  -   * `conj(x, implies(x, y)) = conj(x, y)`
  -   * `conj(y, implies(x, y)) = y`
  -   * `implies(x, conj(y, z)) = conj(implies(x, y), implies(x, z))`
  - * Complement: `notHA(x) = implies(x, false)`
  -}
class HeytingAlgebra a {
  -- TODO: Rename to false
  @{- The false constant. -}
  ff :: a;
  -- TODO: Rename to true
  @{- The true constant. -}
  tt :: a;
  @{- Implication.
    -
    - Note that it is not the case that `implies(x, y) = disj(notHA(p), q)` for
    - all Heyting algebras. If you require this property, you want a
    - `BooleanAlgebra`.
    -}
  implies :: (a ::= a a);
  @{- Conjunction. This function corresponds to the `&&` operator. -}
  conj :: (a ::= a a);
  @{- Disjunction. This function corresponds to the `||` operator. -}
  disj :: (a ::= a a);
  @{- Complement. This function corresponds to the `!` operator.
    -
    - Note that it is not the case that `notHA(notHA(x)) = x` for all Heyting
    - algebras. If you require this property, you want a `BooleanAlgebra`.
    -}
  notHA :: (a ::= a);
}
