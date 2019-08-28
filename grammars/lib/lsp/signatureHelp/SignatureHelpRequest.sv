nonterminal SignatureHelpRequest with jsonValue, signatureHelpRequestParams;

synthesized attribute signatureHelpRequestParams :: TextDocumentPositionParams;

abstract production signatureHelpRequest
top::SignatureHelpRequest::=
  params::TextDocumentPositionParams
{
  top.signatureHelpRequestParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseSignatureHelpRequest
Either<ResponseError SignatureHelpRequest> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError TextDocumentPositionParams>>
    = mapMaybe(parseTextDocumentPositionParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseSignatureHelpRequestError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(signatureHelpRequest(params.fromJust.fromRight));
}

function parseSignatureHelpRequestError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError TextDocumentPositionParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on SignatureHelpRequest"))
  else nothing();
}

function signatureHelpRequestToJson
JSONValue ::= val::SignatureHelpRequest
{
  return val.jsonValue;
}
