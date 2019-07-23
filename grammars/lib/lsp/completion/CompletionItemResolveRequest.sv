nonterminal CompletionItemResolveRequest with jsonValue, completionItemResolveParams;

synthesized attribute completionItemResolveParams :: CompletionItem;

abstract production completionItemResolveRequest
top::CompletionItemResolveRequest::=
  params::CompletionItem
{
  top.completionItemResolveParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseCompletionItemResolveRequest
Either<ResponseError CompletionItemResolveRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError CompletionItem>>
    = mapMaybe(parseCompletionItem, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionItemResolveRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionItemResolveRequest(params.fromJust.fromRight));
}

function parseCompletionItemResolveRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError CompletionItem>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on CompletionItemResolveRequest"))
  else nothing();
}

function completionItemResolveRequestToJson
JSONValue ::= val::CompletionItemResolveRequest
{
  return val.jsonValue;
}
