nonterminal DeleteFile with jsonValue, fileOperationKind, uri, deleteFileOptions;

synthesized attribute deleteFileOptions :: Maybe<DeleteFileOptions>;

abstract production deleteFile
top::DeleteFile::=
  uri::DocumentUri options::Maybe<DeleteFileOptions>
{
  top.fileOperationKind = "delete";
  top.uri = uri;
  top.deleteFileOptions = options;
  top.jsonValue =
    addKeyValuePairToJSONObject("kind", jsonString(top.fileOperationKind), 
    addKeyValuePairToJSONObject("uri", jsonString(uri), 
    maybeAddKeyValuePairToJSONObject("options", mapMaybe((.jsonValue), options), 
    jsonObject([]))));
}

function parseDeleteFile
Either<ResponseError DeleteFile> ::= val::JSONValue
{
  local kind :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("kind", val));
  local uri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("uri", val));
  local options :: Maybe<Either<ResponseError DeleteFileOptions>>
    = mapMaybe(parseDeleteFileOptions, getValueWithKey("options", val));

  local err :: Maybe<ResponseError> = 
    parseDeleteFileError(kind, uri, options);

  return
  if err.isJust
  then left(err.fromJust)
  else right(deleteFile(uri.fromJust, rightMaybe(options)));
}

function parseDeleteFileError
Maybe<ResponseError> ::= 
  kind::Maybe<String> uri::Maybe<DocumentUri> options::Maybe<Either<ResponseError DeleteFileOptions>>
{
  return
  if !kind.isJust || stringEq("delete",kind.fromJust) then just(lspInvalidParams("kind must have value delete but does not"))
  else if !uri.isJust then just(lspInvalidParams("uri not set on DeleteFile"))
  else if options.isJust && options.fromJust.isLeft then just(options.fromJust.fromLeft)
  else nothing();
}

function deleteFileToJson
JSONValue ::= val::DeleteFile
{
  return val.jsonValue;
}
