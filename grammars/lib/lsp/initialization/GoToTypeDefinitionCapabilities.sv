nonterminal GoToTypeDefinitionCapabilities with jsonValue, dynamicRegistration, linkSupport;


abstract production goToTypeDefinitionCapabilities
top::GoToTypeDefinitionCapabilities::=
  dynamicRegistration::Maybe<Boolean> linkSupport::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.linkSupport = linkSupport;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("linkSupport", mapMaybe(jsonBoolean, linkSupport), 
    jsonObject([])));
}

function parseGoToTypeDefinitionCapabilities
Either<ResponseError GoToTypeDefinitionCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local linkSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("linkSupport", val));

  local err :: Maybe<ResponseError> = 
    parseGoToTypeDefinitionCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(goToTypeDefinitionCapabilities(dynamicRegistration, linkSupport));
}

function parseGoToTypeDefinitionCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function goToTypeDefinitionCapabilitiesToJson
JSONValue ::= val::GoToTypeDefinitionCapabilities
{
  return val.jsonValue;
}
