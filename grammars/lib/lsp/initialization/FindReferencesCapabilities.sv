nonterminal FindReferencesCapabilities with jsonValue, dynamicRegistration;


abstract production findReferencesCapabilities
top::FindReferencesCapabilities::=
  dynamicRegistration::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    jsonObject([]));
}

function parseFindReferencesCapabilities
Either<ResponseError FindReferencesCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));

  local err :: Maybe<ResponseError> = 
    parseFindReferencesCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(findReferencesCapabilities(dynamicRegistration));
}

function parseFindReferencesCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function findReferencesCapabilitiesToJson
JSONValue ::= val::FindReferencesCapabilities
{
  return val.jsonValue;
}
