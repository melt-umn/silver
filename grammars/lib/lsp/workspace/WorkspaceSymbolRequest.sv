nonterminal WorkspaceSymbolRequest with jsonValue, workspaceSymbolParams;

synthesized attribute workspaceSymbolParams :: WorkspaceSymbolParams;

abstract production workspaceSymbolRequest
top::WorkspaceSymbolRequest::=
  params::WorkspaceSymbolParams
{
  top.workspaceSymbolParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseWorkspaceSymbolRequest
Either<ResponseError WorkspaceSymbolRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError WorkspaceSymbolParams>>
    = mapMaybe(parseWorkspaceSymbolParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseWorkspaceSymbolRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceSymbolRequest(params.fromJust.fromRight));
}

function parseWorkspaceSymbolRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError WorkspaceSymbolParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on WorkspaceSymbolRequest"))
  else nothing();
}

function workspaceSymbolRequestToJson
JSONValue ::= val::WorkspaceSymbolRequest
{
  return val.jsonValue;
}
