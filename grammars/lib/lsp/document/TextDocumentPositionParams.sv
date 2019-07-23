nonterminal TextDocumentPositionParams with jsonValue, textDocumentId, position;

synthesized attribute textDocumentId :: TextDocumentIdentifier;

abstract production textDocumentPositionParams
top::TextDocumentPositionParams::=
  textDocument::TextDocumentIdentifier position::Position
{
  top.textDocumentId = textDocument;
  top.position = position;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("position", position.jsonValue, 
    jsonObject([])));
}

function parseTextDocumentPositionParams
Either<ResponseError TextDocumentPositionParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local position :: Maybe<Either<ResponseError Position>>
    = mapMaybe(parsePosition, getValueWithKey("position", val));

  local err :: Maybe<ResponseError> = 
    parseTextDocumentPositionParamsError(textDocument, position);

  return
  if err.isJust
  then left(err.fromJust)
  else right(textDocumentPositionParams(textDocument.fromJust.fromRight, position.fromJust.fromRight));
}

function parseTextDocumentPositionParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> position::Maybe<Either<ResponseError Position>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on TextDocumentPositionParams"))
  else if !position.isJust|| position.fromJust.isLeft then just(lspInvalidParams("position not set or invalid on TextDocumentPositionParams"))
  else nothing();
}

function textDocumentPositionParamsToJson
JSONValue ::= val::TextDocumentPositionParams
{
  return val.jsonValue;
}
