grammar silver:core;

{-
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

instance Monoid [a] {
  mempty = [];
}

instance Monoid String {
  mempty = "";
}

instance Monoid a => Monoid Maybe<a> {
  mempty = nothing();
}

instance Monoid Unit {
  mempty = unit();
}

{--
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
