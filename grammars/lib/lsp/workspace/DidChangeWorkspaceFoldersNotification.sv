nonterminal DidChangeWorkspaceFoldersNotification with jsonValue, didChangeWorkspaceFoldersParams;

synthesized attribute didChangeWorkspaceFoldersParams :: DidChangeWorkspaceFoldersParams;

abstract production didChangeWorkspaceFoldersNotification
top::DidChangeWorkspaceFoldersNotification::=
  params::DidChangeWorkspaceFoldersParams
{
  top.didChangeWorkspaceFoldersParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDidChangeWorkspaceFoldersNotification
Either<ResponseError DidChangeWorkspaceFoldersNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DidChangeWorkspaceFoldersParams>>
    = mapMaybe(parseDidChangeWorkspaceFoldersParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeWorkspaceFoldersNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeWorkspaceFoldersNotification(params.fromJust.fromRight));
}

function parseDidChangeWorkspaceFoldersNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DidChangeWorkspaceFoldersParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DidChangeWorkspaceFoldersNotification"))
  else nothing();
}

function didChangeWorkspaceFoldersNotificationToJson
JSONValue ::= val::DidChangeWorkspaceFoldersNotification
{
  return val.jsonValue;
}
