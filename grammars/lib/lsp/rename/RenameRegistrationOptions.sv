nonterminal RenameRegistrationOptions with jsonValue, documentSelector, prepareProvider;

synthesized attribute documentSelector :: Maybe<[DocumentFilter]>;

abstract production renameRegistrationOptions
top::RenameRegistrationOptions::=
  documentSelector::Maybe<[DocumentFilter]> prepareProvider::Maybe<Boolean>
{
  top.documentSelector = documentSelector;
  top.prepareProvider = prepareProvider;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("documentSelector", mapMaybe(jsonArray, mapMaybeList((.jsonValue), documentSelector)), 
    maybeAddKeyValuePairToJSONObject("prepareProvider", mapMaybe(jsonBoolean, prepareProvider), 
    jsonObject([])));
}

function parseRenameRegistrationOptions
Either<ResponseError RenameRegistrationOptions> ::= val::JSONValue
{
  local documentSelector :: Maybe<[Either<ResponseError DocumentFilter>]>
    = mapMaybe(mapJsonArray(parseDocumentFilter, _), getValueWithKey("documentSelector", val));
  local prepareProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("prepareProvider", val));

  local err :: Maybe<ResponseError> = 
    parseRenameRegistrationOptionsError(documentSelector);

  return
  if err.isJust
  then left(err.fromJust)
  else right(renameRegistrationOptions(allFromRightMaybe(documentSelector), prepareProvider));
}

function parseRenameRegistrationOptionsError
Maybe<ResponseError> ::= 
  documentSelector::Maybe<[Either<ResponseError DocumentFilter>]>
{
  return
  if documentSelector.isJust && anyLeft(documentSelector.fromJust) then firstLeft(documentSelector.fromJust)
  else nothing();
}

function renameRegistrationOptionsToJson
JSONValue ::= val::RenameRegistrationOptions
{
  return val.jsonValue;
}
