nonterminal InitializeRequest with jsonValue, initializeRequestParams;

synthesized attribute initializeRequestParams :: InitializeParams;

abstract production initializeRequest
top::InitializeRequest::=
  params::InitializeParams
{
  top.initializeRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseInitializeRequest
Either<ResponseError InitializeRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError InitializeParams>>
    = mapMaybe(parseInitializeParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseInitializeRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(initializeRequest(params.fromJust.fromRight));
}

function parseInitializeRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError InitializeParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on InitializeRequest"))
  else nothing();
}

function initializeRequestToJson
JSONValue ::= val::InitializeRequest
{
  return val.jsonValue;
}
