nonterminal DidChangeWatchedFilesCapabilities with jsonValue, dynamicRegistration;


abstract production didChangeWatchedFilesCapabilities
top::DidChangeWatchedFilesCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseDidChangeWatchedFilesCapabilities
Either<ResponseError DidChangeWatchedFilesCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeWatchedFilesCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeWatchedFilesCapabilities(dynamicRegistration));
}

function parseDidChangeWatchedFilesCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function didChangeWatchedFilesCapabilitiesToJson
JSONValue ::= val::DidChangeWatchedFilesCapabilities
{
  return val.jsonValue;
}
