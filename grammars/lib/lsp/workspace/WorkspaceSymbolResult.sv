nonterminal WorkspaceSymbolResult with jsonValue;

abstract production nullWorkspaceSymbolResult
top::WorkspaceSymbolResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production workspaceSymbolResultSymbolInformationList
top::WorkspaceSymbolResult ::= result::[SymbolInformation]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function workspaceSymbolResultToJson
JSONValue ::= val::WorkspaceSymbolResult
{
  return val.jsonValue;
}
