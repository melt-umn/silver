nonterminal CodeActionContext with jsonValue, diagnostics, clientSupportedCodeActionKinds;

synthesized attribute diagnostics :: [Diagnostic];
synthesized attribute clientSupportedCodeActionKinds :: Maybe<[CodeActionKind]>;

abstract production codeActionContext
top::CodeActionContext::=
  diagnostics::[Diagnostic] only_::Maybe<[CodeActionKind]>
{
  top.diagnostics = diagnostics;
  top.clientSupportedCodeActionKinds = only_;
  top.jsonValue =
    addKeyValuePairToJSONObject("diagnostics", jsonArray(map((.jsonValue), diagnostics)), 
    maybeAddKeyValuePairToJSONObject("only", mapMaybe(jsonArray, mapMaybeList((.jsonValue), only_)), 
    jsonObject([])));
}

function parseCodeActionContext
Either<ResponseError CodeActionContext> ::= val::JSONValue
{
  local diagnostics :: Maybe<[Either<ResponseError Diagnostic>]>
    = mapMaybe(mapJsonArray(parseDiagnostic, _), getValueWithKey("diagnostics", val));
  local only_ :: Maybe<[Either<ResponseError CodeActionKind>]>
    = mapMaybe(mapJsonArray(parseCodeActionKind, _), getValueWithKey("only", val));

  local err :: Maybe<ResponseError> = 
    parseCodeActionContextError(diagnostics, only_);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeActionContext(allFromRight(diagnostics.fromJust), allFromRightMaybe(only_)));
}

function parseCodeActionContextError
Maybe<ResponseError> ::= 
  diagnostics::Maybe<[Either<ResponseError Diagnostic>]> only_::Maybe<[Either<ResponseError CodeActionKind>]>
{
  return
  if !diagnostics.isJust|| anyLeft(diagnostics.fromJust) then just(lspInvalidParams("diagnostics not set or invalid on CodeActionContext"))
  else if only_.isJust && anyLeft(only_.fromJust) then firstLeft(only_.fromJust)
  else nothing();
}

function codeActionContextToJson
JSONValue ::= val::CodeActionContext
{
  return val.jsonValue;
}
