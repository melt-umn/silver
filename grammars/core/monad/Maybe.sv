grammar core:monad;

function bindMaybe
Maybe<b> ::= m::Maybe<a> fn::(Maybe<b> ::= a)
{
  return case m of
    just(x) -> fn(x)
  | nothing() -> nothing()
  end;
}

--global returnMaybe::(Maybe<a> ::= a) = just; -- TODO: Why doesn't this work?
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
