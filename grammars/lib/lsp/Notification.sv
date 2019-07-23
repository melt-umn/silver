grammar lib:lsp;

nonterminal NotificationMessage with methodName, unprocessedParams;
abstract production notificationMessage
top::NotificationMessage ::= method::String params::JSONValue
{
  top.methodName = method;
  top.unprocessedParams = params;
}

function parseNotificationMessage
NotificationMessage ::= val::JSONValue
{
  local method :: Maybe<String> = mapMaybe(jsonToString, getValueWithKey("method", val));
  local params :: Maybe<JSONValue> = getValueWithKey("params", val);
  return notificationMessage(method.fromJust, params.fromJust);
}
