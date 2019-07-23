nonterminal DocumentLinkRegistrationOptions with jsonValue, documentSelector, resolveProvider;

synthesized attribute documentSelector :: Maybe<[DocumentFilter]>;

abstract production documentLinkRegistrationOptions
top::DocumentLinkRegistrationOptions::=
  documentSelector::Maybe<[DocumentFilter]> resolveProvider::Maybe<Boolean>
{
  top.documentSelector = documentSelector;
  top.resolveProvider = resolveProvider;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("documentSelector", mapMaybe(jsonArray, mapMaybeList((.jsonValue), documentSelector)), 
    maybeAddKeyValuePairToJSONObject("resolveProvider", mapMaybe(jsonBoolean, resolveProvider), 
    jsonObject([])));
}

function parseDocumentLinkRegistrationOptions
Either<ResponseError DocumentLinkRegistrationOptions> ::= val::JSONValue
{
  local documentSelector :: Maybe<[Either<ResponseError DocumentFilter>]>
    = mapMaybe(mapJsonArray(parseDocumentFilter, _), getValueWithKey("documentSelector", val));
  local resolveProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("resolveProvider", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentLinkRegistrationOptionsError(documentSelector);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentLinkRegistrationOptions(allFromRightMaybe(documentSelector), resolveProvider));
}

function parseDocumentLinkRegistrationOptionsError
Maybe<ResponseError> ::= 
  documentSelector::Maybe<[Either<ResponseError DocumentFilter>]>
{
  return
  if documentSelector.isJust && anyLeft(documentSelector.fromJust) then firstLeft(documentSelector.fromJust)
  else nothing();
}

function documentLinkRegistrationOptionsToJson
JSONValue ::= val::DocumentLinkRegistrationOptions
{
  return val.jsonValue;
}
