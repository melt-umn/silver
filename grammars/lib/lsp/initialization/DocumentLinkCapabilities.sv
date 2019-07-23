nonterminal DocumentLinkCapabilities with jsonValue, dynamicRegistration;


abstract production documentLinkCapabilities
top::DocumentLinkCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseDocumentLinkCapabilities
Either<ResponseError DocumentLinkCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentLinkCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentLinkCapabilities(dynamicRegistration));
}

function parseDocumentLinkCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function documentLinkCapabilitiesToJson
JSONValue ::= val::DocumentLinkCapabilities
{
  return val.jsonValue;
}
