nonterminal DidChangeWorkspaceFoldersParams with jsonValue, workspaceFolderChangeEvent;

synthesized attribute workspaceFolderChangeEvent :: WorkspaceFoldersChangeEvent;

abstract production didChangeWorkspaceFoldersParams
top::DidChangeWorkspaceFoldersParams::=
  event::WorkspaceFoldersChangeEvent
{
  top.workspaceFolderChangeEvent = event;
  top.jsonValue =
    addKeyValuePairToJSONObject("event", event.jsonValue, 
    jsonObject([]));
}

function parseDidChangeWorkspaceFoldersParams
Either<ResponseError DidChangeWorkspaceFoldersParams> ::= val::JSONValue
{
  local event :: Maybe<Either<ResponseError WorkspaceFoldersChangeEvent>>
    = mapMaybe(parseWorkspaceFoldersChangeEvent, getValueWithKey("event", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeWorkspaceFoldersParamsError(event);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeWorkspaceFoldersParams(event.fromJust.fromRight));
}

function parseDidChangeWorkspaceFoldersParamsError
Maybe<ResponseError> ::= 
  event::Maybe<Either<ResponseError WorkspaceFoldersChangeEvent>>
{
  return
  if !event.isJust|| event.fromJust.isLeft then just(lspInvalidParams("event not set or invalid on DidChangeWorkspaceFoldersParams"))
  else nothing();
}

function didChangeWorkspaceFoldersParamsToJson
JSONValue ::= val::DidChangeWorkspaceFoldersParams
{
  return val.jsonValue;
}
