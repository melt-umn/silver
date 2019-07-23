nonterminal CodeLensResolveResult with jsonValue;

abstract production codeLensResolveResultCodeLens
top::CodeLensResolveResult ::= result::CodeLens
{
  top.jsonValue = result.jsonValue;
}

function codeLensResolveResultToJson
JSONValue ::= val::CodeLensResolveResult
{
  return val.jsonValue;
}
