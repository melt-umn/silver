grammar silver:core;

{-
The class of monoids (types with an associative binary operation that has an identity). Instances should satisfy the following:

Right identity
  append(x, empty) = x
Left identity
  append(empty, x) = x
Associativity
  append(x, append(y, z)) = append(append(x, y), z) (Semigroup law)
Concatenation
  concat = foldr(append, empty, _)

Minimal complete definition: empty
-}
class Semigroup a => Monoid a {
  empty :: a;
  concat :: (a ::= [a]) = foldr(append, empty, _);
}

instance Monoid [a] {
  empty = [];
}

instance Monoid String {
  empty = "";
}

instance Monoid a => Monoid Maybe<a> {
  empty = nothing();
}

instance Monoid Unit {
  empty = unit();
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
