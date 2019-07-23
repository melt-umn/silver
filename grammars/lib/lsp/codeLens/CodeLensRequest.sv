nonterminal CodeLensRequest with jsonValue, codeLensParams;

synthesized attribute codeLensParams :: CodeLensParams;

abstract production codeLensRequest
top::CodeLensRequest::=
  params::CodeLensParams
{
  top.codeLensParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseCodeLensRequest
Either<ResponseError CodeLensRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError CodeLensParams>>
    = mapMaybe(parseCodeLensParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseCodeLensRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeLensRequest(params.fromJust.fromRight));
}

function parseCodeLensRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError CodeLensParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on CodeLensRequest"))
  else nothing();
}

function codeLensRequestToJson
JSONValue ::= val::CodeLensRequest
{
  return val.jsonValue;
}
