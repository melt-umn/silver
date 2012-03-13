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

{--
 - The corresponding fold for Maybes.
 -
 - @param otherwise  The element to return if 'ifJust' is 'nothing'
 - @param ifJust  The maybe value to scrutinize
 - @return  Either the contents of the Maybe (if 'just'), or the otherwise element.
 -}
function fromMaybe
a ::= otherwise::a ifJust::Maybe<a>
{
  return if ifJust.isJust then ifJust.fromJust else otherwise;
}

{--
 - Selects the first existing element, favoring the left.
 -
 - @param l  The first element
 - @param r  The second element
 - @return  A wrapped element, if any, favoring 'l'
 -}
function orElse
Maybe<a> ::= l::Maybe<a> r::Maybe<a>
{
  return if l.isJust then l else r;
}
