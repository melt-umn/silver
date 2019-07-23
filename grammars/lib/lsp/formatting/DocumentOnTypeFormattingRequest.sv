nonterminal DocumentOnTypeFormattingRequest with jsonValue, documentOnTypeFormattingRequestParams;

synthesized attribute documentOnTypeFormattingRequestParams :: DocumentOnTypeFormattingParams;

abstract production documentOnTypeFormattingRequest
top::DocumentOnTypeFormattingRequest::=
  params::DocumentOnTypeFormattingParams
{
  top.documentOnTypeFormattingRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDocumentOnTypeFormattingRequest
Either<ResponseError DocumentOnTypeFormattingRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DocumentOnTypeFormattingParams>>
    = mapMaybe(parseDocumentOnTypeFormattingParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentOnTypeFormattingRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentOnTypeFormattingRequest(params.fromJust.fromRight));
}

function parseDocumentOnTypeFormattingRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DocumentOnTypeFormattingParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DocumentOnTypeFormattingRequest"))
  else nothing();
}

function documentOnTypeFormattingRequestToJson
JSONValue ::= val::DocumentOnTypeFormattingRequest
{
  return val.jsonValue;
}
