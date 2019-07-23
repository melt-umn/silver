nonterminal DidChangeWatchedFilesRegistrationOptions with jsonValue, fileSystemWatchers;

synthesized attribute fileSystemWatchers :: [FileSystemWatcher];

abstract production didChangeWatchedFilesRegistrationOptions
top::DidChangeWatchedFilesRegistrationOptions::=
  watchers::[FileSystemWatcher]
{
  top.fileSystemWatchers = watchers;
  top.jsonValue =
    addKeyValuePairToJSONObject("watchers", jsonArray(map((.jsonValue), watchers)), 
    jsonObject([]));
}

function parseDidChangeWatchedFilesRegistrationOptions
Either<ResponseError DidChangeWatchedFilesRegistrationOptions> ::= val::JSONValue
{
  local watchers :: Maybe<[Either<ResponseError FileSystemWatcher>]>
    = mapMaybe(mapJsonArray(parseFileSystemWatcher, _), getValueWithKey("watchers", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeWatchedFilesRegistrationOptionsError(watchers);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeWatchedFilesRegistrationOptions(allFromRight(watchers.fromJust)));
}

function parseDidChangeWatchedFilesRegistrationOptionsError
Maybe<ResponseError> ::= 
  watchers::Maybe<[Either<ResponseError FileSystemWatcher>]>
{
  return
  if !watchers.isJust|| anyLeft(watchers.fromJust) then just(lspInvalidParams("watchers not set or invalid on DidChangeWatchedFilesRegistrationOptions"))
  else nothing();
}

function didChangeWatchedFilesRegistrationOptionsToJson
JSONValue ::= val::DidChangeWatchedFilesRegistrationOptions
{
  return val.jsonValue;
}
