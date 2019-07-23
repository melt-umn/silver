nonterminal WorkspaceSymbolParams with jsonValue, workspaceSymbolQuery;

synthesized attribute workspaceSymbolQuery :: String;

abstract production workspaceSymbolParams
top::WorkspaceSymbolParams::=
  query::String
{
  top.workspaceSymbolQuery = query;
  top.jsonValue =
    addKeyValuePairToJSONObject("query", jsonString(query), 
    jsonObject([]));
}

function parseWorkspaceSymbolParams
Either<ResponseError WorkspaceSymbolParams> ::= val::JSONValue
{
  local query :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("query", val));

  local err :: Maybe<ResponseError> = 
    parseWorkspaceSymbolParamsError(query);

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceSymbolParams(query.fromJust));
}

function parseWorkspaceSymbolParamsError
Maybe<ResponseError> ::= 
  query::Maybe<String>
{
  return
  if !query.isJust then just(lspInvalidParams("query not set on WorkspaceSymbolParams"))
  else nothing();
}

function workspaceSymbolParamsToJson
JSONValue ::= val::WorkspaceSymbolParams
{
  return val.jsonValue;
}
