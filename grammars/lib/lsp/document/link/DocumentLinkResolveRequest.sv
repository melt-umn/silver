nonterminal DocumentLinkResolveRequest with jsonValue, documentLinkResolveParams;

synthesized attribute documentLinkResolveParams :: DocumentLink;

abstract production documentLinkResolveRequest
top::DocumentLinkResolveRequest::=
  params::DocumentLink
{
  top.documentLinkResolveParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDocumentLinkResolveRequest
Either<ResponseError DocumentLinkResolveRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DocumentLink>>
    = mapMaybe(parseDocumentLink, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentLinkResolveRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentLinkResolveRequest(params.fromJust.fromRight));
}

function parseDocumentLinkResolveRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DocumentLink>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DocumentLinkResolveRequest"))
  else nothing();
}

function documentLinkResolveRequestToJson
JSONValue ::= val::DocumentLinkResolveRequest
{
  return val.jsonValue;
}
