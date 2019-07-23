nonterminal CodeLensResolveRequest with jsonValue, codeLensResolveParams;

synthesized attribute codeLensResolveParams :: CodeLens;

abstract production codeLensResolveRequest
top::CodeLensResolveRequest::=
  params::CodeLens
{
  top.codeLensResolveParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseCodeLensResolveRequest
Either<ResponseError CodeLensResolveRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError CodeLens>>
    = mapMaybe(parseCodeLens, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseCodeLensResolveRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeLensResolveRequest(params.fromJust.fromRight));
}

function parseCodeLensResolveRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError CodeLens>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on CodeLensResolveRequest"))
  else nothing();
}

function codeLensResolveRequestToJson
JSONValue ::= val::CodeLensResolveRequest
{
  return val.jsonValue;
}
