grammar silver:core;

class Functor f {
  map :: (f<b> ::= (b ::= a) f<a>); 
}

instance Functor [] {
  map = \ f::(b ::= a) l::[a] ->
    if null(l) then []
    else f(head(l)) :: map(f, tail(l));
}

instance Functor Maybe {
  map = \ f::(b ::= a) m::Maybe<a> ->
    case m of
    | just(x)   -> just(f(x))
    | nothing() -> nothing()
    end;
}

instance Functor Either<a _> {
  map = \ f::(c ::= b) e::Either<a b> ->
    case e of
    | right(x) -> right(f(x))
    | left(x)  -> left(x)
    end;
}
