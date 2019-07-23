nonterminal GotoTypeDefinitionRequest with jsonValue, gotoTypeDefinitionRequestParams;

synthesized attribute gotoTypeDefinitionRequestParams :: TextDocumentPositionParams;

abstract production gotoTypeDefinitionRequest
top::GotoTypeDefinitionRequest::=
  params::TextDocumentPositionParams
{
  top.gotoTypeDefinitionRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseGotoTypeDefinitionRequest
Either<ResponseError GotoTypeDefinitionRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError TextDocumentPositionParams>>
    = mapMaybe(parseTextDocumentPositionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseGotoTypeDefinitionRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(gotoTypeDefinitionRequest(params.fromJust.fromRight));
}

function parseGotoTypeDefinitionRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError TextDocumentPositionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on GotoTypeDefinitionRequest"))
  else nothing();
}

function gotoTypeDefinitionRequestToJson
JSONValue ::= val::GotoTypeDefinitionRequest
{
  return val.jsonValue;
}
