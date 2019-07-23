nonterminal Unregistration with jsonValue, requestUnregistrationId, methodUnregisteringFor;

synthesized attribute requestUnregistrationId :: String;
synthesized attribute methodUnregisteringFor :: String;

abstract production unregistration
top::Unregistration::=
  id::String method::String
{
  top.requestUnregistrationId = id;
  top.methodUnregisteringFor = method;
  top.jsonValue =
    addKeyValuePairToJSONObject("id", jsonString(id), 
    addKeyValuePairToJSONObject("method", jsonString(method), 
    jsonObject([])));
}

function parseUnregistration
Either<ResponseError Unregistration> ::= val::JSONValue
{
  local id :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("id", val));
  local method :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("method", val));

  local err :: Maybe<ResponseError> = 
    parseUnregistrationError(id, method);

  return
  if err.isJust
  then left(err.fromJust)
  else right(unregistration(id.fromJust, method.fromJust));
}

function parseUnregistrationError
Maybe<ResponseError> ::= 
  id::Maybe<String> method::Maybe<String>
{
  return
  if !id.isJust then just(lspInvalidParams("id not set on Unregistration"))
  else if !method.isJust then just(lspInvalidParams("method not set on Unregistration"))
  else nothing();
}

function unregistrationToJson
JSONValue ::= val::Unregistration
{
  return val.jsonValue;
}
