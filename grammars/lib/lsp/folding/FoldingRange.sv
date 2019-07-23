nonterminal FoldingRange with jsonValue, foldStartLine, foldStartCharacter, foldEndLine, foldEndCharacter, foldingType;

synthesized attribute foldStartLine :: Integer;
synthesized attribute foldStartCharacter :: Maybe<Integer>;
synthesized attribute foldEndLine :: Integer;
synthesized attribute foldEndCharacter :: Maybe<Integer>;
synthesized attribute foldingType :: Maybe<FoldingRangeKind>;

abstract production foldingRange
top::FoldingRange::=
  startLine::Integer startCharacter::Maybe<Integer> endLine::Integer endCharacter::Maybe<Integer> kind::Maybe<FoldingRangeKind>
{
  top.foldStartLine = startLine;
  top.foldStartCharacter = startCharacter;
  top.foldEndLine = endLine;
  top.foldEndCharacter = endCharacter;
  top.foldingType = kind;
  top.jsonValue =
    addKeyValuePairToJSONObject("startLine", jsonInteger(startLine), 
    maybeAddKeyValuePairToJSONObject("startCharacter", mapMaybe(jsonInteger, startCharacter), 
    addKeyValuePairToJSONObject("endLine", jsonInteger(endLine), 
    maybeAddKeyValuePairToJSONObject("endCharacter", mapMaybe(jsonInteger, endCharacter), 
    maybeAddKeyValuePairToJSONObject("kind", mapMaybe((.jsonValue), kind), 
    jsonObject([]))))));
}

function parseFoldingRange
Either<ResponseError FoldingRange> ::= val::JSONValue
{
  local startLine :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("startLine", val));
  local startCharacter :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("startCharacter", val));
  local endLine :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("endLine", val));
  local endCharacter :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("endCharacter", val));
  local kind :: Maybe<Either<ResponseError FoldingRangeKind>>
    = mapMaybe(parseFoldingRangeKind, getValueWithKey("kind", val));

  local err :: Maybe<ResponseError> = 
    parseFoldingRangeError(startLine, endLine, kind);

  return
  if err.isJust
  then left(err.fromJust)
  else right(foldingRange(startLine.fromJust, startCharacter, endLine.fromJust, endCharacter, rightMaybe(kind)));
}

function parseFoldingRangeError
Maybe<ResponseError> ::= 
  startLine::Maybe<Integer> endLine::Maybe<Integer> kind::Maybe<Either<ResponseError FoldingRangeKind>>
{
  return
  if !startLine.isJust then just(lspInvalidParams("startLine not set on FoldingRange"))
  else if !endLine.isJust then just(lspInvalidParams("endLine not set on FoldingRange"))
  else if kind.isJust && kind.fromJust.isLeft then just(kind.fromJust.fromLeft)
  else nothing();
}

function foldingRangeToJson
JSONValue ::= val::FoldingRange
{
  return val.jsonValue;
}
