grammar silver:core;

{-
The class of semigroups (types with an associative binary operation).

Instances should satisfy the following:

Associativity
  append(x, append(y, z)) = append(append(x, y), z)
-}
class Semigroup a {
  append :: (a ::= a a);
}

-- e.g. []
instance Alt m => Semigroup m<a> {
  append = alt;
}

instance Semigroup String {
  append = stringAppend;
}

instance Semigroup a => Semigroup Maybe<a> {
  append = \ x::Maybe<a> y::Maybe<a> ->
    case x, y of
    | just(a), just(b) -> just(a ++ b)
    | just(_), nothing() -> x
    | nothing(), _ -> y
    end;
}

instance Semigroup Unit {
  append = \ Unit Unit -> unit();
}
