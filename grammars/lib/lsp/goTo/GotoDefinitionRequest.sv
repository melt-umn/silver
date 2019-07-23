nonterminal GotoDefinitionRequest with jsonValue, gotoDefinitionRequestParams;

synthesized attribute gotoDefinitionRequestParams :: TextDocumentPositionParams;

abstract production gotoDefinitionRequest
top::GotoDefinitionRequest::=
  params::TextDocumentPositionParams
{
  top.gotoDefinitionRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseGotoDefinitionRequest
Either<ResponseError GotoDefinitionRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError TextDocumentPositionParams>>
    = mapMaybe(parseTextDocumentPositionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseGotoDefinitionRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(gotoDefinitionRequest(params.fromJust.fromRight));
}

function parseGotoDefinitionRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError TextDocumentPositionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on GotoDefinitionRequest"))
  else nothing();
}

function gotoDefinitionRequestToJson
JSONValue ::= val::GotoDefinitionRequest
{
  return val.jsonValue;
}
