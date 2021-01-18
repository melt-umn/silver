grammar silver:core;

class Semigroup a {
  append :: (a ::= a a);
}

instance Semigroup [a] {
  append = appendList;
}

instance Semigroup String {
  append = stringAppend;
}

instance Semigroup a => Semigroup Maybe<a> {
  append = \ x::Maybe<a> y::Maybe<a> ->
    case x, y of
    | just(a), just(b) -> just(append(a, b))
    | just(_), nothing() -> x
    | nothing(), _ -> y
    end;
}

instance Semigroup Unit {
  append = \ Unit Unit -> unit();
}
