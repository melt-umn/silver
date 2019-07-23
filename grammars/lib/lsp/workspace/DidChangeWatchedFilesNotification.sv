nonterminal DidChangeWatchedFilesNotification with jsonValue, didChangeWatchedFilesParams;

synthesized attribute didChangeWatchedFilesParams :: DidChangeWatchedFilesParams;

abstract production didChangeWatchedFilesNotification
top::DidChangeWatchedFilesNotification::=
  params::DidChangeWatchedFilesParams
{
  top.didChangeWatchedFilesParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDidChangeWatchedFilesNotification
Either<ResponseError DidChangeWatchedFilesNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DidChangeWatchedFilesParams>>
    = mapMaybe(parseDidChangeWatchedFilesParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeWatchedFilesNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeWatchedFilesNotification(params.fromJust.fromRight));
}

function parseDidChangeWatchedFilesNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DidChangeWatchedFilesParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DidChangeWatchedFilesNotification"))
  else nothing();
}

function didChangeWatchedFilesNotificationToJson
JSONValue ::= val::DidChangeWatchedFilesNotification
{
  return val.jsonValue;
}
