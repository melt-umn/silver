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

