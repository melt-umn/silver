nonterminal PrepareRenameRequest with jsonValue, prepareRenameParams;

synthesized attribute prepareRenameParams :: TextDocumentPositionParams;

abstract production prepareRenameRequest
top::PrepareRenameRequest::=
  params::TextDocumentPositionParams
{
  top.prepareRenameParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parsePrepareRenameRequest
Either<ResponseError PrepareRenameRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError TextDocumentPositionParams>>
    = mapMaybe(parseTextDocumentPositionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parsePrepareRenameRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(prepareRenameRequest(params.fromJust.fromRight));
}

function parsePrepareRenameRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError TextDocumentPositionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on PrepareRenameRequest"))
  else nothing();
}

function prepareRenameRequestToJson
JSONValue ::= val::PrepareRenameRequest
{
  return val.jsonValue;
}
