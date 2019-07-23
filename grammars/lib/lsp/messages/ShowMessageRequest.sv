nonterminal ShowMessageRequest with jsonValue, showMessageRequestParams;

synthesized attribute showMessageRequestParams :: ShowMessageRequestParams;

abstract production showMessageRequest
top::ShowMessageRequest::=
  params::ShowMessageRequestParams
{
  top.showMessageRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseShowMessageRequest
Either<ResponseError ShowMessageRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError ShowMessageRequestParams>>
    = mapMaybe(parseShowMessageRequestParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseShowMessageRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(showMessageRequest(params.fromJust.fromRight));
}

function parseShowMessageRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError ShowMessageRequestParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on ShowMessageRequest"))
  else nothing();
}

function showMessageRequestToJson
JSONValue ::= val::ShowMessageRequest
{
  return val.jsonValue;
}
