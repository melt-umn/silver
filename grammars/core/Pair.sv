grammar core;

synthesized attribute fst<a> :: a;
synthesized attribute snd<a> :: a;

nonterminal Pair<a b> with fst<a>, snd<b>;

abstract production pair
top::Pair<a b> ::= f::a  s::b
{
  top.fst = f;
  top.snd = s;
}

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

