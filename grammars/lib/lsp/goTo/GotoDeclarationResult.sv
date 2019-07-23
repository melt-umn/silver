nonterminal GotoDeclarationResult with jsonValue;

abstract production nullGotoDeclarationResult
top::GotoDeclarationResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production gotoDeclarationResultLocation
top::GotoDeclarationResult ::= result::Location
{
  top.jsonValue = result.jsonValue;
}

abstract production gotoDeclarationResultLocationList
top::GotoDeclarationResult ::= result::[Location]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

abstract production gotoDeclarationResultLocationLinkList
top::GotoDeclarationResult ::= result::[LocationLink]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function gotoDeclarationResultToJson
JSONValue ::= val::GotoDeclarationResult
{
  return val.jsonValue;
}
