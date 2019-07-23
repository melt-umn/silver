nonterminal PublishDiagnosticsCapabilities with jsonValue, diagnosticRelatedInformationSupport;

synthesized attribute diagnosticRelatedInformationSupport :: Maybe<Boolean>;

abstract production publishDiagnosticsCapabilities
top::PublishDiagnosticsCapabilities::=
  relatedInformation::Maybe<Boolean>
{
  top.diagnosticRelatedInformationSupport = relatedInformation;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("relatedInformation", mapMaybe(jsonBoolean, relatedInformation), 
    jsonObject([]));
}

function parsePublishDiagnosticsCapabilities
Either<ResponseError PublishDiagnosticsCapabilities> ::= val::JSONValue
{
  local relatedInformation :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("relatedInformation", val));

  local err :: Maybe<ResponseError> = 
    parsePublishDiagnosticsCapabilitiesError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(publishDiagnosticsCapabilities(relatedInformation));
}

function parsePublishDiagnosticsCapabilitiesError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function publishDiagnosticsCapabilitiesToJson
JSONValue ::= val::PublishDiagnosticsCapabilities
{
  return val.jsonValue;
}
