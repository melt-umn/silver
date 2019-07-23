nonterminal GotoDefinitionResult with jsonValue;

abstract production nullGotoDefinitionResult
top::GotoDefinitionResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production gotoDefinitionResultLocation
top::GotoDefinitionResult ::= result::Location
{
  top.jsonValue = result.jsonValue;
}

abstract production gotoDefinitionResultLocationList
top::GotoDefinitionResult ::= result::[Location]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

abstract production gotoDefinitionResultLocationLinkList
top::GotoDefinitionResult ::= result::[LocationLink]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function gotoDefinitionResultToJson
JSONValue ::= val::GotoDefinitionResult
{
  return val.jsonValue;
}
