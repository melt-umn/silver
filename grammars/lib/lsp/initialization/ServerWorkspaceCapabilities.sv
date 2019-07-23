nonterminal ServerWorkspaceCapabilities with jsonValue, serverWorkspaceFolderCapabilities;

synthesized attribute serverWorkspaceFolderCapabilities :: Maybe<ServerWorkspaceFoldersCapabilities>;

abstract production serverWorkspaceCapabilities
top::ServerWorkspaceCapabilities::=
  workspaceFolders::Maybe<ServerWorkspaceFoldersCapabilities>
{
  top.serverWorkspaceFolderCapabilities = workspaceFolders;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("workspaceFolders", mapMaybe((.jsonValue), workspaceFolders), 
    jsonObject([]));
}

function parseServerWorkspaceCapabilities
Either<ResponseError ServerWorkspaceCapabilities> ::= val::JSONValue
{
  local workspaceFolders :: Maybe<Either<ResponseError ServerWorkspaceFoldersCapabilities>>
    = mapMaybe(parseServerWorkspaceFoldersCapabilities, getValueWithKey("workspaceFolders", val));

  local err :: Maybe<ResponseError> = 
    parseServerWorkspaceCapabilitiesError(workspaceFolders);

  return
  if err.isJust
  then left(err.fromJust)
  else right(serverWorkspaceCapabilities(rightMaybe(workspaceFolders)));
}

function parseServerWorkspaceCapabilitiesError
Maybe<ResponseError> ::= 
  workspaceFolders::Maybe<Either<ResponseError ServerWorkspaceFoldersCapabilities>>
{
  return
  if workspaceFolders.isJust && workspaceFolders.fromJust.isLeft then just(workspaceFolders.fromJust.fromLeft)
  else nothing();
}

function serverWorkspaceCapabilitiesToJson
JSONValue ::= val::ServerWorkspaceCapabilities
{
  return val.jsonValue;
}
