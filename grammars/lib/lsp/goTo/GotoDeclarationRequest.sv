nonterminal GotoDeclarationRequest with jsonValue, gotoDeclarationRequestParams;

synthesized attribute gotoDeclarationRequestParams :: TextDocumentPositionParams;

abstract production gotoDeclarationRequest
top::GotoDeclarationRequest::=
  params::TextDocumentPositionParams
{
  top.gotoDeclarationRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseGotoDeclarationRequest
Either<ResponseError GotoDeclarationRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError TextDocumentPositionParams>>
    = mapMaybe(parseTextDocumentPositionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseGotoDeclarationRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(gotoDeclarationRequest(params.fromJust.fromRight));
}

function parseGotoDeclarationRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError TextDocumentPositionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on GotoDeclarationRequest"))
  else nothing();
}

function gotoDeclarationRequestToJson
JSONValue ::= val::GotoDeclarationRequest
{
  return val.jsonValue;
}
