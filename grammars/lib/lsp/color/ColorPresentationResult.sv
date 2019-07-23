nonterminal ColorPresentationResult with jsonValue;

abstract production colorPresentationResultColorPresentationList
top::ColorPresentationResult ::= result::[ColorPresentation]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function colorPresentationResultToJson
JSONValue ::= val::ColorPresentationResult
{
  return val.jsonValue;
}
