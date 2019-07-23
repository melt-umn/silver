nonterminal DocumentSymbolResult with jsonValue;

abstract production nullDocumentSymbolResult
top::DocumentSymbolResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production documentSymbolResultDocumentSymbolList
top::DocumentSymbolResult ::= result::[DocumentSymbol]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

abstract production documentSymbolResultSymbolInformationList
top::DocumentSymbolResult ::= result::[SymbolInformation]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function documentSymbolResultToJson
JSONValue ::= val::DocumentSymbolResult
{
  return val.jsonValue;
}
