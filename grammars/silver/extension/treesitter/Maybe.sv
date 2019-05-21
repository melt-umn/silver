
function fstFromMaybe
Maybe<a> ::= p::Maybe<Pair<a b>>
{
  return if p.isJust then
    just(fst(p.fromJust))
  else
    nothing();
}

function sndFromMaybe
Maybe<b> ::= p::Maybe<Pair<a b>>
{
  return if p.isJust then
    just(snd(p.fromJust))
  else
    nothing();
}
