nonterminal PublishDiagnosticsParams with jsonValue, uri, diagnostics;

synthesized attribute diagnostics :: [Diagnostic];

abstract production publishDiagnosticsParams
top::PublishDiagnosticsParams::=
  uri::DocumentUri diagnostics::[Diagnostic]
{
  top.uri = uri;
  top.diagnostics = diagnostics;
  top.jsonValue =
    addKeyValuePairToJSONObject("uri", jsonString(uri), 
    addKeyValuePairToJSONObject("diagnostics", jsonArray(map((.jsonValue), diagnostics)), 
    jsonObject([])));
}

function parsePublishDiagnosticsParams
Either<ResponseError PublishDiagnosticsParams> ::= val::JSONValue
{
  local uri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("uri", val));
  local diagnostics :: Maybe<[Either<ResponseError Diagnostic>]>
    = mapMaybe(mapJsonArray(parseDiagnostic, _), getValueWithKey("diagnostics", val));

  local err :: Maybe<ResponseError> = 
    parsePublishDiagnosticsParamsError(uri, diagnostics);

  return
  if err.isJust
  then left(err.fromJust)
  else right(publishDiagnosticsParams(uri.fromJust, allFromRight(diagnostics.fromJust)));
}

function parsePublishDiagnosticsParamsError
Maybe<ResponseError> ::= 
  uri::Maybe<DocumentUri> diagnostics::Maybe<[Either<ResponseError Diagnostic>]>
{
  return
  if !uri.isJust then just(lspInvalidParams("uri not set on PublishDiagnosticsParams"))
  else if !diagnostics.isJust|| anyLeft(diagnostics.fromJust) then just(lspInvalidParams("diagnostics not set or invalid on PublishDiagnosticsParams"))
  else nothing();
}

function publishDiagnosticsParamsToJson
JSONValue ::= val::PublishDiagnosticsParams
{
  return val.jsonValue;
}
