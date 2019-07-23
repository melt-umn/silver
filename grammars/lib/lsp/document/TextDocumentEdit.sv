nonterminal TextDocumentEdit with jsonValue, versionedDocumentId, textEdits;


abstract production textDocumentEdit
top::TextDocumentEdit::=
  textDocument::VersionedTextDocumentIdentifier edits::[TextEdit]
{
  top.versionedDocumentId = textDocument;
  top.textEdits = edits;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("edits", jsonArray(map((.jsonValue), edits)), 
    jsonObject([])));
}

function parseTextDocumentEdit
Either<ResponseError TextDocumentEdit> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError VersionedTextDocumentIdentifier>>
    = mapMaybe(parseVersionedTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local edits :: Maybe<[Either<ResponseError TextEdit>]>
    = mapMaybe(mapJsonArray(parseTextEdit, _), getValueWithKey("edits", val));

  local err :: Maybe<ResponseError> = 
    parseTextDocumentEditError(textDocument, edits);

  return
  if err.isJust
  then left(err.fromJust)
  else right(textDocumentEdit(textDocument.fromJust.fromRight, allFromRight(edits.fromJust)));
}

function parseTextDocumentEditError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError VersionedTextDocumentIdentifier>> edits::Maybe<[Either<ResponseError TextEdit>]>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on TextDocumentEdit"))
  else if !edits.isJust|| anyLeft(edits.fromJust) then just(lspInvalidParams("edits not set or invalid on TextDocumentEdit"))
  else nothing();
}

function textDocumentEditToJson
JSONValue ::= val::TextDocumentEdit
{
  return val.jsonValue;
}
