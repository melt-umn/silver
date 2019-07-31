nonterminal DocumentSymbolParams with jsonValue, documentId;


abstract production documentSymbolParams
top::DocumentSymbolParams::=
  textDocument::TextDocumentIdentifier
{
  top.documentId = textDocument;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    jsonObject([]));
}

function parseDocumentSymbolParams
Either<ResponseError DocumentSymbolParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentSymbolParamsError(textDocument);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentSymbolParams(textDocument.fromJust.fromRight));
}

function parseDocumentSymbolParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DocumentSymbolParams"))
  else nothing();
}

function documentSymbolParamsToJson
JSONValue ::= val::DocumentSymbolParams
{
  return val.jsonValue;
}
