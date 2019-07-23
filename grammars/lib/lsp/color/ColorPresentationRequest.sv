nonterminal ColorPresentationRequest with jsonValue, colorPresentationParams;

synthesized attribute colorPresentationParams :: ColorPresentationParams;

abstract production colorPresentationRequest
top::ColorPresentationRequest::=
  params::ColorPresentationParams
{
  top.colorPresentationParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseColorPresentationRequest
Either<ResponseError ColorPresentationRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError ColorPresentationParams>>
    = mapMaybe(parseColorPresentationParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseColorPresentationRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(colorPresentationRequest(params.fromJust.fromRight));
}

function parseColorPresentationRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError ColorPresentationParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on ColorPresentationRequest"))
  else nothing();
}

function colorPresentationRequestToJson
JSONValue ::= val::ColorPresentationRequest
{
  return val.jsonValue;
}
