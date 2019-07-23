nonterminal DidChangeConfigurationCapabilities with jsonValue, dynamicRegistration;


abstract production didChangeConfigurationCapabilities
top::DidChangeConfigurationCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseDidChangeConfigurationCapabilities
Either<ResponseError DidChangeConfigurationCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeConfigurationCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeConfigurationCapabilities(dynamicRegistration));
}

function parseDidChangeConfigurationCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function didChangeConfigurationCapabilitiesToJson
JSONValue ::= val::DidChangeConfigurationCapabilities
{
  return val.jsonValue;
}
