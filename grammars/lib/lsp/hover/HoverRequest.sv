nonterminal HoverRequest with jsonValue, hoverRequestParams;

synthesized attribute hoverRequestParams :: TextDocumentPositionParams;

abstract production hoverRequest
top::HoverRequest::=
  params::TextDocumentPositionParams
{
  top.hoverRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseHoverRequest
Either<ResponseError HoverRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError TextDocumentPositionParams>>
    = mapMaybe(parseTextDocumentPositionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseHoverRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(hoverRequest(params.fromJust.fromRight));
}

function parseHoverRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError TextDocumentPositionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on HoverRequest"))
  else nothing();
}

function hoverRequestToJson
JSONValue ::= val::HoverRequest
{
  return val.jsonValue;
}
