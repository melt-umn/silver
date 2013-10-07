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

{--
 - Maybe cons a value to a list, or not.
 -
 - @param h  If a value, the value to cons onto the list.
 - @param t  The list to amend, if there's a value
 - @return  The list, possibly with a new value at its head.
 -}
function consMaybe
[a] ::= h::Maybe<a>  t::[a]
{
  return if h.isJust then h.fromJust :: t else t;
}

{--
 - Turn a list of possible values into a list of values, skipping over
 - any 'nothing's.
 -
 - @param l  A list of optional values
 - @return  The list with all absent values removed, and present values unwrapped.
 -}
function catMaybes
[a] ::= l::[Maybe<a>]
{
  return foldr(consMaybe, [], l);
}

