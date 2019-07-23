nonterminal DocumentSymbolKindCapabilities with jsonValue, supportedSymbolKinds;

synthesized attribute supportedSymbolKinds :: Maybe<[SymbolKind]>;

abstract production documentSymbolKindCapabilities
top::DocumentSymbolKindCapabilities::=
  valueSet::Maybe<[SymbolKind]>
{
  top.supportedSymbolKinds = valueSet;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("valueSet", mapMaybe(jsonArray, mapMaybeList((.jsonValue), valueSet)), 
    jsonObject([]));
}

function parseDocumentSymbolKindCapabilities
Either<ResponseError DocumentSymbolKindCapabilities> ::= val::JSONValue
{
  local valueSet :: Maybe<[Either<ResponseError SymbolKind>]>
    = mapMaybe(mapJsonArray(parseSymbolKind, _), getValueWithKey("valueSet", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentSymbolKindCapabilitiesError(valueSet);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentSymbolKindCapabilities(allFromRightMaybe(valueSet)));
}

function parseDocumentSymbolKindCapabilitiesError
Maybe<ResponseError> ::= 
  valueSet::Maybe<[Either<ResponseError SymbolKind>]>
{
  return
  if valueSet.isJust && anyLeft(valueSet.fromJust) then firstLeft(valueSet.fromJust)
  else nothing();
}

function documentSymbolKindCapabilitiesToJson
JSONValue ::= val::DocumentSymbolKindCapabilities
{
  return val.jsonValue;
}
