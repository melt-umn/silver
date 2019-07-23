nonterminal Hover with jsonValue, hoverContents, optionalRange;

synthesized attribute hoverContents :: String;

abstract production hover
top::Hover::=
  contents::String range::Maybe<Range>
{
  top.hoverContents = contents;
  top.optionalRange = range;
  top.jsonValue =
    addKeyValuePairToJSONObject("contents", jsonString(contents), 
    maybeAddKeyValuePairToJSONObject("range", mapMaybe((.jsonValue), range), 
    jsonObject([])));
}

function parseHover
Either<ResponseError Hover> ::= val::JSONValue
{
  local contents :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("contents", val));
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));

  local err :: Maybe<ResponseError> = 
    parseHoverError(contents, range);

  return
  if err.isJust
  then left(err.fromJust)
  else right(hover(contents.fromJust, rightMaybe(range)));
}

function parseHoverError
Maybe<ResponseError> ::= 
  contents::Maybe<String> range::Maybe<Either<ResponseError Range>>
{
  return
  if !contents.isJust then just(lspInvalidParams("contents not set on Hover"))
  else if range.isJust && range.fromJust.isLeft then just(range.fromJust.fromLeft)
  else nothing();
}

function hoverToJson
JSONValue ::= val::Hover
{
  return val.jsonValue;
}
