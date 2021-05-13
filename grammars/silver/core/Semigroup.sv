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

instance Semigroup a, Semigroup b => Semigroup (a, b) {
  append = \x::(a, b) y::(a, b) -> (append(x.1, y.1), append(x.2, y.2));
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
  append = \ x::Unit  y::Unit ->
    case x, y of
    | unit(), unit() -> unit()
    end;
}
