nonterminal DocumentOnTypeFormattingResult with jsonValue;

abstract production nullDocumentOnTypeFormattingResult
top::DocumentOnTypeFormattingResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production documentOnTypeFormattingResultTextEditList
top::DocumentOnTypeFormattingResult ::= result::[TextEdit]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function documentOnTypeFormattingResultToJson
JSONValue ::= val::DocumentOnTypeFormattingResult
{
  return val.jsonValue;
}
