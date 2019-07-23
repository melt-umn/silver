nonterminal ConfigurationRequest with jsonValue, configurationRequestParams;

synthesized attribute configurationRequestParams :: ConfigurationParams;

abstract production configurationRequest
top::ConfigurationRequest::=
  params::ConfigurationParams
{
  top.configurationRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseConfigurationRequest
Either<ResponseError ConfigurationRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError ConfigurationParams>>
    = mapMaybe(parseConfigurationParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseConfigurationRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(configurationRequest(params.fromJust.fromRight));
}

function parseConfigurationRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError ConfigurationParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on ConfigurationRequest"))
  else nothing();
}

function configurationRequestToJson
JSONValue ::= val::ConfigurationRequest
{
  return val.jsonValue;
}
