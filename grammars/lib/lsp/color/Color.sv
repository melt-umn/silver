nonterminal Color with jsonValue, redComponent, greenComponent, blueComponent, alphaComponent;

synthesized attribute redComponent :: Float;
synthesized attribute greenComponent :: Float;
synthesized attribute blueComponent :: Float;
synthesized attribute alphaComponent :: Float;

abstract production color
top::Color::=
  red::Float green::Float blue::Float alpha::Float
{
  top.redComponent = red;
  top.greenComponent = green;
  top.blueComponent = blue;
  top.alphaComponent = alpha;
  top.jsonValue =
    addKeyValuePairToJSONObject("red", jsonNumber(red), 
    addKeyValuePairToJSONObject("green", jsonNumber(green), 
    addKeyValuePairToJSONObject("blue", jsonNumber(blue), 
    addKeyValuePairToJSONObject("alpha", jsonNumber(alpha), 
    jsonObject([])))));
}

function parseColor
Either<ResponseError Color> ::= val::JSONValue
{
  local red :: Maybe<Float>
    = mapMaybe(jsonToFloat, getValueWithKey("red", val));
  local green :: Maybe<Float>
    = mapMaybe(jsonToFloat, getValueWithKey("green", val));
  local blue :: Maybe<Float>
    = mapMaybe(jsonToFloat, getValueWithKey("blue", val));
  local alpha :: Maybe<Float>
    = mapMaybe(jsonToFloat, getValueWithKey("alpha", val));

  local err :: Maybe<ResponseError> = 
    parseColorError(red, green, blue, alpha);

  return
  if err.isJust
  then left(err.fromJust)
  else right(color(red.fromJust, green.fromJust, blue.fromJust, alpha.fromJust));
}

function parseColorError
Maybe<ResponseError> ::= 
  red::Maybe<Float> green::Maybe<Float> blue::Maybe<Float> alpha::Maybe<Float>
{
  return
  if !red.isJust then just(lspInvalidParams("red not set on Color"))
  else if !green.isJust then just(lspInvalidParams("green not set on Color"))
  else if !blue.isJust then just(lspInvalidParams("blue not set on Color"))
  else if !alpha.isJust then just(lspInvalidParams("alpha not set on Color"))
  else nothing();
}

function colorToJson
JSONValue ::= val::Color
{
  return val.jsonValue;
}
