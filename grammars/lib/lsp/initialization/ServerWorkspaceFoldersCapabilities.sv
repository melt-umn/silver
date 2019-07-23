nonterminal ServerWorkspaceFoldersCapabilities with jsonValue, optionalSupported, receivesFolderChangeNotifications;

synthesized attribute receivesFolderChangeNotifications :: Maybe<Boolean>;

abstract production serverWorkspaceFoldersCapabilities
top::ServerWorkspaceFoldersCapabilities::=
  supported::Maybe<Boolean> changeNotifications::Maybe<Boolean>
{
  top.optionalSupported = supported;
  top.receivesFolderChangeNotifications = changeNotifications;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("supported", mapMaybe(jsonBoolean, supported), 
    maybeAddKeyValuePairToJSONObject("changeNotifications", mapMaybe(jsonBoolean, changeNotifications), 
    jsonObject([])));
}

function parseServerWorkspaceFoldersCapabilities
Either<ResponseError ServerWorkspaceFoldersCapabilities> ::= val::JSONValue
{
  local supported :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("supported", val));
  local changeNotifications :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("changeNotifications", val));

  local err :: Maybe<ResponseError> = 
    parseServerWorkspaceFoldersCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(serverWorkspaceFoldersCapabilities(supported, changeNotifications));
}

function parseServerWorkspaceFoldersCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function serverWorkspaceFoldersCapabilitiesToJson
JSONValue ::= val::ServerWorkspaceFoldersCapabilities
{
  return val.jsonValue;
}
