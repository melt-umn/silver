nonterminal DocumentColorResult with jsonValue;

abstract production documentColorResultColorInformationList
top::DocumentColorResult ::= result::[ColorInformation]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function documentColorResultToJson
JSONValue ::= val::DocumentColorResult
{
  return val.jsonValue;
}
