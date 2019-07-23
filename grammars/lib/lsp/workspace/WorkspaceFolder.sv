nonterminal WorkspaceFolder with jsonValue, uri, workspaceFolderName;

synthesized attribute workspaceFolderName :: String;

abstract production workspaceFolder
top::WorkspaceFolder::=
  uri::DocumentUri name::String
{
  top.uri = uri;
  top.workspaceFolderName = name;
  top.jsonValue =
    addKeyValuePairToJSONObject("uri", jsonString(uri), 
    addKeyValuePairToJSONObject("name", jsonString(name), 
    jsonObject([])));
}

function parseWorkspaceFolder
Either<ResponseError WorkspaceFolder> ::= val::JSONValue
{
  local uri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("uri", val));
  local name :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("name", val));

  local err :: Maybe<ResponseError> = 
    parseWorkspaceFolderError(uri, name);

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceFolder(uri.fromJust, name.fromJust));
}

function parseWorkspaceFolderError
Maybe<ResponseError> ::= 
  uri::Maybe<DocumentUri> name::Maybe<String>
{
  return
  if !uri.isJust then just(lspInvalidParams("uri not set on WorkspaceFolder"))
  else if !name.isJust then just(lspInvalidParams("name not set on WorkspaceFolder"))
  else nothing();
}

function workspaceFolderToJson
JSONValue ::= val::WorkspaceFolder
{
  return val.jsonValue;
}
