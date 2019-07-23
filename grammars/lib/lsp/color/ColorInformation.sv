nonterminal ColorInformation with jsonValue, range, colorValue;

synthesized attribute colorValue :: Color;

abstract production colorInformation
top::ColorInformation::=
  range::Range color::Color
{
  top.range = range;
  top.colorValue = color;
  top.jsonValue =
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    addKeyValuePairToJSONObject("color", color.jsonValue, 
    jsonObject([])));
}

function parseColorInformation
Either<ResponseError ColorInformation> ::= val::JSONValue
{
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local color :: Maybe<Either<ResponseError Color>>
    = mapMaybe(parseColor, getValueWithKey("color", val));

  local err :: Maybe<ResponseError> = 
    parseColorInformationError(range, color);

  return
  if err.isJust
  then left(err.fromJust)
  else right(colorInformation(range.fromJust.fromRight, color.fromJust.fromRight));
}

function parseColorInformationError
Maybe<ResponseError> ::= 
  range::Maybe<Either<ResponseError Range>> color::Maybe<Either<ResponseError Color>>
{
  return
  if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on ColorInformation"))
  else if !color.isJust|| color.fromJust.isLeft then just(lspInvalidParams("color not set or invalid on ColorInformation"))
  else nothing();
}

function colorInformationToJson
JSONValue ::= val::ColorInformation
{
  return val.jsonValue;
}
