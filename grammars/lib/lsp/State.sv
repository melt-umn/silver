grammar lib:lsp;

-- contains the state that exists between calls to LSP interface functions
nonterminal State with isShutdown, serverNeedsToExit, serverExitCode, 
  serverInitiatedMessagesToSend, hasBeenInitialized, initializeSettings;

-- this attribute should be set to true once the shutdown request is received
synthesized attribute isShutdown :: Boolean;
synthesized attribute hasBeenInitialized :: Boolean;
-- this attribute should be true once the exit notification is received
synthesized attribute serverNeedsToExit :: Boolean;
-- the exit code that should be set when exiting the server
synthesized attribute serverExitCode :: Integer;
-- list of json strings containing server initiated messages to send
synthesized attribute serverInitiatedMessagesToSend :: [String];
synthesized attribute initializeSettings :: Maybe<InitializeParams>;

abstract production initialState
top::State ::= 
{
  top.serverNeedsToExit = false;
  top.serverInitiatedMessagesToSend = [];
  top.isShutdown = false;
  top.hasBeenInitialized = false;
  top.serverExitCode = 0;
  top.initializeSettings = nothing();
}

abstract production stateNewServerInitiatedMessages
top::State ::= newMessages::[ServerInitiatedMessage] oldState::State
{
  top.serverInitiatedMessagesToSend = 
    map((.jsonString), map((.jsonValue), newMessages)) ++ oldState.serverInitiatedMessagesToSend;
  forwards to oldState;
}

abstract production shutdownServer
top::State ::= oldState::State
{
  top.isShutdown = true;
  forwards to oldState;
}

abstract production exitServer
top::State ::= oldState::State
{
  top.serverNeedsToExit = true;
  top.serverExitCode = if oldState.isShutdown then 0 else 1;
  forwards to oldState;
}

abstract production stateNewServerInitiatedMessagesRaw
top::State ::= newMessages::[String] oldState::State
{
  top.serverInitiatedMessagesToSend = newMessages ++ oldState.serverInitiatedMessagesToSend;
  forwards to oldState;
}

abstract production stateClearMessagesToSend
top::State ::= oldState::State
{
  top.serverInitiatedMessagesToSend = [];
  forwards to oldState;
}

abstract production setInitializeSettings
top::State ::= params::InitializeParams oldState::State
{
  top.initializeSettings = just(params);
  top.hasBeenInitialized = true;
  forwards to oldState;
}
