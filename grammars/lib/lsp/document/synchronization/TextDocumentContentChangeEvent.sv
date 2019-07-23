nonterminal TextDocumentContentChangeEvent with jsonValue, changedRange, changedRangeLength, newText;

synthesized attribute changedRange :: Maybe<Range>;
synthesized attribute changedRangeLength :: Maybe<Integer>;

abstract production textDocumentContentChangeEvent
top::TextDocumentContentChangeEvent::=
  range::Maybe<Range> rangeLength::Maybe<Integer> text::String
{
  top.changedRange = range;
  top.changedRangeLength = rangeLength;
  top.newText = text;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("range", mapMaybe((.jsonValue), range), 
    maybeAddKeyValuePairToJSONObject("rangeLength", mapMaybe(jsonInteger, rangeLength), 
    addKeyValuePairToJSONObject("text", jsonString(text), 
    jsonObject([]))));
}

function parseTextDocumentContentChangeEvent
Either<ResponseError TextDocumentContentChangeEvent> ::= val::JSONValue
{
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local rangeLength :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("rangeLength", val));
  local text :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("text", val));

  local err :: Maybe<ResponseError> = 
    parseTextDocumentContentChangeEventError(range, text);

  return
  if err.isJust
  then left(err.fromJust)
  else right(textDocumentContentChangeEvent(rightMaybe(range), rangeLength, text.fromJust));
}

function parseTextDocumentContentChangeEventError
Maybe<ResponseError> ::= 
  range::Maybe<Either<ResponseError Range>> text::Maybe<String>
{
  return
  if range.isJust && range.fromJust.isLeft then just(range.fromJust.fromLeft)
  else if !text.isJust then just(lspInvalidParams("text not set on TextDocumentContentChangeEvent"))
  else nothing();
}

function textDocumentContentChangeEventToJson
JSONValue ::= val::TextDocumentContentChangeEvent
{
  return val.jsonValue;
}
