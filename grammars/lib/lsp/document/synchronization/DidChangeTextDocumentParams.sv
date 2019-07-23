nonterminal DidChangeTextDocumentParams with jsonValue, versionedTextDocumentId, documentContentChanges;

synthesized attribute versionedTextDocumentId :: VersionedTextDocumentIdentifier;
synthesized attribute documentContentChanges :: [TextDocumentContentChangeEvent];

abstract production didChangeTextDocumentParams
top::DidChangeTextDocumentParams::=
  textDocument::VersionedTextDocumentIdentifier contentChanges::[TextDocumentContentChangeEvent]
{
  top.versionedTextDocumentId = textDocument;
  top.documentContentChanges = contentChanges;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("contentChanges", jsonArray(map((.jsonValue), contentChanges)), 
    jsonObject([])));
}

function parseDidChangeTextDocumentParams
Either<ResponseError DidChangeTextDocumentParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError VersionedTextDocumentIdentifier>>
    = mapMaybe(parseVersionedTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local contentChanges :: Maybe<[Either<ResponseError TextDocumentContentChangeEvent>]>
    = mapMaybe(mapJsonArray(parseTextDocumentContentChangeEvent, _), getValueWithKey("contentChanges", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeTextDocumentParamsError(textDocument, contentChanges);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeTextDocumentParams(textDocument.fromJust.fromRight, allFromRight(contentChanges.fromJust)));
}

function parseDidChangeTextDocumentParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError VersionedTextDocumentIdentifier>> contentChanges::Maybe<[Either<ResponseError TextDocumentContentChangeEvent>]>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on DidChangeTextDocumentParams"))
  else if !contentChanges.isJust|| anyLeft(contentChanges.fromJust) then just(lspInvalidParams("contentChanges not set or invalid on DidChangeTextDocumentParams"))
  else nothing();
}

function didChangeTextDocumentParamsToJson
JSONValue ::= val::DidChangeTextDocumentParams
{
  return val.jsonValue;
}
