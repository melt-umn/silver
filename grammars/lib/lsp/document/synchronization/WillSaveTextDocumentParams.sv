nonterminal WillSaveTextDocumentParams with jsonValue, documentId, reasonForSave;

synthesized attribute reasonForSave :: TextDocumentSaveReason;

abstract production willSaveTextDocumentParams
top::WillSaveTextDocumentParams::=
  textDocument::TextDocumentIdentifier reason::TextDocumentSaveReason
{
  top.documentId = textDocument;
  top.reasonForSave = reason;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("reason", reason.jsonValue, 
    jsonObject([])));
}

function parseWillSaveTextDocumentParams
Either<ResponseError WillSaveTextDocumentParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local reason :: Maybe<Either<ResponseError TextDocumentSaveReason>>
    = mapMaybe(parseTextDocumentSaveReason, getValueWithKey("reason", val));

  local err :: Maybe<ResponseError> = 
    parseWillSaveTextDocumentParamsError(textDocument, reason);

  return
  if err.isJust
  then left(err.fromJust)
  else right(willSaveTextDocumentParams(textDocument.fromJust.fromRight, reason.fromJust.fromRight));
}

function parseWillSaveTextDocumentParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> reason::Maybe<Either<ResponseError TextDocumentSaveReason>>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on WillSaveTextDocumentParams"))
  else if !reason.isJust|| reason.fromJust.isLeft then just(lspInvalidParams("reason not set or invalid on WillSaveTextDocumentParams"))
  else nothing();
}

function willSaveTextDocumentParamsToJson
JSONValue ::= val::WillSaveTextDocumentParams
{
  return val.jsonValue;
}
