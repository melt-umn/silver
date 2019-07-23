nonterminal Range with jsonValue, startPosition, endPosition;

synthesized attribute startPosition :: Position;
synthesized attribute endPosition :: Position;

abstract production range
top::Range::=
  start::Position end_::Position
{
  top.startPosition = start;
  top.endPosition = end_;
  top.jsonValue =
    addKeyValuePairToJSONObject("start", start.jsonValue, 
    addKeyValuePairToJSONObject("end", end_.jsonValue, 
    jsonObject([])));
}

function parseRange
Either<ResponseError Range> ::= val::JSONValue
{
  local start :: Maybe<Either<ResponseError Position>>
    = mapMaybe(parsePosition, getValueWithKey("start", val));
  local end_ :: Maybe<Either<ResponseError Position>>
    = mapMaybe(parsePosition, getValueWithKey("end", val));

  local err :: Maybe<ResponseError> = 
    parseRangeError(start, end_);

  return
  if err.isJust
  then left(err.fromJust)
  else right(range(start.fromJust.fromRight, end_.fromJust.fromRight));
}

function parseRangeError
Maybe<ResponseError> ::= 
  start::Maybe<Either<ResponseError Position>> end_::Maybe<Either<ResponseError Position>>
{
  return
  if !start.isJust|| start.fromJust.isLeft then just(lspInvalidParams("start not set or invalid on Range"))
  else if !end_.isJust|| end_.fromJust.isLeft then just(lspInvalidParams("end not set or invalid on Range"))
  else nothing();
}

function rangeToJson
JSONValue ::= val::Range
{
  return val.jsonValue;
}
