nonterminal CodeLensCapabilities with jsonValue, dynamicRegistration;


abstract production codeLensCapabilities
top::CodeLensCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseCodeLensCapabilities
Either<ResponseError CodeLensCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseCodeLensCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeLensCapabilities(dynamicRegistration));
}

function parseCodeLensCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function codeLensCapabilitiesToJson
JSONValue ::= val::CodeLensCapabilities
{
  return val.jsonValue;
}
