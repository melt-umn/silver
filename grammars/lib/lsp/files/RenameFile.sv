nonterminal RenameFile with jsonValue, fileOperationKind, oldUri, newUri, renameFileOptions;

synthesized attribute oldUri :: DocumentUri;
synthesized attribute newUri :: DocumentUri;
synthesized attribute renameFileOptions :: Maybe<RenameFileOptions>;

abstract production renameFile
top::RenameFile::=
  oldUri::DocumentUri newUri::DocumentUri options::Maybe<RenameFileOptions>
{
  top.fileOperationKind = "rename";
  top.oldUri = oldUri;
  top.newUri = newUri;
  top.renameFileOptions = options;
  top.jsonValue =
    addKeyValuePairToJSONObject("kind", jsonString(top.fileOperationKind), 
    addKeyValuePairToJSONObject("oldUri", jsonString(oldUri), 
    addKeyValuePairToJSONObject("newUri", jsonString(newUri), 
    maybeAddKeyValuePairToJSONObject("options", mapMaybe((.jsonValue), options), 
    jsonObject([])))));
}

function parseRenameFile
Either<ResponseError RenameFile> ::= val::JSONValue
{
  local kind :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("kind", val));
  local oldUri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("oldUri", val));
  local newUri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("newUri", val));
  local options :: Maybe<Either<ResponseError RenameFileOptions>>
    = mapMaybe(parseRenameFileOptions, getValueWithKey("options", val));

  local err :: Maybe<ResponseError> = 
    parseRenameFileError(kind, oldUri, newUri, options);

  return
  if err.isJust
  then left(err.fromJust)
  else right(renameFile(oldUri.fromJust, newUri.fromJust, rightMaybe(options)));
}

function parseRenameFileError
Maybe<ResponseError> ::= 
  kind::Maybe<String> oldUri::Maybe<DocumentUri> newUri::Maybe<DocumentUri> options::Maybe<Either<ResponseError RenameFileOptions>>
{
  return
  if !kind.isJust || stringEq("rename",kind.fromJust) then just(lspInvalidParams("kind must have value rename but does not"))
  else if !oldUri.isJust then just(lspInvalidParams("oldUri not set on RenameFile"))
  else if !newUri.isJust then just(lspInvalidParams("newUri not set on RenameFile"))
  else if options.isJust && options.fromJust.isLeft then just(options.fromJust.fromLeft)
  else nothing();
}

function renameFileToJson
JSONValue ::= val::RenameFile
{
  return val.jsonValue;
}
