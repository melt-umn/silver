nonterminal ExecuteCommandResult with jsonValue;

abstract production nullExecuteCommandResult
top::ExecuteCommandResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production executeCommandResultJSONValue
top::ExecuteCommandResult ::= result::JSONValue
{
  top.jsonValue = result;
}

function executeCommandResultToJson
JSONValue ::= val::ExecuteCommandResult
{
  return val.jsonValue;
}
