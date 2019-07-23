nonterminal CreateFile with jsonValue, fileOperationKind, uri, createFileOptions;

synthesized attribute createFileOptions :: Maybe<CreateFileOptions>;

abstract production createFile
top::CreateFile::=
  uri::DocumentUri options::Maybe<CreateFileOptions>
{
  top.fileOperationKind = "create";
  top.uri = uri;
  top.createFileOptions = options;
  top.jsonValue =
    addKeyValuePairToJSONObject("kind", jsonString(top.fileOperationKind), 
    addKeyValuePairToJSONObject("uri", jsonString(uri), 
    maybeAddKeyValuePairToJSONObject("options", mapMaybe((.jsonValue), options), 
    jsonObject([]))));
}

function parseCreateFile
Either<ResponseError CreateFile> ::= val::JSONValue
{
  local kind :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("kind", val));
  local uri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("uri", val));
  local options :: Maybe<Either<ResponseError CreateFileOptions>>
    = mapMaybe(parseCreateFileOptions, getValueWithKey("options", val));

  local err :: Maybe<ResponseError> = 
    parseCreateFileError(kind, uri, options);

  return
  if err.isJust
  then left(err.fromJust)
  else right(createFile(uri.fromJust, rightMaybe(options)));
}

function parseCreateFileError
Maybe<ResponseError> ::= 
  kind::Maybe<String> uri::Maybe<DocumentUri> options::Maybe<Either<ResponseError CreateFileOptions>>
{
  return
  if !kind.isJust || stringEq("create",kind.fromJust) then just(lspInvalidParams("kind must have value create but does not"))
  else if !uri.isJust then just(lspInvalidParams("uri not set on CreateFile"))
  else if options.isJust && options.fromJust.isLeft then just(options.fromJust.fromLeft)
  else nothing();
}

function createFileToJson
JSONValue ::= val::CreateFile
{
  return val.jsonValue;
}
