grammar silver:core;

annotation fst<a> :: a;
annotation snd<a> :: a;

@{--
 - The basic product type, counterpart to Either.
 -}
data Pair<a b> = pair
  with fst<a>, snd<b>;
derive Eq, Ord on Pair;

global fst :: (a ::= Pair<a b>) = (.fst);
global snd :: (b ::= Pair<a b>) = (.snd);

@{--
 - Look up an element in an association list, using the specified equality
 - function.
 -
 - @param eqf  The function to use to test for equality
 - @param elem   The element to look up
 - @param lst  The list of assocation pairs
 - @return  The first association pair found in the list, where the element
 -   equaled the first element of the pair.
 -}
fun lookupBy Maybe<b> ::= eqf::(Boolean ::= a a)  elem::a  lst::[Pair<a b>] =
  if null(lst)
  then nothing()
  else if eqf(elem, head(lst).fst)
       then just(head(lst).snd)
       else lookupBy(eqf, elem, tail(lst));

@{--
 - Look up an element in an association list, using ==.
 -
 - @param elem   The element to look up
 - @param lst  The list of assocation pairs
 - @return  The first association pair found in the list, where the element
 -   equaled the first element of the pair.
 -}
fun lookup Eq a => Maybe<b> ::= elem::a  lst::[Pair<a b>] = lookupBy(eq, elem, lst);

fun lookupAllBy [b] ::= eqf::(Boolean ::= a a)  elem::a  lst::[Pair<a b>] =
  if null(lst)
  then []
  else if eqf(elem, head(lst).fst)
       then head(lst).snd :: lookupAllBy(eqf, elem, tail(lst))
       else lookupAllBy(eqf, elem, tail(lst));

fun lookupAll Eq a => [b] ::= elem::a  lst::[Pair<a b>] = lookupAllBy(eq, elem, lst);

@{--
 - Decomposes a list of pairs into a pair of lists.
 -
 - unzipPairs(zip(lst)) == lst
 -
 - @param lst  A list to decompose into two lists.
 -}
function unzipPairs
Pair<[a] [b]> ::= lst::[Pair<a b>]
{
  local recurse :: Pair<[a] [b]> = unzipPairs(tail(lst));
  
  return if null(lst) then ([], [])
  else (head(lst).fst :: recurse.fst, head(lst).snd :: recurse.snd);
}


