nonterminal CompletionRequest with jsonValue, completionParams;

synthesized attribute completionParams :: CompletionParams;

abstract production completionRequest
top::CompletionRequest::=
  params::CompletionParams
{
  top.completionParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseCompletionRequest
Either<ResponseError CompletionRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError CompletionParams>>
    = mapMaybe(parseCompletionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionRequest(params.fromJust.fromRight));
}

function parseCompletionRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError CompletionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on CompletionRequest"))
  else nothing();
}

function completionRequestToJson
JSONValue ::= val::CompletionRequest
{
  return val.jsonValue;
}
