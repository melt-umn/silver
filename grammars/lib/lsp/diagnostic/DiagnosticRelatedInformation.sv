nonterminal DiagnosticRelatedInformation with jsonValue, relatedDiagnosticLocation, relatedDiagnosticMessage;

synthesized attribute relatedDiagnosticLocation :: Location;
synthesized attribute relatedDiagnosticMessage :: String;

abstract production diagnosticRelatedInformation
top::DiagnosticRelatedInformation::=
  location::Location message::String
{
  top.relatedDiagnosticLocation = location;
  top.relatedDiagnosticMessage = message;
  top.jsonValue =
    addKeyValuePairToJSONObject("location", location.jsonValue, 
    addKeyValuePairToJSONObject("message", jsonString(message), 
    jsonObject([])));
}

function parseDiagnosticRelatedInformation
Either<ResponseError DiagnosticRelatedInformation> ::= val::JSONValue
{
  local location :: Maybe<Either<ResponseError Location>>
    = mapMaybe(parseLocation, getValueWithKey("location", val));
  local message :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("message", val));

  local err :: Maybe<ResponseError> = 
    parseDiagnosticRelatedInformationError(location, message);

  return
  if err.isJust
  then left(err.fromJust)
  else right(diagnosticRelatedInformation(location.fromJust.fromRight, message.fromJust));
}

function parseDiagnosticRelatedInformationError
Maybe<ResponseError> ::= 
  location::Maybe<Either<ResponseError Location>> message::Maybe<String>
{
  return
  if !location.isJust|| location.fromJust.isLeft then just(lspInvalidParams("location not set or invalid on DiagnosticRelatedInformation"))
  else if !message.isJust then just(lspInvalidParams("message not set on DiagnosticRelatedInformation"))
  else nothing();
}

function diagnosticRelatedInformationToJson
JSONValue ::= val::DiagnosticRelatedInformation
{
  return val.jsonValue;
}
