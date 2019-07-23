nonterminal PrepareRenameResult with jsonValue;

abstract production nullPrepareRenameResult
top::PrepareRenameResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production prepareRenameResultRange
top::PrepareRenameResult ::= result::Range
{
  top.jsonValue = result.jsonValue;
}

function prepareRenameResultToJson
JSONValue ::= val::PrepareRenameResult
{
  return val.jsonValue;
}
