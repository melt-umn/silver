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

function failEither
Either<a b> ::= x::a
{
  return left(x);
}

function mplusEither
Either<a b> ::= e1::Either<a b> e2::Either<a b>
{
  return case e1, e2 of
         | right(x), _ -> right(x)
         | _, right(x) -> right(x)
         --if they're both left, arbitrarily take the first one
         | _, _ -> e1
         end;
}
