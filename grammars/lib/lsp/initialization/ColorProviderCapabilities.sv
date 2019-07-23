nonterminal ColorProviderCapabilities with jsonValue, dynamicRegistration;


abstract production colorProviderCapabilities
top::ColorProviderCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseColorProviderCapabilities
Either<ResponseError ColorProviderCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseColorProviderCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(colorProviderCapabilities(dynamicRegistration));
}

function parseColorProviderCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function colorProviderCapabilitiesToJson
JSONValue ::= val::ColorProviderCapabilities
{
  return val.jsonValue;
}
