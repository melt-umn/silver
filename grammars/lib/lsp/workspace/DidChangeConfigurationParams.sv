nonterminal DidChangeConfigurationParams with jsonValue, configurationSettings;

synthesized attribute configurationSettings :: JSONValue;

abstract production didChangeConfigurationParams
top::DidChangeConfigurationParams::=
  settings::JSONValue
{
  top.configurationSettings = settings;
  top.jsonValue =
    addKeyValuePairToJSONObject("settings", settings, 
    jsonObject([]));
}

function parseDidChangeConfigurationParams
Either<ResponseError DidChangeConfigurationParams> ::= val::JSONValue
{
  local settings :: Maybe<JSONValue>
    = mapMaybe(identity, getValueWithKey("settings", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeConfigurationParamsError(settings);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeConfigurationParams(settings.fromJust));
}

function parseDidChangeConfigurationParamsError
Maybe<ResponseError> ::= 
  settings::Maybe<JSONValue>
{
  return
  if !settings.isJust then just(lspInvalidParams("settings not set on DidChangeConfigurationParams"))
  else nothing();
}

function didChangeConfigurationParamsToJson
JSONValue ::= val::DidChangeConfigurationParams
{
  return val.jsonValue;
}
