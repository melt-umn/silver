nonterminal DidOpenTextDocumentParams with jsonValue, openedDocument;

synthesized attribute openedDocument :: TextDocumentItem;

abstract production didOpenTextDocumentParams
top::DidOpenTextDocumentParams::=
  textDocument::TextDocumentItem
{
  top.openedDocument = textDocument;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    jsonObject([]));
}

function parseDidOpenTextDocumentParams
Either<ResponseError DidOpenTextDocumentParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentItem>>
    = mapMaybe(parseTextDocumentItem, getValueWithKey("textDocument", val));

  local err :: Maybe<ResponseError> = 
    parseDidOpenTextDocumentParamsError(textDocument);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didOpenTextDocumentParams(textDocument.fromJust.fromRight));
}

function parseDidOpenTextDocumentParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentItem>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DidOpenTextDocumentParams"))
  else nothing();
}

function didOpenTextDocumentParamsToJson
JSONValue ::= val::DidOpenTextDocumentParams
{
  return val.jsonValue;
}
