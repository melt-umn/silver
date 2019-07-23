nonterminal Registration with jsonValue, requestRegistrationId, methodRegisteringFor, registrationOptions;

synthesized attribute requestRegistrationId :: String;
synthesized attribute methodRegisteringFor :: String;
synthesized attribute registrationOptions :: Maybe<JSONValue>;

abstract production registration
top::Registration::=
  id::String method::String registerOptions::Maybe<JSONValue>
{
  top.requestRegistrationId = id;
  top.methodRegisteringFor = method;
  top.registrationOptions = registerOptions;
  top.jsonValue =
    addKeyValuePairToJSONObject("id", jsonString(id), 
    addKeyValuePairToJSONObject("method", jsonString(method), 
    maybeAddKeyValuePairToJSONObject("registerOptions", registerOptions, 
    jsonObject([]))));
}

function parseRegistration
Either<ResponseError Registration> ::= val::JSONValue
{
  local id :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("id", val));
  local method :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("method", val));
  local registerOptions :: Maybe<JSONValue>
    = mapMaybe(identity, getValueWithKey("registerOptions", val));

  local err :: Maybe<ResponseError> = 
    parseRegistrationError(id, method);

  return
  if err.isJust
  then left(err.fromJust)
  else right(registration(id.fromJust, method.fromJust, registerOptions));
}

function parseRegistrationError
Maybe<ResponseError> ::= 
  id::Maybe<String> method::Maybe<String>
{
  return
  if !id.isJust then just(lspInvalidParams("id not set on Registration"))
  else if !method.isJust then just(lspInvalidParams("method not set on Registration"))
  else nothing();
}

function registrationToJson
JSONValue ::= val::Registration
{
  return val.jsonValue;
}
