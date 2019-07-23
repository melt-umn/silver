nonterminal DidChangeConfigurationNotification with jsonValue, didChangeConfigurationParams;

synthesized attribute didChangeConfigurationParams :: DidChangeConfigurationParams;

abstract production didChangeConfigurationNotification
top::DidChangeConfigurationNotification::=
  params::DidChangeConfigurationParams
{
  top.didChangeConfigurationParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDidChangeConfigurationNotification
Either<ResponseError DidChangeConfigurationNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DidChangeConfigurationParams>>
    = mapMaybe(parseDidChangeConfigurationParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeConfigurationNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeConfigurationNotification(params.fromJust.fromRight));
}

function parseDidChangeConfigurationNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DidChangeConfigurationParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DidChangeConfigurationNotification"))
  else nothing();
}

function didChangeConfigurationNotificationToJson
JSONValue ::= val::DidChangeConfigurationNotification
{
  return val.jsonValue;
}
