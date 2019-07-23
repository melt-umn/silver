nonterminal DocumentSymbolRequest with jsonValue, documentSymbolParams;

synthesized attribute documentSymbolParams :: DocumentSymbolParams;

abstract production documentSymbolRequest
top::DocumentSymbolRequest::=
  params::DocumentSymbolParams
{
  top.documentSymbolParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDocumentSymbolRequest
Either<ResponseError DocumentSymbolRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DocumentSymbolParams>>
    = mapMaybe(parseDocumentSymbolParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentSymbolRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentSymbolRequest(params.fromJust.fromRight));
}

function parseDocumentSymbolRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DocumentSymbolParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DocumentSymbolRequest"))
  else nothing();
}

function documentSymbolRequestToJson
JSONValue ::= val::DocumentSymbolRequest
{
  return val.jsonValue;
}
