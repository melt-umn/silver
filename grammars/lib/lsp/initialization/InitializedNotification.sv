nonterminal InitializedNotification with jsonValue, initializedNotificationParams;

synthesized attribute initializedNotificationParams :: InitializedParams;

abstract production initializedNotification
top::InitializedNotification::=
  params::InitializedParams
{
  top.initializedNotificationParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseInitializedNotification
Either<ResponseError InitializedNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError InitializedParams>>
    = mapMaybe(parseInitializedParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseInitializedNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(initializedNotification(params.fromJust.fromRight));
}

function parseInitializedNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError InitializedParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on InitializedNotification"))
  else nothing();
}

function initializedNotificationToJson
JSONValue ::= val::InitializedNotification
{
  return val.jsonValue;
}
