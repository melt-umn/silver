grammar silver:core;

synthesized attribute fromLeft<a> :: a;
synthesized attribute fromRight<a> :: a;
synthesized attribute isLeft :: Boolean;
synthesized attribute isRight :: Boolean;

{--
 - The basic sum type, counterpart to Pair.
 -
 - Occasionally used as a poor-quality "result or error" type.
 - By convention, the error type is the FIRST type, and the 
 - expected return value is the second.
 - e.g. Either<String Tree>
 -}
nonterminal Either<a b> with fromLeft<a>, fromRight<b>, isLeft, isRight;

abstract production left
top::Either<a b> ::= value::a
{
  top.fromLeft = value;
  top.fromRight = error("fromRight accessed on a Either that was actually left!");
  top.isLeft = true;
  top.isRight = false;
}

abstract production right
top::Either<a b> ::= value::b
{
  top.fromLeft = error("fromRight accessed on a Either that was actually left!");
  top.fromRight = value;
  top.isLeft = false;
  top.isRight = true;
}

instance Functor Either<a _> {
  map = \ f::(c ::= b) e::Either<a b> ->
    case e of
    | right(x) -> right(f(x))
    | left(x)  -> left(x)
    end;
}

instance Apply Either<a _> {
  ap = \ ef::Either<a (c ::= b)> e::Either<a b> ->
    case ef of
    | left(x)  -> left(x)
    | right(f) -> map(f, e)
    end;
}

instance Applicative Either<a _> {
  pure = right;
}

instance Bind Either<a _> {
  bind = \ e::Either<a b> fn::(Either<a c> ::= b) ->
    case e of
    | left(x) -> left(x)
    | right(x) -> fn(x)
    end;
}

instance Monad Either<a _> {}

instance MonadFail Either<String _> {
  fail = left;
}

instance Alt Either<a _> {
  alt = \ e1::Either<a b> e2::Either<a b> ->
    case e1, e2 of
    | right(x), _ -> right(x)
    | _, right(x) -> right(x)
    -- If they're both left, arbitrarily take the first one
    | _, _ -> e1
    end;
}

{--
 - Order preserving partitioning of a list of eithers into a pair
 - of lists of the two different results.
 -}
function partitionEithers
Pair<[a] [b]> ::= l::[Either<a b>]
{
  local recurse :: Pair<[a] [b]> = partitionEithers(tail(l));
  
  return case l of
  | [] -> pair([], [])
  | left(a) :: _ -> pair(a :: recurse.fst, recurse.snd)
  | right(b) :: _ -> pair(recurse.fst, b :: recurse.snd)
  end;
}

{--
 - Returns the left value, or the default if there is no left value.
 -}
function fromLeft
a ::= e::Either<a b> o::a
{
  return case e of
  | left(a) -> a
  | right(_) -> o
  end;
}

{--
 - Returns the right value, or the default if there is no right value.
 -}
function fromRight
b ::= e::Either<a b> o::b
{
  return case e of
  | left(_) -> o
  | right(b) -> b
  end;
}

