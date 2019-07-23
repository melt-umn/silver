nonterminal DocumentHighlightResult with jsonValue;

abstract production nullDocumentHighlightResult
top::DocumentHighlightResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production documentHighlightResultDocumentHighlightList
top::DocumentHighlightResult ::= result::[DocumentHighlight]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function documentHighlightResultToJson
JSONValue ::= val::DocumentHighlightResult
{
  return val.jsonValue;
}
