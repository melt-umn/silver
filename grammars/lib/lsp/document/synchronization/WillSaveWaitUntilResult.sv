nonterminal WillSaveWaitUntilResult with jsonValue;

abstract production nullWillSaveWaitUntilResult
top::WillSaveWaitUntilResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production willSaveWaitUntilResultTextEditList
top::WillSaveWaitUntilResult ::= result::[TextEdit]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function willSaveWaitUntilResultToJson
JSONValue ::= val::WillSaveWaitUntilResult
{
  return val.jsonValue;
}
