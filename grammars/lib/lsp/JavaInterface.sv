{-- 
  Functions in this file are functions that are called from the Java code
  to do things on the Silver side. The goal is to keep this as small as
  possible, but since in the Java translation of Silver you can't get
  attributes off of nonterminals we need functions in Silver to do
  this for us.
--}

{-- builds the interface of request handlers and notification handlers
    that is passed to Silver with each call
--}
function buildInterface
LSP_Interface ::= 
{
  production attribute requestHandlers::[RequestHandler] with ++;
  production attribute notificationHandlers::[NotificationHandler] with ++;
  requestHandlers := [shutdownRequestHandler(handleShutdown)];
  notificationHandlers := [exitNotificationHandler(handleExit)];
  return lspInterface(requestHandlers, notificationHandlers);
}

{-- handles a message sent from the IDE UI client --}
function handleMessage
Pair<State Maybe<String>> ::= state::State json::String interface::LSP_Interface
{
  local io :: IO = unsafeIO();
  local parsedInput :: Either<String JSONValue> = parseJson(json);
  local input :: JSONValue = parsedInput.fromRight;
  local method :: Maybe<String> = mapMaybe(jsonToString, getValueWithKey("method", input));
  local methodName :: String = method.fromJust;

  local notificationMethod :: Boolean = isNotificationMethod(methodName);
  local notificationResult :: State = handleNotification(state, input, interface, io);
  local requestResult :: Pair<State String> = handleRequest(state, input, interface, io);

  return 
    if parsedInput.isLeft || !method.isJust
    then pair(state, just(errorResponse(jsonInteger(0), lspParseError(escapeString(parsedInput.fromLeft))).jsonValue.jsonString))
    else if notificationMethod
    then pair(notificationResult, nothing())
    else pair(fst(requestResult), just(snd(requestResult)));
}

function getAndClearServerInitiatedMessages
Pair<State [String]> ::= state::State
{
  return pair(stateClearMessagesToSend(state), state.serverInitiatedMessagesToSend);
}

-- returns true if needs to exit and just of the exit code otherwise false and nothing
function needToExit
Pair<Boolean Maybe<Integer>> ::= state::State
{
  return
  if state.serverNeedsToExit 
  then pair(true, just(state.serverExitCode))
  else pair(false, nothing());
}
