nonterminal FormattingCapabilities with jsonValue, dynamicRegistration;


abstract production formattingCapabilities
top::FormattingCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseFormattingCapabilities
Either<ResponseError FormattingCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseFormattingCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(formattingCapabilities(dynamicRegistration));
}

function parseFormattingCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function formattingCapabilitiesToJson
JSONValue ::= val::FormattingCapabilities
{
  return val.jsonValue;
}
