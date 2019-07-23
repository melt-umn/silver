nonterminal GotoImplementationRequest with jsonValue, gotoImplementationRequestParams;

synthesized attribute gotoImplementationRequestParams :: TextDocumentPositionParams;

abstract production gotoImplementationRequest
top::GotoImplementationRequest::=
  params::TextDocumentPositionParams
{
  top.gotoImplementationRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseGotoImplementationRequest
Either<ResponseError GotoImplementationRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError TextDocumentPositionParams>>
    = mapMaybe(parseTextDocumentPositionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseGotoImplementationRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(gotoImplementationRequest(params.fromJust.fromRight));
}

function parseGotoImplementationRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError TextDocumentPositionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on GotoImplementationRequest"))
  else nothing();
}

function gotoImplementationRequestToJson
JSONValue ::= val::GotoImplementationRequest
{
  return val.jsonValue;
}
