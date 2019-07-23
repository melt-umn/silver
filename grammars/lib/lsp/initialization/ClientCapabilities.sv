nonterminal ClientCapabilities with jsonValue, workspaceCapabilities, documentCapabilities;

synthesized attribute workspaceCapabilities :: Maybe<WorkspaceClientCapabilities>;
synthesized attribute documentCapabilities :: Maybe<TextDocumentClientCapabilities>;

abstract production clientCapabilities
top::ClientCapabilities::=
  workspace::Maybe<WorkspaceClientCapabilities> textDocument::Maybe<TextDocumentClientCapabilities>
{
  top.workspaceCapabilities = workspace;
  top.documentCapabilities = textDocument;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("workspace", mapMaybe((.jsonValue), workspace), 
    maybeAddKeyValuePairToJSONObject("textDocument", mapMaybe((.jsonValue), textDocument), 
    jsonObject([])));
}

function parseClientCapabilities
Either<ResponseError ClientCapabilities> ::= val::JSONValue
{
  local workspace :: Maybe<Either<ResponseError WorkspaceClientCapabilities>>
    = mapMaybe(parseWorkspaceClientCapabilities, getValueWithKey("workspace", val));
  local textDocument :: Maybe<Either<ResponseError TextDocumentClientCapabilities>>
    = mapMaybe(parseTextDocumentClientCapabilities, getValueWithKey("textDocument", val));

  local err :: Maybe<ResponseError> = 
    parseClientCapabilitiesError(workspace, textDocument);

  return
  if err.isJust
  then left(err.fromJust)
  else right(clientCapabilities(rightMaybe(workspace), rightMaybe(textDocument)));
}

function parseClientCapabilitiesError
Maybe<ResponseError> ::= 
  workspace::Maybe<Either<ResponseError WorkspaceClientCapabilities>> textDocument::Maybe<Either<ResponseError TextDocumentClientCapabilities>>
{
  return
  if workspace.isJust && workspace.fromJust.isLeft then just(workspace.fromJust.fromLeft)
  else if textDocument.isJust && textDocument.fromJust.isLeft then just(textDocument.fromJust.fromLeft)
  else nothing();
}

function clientCapabilitiesToJson
JSONValue ::= val::ClientCapabilities
{
  return val.jsonValue;
}
