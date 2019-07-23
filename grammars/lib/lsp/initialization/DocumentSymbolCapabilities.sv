nonterminal DocumentSymbolCapabilities with jsonValue, dynamicRegistration, documentSymbolKindCapabilities, hierarchicalDocumentSymbolSuport;

synthesized attribute documentSymbolKindCapabilities :: Maybe<DocumentSymbolKindCapabilities>;
synthesized attribute hierarchicalDocumentSymbolSuport :: Maybe<Boolean>;

abstract production documentSymbolCapabilities
top::DocumentSymbolCapabilities::=
  dynamicRegistration::Maybe<Boolean> symbolKind::Maybe<DocumentSymbolKindCapabilities> hierarchicalDocumentSymbolSupport::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.documentSymbolKindCapabilities = symbolKind;
  top.hierarchicalDocumentSymbolSuport = hierarchicalDocumentSymbolSupport;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("symbolKind", mapMaybe((.jsonValue), symbolKind), 
    maybeAddKeyValuePairToJSONObject("hierarchicalDocumentSymbolSupport", mapMaybe(jsonBoolean, hierarchicalDocumentSymbolSupport), 
    jsonObject([]))));
}

function parseDocumentSymbolCapabilities
Either<ResponseError DocumentSymbolCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local symbolKind :: Maybe<Either<ResponseError DocumentSymbolKindCapabilities>>
    = mapMaybe(parseDocumentSymbolKindCapabilities, getValueWithKey("symbolKind", val));
  local hierarchicalDocumentSymbolSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("hierarchicalDocumentSymbolSupport", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentSymbolCapabilitiesError(symbolKind);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentSymbolCapabilities(dynamicRegistration, rightMaybe(symbolKind), hierarchicalDocumentSymbolSupport));
}

function parseDocumentSymbolCapabilitiesError
Maybe<ResponseError> ::= 
  symbolKind::Maybe<Either<ResponseError DocumentSymbolKindCapabilities>>
{
  return
  if symbolKind.isJust && symbolKind.fromJust.isLeft then just(symbolKind.fromJust.fromLeft)
  else nothing();
}

function documentSymbolCapabilitiesToJson
JSONValue ::= val::DocumentSymbolCapabilities
{
  return val.jsonValue;
}
