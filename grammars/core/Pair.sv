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

function lookupBy
Maybe<b> ::= eqf::Function(Boolean ::= a a) elem::a lst::[Pair<a b>]
{
  return if null(lst)
         then nothing()
         else if eqf(elem, head(lst).fst)
              then just(head(lst).snd)
              else lookupBy(eqf, elem, tail(lst));
}

