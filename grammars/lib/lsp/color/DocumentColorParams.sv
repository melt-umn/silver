nonterminal DocumentColorParams with jsonValue, textDocumentId;


abstract production documentColorParams
top::DocumentColorParams::=
  textDocument::TextDocumentIdentifier
{
  top.textDocumentId = textDocument;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    jsonObject([]));
}

function parseDocumentColorParams
Either<ResponseError DocumentColorParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentColorParamsError(textDocument);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentColorParams(textDocument.fromJust.fromRight));
}

function parseDocumentColorParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DocumentColorParams"))
  else nothing();
}

function documentColorParamsToJson
JSONValue ::= val::DocumentColorParams
{
  return val.jsonValue;
}
