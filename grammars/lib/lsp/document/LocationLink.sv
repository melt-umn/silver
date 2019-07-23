nonterminal LocationLink with jsonValue, originalSelectionRange, targetUri, targetRange, targetSelectionRange;

synthesized attribute originalSelectionRange :: Maybe<Range>;
synthesized attribute targetUri :: DocumentUri;
synthesized attribute targetRange :: Range;
synthesized attribute targetSelectionRange :: Range;

abstract production locationLink
top::LocationLink::=
  originSelectionRange::Maybe<Range> targetUri::DocumentUri targetRange::Range targetSelectionRange::Range
{
  top.originalSelectionRange = originSelectionRange;
  top.targetUri = targetUri;
  top.targetRange = targetRange;
  top.targetSelectionRange = targetSelectionRange;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("originSelectionRange", mapMaybe((.jsonValue), originSelectionRange), 
    addKeyValuePairToJSONObject("targetUri", jsonString(targetUri), 
    addKeyValuePairToJSONObject("targetRange", targetRange.jsonValue, 
    addKeyValuePairToJSONObject("targetSelectionRange", targetSelectionRange.jsonValue, 
    jsonObject([])))));
}

function parseLocationLink
Either<ResponseError LocationLink> ::= val::JSONValue
{
  local originSelectionRange :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("originSelectionRange", val));
  local targetUri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("targetUri", val));
  local targetRange :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("targetRange", val));
  local targetSelectionRange :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("targetSelectionRange", val));

  local err :: Maybe<ResponseError> = 
    parseLocationLinkError(originSelectionRange, targetUri, targetRange, targetSelectionRange);

  return
  if err.isJust
  then left(err.fromJust)
  else right(locationLink(rightMaybe(originSelectionRange), targetUri.fromJust, targetRange.fromJust.fromRight, targetSelectionRange.fromJust.fromRight));
}

function parseLocationLinkError
Maybe<ResponseError> ::= 
  originSelectionRange::Maybe<Either<ResponseError Range>> targetUri::Maybe<DocumentUri> targetRange::Maybe<Either<ResponseError Range>> targetSelectionRange::Maybe<Either<ResponseError Range>>
{
  return
  if originSelectionRange.isJust && originSelectionRange.fromJust.isLeft then just(originSelectionRange.fromJust.fromLeft)
  else if !targetUri.isJust then just(lspInvalidParams("targetUri not set on LocationLink"))
  else if !targetRange.isJust|| targetRange.fromJust.isLeft then just(lspInvalidParams("targetRange not set or invalid on LocationLink"))
  else if !targetSelectionRange.isJust|| targetSelectionRange.fromJust.isLeft then just(lspInvalidParams("targetSelectionRange not set or invalid on LocationLink"))
  else nothing();
}

function locationLinkToJson
JSONValue ::= val::LocationLink
{
  return val.jsonValue;
}
