nonterminal WorkspaceFoldersResult with jsonValue;

abstract production nullWorkspaceFoldersResult
top::WorkspaceFoldersResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production workspaceFoldersResultWorkspaceFolderList
top::WorkspaceFoldersResult ::= result::[WorkspaceFolder]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function workspaceFoldersResultToJson
JSONValue ::= val::WorkspaceFoldersResult
{
  return val.jsonValue;
}
