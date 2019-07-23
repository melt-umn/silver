nonterminal RenameCapabilities with jsonValue, dynamicRegistration, renameValidityTestingSupport;

synthesized attribute renameValidityTestingSupport :: Maybe<Boolean>;

abstract production renameCapabilities
top::RenameCapabilities::=
  dynamicRegistration::Maybe<Boolean> prepareSupport::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.renameValidityTestingSupport = prepareSupport;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("prepareSupport", mapMaybe(jsonBoolean, prepareSupport), 
    jsonObject([])));
}

function parseRenameCapabilities
Either<ResponseError RenameCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local prepareSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("prepareSupport", val));

  local err :: Maybe<ResponseError> = 
    parseRenameCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(renameCapabilities(dynamicRegistration, prepareSupport));
}

function parseRenameCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function renameCapabilitiesToJson
JSONValue ::= val::RenameCapabilities
{
  return val.jsonValue;
}
