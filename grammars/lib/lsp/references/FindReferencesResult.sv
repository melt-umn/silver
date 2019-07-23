nonterminal FindReferencesResult with jsonValue;

abstract production nullFindReferencesResult
top::FindReferencesResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production findReferencesResultLocationList
top::FindReferencesResult ::= result::[Location]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function findReferencesResultToJson
JSONValue ::= val::FindReferencesResult
{
  return val.jsonValue;
}
