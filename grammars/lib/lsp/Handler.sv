grammar lib:lsp;

nonterminal NotificationHandler with methodFor;

nonterminal RequestHandler with methodFor; 

type InputConverterFunction<a> = (Either<ResponseError a> ::= JSONValue);
type OutputConverterFunction<b> = (JSONValue ::= b);
type LSPRequestFunction<a b> = (Pair<State b> ::= State a IO);
type LSPNotificationFunction<a> = (State ::= State a IO);

--synthesized attribute outputToJSONFunction<b> :: OutputConverterFunction<b>;
--synthesized attribute JSONToInputFunction<a> :: InputConverterFunction<a>;
--synthesized attribute lspRequestFunction<a b> :: LSPRequestFunction<a b>;
--synthesized attribute lspNotificationFunction<a> :: LSPNotificationFunction<a>;
synthesized attribute methodFor :: String; -- could be an LSP Method type?

-- takes in a raw function the boolean in the return value represents whether
-- this is an error response or result response
abstract production rawRequestHandler
top::RequestHandler ::= method::String func::(Pair<Boolean Pair<State JSONValue>> ::= State JSONValue IO)
{
  top.methodFor = method;
}

abstract production initializeRequestHandler
top::RequestHandler ::= func::LSPRequestFunction<InitializeRequest InitializeResult>
{
  top.methodFor = "initialize";
}

abstract production initializedNotificationHandler
top::NotificationHandler ::= func::LSPNotificationFunction<InitializedNotification>
{
  top.methodFor = "initialized";
}

abstract production shutdownRequestHandler
top::RequestHandler ::= func::LSPRequestFunction<ShutdownRequest ShutdownResult>
{
  top.methodFor = "shutdown";
}

abstract production exitNotificationHandler
top::NotificationHandler ::= func::LSPNotificationFunction<ExitNotification>
{
  top.methodFor = "exit";
}

abstract production showMessageNotificationHandler
top::NotificationHandler ::= func::LSPNotificationFunction<ShowMessageNotification>
{
  top.methodFor = "window/showMessage";
}

abstract production showMessageRequestHandler
top::RequestHandler ::= func::LSPRequestFunction<ShowMessageRequest ShowMessageResult>
{
  top.methodFor = "window/showMessageRequest";
}

abstract production logMessageNotificationHandler
top::NotificationHandler ::= func::LSPNotificationFunction<LogMessageNotification>
{
  top.methodFor = "window/logMessage";
}

abstract production registerCapabilityRequestHandler
top::RequestHandler ::= func::LSPRequestFunction<RegisterCapabilityRequest RegisterCapabilityResult>
{
  top.methodFor = "client/registerCapability";
}

abstract production unregisterCapabilityRequestHandler
top::RequestHandler ::= func::LSPRequestFunction<UnregisterCapabilityRequest UnregisterCapabilityResult>
{
  top.methodFor = "client/unregisterCapability";
}

abstract production hoverRequestHandler
top::RequestHandler ::= func::LSPRequestFunction<HoverRequest HoverResult>
{
  top.methodFor = "textDocument/hover";
}


abstract production willSaveWaitUntilRequestHandler
top::RequestHandler ::= func::LSPRequestFunction<WillSaveWaitUntilRequest WillSaveWaitUntilResult>
{
  top.methodFor = "textDocument/willSaveWaitUntil";
}

abstract production documentFormattingHandler
top::RequestHandler ::= func::LSPRequestFunction<DocumentFormattingRequest DocumentFormattingResult>
{
  top.methodFor = "textDocument/formatting";
}

abstract production documentRangeFormattingHandler
top::RequestHandler ::= func::LSPRequestFunction<DocumentRangeFormattingRequest DocumentRangeFormattingResult>
{
  top.methodFor = "textDocument/rangeFormatting";
}

abstract production documentOnTypeFormattingHandler
top::RequestHandler ::= func::LSPRequestFunction<DocumentOnTypeFormattingRequest DocumentOnTypeFormattingResult>
{
  top.methodFor = "textDocument/onTypeFormatting";
}

abstract production gotoDeclarationHandler
top::RequestHandler ::= func::LSPRequestFunction<GotoDeclarationRequest GotoDeclarationResult>
{
  top.methodFor = "textDocument/declaration";
}

abstract production gotoDefinitionHandler
top::RequestHandler ::= func::LSPRequestFunction<GotoDefinitionRequest GotoDefinitionResult>
{
  top.methodFor = "textDocument/definition";
}

abstract production gotoTypeDefinitionHandler
top::RequestHandler ::= func::LSPRequestFunction<GotoTypeDefinitionRequest GotoTypeDefinitionResult>
{
  top.methodFor = "textDocument/typeDefinition";
}

abstract production gotoImplementationHandler
top::RequestHandler ::= func::LSPRequestFunction<GotoImplementationRequest GotoImplementationResult>
{
  top.methodFor = "textDocument/implementation";
}

abstract production didOpenNotificationHandler
top::NotificationHandler ::= func::LSPNotificationFunction<DidOpenTextDocumentNotification> 
{
  top.methodFor = "textDocument/didOpen";
}

abstract production didCloseNotificationHandler
top::NotificationHandler ::= func::LSPNotificationFunction<DidCloseTextDocumentNotification> 
{
  top.methodFor = "textDocument/didClose";
}

abstract production didChangeNotificationHandler
top::NotificationHandler ::= func::LSPNotificationFunction<DidChangeTextDocumentNotification> 
{
  top.methodFor = "textDocument/didChange";
}

abstract production didSaveNotificationHandler
top::NotificationHandler ::= func::LSPNotificationFunction<DidSaveTextDocumentNotification> 
{
  top.methodFor = "textDocument/didSave";
}

abstract production willSaveNotificationHandler
top::NotificationHandler ::= func::LSPNotificationFunction<WillSaveTextDocumentNotification> 
{
  top.methodFor = "textDocument/willSave";
}

abstract production rawNotificationHandler
top::NotificationHandler ::= method::String func::LSPNotificationFunction<JSONValue>
{
  top.methodFor = method;
}

function runTypedRequestHandler
Pair<Boolean Pair<State JSONValue>> ::= state::State input::JSONValue
  jsonToInputFunc::InputConverterFunction<a> func::LSPRequestFunction<a b> 
  toJSONFunc::OutputConverterFunction<b> io::IO
{
  local requestIdMaybe :: Maybe<JSONValue> = getValueWithKey("id", input);
  local requestId :: JSONValue = requestIdMaybe.fromJust;
  local parseResult :: Either<ResponseError a> = jsonToInputFunc(input);

  return
  if !requestIdMaybe.isJust 
  then pair(false, pair(state, errorResponse(jsonInteger(0), lspInvalidParams("No id specified for request")).jsonValue))
  else if parseResult.isLeft
  then pair(false, pair(state, errorResponse(requestId, parseResult.fromLeft).jsonValue))
  else pair(true, mapSnd(toJSONFunc, func(state, parseResult.fromRight, io)));
}

function runRequestHandler
Pair<Boolean Pair<State JSONValue>> ::= handler::RequestHandler state::State input::JSONValue io::IO
{
  return
  case handler of
  | rawRequestHandler(_, func) -> func(state, input, io)
  | documentFormattingHandler(func) ->
      runTypedRequestHandler(state, input, parseDocumentFormattingRequest, func, documentFormattingResultToJson, io)
  | documentRangeFormattingHandler(func) ->
      runTypedRequestHandler(state, input, parseDocumentRangeFormattingRequest, func, documentRangeFormattingResultToJson, io)
  | documentOnTypeFormattingHandler(func) ->
      runTypedRequestHandler(state, input, parseDocumentOnTypeFormattingRequest, func, documentOnTypeFormattingResultToJson, io)
  | gotoDeclarationHandler(func) ->
      runTypedRequestHandler(state, input, parseGotoDeclarationRequest, func, gotoDeclarationResultToJson, io)
  | gotoDefinitionHandler(func) ->
      runTypedRequestHandler(state, input, parseGotoDefinitionRequest, func, gotoDefinitionResultToJson, io)
  | gotoTypeDefinitionHandler(func) ->
      runTypedRequestHandler(state, input, parseGotoTypeDefinitionRequest, func, gotoTypeDefinitionResultToJson, io)
  | gotoImplementationHandler(func) ->
      runTypedRequestHandler(state, input, parseGotoImplementationRequest, func, gotoImplementationResultToJson, io)
  | hoverRequestHandler(func) -> 
      runTypedRequestHandler(state, input, parseHoverRequest, func, hoverResultToJson, io)
  | initializeRequestHandler(func) ->
      runTypedRequestHandler(state, input, parseInitializeRequest, func, initializeResultToJson, io)
  | shutdownRequestHandler(func) ->
      runTypedRequestHandler(state, input, parseShutdownRequest, func, shutdownResultToJson, io)
  | willSaveWaitUntilRequestHandler(func) ->
      runTypedRequestHandler(state, input, parseWillSaveWaitUntilRequest, func, willSaveWaitUntilResultToJson, io)
  end;
}

function runTypedNotificationHandler
State ::= state::State input::JSONValue jsonToInputFunc::InputConverterFunction<a>
  func::LSPNotificationFunction<a> io::IO
{
  local parseResult :: Either<ResponseError a> = jsonToInputFunc(input);
  return
  if parseResult.isLeft 
  then state
  else func(state, parseResult.fromRight, io);
}

function runNotificationHandler
State ::= handler::NotificationHandler state::State input::JSONValue io::IO
{
  return case handler of 
  | rawNotificationHandler(_, func) -> func(state, input, io) 
  | didChangeNotificationHandler(func) -> 
      runTypedNotificationHandler(state, input, parseDidChangeTextDocumentNotification, func, io)
  | didCloseNotificationHandler(func) -> 
      runTypedNotificationHandler(state, input, parseDidCloseTextDocumentNotification, func, io)
  | didOpenNotificationHandler(func) -> 
      runTypedNotificationHandler(state, input, parseDidOpenTextDocumentNotification, func, io)
  | didSaveNotificationHandler(func) -> 
      runTypedNotificationHandler(state, input, parseDidSaveTextDocumentNotification, func, io)
  | exitNotificationHandler(func) ->
      runTypedNotificationHandler(state, input, parseExitNotification, func, io)
  | willSaveNotificationHandler(func) -> 
      runTypedNotificationHandler(state, input, parseWillSaveTextDocumentNotification, func, io)
  | doNothingNotificationHandler(_) -> doNothingOnNotification(state, input, io)
  end;
}

abstract production nullRequestHandler
top::RequestHandler ::= method::String
{
  top.methodFor = method;
}

abstract production doNothingNotificationHandler
top::NotificationHandler ::= method::String
{
  top.methodFor = method;
}


function doNothingNullResponseFunction
Pair<State JSONValue> ::= oldState::State input::JSONValue io::IO
{
  return pair(oldState, jsonNull());
}

function doNothingOnNotification
State ::= oldState::State input::JSONValue io::IO
{
  return oldState;
}

