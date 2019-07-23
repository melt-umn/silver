nonterminal ApplyWorkspaceEditRequest with jsonValue, applyWorkspaceEditParams;

synthesized attribute applyWorkspaceEditParams :: ApplyWorkspaceEditParams;

abstract production applyWorkspaceEditRequest
top::ApplyWorkspaceEditRequest::=
  params::ApplyWorkspaceEditParams
{
  top.applyWorkspaceEditParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseApplyWorkspaceEditRequest
Either<ResponseError ApplyWorkspaceEditRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError ApplyWorkspaceEditParams>>
    = mapMaybe(parseApplyWorkspaceEditParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseApplyWorkspaceEditRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(applyWorkspaceEditRequest(params.fromJust.fromRight));
}

function parseApplyWorkspaceEditRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError ApplyWorkspaceEditParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on ApplyWorkspaceEditRequest"))
  else nothing();
}

function applyWorkspaceEditRequestToJson
JSONValue ::= val::ApplyWorkspaceEditRequest
{
  return val.jsonValue;
}
