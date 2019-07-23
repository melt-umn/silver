nonterminal TextDocumentRegistrationOptions with jsonValue, documentSelector;

synthesized attribute documentSelector :: Maybe<[DocumentFilter]>;

abstract production textDocumentRegistrationOptions
top::TextDocumentRegistrationOptions::=
  documentSelector::Maybe<[DocumentFilter]>
{
  top.documentSelector = documentSelector;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("documentSelector", mapMaybe(jsonArray, mapMaybeList((.jsonValue), documentSelector)), 
    jsonObject([]));
}

function parseTextDocumentRegistrationOptions
Either<ResponseError TextDocumentRegistrationOptions> ::= val::JSONValue
{
  local documentSelector :: Maybe<[Either<ResponseError DocumentFilter>]>
    = mapMaybe(mapJsonArray(parseDocumentFilter, _), getValueWithKey("documentSelector", val));

  local err :: Maybe<ResponseError> = 
    parseTextDocumentRegistrationOptionsError(documentSelector);

  return
  if err.isJust
  then left(err.fromJust)
  else right(textDocumentRegistrationOptions(allFromRightMaybe(documentSelector)));
}

function parseTextDocumentRegistrationOptionsError
Maybe<ResponseError> ::= 
  documentSelector::Maybe<[Either<ResponseError DocumentFilter>]>
{
  return
  if documentSelector.isJust && anyLeft(documentSelector.fromJust) then firstLeft(documentSelector.fromJust)
  else nothing();
}

function textDocumentRegistrationOptionsToJson
JSONValue ::= val::TextDocumentRegistrationOptions
{
  return val.jsonValue;
}
