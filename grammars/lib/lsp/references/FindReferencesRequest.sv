nonterminal FindReferencesRequest with jsonValue, findReferenceParams;

synthesized attribute findReferenceParams :: ReferenceParams;

abstract production findReferencesRequest
top::FindReferencesRequest::=
  params::ReferenceParams
{
  top.findReferenceParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseFindReferencesRequest
Either<ResponseError FindReferencesRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError ReferenceParams>>
    = mapMaybe(parseReferenceParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseFindReferencesRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(findReferencesRequest(params.fromJust.fromRight));
}

function parseFindReferencesRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError ReferenceParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on FindReferencesRequest"))
  else nothing();
}

function findReferencesRequestToJson
JSONValue ::= val::FindReferencesRequest
{
  return val.jsonValue;
}
