nonterminal FileSystemWatcher with jsonValue, fileWatcherGlobPattern, fileEventWatcher;

synthesized attribute fileWatcherGlobPattern :: String;
synthesized attribute fileEventWatcher :: Maybe<WatchKind>;

abstract production fileSystemWatcher
top::FileSystemWatcher::=
  globPattern::String kind::Maybe<WatchKind>
{
  top.fileWatcherGlobPattern = globPattern;
  top.fileEventWatcher = kind;
  top.jsonValue =
    addKeyValuePairToJSONObject("globPattern", jsonString(globPattern), 
    maybeAddKeyValuePairToJSONObject("kind", mapMaybe((.jsonValue), kind), 
    jsonObject([])));
}

function parseFileSystemWatcher
Either<ResponseError FileSystemWatcher> ::= val::JSONValue
{
  local globPattern :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("globPattern", val));
  local kind :: Maybe<Either<ResponseError WatchKind>>
    = mapMaybe(parseWatchKind, getValueWithKey("kind", val));

  local err :: Maybe<ResponseError> = 
    parseFileSystemWatcherError(globPattern, kind);

  return
  if err.isJust
  then left(err.fromJust)
  else right(fileSystemWatcher(globPattern.fromJust, rightMaybe(kind)));
}

function parseFileSystemWatcherError
Maybe<ResponseError> ::= 
  globPattern::Maybe<String> kind::Maybe<Either<ResponseError WatchKind>>
{
  return
  if !globPattern.isJust then just(lspInvalidParams("globPattern not set on FileSystemWatcher"))
  else if kind.isJust && kind.fromJust.isLeft then just(kind.fromJust.fromLeft)
  else nothing();
}

function fileSystemWatcherToJson
JSONValue ::= val::FileSystemWatcher
{
  return val.jsonValue;
}
