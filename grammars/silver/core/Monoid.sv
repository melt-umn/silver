grammar silver:core;

@{-
The class of monoids (types with an associative binary operation that has an identity). Instances should satisfy the following:

Right identity
  append(x, mempty) = x
Left identity
  append(mempty, x) = x
Associativity
  append(x, append(y, z)) = append(append(x, y), z) (Semigroup law)
Concatenation
  concat = foldr(append, mempty, _)

Minimal complete definition: mempty
-}
class Semigroup a => Monoid a {
  mempty :: a;
  concat :: (a ::= [a]) = foldr(append, mempty, _);
}

-- e.g. [] and Maybe
instance Plus m => Monoid m<a> {
  mempty = empty;
}

instance Monoid a, Monoid b => Monoid (a, b) {
  mempty = (mempty, mempty);
}

instance Monoid String {
  mempty = "";
}

instance Monoid Unit {
  mempty = unit();
}

@{--
 - Map a function over a list, and then concatenates the results together.
 -
 - @param f  A function to apply to each element of a list, returning a monoid.
 - @param lst  A list
 - @return  The combined list
 -}
function flatMap
Monoid m => m ::= f::(m ::= a)  lst::[a]
{
  return concat(map(f, lst));
}

@{- Computes the integer exponent of some monoid (treating the semigroup as
  - multiplication, as is standard in algebra).
  -
  - Note that for negative `n`, this errors out. If your type is a `Group`, use
  - `power` instead.
  -}
function mpower
Monoid m => m ::= x::m  n::Integer
{
  return case power(mPowerHelper(x), n) of mPowerHelper(y) -> y end;
}

@{- @hide -}
nonterminal MPowerHelper<m>;

@{- @hide -}
abstract production mPowerHelper
top::MPowerHelper<m> ::= m
{}

@{- @hide -}
function mPowerHelperAppend
Semigroup m => MPowerHelper<m> ::= xH::MPowerHelper<m>  yH::MPowerHelper<m>
{
  return
    case xH, yH of
    | mPowerHelper(x), mPowerHelper(y) ->
        mPowerHelper(append(x, y))
    end;
}

instance Semigroup m => Semigroup MPowerHelper<m> { append = mPowerHelperAppend; }
instance Monoid m => Monoid MPowerHelper<m> { mempty = mPowerHelper(mempty); }
instance Monoid m => Group MPowerHelper<m> {
  invert = error("Cannot perform a negative mpower");
}
