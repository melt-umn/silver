nonterminal FoldingRangeResult with jsonValue;

abstract production nullFoldingRangeResult
top::FoldingRangeResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production foldingRangeResultFoldingRangeList
top::FoldingRangeResult ::= result::[FoldingRange]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function foldingRangeResultToJson
JSONValue ::= val::FoldingRangeResult
{
  return val.jsonValue;
}
