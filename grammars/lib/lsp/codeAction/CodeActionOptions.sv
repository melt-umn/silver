nonterminal CodeActionOptions with jsonValue, serverCodeActionKindsSupported;

synthesized attribute serverCodeActionKindsSupported :: Maybe<[CodeActionKind]>;

abstract production codeActionOptions
top::CodeActionOptions::=
  codeActionKinds::Maybe<[CodeActionKind]>
{
  top.serverCodeActionKindsSupported = codeActionKinds;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("codeActionKinds", mapMaybe(jsonArray, mapMaybeList((.jsonValue), codeActionKinds)), 
    jsonObject([]));
}

function parseCodeActionOptions
Either<ResponseError CodeActionOptions> ::= val::JSONValue
{
  local codeActionKinds :: Maybe<[Either<ResponseError CodeActionKind>]>
    = mapMaybe(mapJsonArray(parseCodeActionKind, _), getValueWithKey("codeActionKinds", val));

  local err :: Maybe<ResponseError> = 
    parseCodeActionOptionsError(codeActionKinds);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeActionOptions(allFromRightMaybe(codeActionKinds)));
}

function parseCodeActionOptionsError
Maybe<ResponseError> ::= 
  codeActionKinds::Maybe<[Either<ResponseError CodeActionKind>]>
{
  return
  if codeActionKinds.isJust && anyLeft(codeActionKinds.fromJust) then firstLeft(codeActionKinds.fromJust)
  else nothing();
}

function codeActionOptionsToJson
JSONValue ::= val::CodeActionOptions
{
  return val.jsonValue;
}
