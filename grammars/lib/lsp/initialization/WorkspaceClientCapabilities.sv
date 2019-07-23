nonterminal WorkspaceClientCapabilities with jsonValue, applyEditCapability, workspaceEditCapabilities, didChangeConfigurationCapabilities, didChangeWatchedFilesCapabilities, workspaceSymbolCapabilities, executeCommandCapabilities, workspaceFolderCapability, workspaceConfigurationCapability;

synthesized attribute applyEditCapability :: Maybe<Boolean>;
synthesized attribute workspaceEditCapabilities :: Maybe<WorkspaceEditCapabilities>;
synthesized attribute didChangeConfigurationCapabilities :: Maybe<DidChangeConfigurationCapabilities>;
synthesized attribute didChangeWatchedFilesCapabilities :: Maybe<DidChangeWatchedFilesCapabilities>;
synthesized attribute workspaceSymbolCapabilities :: Maybe<WorkspaceSymbolCapabilities>;
synthesized attribute executeCommandCapabilities :: Maybe<ExecuteCommandCapabilities>;
synthesized attribute workspaceFolderCapability :: Maybe<Boolean>;
synthesized attribute workspaceConfigurationCapability :: Maybe<Boolean>;

abstract production workspaceClientCapabilities
top::WorkspaceClientCapabilities::=
  applyEdit::Maybe<Boolean> workspaceEdit::Maybe<WorkspaceEditCapabilities> didChangeConfiguration::Maybe<DidChangeConfigurationCapabilities> didChangeWatchedFiles::Maybe<DidChangeWatchedFilesCapabilities> symbol::Maybe<WorkspaceSymbolCapabilities> executeCommand::Maybe<ExecuteCommandCapabilities> workspaceFolders::Maybe<Boolean> configuration::Maybe<Boolean>
{
  top.applyEditCapability = applyEdit;
  top.workspaceEditCapabilities = workspaceEdit;
  top.didChangeConfigurationCapabilities = didChangeConfiguration;
  top.didChangeWatchedFilesCapabilities = didChangeWatchedFiles;
  top.workspaceSymbolCapabilities = symbol;
  top.executeCommandCapabilities = executeCommand;
  top.workspaceFolderCapability = workspaceFolders;
  top.workspaceConfigurationCapability = configuration;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("applyEdit", mapMaybe(jsonBoolean, applyEdit), 
    maybeAddKeyValuePairToJSONObject("workspaceEdit", mapMaybe((.jsonValue), workspaceEdit), 
    maybeAddKeyValuePairToJSONObject("didChangeConfiguration", mapMaybe((.jsonValue), didChangeConfiguration), 
    maybeAddKeyValuePairToJSONObject("didChangeWatchedFiles", mapMaybe((.jsonValue), didChangeWatchedFiles), 
    maybeAddKeyValuePairToJSONObject("symbol", mapMaybe((.jsonValue), symbol), 
    maybeAddKeyValuePairToJSONObject("executeCommand", mapMaybe((.jsonValue), executeCommand), 
    maybeAddKeyValuePairToJSONObject("workspaceFolders", mapMaybe(jsonBoolean, workspaceFolders), 
    maybeAddKeyValuePairToJSONObject("configuration", mapMaybe(jsonBoolean, configuration), 
    jsonObject([])))))))));
}

function parseWorkspaceClientCapabilities
Either<ResponseError WorkspaceClientCapabilities> ::= val::JSONValue
{
  local applyEdit :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("applyEdit", val));
  local workspaceEdit :: Maybe<Either<ResponseError WorkspaceEditCapabilities>>
    = mapMaybe(parseWorkspaceEditCapabilities, getValueWithKey("workspaceEdit", val));
  local didChangeConfiguration :: Maybe<Either<ResponseError DidChangeConfigurationCapabilities>>
    = mapMaybe(parseDidChangeConfigurationCapabilities, getValueWithKey("didChangeConfiguration", val));
  local didChangeWatchedFiles :: Maybe<Either<ResponseError DidChangeWatchedFilesCapabilities>>
    = mapMaybe(parseDidChangeWatchedFilesCapabilities, getValueWithKey("didChangeWatchedFiles", val));
  local symbol :: Maybe<Either<ResponseError WorkspaceSymbolCapabilities>>
    = mapMaybe(parseWorkspaceSymbolCapabilities, getValueWithKey("symbol", val));
  local executeCommand :: Maybe<Either<ResponseError ExecuteCommandCapabilities>>
    = mapMaybe(parseExecuteCommandCapabilities, getValueWithKey("executeCommand", val));
  local workspaceFolders :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("workspaceFolders", val));
  local configuration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("configuration", val));

  local err :: Maybe<ResponseError> = 
    parseWorkspaceClientCapabilitiesError(workspaceEdit, didChangeConfiguration, didChangeWatchedFiles, symbol, executeCommand);

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceClientCapabilities(applyEdit, rightMaybe(workspaceEdit), rightMaybe(didChangeConfiguration), rightMaybe(didChangeWatchedFiles), rightMaybe(symbol), rightMaybe(executeCommand), workspaceFolders, configuration));
}

function parseWorkspaceClientCapabilitiesError
Maybe<ResponseError> ::= 
  workspaceEdit::Maybe<Either<ResponseError WorkspaceEditCapabilities>> didChangeConfiguration::Maybe<Either<ResponseError DidChangeConfigurationCapabilities>> didChangeWatchedFiles::Maybe<Either<ResponseError DidChangeWatchedFilesCapabilities>> symbol::Maybe<Either<ResponseError WorkspaceSymbolCapabilities>> executeCommand::Maybe<Either<ResponseError ExecuteCommandCapabilities>>
{
  return
  if workspaceEdit.isJust && workspaceEdit.fromJust.isLeft then just(workspaceEdit.fromJust.fromLeft)
  else if didChangeConfiguration.isJust && didChangeConfiguration.fromJust.isLeft then just(didChangeConfiguration.fromJust.fromLeft)
  else if didChangeWatchedFiles.isJust && didChangeWatchedFiles.fromJust.isLeft then just(didChangeWatchedFiles.fromJust.fromLeft)
  else if symbol.isJust && symbol.fromJust.isLeft then just(symbol.fromJust.fromLeft)
  else if executeCommand.isJust && executeCommand.fromJust.isLeft then just(executeCommand.fromJust.fromLeft)
  else nothing();
}

function workspaceClientCapabilitiesToJson
JSONValue ::= val::WorkspaceClientCapabilities
{
  return val.jsonValue;
}
