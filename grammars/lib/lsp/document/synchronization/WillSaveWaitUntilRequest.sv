nonterminal WillSaveWaitUntilRequest with jsonValue, willSaveWaitUntilRequestParams;

synthesized attribute willSaveWaitUntilRequestParams :: WillSaveTextDocumentParams;

abstract production willSaveWaitUntilRequest
top::WillSaveWaitUntilRequest::=
  params::WillSaveTextDocumentParams
{
  top.willSaveWaitUntilRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseWillSaveWaitUntilRequest
Either<ResponseError WillSaveWaitUntilRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError WillSaveTextDocumentParams>>
    = mapMaybe(parseWillSaveTextDocumentParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseWillSaveWaitUntilRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(willSaveWaitUntilRequest(params.fromJust.fromRight));
}

function parseWillSaveWaitUntilRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError WillSaveTextDocumentParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on WillSaveWaitUntilRequest"))
  else nothing();
}

function willSaveWaitUntilRequestToJson
JSONValue ::= val::WillSaveWaitUntilRequest
{
  return val.jsonValue;
}
