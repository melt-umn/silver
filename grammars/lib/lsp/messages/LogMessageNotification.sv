nonterminal LogMessageNotification with jsonValue, logMessageNotificationParams;

synthesized attribute logMessageNotificationParams :: LogMessageParams;

abstract production logMessageNotification
top::LogMessageNotification::=
  params::LogMessageParams
{
  top.logMessageNotificationParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseLogMessageNotification
Either<ResponseError LogMessageNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError LogMessageParams>>
    = mapMaybe(parseLogMessageParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseLogMessageNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(logMessageNotification(params.fromJust.fromRight));
}

function parseLogMessageNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError LogMessageParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on LogMessageNotification"))
  else nothing();
}

function logMessageNotificationToJson
JSONValue ::= val::LogMessageNotification
{
  return val.jsonValue;
}
