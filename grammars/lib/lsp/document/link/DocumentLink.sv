nonterminal DocumentLink with jsonValue, range, targetOfLink, dataForDocumentLinkResolve;

synthesized attribute targetOfLink :: Maybe<DocumentUri>;
synthesized attribute dataForDocumentLinkResolve :: Maybe<JSONValue>;

abstract production documentLink
top::DocumentLink::=
  range::Range target::Maybe<DocumentUri> data::Maybe<JSONValue>
{
  top.range = range;
  top.targetOfLink = target;
  top.dataForDocumentLinkResolve = data;
  top.jsonValue =
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    maybeAddKeyValuePairToJSONObject("target", mapMaybe(jsonString, target), 
    maybeAddKeyValuePairToJSONObject("data", data, 
    jsonObject([]))));
}

function parseDocumentLink
Either<ResponseError DocumentLink> ::= val::JSONValue
{
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local target :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("target", val));
  local data :: Maybe<JSONValue>
    = mapMaybe(identity, getValueWithKey("data", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentLinkError(range);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentLink(range.fromJust.fromRight, target, data));
}

function parseDocumentLinkError
Maybe<ResponseError> ::= 
  range::Maybe<Either<ResponseError Range>>
{
  return
  if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on DocumentLink"))
  else nothing();
}

function documentLinkToJson
JSONValue ::= val::DocumentLink
{
  return val.jsonValue;
}
