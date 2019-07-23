nonterminal PublishDiagnosticsNotification with jsonValue, publishDiagnosticsParams;

synthesized attribute publishDiagnosticsParams :: PublishDiagnosticsParams;

abstract production publishDiagnosticsNotification
top::PublishDiagnosticsNotification::=
  params::PublishDiagnosticsParams
{
  top.publishDiagnosticsParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parsePublishDiagnosticsNotification
Either<ResponseError PublishDiagnosticsNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError PublishDiagnosticsParams>>
    = mapMaybe(parsePublishDiagnosticsParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parsePublishDiagnosticsNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(publishDiagnosticsNotification(params.fromJust.fromRight));
}

function parsePublishDiagnosticsNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError PublishDiagnosticsParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on PublishDiagnosticsNotification"))
  else nothing();
}

function publishDiagnosticsNotificationToJson
JSONValue ::= val::PublishDiagnosticsNotification
{
  return val.jsonValue;
}
