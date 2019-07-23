nonterminal ApplyWorkspaceEditParams with jsonValue, workspaceEditLabel, workspaceEdit;

synthesized attribute workspaceEditLabel :: Maybe<String>;

abstract production applyWorkspaceEditParams
top::ApplyWorkspaceEditParams::=
  label::Maybe<String> edit::WorkspaceEdit
{
  top.workspaceEditLabel = label;
  top.workspaceEdit = edit;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("label", mapMaybe(jsonString, label), 
    addKeyValuePairToJSONObject("edit", edit.jsonValue, 
    jsonObject([])));
}

function parseApplyWorkspaceEditParams
Either<ResponseError ApplyWorkspaceEditParams> ::= val::JSONValue
{
  local label :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("label", val));
  local edit :: Maybe<Either<ResponseError WorkspaceEdit>>
    = mapMaybe(parseWorkspaceEdit, getValueWithKey("edit", val));

  local err :: Maybe<ResponseError> = 
    parseApplyWorkspaceEditParamsError(edit);

  return
  if err.isJust
  then left(err.fromJust)
  else right(applyWorkspaceEditParams(label, edit.fromJust.fromRight));
}

function parseApplyWorkspaceEditParamsError
Maybe<ResponseError> ::= 
  edit::Maybe<Either<ResponseError WorkspaceEdit>>
{
  return
  if !edit.isJust|| edit.fromJust.isLeft then just(lspInvalidParams("edit not set or invalid on ApplyWorkspaceEditParams"))
  else nothing();
}

function applyWorkspaceEditParamsToJson
JSONValue ::= val::ApplyWorkspaceEditParams
{
  return val.jsonValue;
}
