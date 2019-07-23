nonterminal RenameParams with jsonValue, textDocumentId, position, renameToString;

synthesized attribute renameToString :: String;

abstract production renameParams
top::RenameParams::=
  textDocument::TextDocumentIdentifier position::Position newName::String
{
  top.textDocumentId = textDocument;
  top.position = position;
  top.renameToString = newName;
  top.jsonValue =
    addKeyValuePairToJSONObject("textDocument", textDocument.jsonValue, 
    addKeyValuePairToJSONObject("position", position.jsonValue, 
    addKeyValuePairToJSONObject("newName", jsonString(newName), 
    jsonObject([]))));
}

function parseRenameParams
Either<ResponseError RenameParams> ::= val::JSONValue
{
  local textDocument :: Maybe<Either<ResponseError TextDocumentIdentifier>>
    = mapMaybe(parseTextDocumentIdentifier, getValueWithKey("textDocument", val));
  local position :: Maybe<Either<ResponseError Position>>
    = mapMaybe(parsePosition, getValueWithKey("position", val));
  local newName :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("newName", val));

  local err :: Maybe<ResponseError> = 
    parseRenameParamsError(textDocument, position, newName);

  return
  if err.isJust
  then left(err.fromJust)
  else right(renameParams(textDocument.fromJust.fromRight, position.fromJust.fromRight, newName.fromJust));
}

function parseRenameParamsError
Maybe<ResponseError> ::= 
  textDocument::Maybe<Either<ResponseError TextDocumentIdentifier>> position::Maybe<Either<ResponseError Position>> newName::Maybe<String>
{
  return
  if !textDocument.isJust|| textDocument.fromJust.isLeft then just(lspInvalidParams("textDocument not set or invalid on RenameParams"))
  else if !position.isJust|| position.fromJust.isLeft then just(lspInvalidParams("position not set or invalid on RenameParams"))
  else if !newName.isJust then just(lspInvalidParams("newName not set on RenameParams"))
  else nothing();
}

function renameParamsToJson
JSONValue ::= val::RenameParams
{
  return val.jsonValue;
}
