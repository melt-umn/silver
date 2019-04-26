
function getJustFromSnd
Pair<a b> ::= p::Pair<a Maybe<b>>
{
  return pair(p.fst, p.snd.fromJust);
}

function lookupKeyHasValue
Boolean ::= keyValPair::Pair<a Maybe<b>>
{
  return snd(keyValPair).isJust;
}
