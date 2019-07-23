nonterminal SignatureParameterInformationCapabilities with jsonValue, labelOffsetSupport;

synthesized attribute labelOffsetSupport :: Boolean;

abstract production signatureParameterInformationCapabilities
top::SignatureParameterInformationCapabilities::=
  labelOffsetSupport::Boolean
{
  top.labelOffsetSupport = labelOffsetSupport;
  top.jsonValue =
    addKeyValuePairToJSONObject("labelOffsetSupport", jsonBoolean(labelOffsetSupport), 
    jsonObject([]));
}

function parseSignatureParameterInformationCapabilities
Either<ResponseError SignatureParameterInformationCapabilities> ::= val::JSONValue
{
  local labelOffsetSupport :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("labelOffsetSupport", val));

  local err :: Maybe<ResponseError> = 
    parseSignatureParameterInformationCapabilitiesError(labelOffsetSupport);

  return
  if err.isJust
  then left(err.fromJust)
  else right(signatureParameterInformationCapabilities(labelOffsetSupport.fromJust));
}

function parseSignatureParameterInformationCapabilitiesError
Maybe<ResponseError> ::= 
  labelOffsetSupport::Maybe<Boolean>
{
  return
  if !labelOffsetSupport.isJust then just(lspInvalidParams("labelOffsetSupport not set on SignatureParameterInformationCapabilities"))
  else nothing();
}

function signatureParameterInformationCapabilitiesToJson
JSONValue ::= val::SignatureParameterInformationCapabilities
{
  return val.jsonValue;
}
