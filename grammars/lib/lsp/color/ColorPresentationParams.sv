nonterminal ColorPresentationParams with jsonValue, documentId, colorValue, range;


abstract production colorPresentationParams
top::ColorPresentationParams::=
  textDocument::TextDocumentIdentifier color::Color range::Range
{
  top.documentId = textDocument;
  top.colorValue = color;
  top.range = range;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("color", color.jsonValue, 
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    jsonObject([]))));
}

function parseColorPresentationParams
Either<ResponseError ColorPresentationParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local color :: Maybe<Either<ResponseError Color>>
    = mapMaybe(parseColor, getValueWithKey("color", val));
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));

  local err :: Maybe<ResponseError> = 
    parseColorPresentationParamsError(textDocument, color, range);

  return
  if err.isJust
  then left(err.fromJust)
  else right(colorPresentationParams(textDocument.fromJust.fromRight, color.fromJust.fromRight, range.fromJust.fromRight));
}

function parseColorPresentationParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> color::Maybe<Either<ResponseError Color>> range::Maybe<Either<ResponseError Range>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on ColorPresentationParams"))
  else if !color.isJust|| color.fromJust.isLeft then just(lspInvalidParams("color not set or invalid on ColorPresentationParams"))
  else if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on ColorPresentationParams"))
  else nothing();
}

function colorPresentationParamsToJson
JSONValue ::= val::ColorPresentationParams
{
  return val.jsonValue;
}
