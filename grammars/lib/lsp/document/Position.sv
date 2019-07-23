nonterminal Position with jsonValue, lineNum, characterPos;

synthesized attribute lineNum :: Integer;
synthesized attribute characterPos :: Integer;

abstract production position
top::Position::=
  line_::Integer character::Integer
{
  top.lineNum = line_;
  top.characterPos = character;
  top.jsonValue =
    addKeyValuePairToJSONObject("line", jsonInteger(line_), 
    addKeyValuePairToJSONObject("character", jsonInteger(character), 
    jsonObject([])));
}

function parsePosition
Either<ResponseError Position> ::= val::JSONValue
{
  local line_ :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("line", val));
  local character :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("character", val));

  local err :: Maybe<ResponseError> = 
    parsePositionError(line_, character);

  return
  if err.isJust
  then left(err.fromJust)
  else right(position(line_.fromJust, character.fromJust));
}

function parsePositionError
Maybe<ResponseError> ::= 
  line_::Maybe<Integer> character::Maybe<Integer>
{
  return
  if !line_.isJust then just(lspInvalidParams("line not set on Position"))
  else if !character.isJust then just(lspInvalidParams("character not set on Position"))
  else nothing();
}

function positionToJson
JSONValue ::= val::Position
{
  return val.jsonValue;
}
