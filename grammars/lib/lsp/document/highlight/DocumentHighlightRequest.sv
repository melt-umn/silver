nonterminal DocumentHighlightRequest with jsonValue, documentHighlightParams;

synthesized attribute documentHighlightParams :: TextDocumentPositionParams;

abstract production documentHighlightRequest
top::DocumentHighlightRequest::=
  params::TextDocumentPositionParams
{
  top.documentHighlightParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDocumentHighlightRequest
Either<ResponseError DocumentHighlightRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError TextDocumentPositionParams>>
    = mapMaybe(parseTextDocumentPositionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentHighlightRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentHighlightRequest(params.fromJust.fromRight));
}

function parseDocumentHighlightRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError TextDocumentPositionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DocumentHighlightRequest"))
  else nothing();
}

function documentHighlightRequestToJson
JSONValue ::= val::DocumentHighlightRequest
{
  return val.jsonValue;
}
