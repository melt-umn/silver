nonterminal RangeFormattingCapabilities with jsonValue, dynamicRegistration;


abstract production rangeFormattingCapabilities
top::RangeFormattingCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseRangeFormattingCapabilities
Either<ResponseError RangeFormattingCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseRangeFormattingCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(rangeFormattingCapabilities(dynamicRegistration));
}

function parseRangeFormattingCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function rangeFormattingCapabilitiesToJson
JSONValue ::= val::RangeFormattingCapabilities
{
  return val.jsonValue;
}
