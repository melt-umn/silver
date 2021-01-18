grammar silver:core;

class Semigroup a => Monoid a {
  empty :: a;
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
 - Concatenates a list of monoids.
 -
 - @param lst  A list containing monoids
 - @return  A flattened monoid
 -}
function concat
Monoid m => m ::= lst::[m]
{
  return foldr(append, empty, lst);
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
