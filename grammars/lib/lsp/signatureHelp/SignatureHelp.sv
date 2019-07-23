nonterminal SignatureHelp with jsonValue, signatureInformations, signatureToShow, parameterToShow;

synthesized attribute signatureInformations :: [SignatureInformation];
synthesized attribute signatureToShow :: Maybe<Integer>;
synthesized attribute parameterToShow :: Maybe<Integer>;

abstract production signatureHelp
top::SignatureHelp::=
  signatures::[SignatureInformation] activeSignature::Maybe<Integer> activeParameter::Maybe<Integer>
{
  top.signatureInformations = signatures;
  top.signatureToShow = activeSignature;
  top.parameterToShow = activeParameter;
  top.jsonValue =
    addKeyValuePairToJSONObject("signatures", jsonArray(map((.jsonValue), signatures)), 
    maybeAddKeyValuePairToJSONObject("activeSignature", mapMaybe(jsonInteger, activeSignature), 
    maybeAddKeyValuePairToJSONObject("activeParameter", mapMaybe(jsonInteger, activeParameter), 
    jsonObject([]))));
}

function parseSignatureHelp
Either<ResponseError SignatureHelp> ::= val::JSONValue
{
  local signatures :: Maybe<[Either<ResponseError SignatureInformation>]>
    = mapMaybe(mapJsonArray(parseSignatureInformation, _), getValueWithKey("signatures", val));
  local activeSignature :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("activeSignature", val));
  local activeParameter :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("activeParameter", val));

  local err :: Maybe<ResponseError> = 
    parseSignatureHelpError(signatures);

  return
  if err.isJust
  then left(err.fromJust)
  else right(signatureHelp(allFromRight(signatures.fromJust), activeSignature, activeParameter));
}

function parseSignatureHelpError
Maybe<ResponseError> ::= 
  signatures::Maybe<[Either<ResponseError SignatureInformation>]>
{
  return
  if !signatures.isJust|| anyLeft(signatures.fromJust) then just(lspInvalidParams("signatures not set or invalid on SignatureHelp"))
  else nothing();
}

function signatureHelpToJson
JSONValue ::= val::SignatureHelp
{
  return val.jsonValue;
}
