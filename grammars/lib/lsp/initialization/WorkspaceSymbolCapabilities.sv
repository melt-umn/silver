nonterminal WorkspaceSymbolCapabilities with jsonValue, dynamicRegistration, symbolKindCapabilities;

synthesized attribute symbolKindCapabilities :: Maybe<SymbolKindCapabilities>;

abstract production workspaceSymbolCapabilities
top::WorkspaceSymbolCapabilities::=
  dynamicRegistration::Maybe<Boolean> symbolKind::Maybe<SymbolKindCapabilities>
{
  top.dynamicRegistration = dynamicRegistration;
  top.symbolKindCapabilities = symbolKind;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("symbolKind", mapMaybe((.jsonValue), symbolKind), 
    jsonObject([])));
}

function parseWorkspaceSymbolCapabilities
Either<ResponseError WorkspaceSymbolCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local symbolKind :: Maybe<Either<ResponseError SymbolKindCapabilities>>
    = mapMaybe(parseSymbolKindCapabilities, getValueWithKey("symbolKind", val));

  local err :: Maybe<ResponseError> = 
    parseWorkspaceSymbolCapabilitiesError(symbolKind);

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceSymbolCapabilities(dynamicRegistration, rightMaybe(symbolKind)));
}

function parseWorkspaceSymbolCapabilitiesError
Maybe<ResponseError> ::= 
  symbolKind::Maybe<Either<ResponseError SymbolKindCapabilities>>
{
  return
  if symbolKind.isJust && symbolKind.fromJust.isLeft then just(symbolKind.fromJust.fromLeft)
  else nothing();
}

function workspaceSymbolCapabilitiesToJson
JSONValue ::= val::WorkspaceSymbolCapabilities
{
  return val.jsonValue;
}
