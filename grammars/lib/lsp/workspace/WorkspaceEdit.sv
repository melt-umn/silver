nonterminal WorkspaceEdit with jsonValue, workspaceChanges, workspaceDocumentChanges;

synthesized attribute workspaceChanges :: Maybe<[TextEdit]>;
synthesized attribute workspaceDocumentChanges :: Maybe<[TextDocumentEdit]>;

abstract production workspaceEdit
top::WorkspaceEdit::=
  changes::Maybe<[TextEdit]> documentChanges::Maybe<[TextDocumentEdit]>
{
  top.workspaceChanges = changes;
  top.workspaceDocumentChanges = documentChanges;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("changes", mapMaybe(jsonArray, mapMaybeList((.jsonValue), changes)), 
    maybeAddKeyValuePairToJSONObject("documentChanges", mapMaybe(jsonArray, mapMaybeList((.jsonValue), documentChanges)), 
    jsonObject([])));
}

function parseWorkspaceEdit
Either<ResponseError WorkspaceEdit> ::= val::JSONValue
{
  local changes :: Maybe<[Either<ResponseError TextEdit>]>
    = mapMaybe(mapJsonArray(parseTextEdit, _), getValueWithKey("changes", val));
  local documentChanges :: Maybe<[Either<ResponseError TextDocumentEdit>]>
    = mapMaybe(mapJsonArray(parseTextDocumentEdit, _), getValueWithKey("documentChanges", val));

  local err :: Maybe<ResponseError> = 
    parseWorkspaceEditError(changes, documentChanges);

  return
  if err.isJust
  then left(err.fromJust)
  else right(workspaceEdit(allFromRightMaybe(changes), allFromRightMaybe(documentChanges)));
}

function parseWorkspaceEditError
Maybe<ResponseError> ::= 
  changes::Maybe<[Either<ResponseError TextEdit>]> documentChanges::Maybe<[Either<ResponseError TextDocumentEdit>]>
{
  return
  if changes.isJust && anyLeft(changes.fromJust) then firstLeft(changes.fromJust)
  else if documentChanges.isJust && anyLeft(documentChanges.fromJust) then firstLeft(documentChanges.fromJust)
  else nothing();
}

function workspaceEditToJson
JSONValue ::= val::WorkspaceEdit
{
  return val.jsonValue;
}
