nonterminal DocumentHighlightCapabilities with jsonValue, dynamicRegistration;


abstract production documentHighlightCapabilities
top::DocumentHighlightCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseDocumentHighlightCapabilities
Either<ResponseError DocumentHighlightCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentHighlightCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentHighlightCapabilities(dynamicRegistration));
}

function parseDocumentHighlightCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function documentHighlightCapabilitiesToJson
JSONValue ::= val::DocumentHighlightCapabilities
{
  return val.jsonValue;
}
