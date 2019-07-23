nonterminal DocumentSynchronizationCapabilities with jsonValue, dynamicRegistration, supportsWillSaveNotification, supportsWillSaveWaitUntilRequest, supportsDidSaveNotification;

synthesized attribute supportsWillSaveNotification :: Maybe<Boolean>;
synthesized attribute supportsWillSaveWaitUntilRequest :: Maybe<Boolean>;
synthesized attribute supportsDidSaveNotification :: Maybe<Boolean>;

abstract production documentSynchronizationCapabilities
top::DocumentSynchronizationCapabilities::=
  dynamicRegistration::Maybe<Boolean> willSave::Maybe<Boolean> willSaveWaitUntil::Maybe<Boolean> didSave::Maybe<Boolean>
{
  top.dynamicRegistration = dynamicRegistration;
  top.supportsWillSaveNotification = willSave;
  top.supportsWillSaveWaitUntilRequest = willSaveWaitUntil;
  top.supportsDidSaveNotification = didSave;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("willSave", mapMaybe(jsonBoolean, willSave), 
    maybeAddKeyValuePairToJSONObject("willSaveWaitUntil", mapMaybe(jsonBoolean, willSaveWaitUntil), 
    maybeAddKeyValuePairToJSONObject("didSave", mapMaybe(jsonBoolean, didSave), 
    jsonObject([])))));
}

function parseDocumentSynchronizationCapabilities
Either<ResponseError DocumentSynchronizationCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local willSave :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("willSave", val));
  local willSaveWaitUntil :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("willSaveWaitUntil", val));
  local didSave :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("didSave", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentSynchronizationCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentSynchronizationCapabilities(dynamicRegistration, willSave, willSaveWaitUntil, didSave));
}

function parseDocumentSynchronizationCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function documentSynchronizationCapabilitiesToJson
JSONValue ::= val::DocumentSynchronizationCapabilities
{
  return val.jsonValue;
}
