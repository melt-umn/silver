nonterminal RegisterCapabilityRequest with jsonValue, registrationParams;

synthesized attribute registrationParams :: RegistrationParams;

abstract production registerCapabilityRequest
top::RegisterCapabilityRequest::=
  params::RegistrationParams
{
  top.registrationParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseRegisterCapabilityRequest
Either<ResponseError RegisterCapabilityRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError RegistrationParams>>
    = mapMaybe(parseRegistrationParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseRegisterCapabilityRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(registerCapabilityRequest(params.fromJust.fromRight));
}

function parseRegisterCapabilityRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError RegistrationParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on RegisterCapabilityRequest"))
  else nothing();
}

function registerCapabilityRequestToJson
JSONValue ::= val::RegisterCapabilityRequest
{
  return val.jsonValue;
}
