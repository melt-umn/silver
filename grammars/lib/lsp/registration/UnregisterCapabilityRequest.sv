nonterminal UnregisterCapabilityRequest with jsonValue, unregistrationParams;

synthesized attribute unregistrationParams :: UnregistrationParams;

abstract production unregisterCapabilityRequest
top::UnregisterCapabilityRequest::=
  params::UnregistrationParams
{
  top.unregistrationParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseUnregisterCapabilityRequest
Either<ResponseError UnregisterCapabilityRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError UnregistrationParams>>
    = mapMaybe(parseUnregistrationParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseUnregisterCapabilityRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(unregisterCapabilityRequest(params.fromJust.fromRight));
}

function parseUnregisterCapabilityRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError UnregistrationParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on UnregisterCapabilityRequest"))
  else nothing();
}

function unregisterCapabilityRequestToJson
JSONValue ::= val::UnregisterCapabilityRequest
{
  return val.jsonValue;
}
