nonterminal CodeLensRegistrationOptions with jsonValue, documentSelector, resolveProvider;

synthesized attribute documentSelector :: Maybe<[DocumentFilter]>;

abstract production codeLensRegistrationOptions
top::CodeLensRegistrationOptions::=
  documentSelector::Maybe<[DocumentFilter]> resolveProvider::Maybe<Boolean>
{
  top.documentSelector = documentSelector;
  top.resolveProvider = resolveProvider;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("documentSelector", mapMaybe(jsonArray, mapMaybeList((.jsonValue), documentSelector)), 
    maybeAddKeyValuePairToJSONObject("resolveProvider", mapMaybe(jsonBoolean, resolveProvider), 
    jsonObject([])));
}

function parseCodeLensRegistrationOptions
Either<ResponseError CodeLensRegistrationOptions> ::= val::JSONValue
{
  local documentSelector :: Maybe<[Either<ResponseError DocumentFilter>]>
    = mapMaybe(mapJsonArray(parseDocumentFilter, _), getValueWithKey("documentSelector", val));
  local resolveProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("resolveProvider", val));

  local err :: Maybe<ResponseError> = 
    parseCodeLensRegistrationOptionsError(documentSelector);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeLensRegistrationOptions(allFromRightMaybe(documentSelector), resolveProvider));
}

function parseCodeLensRegistrationOptionsError
Maybe<ResponseError> ::= 
  documentSelector::Maybe<[Either<ResponseError DocumentFilter>]>
{
  return
  if documentSelector.isJust && anyLeft(documentSelector.fromJust) then firstLeft(documentSelector.fromJust)
  else nothing();
}

function codeLensRegistrationOptionsToJson
JSONValue ::= val::CodeLensRegistrationOptions
{
  return val.jsonValue;
}
