nonterminal DidChangeWatchedFilesParams with jsonValue, fileEvents;

synthesized attribute fileEvents :: [FileEvent];

abstract production didChangeWatchedFilesParams
top::DidChangeWatchedFilesParams::=
  changes::[FileEvent]
{
  top.fileEvents = changes;
  top.jsonValue =
    addKeyValuePairToJSONObject("changes", jsonArray(map((.jsonValue), changes)), 
    jsonObject([]));
}

function parseDidChangeWatchedFilesParams
Either<ResponseError DidChangeWatchedFilesParams> ::= val::JSONValue
{
  local changes :: Maybe<[Either<ResponseError FileEvent>]>
    = mapMaybe(mapJsonArray(parseFileEvent, _), getValueWithKey("changes", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeWatchedFilesParamsError(changes);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeWatchedFilesParams(allFromRight(changes.fromJust)));
}

function parseDidChangeWatchedFilesParamsError
Maybe<ResponseError> ::= 
  changes::Maybe<[Either<ResponseError FileEvent>]>
{
  return
  if !changes.isJust|| anyLeft(changes.fromJust) then just(lspInvalidParams("changes not set or invalid on DidChangeWatchedFilesParams"))
  else nothing();
}

function didChangeWatchedFilesParamsToJson
JSONValue ::= val::DidChangeWatchedFilesParams
{
  return val.jsonValue;
}
