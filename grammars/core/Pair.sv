grammar core;

synthesized attribute fst<a> :: a;
synthesized attribute snd<a> :: a;

{--
 - The basic product type, counterpart to Either.
 -}
nonterminal Pair<a b> with fst<a>, snd<b>;

abstract production pair
top::Pair<a b> ::= f::a  s::b
{
  top.fst = f;
  top.snd = s;
}

function fst
a ::= p::Pair<a b>
{ return p.fst; }

function snd
b ::= p::Pair<a b>
{ return p.snd; }

{--
 - Look up an element in an association list, using the specified equality
 - function.
 -
 - @param eqf  The function to use to test for equality
 - @param elem   The element to look up
 - @param lst  The list of assocation pairs
 - @return  The first association pair found in the list, where the element
 -   equaled the first element of the pair.
 -}
function lookupBy
Maybe<b> ::= eqf::(Boolean ::= a a)  elem::a  lst::[Pair<a b>]
{
  return if null(lst)
         then nothing()
         else if eqf(elem, head(lst).fst)
              then just(head(lst).snd)
              else lookupBy(eqf, elem, tail(lst));
}

function lookupAllBy
[b] ::= eqf::(Boolean ::= a a)  elem::a  lst::[Pair<a b>]
{
  return if null(lst)
         then []
         else if eqf(elem, head(lst).fst)
              then head(lst).snd :: lookupAllBy(eqf, elem, tail(lst))
              else lookupAllBy(eqf, elem, tail(lst));
}

{--
 - Decomposes a list of pairs into a pair of lists.
 -
 - unzipPairs(zipWith(pair, lst)) == lst
 -
 - @param lst  A list to decompose into two lists.
 - @return 
 -}
function unzipPairs
Pair<[a] [b]> ::= lst::[Pair<a b>]
{
  local recurse :: Pair<[a] [b]> = unzipPairs(tail(lst));
  
  return if null(lst) then pair([], [])
  else pair(head(lst).fst :: recurse.fst, head(lst).snd :: recurse.snd);
}


