nonterminal GotoTypeDefinitionResult with jsonValue;

abstract production nullGotoTypeDefinitionResult
top::GotoTypeDefinitionResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production gotoTypeDefinitionResultLocation
top::GotoTypeDefinitionResult ::= result::Location
{
  top.jsonValue = result.jsonValue;
}

abstract production gotoTypeDefinitionResultLocationList
top::GotoTypeDefinitionResult ::= result::[Location]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

abstract production gotoTypeDefinitionResultLocationLinkList
top::GotoTypeDefinitionResult ::= result::[LocationLink]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function gotoTypeDefinitionResultToJson
JSONValue ::= val::GotoTypeDefinitionResult
{
  return val.jsonValue;
}
