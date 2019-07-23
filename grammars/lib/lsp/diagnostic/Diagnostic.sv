nonterminal Diagnostic with jsonValue, range, severityOfDiagnostic, diagnosticCode, diagnosticSource, diagnosticMessage, diagnosticRelatedInfo;

synthesized attribute severityOfDiagnostic :: Maybe<DiagnosticSeverity>;
synthesized attribute diagnosticCode :: Maybe<Integer>;
synthesized attribute diagnosticSource :: Maybe<String>;
synthesized attribute diagnosticMessage :: String;
synthesized attribute diagnosticRelatedInfo :: Maybe<[DiagnosticRelatedInformation]>;

abstract production diagnostic
top::Diagnostic::=
  range::Range severity::Maybe<DiagnosticSeverity> code::Maybe<Integer> source::Maybe<String> message::String relatedInformation::Maybe<[DiagnosticRelatedInformation]>
{
  top.range = range;
  top.severityOfDiagnostic = severity;
  top.diagnosticCode = code;
  top.diagnosticSource = source;
  top.diagnosticMessage = message;
  top.diagnosticRelatedInfo = relatedInformation;
  top.jsonValue =
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    maybeAddKeyValuePairToJSONObject("severity", mapMaybe((.jsonValue), severity), 
    maybeAddKeyValuePairToJSONObject("code", mapMaybe(jsonInteger, code), 
    maybeAddKeyValuePairToJSONObject("source", mapMaybe(jsonString, source), 
    addKeyValuePairToJSONObject("message", jsonString(message), 
    maybeAddKeyValuePairToJSONObject("relatedInformation", mapMaybe(jsonArray, mapMaybeList((.jsonValue), relatedInformation)), 
    jsonObject([])))))));
}

function parseDiagnostic
Either<ResponseError Diagnostic> ::= val::JSONValue
{
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local severity :: Maybe<Either<ResponseError DiagnosticSeverity>>
    = mapMaybe(parseDiagnosticSeverity, getValueWithKey("severity", val));
  local code :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("code", val));
  local source :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("source", val));
  local message :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("message", val));
  local relatedInformation :: Maybe<[Either<ResponseError DiagnosticRelatedInformation>]>
    = mapMaybe(mapJsonArray(parseDiagnosticRelatedInformation, _), getValueWithKey("relatedInformation", val));

  local err :: Maybe<ResponseError> = 
    parseDiagnosticError(range, severity, message, relatedInformation);

  return
  if err.isJust
  then left(err.fromJust)
  else right(diagnostic(range.fromJust.fromRight, rightMaybe(severity), code, source, message.fromJust, allFromRightMaybe(relatedInformation)));
}

function parseDiagnosticError
Maybe<ResponseError> ::= 
  range::Maybe<Either<ResponseError Range>> severity::Maybe<Either<ResponseError DiagnosticSeverity>> message::Maybe<String> relatedInformation::Maybe<[Either<ResponseError DiagnosticRelatedInformation>]>
{
  return
  if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on Diagnostic"))
  else if severity.isJust && severity.fromJust.isLeft then just(severity.fromJust.fromLeft)
  else if !message.isJust then just(lspInvalidParams("message not set on Diagnostic"))
  else if relatedInformation.isJust && anyLeft(relatedInformation.fromJust) then firstLeft(relatedInformation.fromJust)
  else nothing();
}

function diagnosticToJson
JSONValue ::= val::Diagnostic
{
  return val.jsonValue;
}
