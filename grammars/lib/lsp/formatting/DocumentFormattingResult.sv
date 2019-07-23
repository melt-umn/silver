nonterminal DocumentFormattingResult with jsonValue;

abstract production nullDocumentFormattingResult
top::DocumentFormattingResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production documentFormattingResultTextEditList
top::DocumentFormattingResult ::= result::[TextEdit]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function documentFormattingResultToJson
JSONValue ::= val::DocumentFormattingResult
{
  return val.jsonValue;
}
