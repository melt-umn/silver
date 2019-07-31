nonterminal DocumentLinkParams with jsonValue, documentId;


abstract production documentLinkParams
top::DocumentLinkParams::=
  textDocument::TextDocumentIdentifier
{
  top.documentId = textDocument;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    jsonObject([]));
}

function parseDocumentLinkParams
Either<ResponseError DocumentLinkParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentLinkParamsError(textDocument);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentLinkParams(textDocument.fromJust.fromRight));
}

function parseDocumentLinkParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DocumentLinkParams"))
  else nothing();
}

function documentLinkParamsToJson
JSONValue ::= val::DocumentLinkParams
{
  return val.jsonValue;
}
