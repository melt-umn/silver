nonterminal DidCloseTextDocumentParams with jsonValue, documentId;


abstract production didCloseTextDocumentParams
top::DidCloseTextDocumentParams::=
  textDocument::TextDocumentIdentifier
{
  top.documentId = textDocument;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    jsonObject([]));
}

function parseDidCloseTextDocumentParams
Either<ResponseError DidCloseTextDocumentParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));

  local err :: Maybe<ResponseError> = 
    parseDidCloseTextDocumentParamsError(textDocument);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didCloseTextDocumentParams(textDocument.fromJust.fromRight));
}

function parseDidCloseTextDocumentParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DidCloseTextDocumentParams"))
  else nothing();
}

function didCloseTextDocumentParamsToJson
JSONValue ::= val::DidCloseTextDocumentParams
{
  return val.jsonValue;
}
