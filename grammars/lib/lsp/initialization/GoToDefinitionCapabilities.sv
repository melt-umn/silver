nonterminal GoToDefinitionCapabilities with jsonValue, dynamicRegistration, linkSupport;


abstract production goToDefinitionCapabilities
top::GoToDefinitionCapabilities::=
  dynamicRegistration::Maybe<Boolean> linkSupport::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.linkSupport = linkSupport;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("linkSupport", mapMaybe(jsonBoolean, linkSupport), 
    jsonObject([])));
}

function parseGoToDefinitionCapabilities
Either<ResponseError GoToDefinitionCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local linkSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("linkSupport", val));

  local err :: Maybe<ResponseError> = 
    parseGoToDefinitionCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(goToDefinitionCapabilities(dynamicRegistration, linkSupport));
}

function parseGoToDefinitionCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function goToDefinitionCapabilitiesToJson
JSONValue ::= val::GoToDefinitionCapabilities
{
  return val.jsonValue;
}
