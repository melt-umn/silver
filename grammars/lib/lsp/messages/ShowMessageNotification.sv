nonterminal ShowMessageNotification with jsonValue, showMessageNotificationParams;

synthesized attribute showMessageNotificationParams :: ShowMessageParams;

abstract production showMessageNotification
top::ShowMessageNotification::=
  params::ShowMessageParams
{
  top.showMessageNotificationParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseShowMessageNotification
Either<ResponseError ShowMessageNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError ShowMessageParams>>
    = mapMaybe(parseShowMessageParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseShowMessageNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(showMessageNotification(params.fromJust.fromRight));
}

function parseShowMessageNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError ShowMessageParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on ShowMessageNotification"))
  else nothing();
}

function showMessageNotificationToJson
JSONValue ::= val::ShowMessageNotification
{
  return val.jsonValue;
}
