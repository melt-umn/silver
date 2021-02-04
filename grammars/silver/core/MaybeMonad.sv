grammar silver:core;

function bindMaybe
Maybe<b> ::= m::Maybe<a> fn::(Maybe<b> ::= a)
{
  return case m of
    just(x) -> fn(x)
  | nothing() -> nothing()
  end;
}

function returnMaybe
Maybe<a> ::= x::a
{
  return just(x);
}

function failMaybe
Maybe<a> ::= x::b
{
  return nothing();
}

function mplusMaybe
Maybe<a> ::= m1::Maybe<a> m2::Maybe<a>
{
  return case m1, m2 of
         | just(x), _ -> just(x)
         | _, _ -> m2
         end;
}
