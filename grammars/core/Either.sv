grammar core;

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

