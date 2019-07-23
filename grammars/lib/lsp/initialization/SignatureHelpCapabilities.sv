nonterminal SignatureHelpCapabilities with jsonValue, dynamicRegistration, signatureInformationCapabilities;

synthesized attribute signatureInformationCapabilities :: Maybe<SignatureInformationCapabilities>;

abstract production signatureHelpCapabilities
top::SignatureHelpCapabilities::=
  dynamicRegistration::Maybe<Boolean> signatureInformation::Maybe<SignatureInformationCapabilities>
{
  top.dynamicRegistration = dynamicRegistration;
  top.signatureInformationCapabilities = signatureInformation;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("signatureInformation", mapMaybe((.jsonValue), signatureInformation), 
    jsonObject([])));
}

function parseSignatureHelpCapabilities
Either<ResponseError SignatureHelpCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local signatureInformation :: Maybe<Either<ResponseError SignatureInformationCapabilities>>
    = mapMaybe(parseSignatureInformationCapabilities, getValueWithKey("signatureInformation", val));

  local err :: Maybe<ResponseError> = 
    parseSignatureHelpCapabilitiesError(signatureInformation);

  return
  if err.isJust
  then left(err.fromJust)
  else right(signatureHelpCapabilities(dynamicRegistration, rightMaybe(signatureInformation)));
}

function parseSignatureHelpCapabilitiesError
Maybe<ResponseError> ::= 
  signatureInformation::Maybe<Either<ResponseError SignatureInformationCapabilities>>
{
  return
  if signatureInformation.isJust && signatureInformation.fromJust.isLeft then just(signatureInformation.fromJust.fromLeft)
  else nothing();
}

function signatureHelpCapabilitiesToJson
JSONValue ::= val::SignatureHelpCapabilities
{
  return val.jsonValue;
}
