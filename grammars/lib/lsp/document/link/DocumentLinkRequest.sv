nonterminal DocumentLinkRequest with jsonValue, documentLinkParams;

synthesized attribute documentLinkParams :: DocumentLinkParams;

abstract production documentLinkRequest
top::DocumentLinkRequest::=
  params::DocumentLinkParams
{
  top.documentLinkParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDocumentLinkRequest
Either<ResponseError DocumentLinkRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DocumentLinkParams>>
    = mapMaybe(parseDocumentLinkParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentLinkRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentLinkRequest(params.fromJust.fromRight));
}

function parseDocumentLinkRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DocumentLinkParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DocumentLinkRequest"))
  else nothing();
}

function documentLinkRequestToJson
JSONValue ::= val::DocumentLinkRequest
{
  return val.jsonValue;
}
