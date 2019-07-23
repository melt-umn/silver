nonterminal GoToDeclarationCapabilities with jsonValue, dynamicRegistration, linkSupport;


abstract production goToDeclarationCapabilities
top::GoToDeclarationCapabilities::=
  dynamicRegistration::Maybe<Boolean> linkSupport::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.linkSupport = linkSupport;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("linkSupport", mapMaybe(jsonBoolean, linkSupport), 
    jsonObject([])));
}

function parseGoToDeclarationCapabilities
Either<ResponseError GoToDeclarationCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local linkSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("linkSupport", val));

  local err :: Maybe<ResponseError> = 
    parseGoToDeclarationCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(goToDeclarationCapabilities(dynamicRegistration, linkSupport));
}

function parseGoToDeclarationCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function goToDeclarationCapabilitiesToJson
JSONValue ::= val::GoToDeclarationCapabilities
{
  return val.jsonValue;
}
