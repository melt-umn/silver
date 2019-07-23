grammar lib:lsp;

-- contains the interface of functions to call 
nonterminal LSP_Interface with requestHandlerLookups, notificationHandlerLookups, requestHandlers, notificationHandlers;
synthesized attribute requestHandlerLookups :: [Pair<String RequestHandler>];
synthesized attribute notificationHandlerLookups :: [Pair<String NotificationHandler>];
synthesized attribute requestHandlers :: [RequestHandler];
synthesized attribute notificationHandlers :: [NotificationHandler];
-- contains LSP errors


function handleNotification
State ::= state::State input::JSONValue interface::LSP_Interface io::IO
{
  local notification :: NotificationMessage = parseNotificationMessage(input);
  local handler :: Either<ResponseError NotificationHandler> =
    getHandlerForNotificationMethod(notification.methodName, interface);
  return 
    if handler.isLeft 
    then state
    else runNotificationHandler(handler.fromRight, state, input, io);
}

function handleRequest
Pair<State String> ::= state::State input::JSONValue interface::LSP_Interface io::IO
{
  local request :: RequestMessage = parseRequestMessage(input);

  local handler :: Either<ResponseError RequestHandler> =
    getHandlerForRequestMethod(request.methodName, interface);
  
  -- the boolean represents whether there was success or error
  local result :: Pair<Boolean Pair<State JSONValue>> = 
    runRequestHandler(handler.fromRight, state, input, io);

  local response :: ResponseMessage =
    if fst(result) -- checks if running the request handler returned success or error
    then resultResponse(request.requestId, snd(snd(result)))
    else errorResponseRaw(request.requestId, snd(snd(result)));

  return 
    if handler.isLeft
    then pair(state, errorResponse(request.requestId, handler.fromLeft).jsonValue.jsonString)
    else pair(fst(snd(result)), response.jsonValue.jsonString);
}

function getHandlerForNotificationMethod
Either<ResponseError NotificationHandler> ::= method::String interface::LSP_Interface
{
  local handler :: Maybe<NotificationHandler> = lookupBy(stringEq, method, interface.notificationHandlerLookups);
  return 
    if handler.isJust 
    then right(handler.fromJust)
    else left(lspMethodNotFound(s"""Method ${method} does not have a handler"""));
}

function getHandlerForRequestMethod
Either<ResponseError RequestHandler> ::= method::String interface::LSP_Interface
{
  local handler :: Maybe<RequestHandler> = lookupBy(stringEq, method, interface.requestHandlerLookups);
  return 
    if handler.isJust 
    then right(handler.fromJust)
    else left(lspMethodNotFound(s"""Method ${method} does not have a handler"""));
}

function isRequestMethod
Boolean ::= method::String
{
  return stringEq(method, "initialize") 
      || stringEq(method, "shutdown")
      || stringEq(method, "window/showMessageRequest")
      || stringEq(method, "client/registerCapability")
      || stringEq(method, "workspace/workspaceFolders")
      || stringEq(method, "workspace/configuration")
      || stringEq(method, "workspace/symbol")
      || stringEq(method, "workspace/executeCommand")
      || stringEq(method, "workspace/applyEdit")
      || stringEq(method, "textDocument/willSaveWaitUntil")
      || stringEq(method, "textDocument/completion")
      || stringEq(method, "completionItem/resolve")
      || stringEq(method, "textDocument/hover")
      || stringEq(method, "textDocument/signatureHelp")
      || stringEq(method, "textDocument/declaration")
      || stringEq(method, "textDocument/definition")
      || stringEq(method, "textDocument/typeDefinition")
      || stringEq(method, "textDocument/implementation")
      || stringEq(method, "textDocument/references")
      || stringEq(method, "textDocument/documentHighlight")
      || stringEq(method, "textDocument/documentSymbol")
      || stringEq(method, "textDocument/codeAction")
      || stringEq(method, "textDocument/codeLens")
      || stringEq(method, "codeLens/resolve")
      || stringEq(method, "textDocument/documentLink")
      || stringEq(method, "documentLink/resolve")
      || stringEq(method, "textDocument/documentColor")
      || stringEq(method, "textDocument/colorPresentation")
      || stringEq(method, "textDocument/formatting")
      || stringEq(method, "textDocument/rangeFormatting")
      || stringEq(method, "textDocument/onTypeFormatting")
      || stringEq(method, "textDocument/rename")
      || stringEq(method, "textDocument/prepareRename")
      || stringEq(method, "textDocument/foldingRange");
}

function isNotificationMethod
Boolean ::= method::String
{
  return stringEq(method, "initialized") 
      || stringEq(method, "exit")
      || stringEq(method, "$/cancelRequest")
      || stringEq(method, "window/showMessage")
      || stringEq(method, "window/logMessage")
      || stringEq(method, "telemetry/event")
      || stringEq(method, "telemetry/event")
      || stringEq(method, "workspace/didChangeWorkspaceFolders")
      || stringEq(method, "workspace/didChangeConfiguration")
      || stringEq(method, "workspace/didChangeWatchedFiles")
      || stringEq(method, "textDocument/didOpen")
      || stringEq(method, "textDocument/didChange")
      || stringEq(method, "textDocument/willSave")
      || stringEq(method, "textDocument/didSave")
      || stringEq(method, "textDocument/didClose")
      || stringEq(method, "textDocument/publishDiagnostics");
}

abstract production lspInterface
top::LSP_Interface ::= requestFuncs::[RequestHandler] notifFuncs::[NotificationHandler]
{
  top.requestHandlers = requestFuncs;
  top.notificationHandlers = notifFuncs;
  top.requestHandlerLookups = zipWith(pair, map((.methodFor), requestFuncs), requestFuncs);
  top.notificationHandlerLookups = zipWith(pair, map((.methodFor), notifFuncs), notifFuncs);
}

