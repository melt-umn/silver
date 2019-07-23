nonterminal CodeActionRequest with jsonValue, codeActionParams;

synthesized attribute codeActionParams :: CodeActionParams;

abstract production codeActionRequest
top::CodeActionRequest::=
  params::CodeActionParams
{
  top.codeActionParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseCodeActionRequest
Either<ResponseError CodeActionRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError CodeActionParams>>
    = mapMaybe(parseCodeActionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseCodeActionRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeActionRequest(params.fromJust.fromRight));
}

function parseCodeActionRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError CodeActionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on CodeActionRequest"))
  else nothing();
}

function codeActionRequestToJson
JSONValue ::= val::CodeActionRequest
{
  return val.jsonValue;
}
