nonterminal DidSaveTextDocumentParams with jsonValue, textDocumentId, contentWhenSaved;

synthesized attribute contentWhenSaved :: Maybe<String>;

abstract production didSaveTextDocumentParams
top::DidSaveTextDocumentParams::=
  textDocument::TextDocumentIdentifier text::Maybe<String>
{
  top.textDocumentId = textDocument;
  top.contentWhenSaved = text;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    maybeAddKeyValuePairToJSONObject("text", mapMaybe(jsonString, text), 
    jsonObject([])));
}

function parseDidSaveTextDocumentParams
Either<ResponseError DidSaveTextDocumentParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local text :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("text", val));

  local err :: Maybe<ResponseError> = 
    parseDidSaveTextDocumentParamsError(textDocument);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didSaveTextDocumentParams(textDocument.fromJust.fromRight, text));
}

function parseDidSaveTextDocumentParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DidSaveTextDocumentParams"))
  else nothing();
}

function didSaveTextDocumentParamsToJson
JSONValue ::= val::DidSaveTextDocumentParams
{
  return val.jsonValue;
}
