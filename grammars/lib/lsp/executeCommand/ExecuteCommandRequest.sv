nonterminal ExecuteCommandRequest with jsonValue, executeCommandParams;

synthesized attribute executeCommandParams :: ExecuteCommandParams;

abstract production executeCommandRequest
top::ExecuteCommandRequest::=
  params::ExecuteCommandParams
{
  top.executeCommandParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseExecuteCommandRequest
Either<ResponseError ExecuteCommandRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError ExecuteCommandParams>>
    = mapMaybe(parseExecuteCommandParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseExecuteCommandRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(executeCommandRequest(params.fromJust.fromRight));
}

function parseExecuteCommandRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError ExecuteCommandParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on ExecuteCommandRequest"))
  else nothing();
}

function executeCommandRequestToJson
JSONValue ::= val::ExecuteCommandRequest
{
  return val.jsonValue;
}
