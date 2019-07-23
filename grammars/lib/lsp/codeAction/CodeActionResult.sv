nonterminal CodeActionResult with jsonValue;

abstract production nullCodeActionResult
top::CodeActionResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production codeActionResultCodeActionList
top::CodeActionResult ::= result::[CodeAction]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function codeActionResultToJson
JSONValue ::= val::CodeActionResult
{
  return val.jsonValue;
}
