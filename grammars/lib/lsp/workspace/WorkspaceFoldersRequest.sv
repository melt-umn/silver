nonterminal WorkspaceFoldersRequest with jsonValue, workspaceFolderRequestsParams;

synthesized attribute workspaceFolderRequestsParams :: WorkspaceFoldersParams;

abstract production workspaceFoldersRequest
top::WorkspaceFoldersRequest::=
  params::WorkspaceFoldersParams
{
  top.workspaceFolderRequestsParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseWorkspaceFoldersRequest
Either<ResponseError WorkspaceFoldersRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError WorkspaceFoldersParams>>
    = mapMaybe(parseWorkspaceFoldersParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseWorkspaceFoldersRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceFoldersRequest(params.fromJust.fromRight));
}

function parseWorkspaceFoldersRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError WorkspaceFoldersParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on WorkspaceFoldersRequest"))
  else nothing();
}

function workspaceFoldersRequestToJson
JSONValue ::= val::WorkspaceFoldersRequest
{
  return val.jsonValue;
}
