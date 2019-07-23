nonterminal DidSaveTextDocumentNotification with jsonValue, didSaveTextDocumentParams;

synthesized attribute didSaveTextDocumentParams :: DidSaveTextDocumentParams;

abstract production didSaveTextDocumentNotification
top::DidSaveTextDocumentNotification::=
  params::DidSaveTextDocumentParams
{
  top.didSaveTextDocumentParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDidSaveTextDocumentNotification
Either<ResponseError DidSaveTextDocumentNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DidSaveTextDocumentParams>>
    = mapMaybe(parseDidSaveTextDocumentParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDidSaveTextDocumentNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didSaveTextDocumentNotification(params.fromJust.fromRight));
}

function parseDidSaveTextDocumentNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DidSaveTextDocumentParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DidSaveTextDocumentNotification"))
  else nothing();
}

function didSaveTextDocumentNotificationToJson
JSONValue ::= val::DidSaveTextDocumentNotification
{
  return val.jsonValue;
}
