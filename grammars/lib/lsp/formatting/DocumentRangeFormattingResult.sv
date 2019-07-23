nonterminal DocumentRangeFormattingResult with jsonValue;

abstract production nullDocumentRangeFormattingResult
top::DocumentRangeFormattingResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production documentRangeFormattingResultTextEditList
top::DocumentRangeFormattingResult ::= result::[TextEdit]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function documentRangeFormattingResultToJson
JSONValue ::= val::DocumentRangeFormattingResult
{
  return val.jsonValue;
}
