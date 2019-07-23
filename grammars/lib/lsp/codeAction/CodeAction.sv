nonterminal CodeAction with jsonValue, codeActionTitle, typeOfCodeAction, diagnosticsResolved, editToPerform, commandToExecute;

synthesized attribute codeActionTitle :: String;
synthesized attribute typeOfCodeAction :: Maybe<CodeActionKind>;
synthesized attribute diagnosticsResolved :: Maybe<[Diagnostic]>;
synthesized attribute editToPerform :: Maybe<WorkspaceEdit>;
synthesized attribute commandToExecute :: Maybe<Command>;

abstract production codeAction
top::CodeAction::=
  title::String kind::Maybe<CodeActionKind> diagnostics::Maybe<[Diagnostic]> edit::Maybe<WorkspaceEdit> command_::Maybe<Command>
{
  top.codeActionTitle = title;
  top.typeOfCodeAction = kind;
  top.diagnosticsResolved = diagnostics;
  top.editToPerform = edit;
  top.commandToExecute = command_;
  top.jsonValue =
    addKeyValuePairToJSONObject("title", jsonString(title), 
    maybeAddKeyValuePairToJSONObject("kind", mapMaybe((.jsonValue), kind), 
    maybeAddKeyValuePairToJSONObject("diagnostics", mapMaybe(jsonArray, mapMaybeList((.jsonValue), diagnostics)), 
    maybeAddKeyValuePairToJSONObject("edit", mapMaybe((.jsonValue), edit), 
    maybeAddKeyValuePairToJSONObject("command", mapMaybe((.jsonValue), command_), 
    jsonObject([]))))));
}

function parseCodeAction
Either<ResponseError CodeAction> ::= val::JSONValue
{
  local title :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("title", val));
  local kind :: Maybe<Either<ResponseError CodeActionKind>>
    = mapMaybe(parseCodeActionKind, getValueWithKey("kind", val));
  local diagnostics :: Maybe<[Either<ResponseError Diagnostic>]>
    = mapMaybe(mapJsonArray(parseDiagnostic, _), getValueWithKey("diagnostics", val));
  local edit :: Maybe<Either<ResponseError WorkspaceEdit>>
    = mapMaybe(parseWorkspaceEdit, getValueWithKey("edit", val));
  local command_ :: Maybe<Either<ResponseError Command>>
    = mapMaybe(parseCommand, getValueWithKey("command", val));

  local err :: Maybe<ResponseError> = 
    parseCodeActionError(title, kind, diagnostics, edit, command_);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeAction(title.fromJust, rightMaybe(kind), allFromRightMaybe(diagnostics), rightMaybe(edit), rightMaybe(command_)));
}

function parseCodeActionError
Maybe<ResponseError> ::= 
  title::Maybe<String> kind::Maybe<Either<ResponseError CodeActionKind>> diagnostics::Maybe<[Either<ResponseError Diagnostic>]> edit::Maybe<Either<ResponseError WorkspaceEdit>> command_::Maybe<Either<ResponseError Command>>
{
  return
  if !title.isJust then just(lspInvalidParams("title not set on CodeAction"))
  else if kind.isJust && kind.fromJust.isLeft then just(kind.fromJust.fromLeft)
  else if diagnostics.isJust && anyLeft(diagnostics.fromJust) then firstLeft(diagnostics.fromJust)
  else if edit.isJust && edit.fromJust.isLeft then just(edit.fromJust.fromLeft)
  else if command_.isJust && command_.fromJust.isLeft then just(command_.fromJust.fromLeft)
  else nothing();
}

function codeActionToJson
JSONValue ::= val::CodeAction
{
  return val.jsonValue;
}
