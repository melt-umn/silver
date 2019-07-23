nonterminal SignatureInformationCapabilities with jsonValue, supportedContentFormats, signatureParameterInformationCapabilities;

synthesized attribute signatureParameterInformationCapabilities :: Maybe<SignatureParameterInformationCapabilities>;

abstract production signatureInformationCapabilities
top::SignatureInformationCapabilities::=
  documentationFormat::Maybe<[MarkupKind]> parameterInformation::Maybe<SignatureParameterInformationCapabilities>
{
  top.supportedContentFormats = documentationFormat;
  top.signatureParameterInformationCapabilities = parameterInformation;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("documentationFormat", mapMaybe(jsonArray, mapMaybeList((.jsonValue), documentationFormat)), 
    maybeAddKeyValuePairToJSONObject("parameterInformation", mapMaybe((.jsonValue), parameterInformation), 
    jsonObject([])));
}

function parseSignatureInformationCapabilities
Either<ResponseError SignatureInformationCapabilities> ::= val::JSONValue
{
  local documentationFormat :: Maybe<[Either<ResponseError MarkupKind>]>
    = mapMaybe(mapJsonArray(parseMarkupKind, _), getValueWithKey("documentationFormat", val));
  local parameterInformation :: Maybe<Either<ResponseError SignatureParameterInformationCapabilities>>
    = mapMaybe(parseSignatureParameterInformationCapabilities, getValueWithKey("parameterInformation", val));

  local err :: Maybe<ResponseError> = 
    parseSignatureInformationCapabilitiesError(documentationFormat, parameterInformation);

  return
  if err.isJust
  then left(err.fromJust)
  else right(signatureInformationCapabilities(allFromRightMaybe(documentationFormat), rightMaybe(parameterInformation)));
}

function parseSignatureInformationCapabilitiesError
Maybe<ResponseError> ::= 
  documentationFormat::Maybe<[Either<ResponseError MarkupKind>]> parameterInformation::Maybe<Either<ResponseError SignatureParameterInformationCapabilities>>
{
  return
  if documentationFormat.isJust && anyLeft(documentationFormat.fromJust) then firstLeft(documentationFormat.fromJust)
  else if parameterInformation.isJust && parameterInformation.fromJust.isLeft then just(parameterInformation.fromJust.fromLeft)
  else nothing();
}

function signatureInformationCapabilitiesToJson
JSONValue ::= val::SignatureInformationCapabilities
{
  return val.jsonValue;
}
