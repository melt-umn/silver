nonterminal SignatureHelpRegistrationOptions with jsonValue, documentSelector, charactersToTriggerSignatureHelp;

synthesized attribute documentSelector :: Maybe<[DocumentFilter]>;
synthesized attribute charactersToTriggerSignatureHelp :: Maybe<[String]>;

abstract production signatureHelpRegistrationOptions
top::SignatureHelpRegistrationOptions::=
  documentSelector::Maybe<[DocumentFilter]> triggerCharacters::Maybe<[String]>
{
  top.documentSelector = documentSelector;
  top.charactersToTriggerSignatureHelp = triggerCharacters;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("documentSelector", mapMaybe(jsonArray, mapMaybeList((.jsonValue), documentSelector)), 
    maybeAddKeyValuePairToJSONObject("triggerCharacters", mapMaybe(jsonArray, mapMaybeList(jsonString, triggerCharacters)), 
    jsonObject([])));
}

function parseSignatureHelpRegistrationOptions
Either<ResponseError SignatureHelpRegistrationOptions> ::= val::JSONValue
{
  local documentSelector :: Maybe<[Either<ResponseError DocumentFilter>]>
    = mapMaybe(mapJsonArray(parseDocumentFilter, _), getValueWithKey("documentSelector", val));
  local triggerCharacters :: Maybe<[String]>
    = mapMaybe(mapJsonArray(jsonToString, _), getValueWithKey("triggerCharacters", val));

  local err :: Maybe<ResponseError> = 
    parseSignatureHelpRegistrationOptionsError(documentSelector);

  return
  if err.isJust
  then left(err.fromJust)
  else right(signatureHelpRegistrationOptions(allFromRightMaybe(documentSelector), triggerCharacters));
}

function parseSignatureHelpRegistrationOptionsError
Maybe<ResponseError> ::= 
  documentSelector::Maybe<[Either<ResponseError DocumentFilter>]>
{
  return
  if documentSelector.isJust && anyLeft(documentSelector.fromJust) then firstLeft(documentSelector.fromJust)
  else nothing();
}

function signatureHelpRegistrationOptionsToJson
JSONValue ::= val::SignatureHelpRegistrationOptions
{
  return val.jsonValue;
}
