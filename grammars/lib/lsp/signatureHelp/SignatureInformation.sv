nonterminal SignatureInformation with jsonValue, signatureLabel, signatureDocumentation, signatureParameters;

synthesized attribute signatureLabel :: String;
synthesized attribute signatureDocumentation :: Maybe<String>;
synthesized attribute signatureParameters :: Maybe<[ParameterInformation]>;

abstract production signatureInformation
top::SignatureInformation::=
  label::String documentation::Maybe<String> parameters::Maybe<[ParameterInformation]>
{
  top.signatureLabel = label;
  top.signatureDocumentation = documentation;
  top.signatureParameters = parameters;
  top.jsonValue =
    addKeyValuePairToJSONObject("label", jsonString(label), 
    maybeAddKeyValuePairToJSONObject("documentation", mapMaybe(jsonString, documentation), 
    maybeAddKeyValuePairToJSONObject("parameters", mapMaybe(jsonArray, mapMaybeList((.jsonValue), parameters)), 
    jsonObject([]))));
}

function parseSignatureInformation
Either<ResponseError SignatureInformation> ::= val::JSONValue
{
  local label :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("label", val));
  local documentation :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("documentation", val));
  local parameters :: Maybe<[Either<ResponseError ParameterInformation>]>
    = mapMaybe(mapJsonArray(parseParameterInformation, _), getValueWithKey("parameters", val));

  local err :: Maybe<ResponseError> = 
    parseSignatureInformationError(label, parameters);

  return
  if err.isJust
  then left(err.fromJust)
  else right(signatureInformation(label.fromJust, documentation, allFromRightMaybe(parameters)));
}

function parseSignatureInformationError
Maybe<ResponseError> ::= 
  label::Maybe<String> parameters::Maybe<[Either<ResponseError ParameterInformation>]>
{
  return
  if !label.isJust then just(lspInvalidParams("label not set on SignatureInformation"))
  else if parameters.isJust && anyLeft(parameters.fromJust) then firstLeft(parameters.fromJust)
  else nothing();
}

function signatureInformationToJson
JSONValue ::= val::SignatureInformation
{
  return val.jsonValue;
}
