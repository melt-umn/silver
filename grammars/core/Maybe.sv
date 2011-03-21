grammar core;

synthesized attribute fromJust<a> :: a;
synthesized attribute isJust :: Boolean;

nonterminal Maybe<a> with fromJust<a>, isJust;

abstract production just
top::Maybe<a> ::= v::a
{
  top.fromJust = v;
  top.isJust = true;
}

abstract production nothing
top::Maybe<a> ::=
{
  top.fromJust = error("fromJust accessed on a Maybe that was actually nothing!");
  top.isJust = false;
}

--------------------------------------------------------------------------------

function fromMaybe
a ::= otherwise::a ifJust::Maybe<a>
{
  return if ifJust.isJust
         then ifJust.fromJust
         else otherwise;
}

function orElse
Maybe<a> ::= f::Maybe<a> s::Maybe<a>
{
  return if f.isJust
         then f
         else s;
}
