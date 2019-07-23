nonterminal WorkspaceFoldersChangeEvent with jsonValue, workspaceFoldersAdded, workspaceFoldersRemoved;

synthesized attribute workspaceFoldersAdded :: [WorkspaceFolder];
synthesized attribute workspaceFoldersRemoved :: [WorkspaceFolder];

abstract production workspaceFoldersChangeEvent
top::WorkspaceFoldersChangeEvent::=
  added::[WorkspaceFolder] removed::[WorkspaceFolder]
{
  top.workspaceFoldersAdded = added;
  top.workspaceFoldersRemoved = removed;
  top.jsonValue =
    addKeyValuePairToJSONObject("added", jsonArray(map((.jsonValue), added)), 
    addKeyValuePairToJSONObject("removed", jsonArray(map((.jsonValue), removed)), 
    jsonObject([])));
}

function parseWorkspaceFoldersChangeEvent
Either<ResponseError WorkspaceFoldersChangeEvent> ::= val::JSONValue
{
  local added :: Maybe<[Either<ResponseError WorkspaceFolder>]>
    = mapMaybe(mapJsonArray(parseWorkspaceFolder, _), getValueWithKey("added", val));
  local removed :: Maybe<[Either<ResponseError WorkspaceFolder>]>
    = mapMaybe(mapJsonArray(parseWorkspaceFolder, _), getValueWithKey("removed", val));

  local err :: Maybe<ResponseError> = 
    parseWorkspaceFoldersChangeEventError(added, removed);

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceFoldersChangeEvent(allFromRight(added.fromJust), allFromRight(removed.fromJust)));
}

function parseWorkspaceFoldersChangeEventError
Maybe<ResponseError> ::= 
  added::Maybe<[Either<ResponseError WorkspaceFolder>]> removed::Maybe<[Either<ResponseError WorkspaceFolder>]>
{
  return
  if !added.isJust|| anyLeft(added.fromJust) then just(lspInvalidParams("added not set or invalid on WorkspaceFoldersChangeEvent"))
  else if !removed.isJust|| anyLeft(removed.fromJust) then just(lspInvalidParams("removed not set or invalid on WorkspaceFoldersChangeEvent"))
  else nothing();
}

function workspaceFoldersChangeEventToJson
JSONValue ::= val::WorkspaceFoldersChangeEvent
{
  return val.jsonValue;
}
