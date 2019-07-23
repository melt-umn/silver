nonterminal ExitNotification with jsonValue, exitParams;

synthesized attribute exitParams :: Maybe<JSONValue>;

abstract production exitNotification
top::ExitNotification::=
  params::Maybe<JSONValue>
{
  top.exitParams = params;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("params", params, 
    jsonObject([]));
}

function parseExitNotification
Either<ResponseError ExitNotification> ::= val::JSONValue
{
  local params :: Maybe<JSONValue>
    = mapMaybe(identity, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseExitNotificationError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(exitNotification(params));
}

function parseExitNotificationError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function exitNotificationToJson
JSONValue ::= val::ExitNotification
{
  return val.jsonValue;
}
