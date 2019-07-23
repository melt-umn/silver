nonterminal DidOpenTextDocumentNotification with jsonValue, didOpenTextDocumentParams;

synthesized attribute didOpenTextDocumentParams :: DidOpenTextDocumentParams;

abstract production didOpenTextDocumentNotification
top::DidOpenTextDocumentNotification::=
  params::DidOpenTextDocumentParams
{
  top.didOpenTextDocumentParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDidOpenTextDocumentNotification
Either<ResponseError DidOpenTextDocumentNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DidOpenTextDocumentParams>>
    = mapMaybe(parseDidOpenTextDocumentParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDidOpenTextDocumentNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didOpenTextDocumentNotification(params.fromJust.fromRight));
}

function parseDidOpenTextDocumentNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DidOpenTextDocumentParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DidOpenTextDocumentNotification"))
  else nothing();
}

function didOpenTextDocumentNotificationToJson
JSONValue ::= val::DidOpenTextDocumentNotification
{
  return val.jsonValue;
}
