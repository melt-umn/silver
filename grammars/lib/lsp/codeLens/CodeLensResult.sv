nonterminal CodeLensResult with jsonValue;

abstract production nullCodeLensResult
top::CodeLensResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production codeLensResultCodeLensList
top::CodeLensResult ::= result::[CodeLens]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function codeLensResultToJson
JSONValue ::= val::CodeLensResult
{
  return val.jsonValue;
}
