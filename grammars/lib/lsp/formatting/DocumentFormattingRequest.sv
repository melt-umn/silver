nonterminal DocumentFormattingRequest with jsonValue, documentFormattingRequestParams;

synthesized attribute documentFormattingRequestParams :: DocumentFormattingParams;

abstract production documentFormattingRequest
top::DocumentFormattingRequest::=
  params::DocumentFormattingParams
{
  top.documentFormattingRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDocumentFormattingRequest
Either<ResponseError DocumentFormattingRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DocumentFormattingParams>>
    = mapMaybe(parseDocumentFormattingParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentFormattingRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentFormattingRequest(params.fromJust.fromRight));
}

function parseDocumentFormattingRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DocumentFormattingParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DocumentFormattingRequest"))
  else nothing();
}

function documentFormattingRequestToJson
JSONValue ::= val::DocumentFormattingRequest
{
  return val.jsonValue;
}
