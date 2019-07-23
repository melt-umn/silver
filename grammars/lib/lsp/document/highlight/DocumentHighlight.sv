nonterminal DocumentHighlight with jsonValue, range, kindOfHighlight;

synthesized attribute kindOfHighlight :: Maybe<DocumentHighlightKind>;

abstract production documentHighlight
top::DocumentHighlight::=
  range::Range kind::Maybe<DocumentHighlightKind>
{
  top.range = range;
  top.kindOfHighlight = kind;
  top.jsonValue =
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    maybeAddKeyValuePairToJSONObject("kind", mapMaybe((.jsonValue), kind), 
    jsonObject([])));
}

function parseDocumentHighlight
Either<ResponseError DocumentHighlight> ::= val::JSONValue
{
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local kind :: Maybe<Either<ResponseError DocumentHighlightKind>>
    = mapMaybe(parseDocumentHighlightKind, getValueWithKey("kind", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentHighlightError(range, kind);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentHighlight(range.fromJust.fromRight, rightMaybe(kind)));
}

function parseDocumentHighlightError
Maybe<ResponseError> ::= 
  range::Maybe<Either<ResponseError Range>> kind::Maybe<Either<ResponseError DocumentHighlightKind>>
{
  return
  if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on DocumentHighlight"))
  else if kind.isJust && kind.fromJust.isLeft then just(kind.fromJust.fromLeft)
  else nothing();
}

function documentHighlightToJson
JSONValue ::= val::DocumentHighlight
{
  return val.jsonValue;
}
