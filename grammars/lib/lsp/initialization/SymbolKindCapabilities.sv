nonterminal SymbolKindCapabilities with jsonValue, supportedSymbolValues;

synthesized attribute supportedSymbolValues :: Maybe<[SymbolKind]>;

abstract production symbolKindCapabilities
top::SymbolKindCapabilities::=
  valueSet::Maybe<[SymbolKind]>
{
  top.supportedSymbolValues = valueSet;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("valueSet", mapMaybe(jsonArray, mapMaybeList((.jsonValue), valueSet)), 
    jsonObject([]));
}

function parseSymbolKindCapabilities
Either<ResponseError SymbolKindCapabilities> ::= val::JSONValue
{
  local valueSet :: Maybe<[Either<ResponseError SymbolKind>]>
    = mapMaybe(mapJsonArray(parseSymbolKind, _), getValueWithKey("valueSet", val));

  local err :: Maybe<ResponseError> = 
    parseSymbolKindCapabilitiesError(valueSet);

  return
  if err.isJust
  then left(err.fromJust)
  else right(symbolKindCapabilities(allFromRightMaybe(valueSet)));
}

function parseSymbolKindCapabilitiesError
Maybe<ResponseError> ::= 
  valueSet::Maybe<[Either<ResponseError SymbolKind>]>
{
  return
  if valueSet.isJust && anyLeft(valueSet.fromJust) then firstLeft(valueSet.fromJust)
  else nothing();
}

function symbolKindCapabilitiesToJson
JSONValue ::= val::SymbolKindCapabilities
{
  return val.jsonValue;
}
