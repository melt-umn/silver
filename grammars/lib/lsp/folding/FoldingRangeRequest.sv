nonterminal FoldingRangeRequest with jsonValue, foldingRangeParams;

synthesized attribute foldingRangeParams :: FoldingRangeParams;

abstract production foldingRangeRequest
top::FoldingRangeRequest::=
  params::FoldingRangeParams
{
  top.foldingRangeParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseFoldingRangeRequest
Either<ResponseError FoldingRangeRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError FoldingRangeParams>>
    = mapMaybe(parseFoldingRangeParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseFoldingRangeRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(foldingRangeRequest(params.fromJust.fromRight));
}

function parseFoldingRangeRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError FoldingRangeParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on FoldingRangeRequest"))
  else nothing();
}

function foldingRangeRequestToJson
JSONValue ::= val::FoldingRangeRequest
{
  return val.jsonValue;
}
