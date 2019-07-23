nonterminal DocumentColorRequest with jsonValue, documentColorParams;

synthesized attribute documentColorParams :: DocumentColorParams;

abstract production documentColorRequest
top::DocumentColorRequest::=
  params::DocumentColorParams
{
  top.documentColorParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDocumentColorRequest
Either<ResponseError DocumentColorRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DocumentColorParams>>
    = mapMaybe(parseDocumentColorParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentColorRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentColorRequest(params.fromJust.fromRight));
}

function parseDocumentColorRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DocumentColorParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DocumentColorRequest"))
  else nothing();
}

function documentColorRequestToJson
JSONValue ::= val::DocumentColorRequest
{
  return val.jsonValue;
}
