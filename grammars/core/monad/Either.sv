grammar core:monad;

-- Here we treat left as 'failure' and right as 'success'

function bindEither
Either<a c> ::= m::Either<a b> fn::(Either<a c> ::= b)
{
  return case m of
    left(x) -> left(x)
  | right(x) -> fn(x)
  end;
}

function returnEither
Either<a b> ::= x::b
{
  return right(x);
}
