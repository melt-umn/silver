nonterminal GotoImplementationResult with jsonValue;

abstract production nullGotoImplementationResult
top::GotoImplementationResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production gotoImplementationResultLocation
top::GotoImplementationResult ::= result::Location
{
  top.jsonValue = result.jsonValue;
}

abstract production gotoImplementationResultLocationList
top::GotoImplementationResult ::= result::[Location]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

abstract production gotoImplementationResultLocationLinkList
top::GotoImplementationResult ::= result::[LocationLink]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function gotoImplementationResultToJson
JSONValue ::= val::GotoImplementationResult
{
  return val.jsonValue;
}
