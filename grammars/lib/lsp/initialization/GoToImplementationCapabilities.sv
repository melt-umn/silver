nonterminal GoToImplementationCapabilities with jsonValue, dynamicRegistration, linkSupport;


abstract production goToImplementationCapabilities
top::GoToImplementationCapabilities::=
  dynamicRegistration::Maybe<Boolean> linkSupport::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.linkSupport = linkSupport;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("linkSupport", mapMaybe(jsonBoolean, linkSupport), 
    jsonObject([])));
}

function parseGoToImplementationCapabilities
Either<ResponseError GoToImplementationCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local linkSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("linkSupport", val));

  local err :: Maybe<ResponseError> = 
    parseGoToImplementationCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(goToImplementationCapabilities(dynamicRegistration, linkSupport));
}

function parseGoToImplementationCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function goToImplementationCapabilitiesToJson
JSONValue ::= val::GoToImplementationCapabilities
{
  return val.jsonValue;
}
