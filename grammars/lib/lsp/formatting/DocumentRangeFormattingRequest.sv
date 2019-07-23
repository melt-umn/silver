nonterminal DocumentRangeFormattingRequest with jsonValue, documentRangeFormattingRequestParams;

synthesized attribute documentRangeFormattingRequestParams :: DocumentRangeFormattingParams;

abstract production documentRangeFormattingRequest
top::DocumentRangeFormattingRequest::=
  params::DocumentRangeFormattingParams
{
  top.documentRangeFormattingRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDocumentRangeFormattingRequest
Either<ResponseError DocumentRangeFormattingRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DocumentRangeFormattingParams>>
    = mapMaybe(parseDocumentRangeFormattingParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentRangeFormattingRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentRangeFormattingRequest(params.fromJust.fromRight));
}

function parseDocumentRangeFormattingRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DocumentRangeFormattingParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DocumentRangeFormattingRequest"))
  else nothing();
}

function documentRangeFormattingRequestToJson
JSONValue ::= val::DocumentRangeFormattingRequest
{
  return val.jsonValue;
}
