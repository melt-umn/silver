nonterminal RenameRequest with jsonValue, renameParams;

synthesized attribute renameParams :: RenameParams;

abstract production renameRequest
top::RenameRequest::=
  params::RenameParams
{
  top.renameParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseRenameRequest
Either<ResponseError RenameRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError RenameParams>>
    = mapMaybe(parseRenameParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseRenameRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(renameRequest(params.fromJust.fromRight));
}

function parseRenameRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError RenameParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on RenameRequest"))
  else nothing();
}

function renameRequestToJson
JSONValue ::= val::RenameRequest
{
  return val.jsonValue;
}
