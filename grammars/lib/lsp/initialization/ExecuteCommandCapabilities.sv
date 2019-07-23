nonterminal ExecuteCommandCapabilities with jsonValue, dynamicRegistration;


abstract production executeCommandCapabilities
top::ExecuteCommandCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseExecuteCommandCapabilities
Either<ResponseError ExecuteCommandCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseExecuteCommandCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(executeCommandCapabilities(dynamicRegistration));
}

function parseExecuteCommandCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function executeCommandCapabilitiesToJson
JSONValue ::= val::ExecuteCommandCapabilities
{
  return val.jsonValue;
}
