grammar lib:lsp;

nonterminal RequestMessage with requestId, methodName, unprocessedParams;

synthesized attribute requestId :: JSONValue;
synthesized attribute methodName :: String;
synthesized attribute unprocessedParams :: JSONValue;

abstract production requestMessage
top::RequestMessage ::= id::JSONValue method::String params::JSONValue
{
  top.requestId = id;
  top.methodName = method;
  top.unprocessedParams = params;
}

function parseRequestMessage
RequestMessage ::= val::JSONValue
{
  local id :: Maybe<JSONValue> = getValueWithKey("id", val);
  local method :: Maybe<String> = mapMaybe(jsonToString, getValueWithKey("method", val));
  local params :: Maybe<JSONValue> = getValueWithKey("params", val);
  return requestMessage(id.fromJust, method.fromJust, params.fromJust);
}
