
nonterminal ServerInitiatedMessage with jsonValue;

-- maybe since notifications do not have an ID
-- synthesized attribute serverRequestId :: Maybe<Integer>;

abstract production serverInitiatedLogMessage
top::ServerInitiatedMessage ::= logNotif::LogMessageNotification
{
  top.jsonValue = addKeyValuePairToJSONObject("jsonrpc", jsonString("2.0"),
                  addKeyValuePairToJSONObject("method", jsonString("window/logMessage"),
                  addKeyValuePairToJSONObject("params", logNotif.logMessageNotificationParams.jsonValue,
                  jsonObject([]))));
}

abstract production serverInitiatedShowMessage
top::ServerInitiatedMessage ::= showNotif::ShowMessageNotification
{
  top.jsonValue = addKeyValuePairToJSONObject("jsonrpc", jsonString("2.0"),
                  addKeyValuePairToJSONObject("method", jsonString("window/showMessage"),
                  addKeyValuePairToJSONObject("params", showNotif.showMessageNotificationParams.jsonValue,
                  jsonObject([]))));
}

abstract production serverInitiatedDiagnosticNotification
top::ServerInitiatedMessage ::= notif::PublishDiagnosticsNotification
{
  top.jsonValue = addKeyValuePairToJSONObject("jsonrpc", jsonString("2.0"),
                  addKeyValuePairToJSONObject("method", jsonString("textDocument/publishDiagnostics"),
                  addKeyValuePairToJSONObject("params", notif.publishDiagnosticsParams.jsonValue,
                  jsonObject([]))));
}



