nonterminal OnTypeFormattingCapabilities with jsonValue, dynamicRegistration;


abstract production onTypeFormattingCapabilities
top::OnTypeFormattingCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseOnTypeFormattingCapabilities
Either<ResponseError OnTypeFormattingCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseOnTypeFormattingCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(onTypeFormattingCapabilities(dynamicRegistration));
}

function parseOnTypeFormattingCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function onTypeFormattingCapabilitiesToJson
JSONValue ::= val::OnTypeFormattingCapabilities
{
  return val.jsonValue;
}
