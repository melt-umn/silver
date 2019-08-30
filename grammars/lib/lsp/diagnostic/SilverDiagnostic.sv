import silver:langutil;

synthesized attribute associatedDiagnostic :: Diagnostic;
synthesized attribute equivalentDiagnostic :: SilverDiagnostic occurs on Message, ParseError;
synthesized attribute equivalentDiagnosticRelatedInfo :: DiagnosticRelatedInformation occurs on Message;

-- a nonterminal that better corresponds to Silver diagnostics
nonterminal SilverDiagnostic with associatedDiagnostic, uri;
abstract production silverDiagnostic
top::SilverDiagnostic ::= d::Diagnostic doc::DocumentUri 
{
  top.associatedDiagnostic = d;
  top.uri = doc;
}

function noErrorsMessage
ServerInitiatedMessage ::= doc::DocumentUri
{
  return 
    serverInitiatedDiagnosticNotification(
      publishDiagnosticsNotification(
        publishDiagnosticsParams(doc, [])));
}

function silverSeverityToLSPSeverity
DiagnosticSeverity ::= severity::Integer
{
  -- 0 info, 1 warn, 2 error for Silver
  return
  if severity == 0 then diagnosticSeverityInformation()
  else if severity == 1 then diagnosticSeverityWarning()
  else if severity == 2 then diagnosticSeverityError()
  else error("severity not a valid severity");
}

function silverDiagnosticsToServerInitiatedMessages
[ServerInitiatedMessage] ::= diagnostics::[SilverDiagnostic]
{
  return map(serverInitiatedDiagnosticNotification, silverDiagnosticsToPublishNotification(diagnostics));
}

function noErrorsMessageForUris
[ServerInitiatedMessage] ::= uris::[DocumentUri]
{
  return
  map(serverInitiatedDiagnosticNotification, 
    map(publishDiagnosticsNotification,
      map(publishDiagnosticsParams(_, []), uris)));
}

function silverDiagnosticsToPublishNotification
[PublishDiagnosticsNotification] ::= diagnostics::[SilverDiagnostic]
{
  local diagnosticsByDoc :: [[SilverDiagnostic]] = groupSilverDiagnosticByDocument(diagnostics);
  local associatedDocs :: [DocumentUri] = map((.uri), map(head, diagnosticsByDoc));
  local lspDiagnostics :: [[Diagnostic]] = map(map((.associatedDiagnostic), _), diagnosticsByDoc);
  return map(publishDiagnosticsNotification, zipWith(publishDiagnosticsParams, associatedDocs, lspDiagnostics));
}


function diagnosticComesFromSameDocument
Boolean ::= d1::SilverDiagnostic d2::SilverDiagnostic
{
  return stringEq(d1.uri, d2.uri);
}

function groupSilverDiagnosticByDocument
[[SilverDiagnostic]] ::= diagnostics::[SilverDiagnostic]
{
  return groupBy(diagnosticComesFromSameDocument, diagnostics);
}


{-- ASPECT PRODUCTIONS FOR ParseError TYPE IN CORE --}
aspect production syntaxError
top::ParseError ::=
  diagnosticString::String
  location::CoreLocation
  expectedNames::[String]
  matchedNames::[String]
{
  local diagnosticVal :: Diagnostic = 
    diagnostic(silverLocationToRange(location),just(diagnosticSeverityError()), 
              nothing(), just("Copper"), escapeString(diagnosticString), nothing());
  top.equivalentDiagnostic = silverDiagnostic(diagnosticVal, fileToUri(location.filename));
}

aspect production unknownParseError
top::ParseError ::=
  diagnosticString::String
  file::String
{
  local diagnosticVal :: Diagnostic = 
    diagnostic(range(position(0,0), position(0,0)), just(diagnosticSeverityError()), 
              nothing(), just("Copper"), escapeString(diagnosticString), nothing());
  top.equivalentDiagnostic = silverDiagnostic(diagnosticVal, fileToUri(file));
}

{-- ASPECT PRODUCTIONS FOR Message TYPE IN CORE --}
aspect production err
top::Message ::= l::CoreLocation m::String
{
  local diagnosticVal :: Diagnostic = 
    diagnostic(silverLocationToRange(l),just(diagnosticSeverityError()), 
              nothing(), just("Silver"), escapeString(m), nothing());
  top.equivalentDiagnostic = silverDiagnostic(diagnosticVal, fileToUri(l.filename));

  top.equivalentDiagnosticRelatedInfo = diagnosticRelatedInformation(silverLocationToLSPLocation(l), m);
}

aspect production wrn
top::Message ::= l::CoreLocation m::String
{
  local diagnosticVal :: Diagnostic = 
    diagnostic(silverLocationToRange(l), just(diagnosticSeverityWarning()), 
              nothing(), just("Silver"), escapeString(m), nothing());
  top.equivalentDiagnostic = silverDiagnostic(diagnosticVal, fileToUri(l.filename));

  top.equivalentDiagnosticRelatedInfo = diagnosticRelatedInformation(silverLocationToLSPLocation(l), m);
}

aspect production info
top::Message ::= l::CoreLocation m::String
{
  local diagnosticVal :: Diagnostic = 
    diagnostic(silverLocationToRange(l), just(diagnosticSeverityInformation()), 
              nothing(), just("Silver"), escapeString(m), nothing());
  top.equivalentDiagnostic = silverDiagnostic(diagnosticVal, fileToUri(l.filename));

  top.equivalentDiagnosticRelatedInfo = diagnosticRelatedInformation(silverLocationToLSPLocation(l), m);
}

aspect production nested
top::Message ::= l::CoreLocation m::String others::[Message]
{
  top.equivalentDiagnosticRelatedInfo = diagnosticRelatedInformation(silverLocationToLSPLocation(l), m);
  
  local diagnosticVal :: Diagnostic = 
    diagnostic(silverLocationToRange(l), just(silverSeverityToLSPSeverity(top.severity)), 
              nothing(), just("Silver"), escapeString(m), just(map((.equivalentDiagnosticRelatedInfo), others)));
  top.equivalentDiagnostic = silverDiagnostic(diagnosticVal, fileToUri(l.filename));

}


