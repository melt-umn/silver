grammar core;

{--
 - The basic sum type, counterpart to Pair.
 -
 - Occasionally used as a poor-quality "result or error" type.
 - By convention, the error type is the FIRST type, and the 
 - expected return value is the second.
 - e.g. Either<String Tree>
 -}
nonterminal Either<a b>;


abstract production left
top::Either<a b> ::= value::a
{
}

abstract production right
top::Either<a b> ::= value::b
{
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

