nonterminal DidCloseTextDocumentNotification with jsonValue, didCloseTextDocumentParams;

synthesized attribute didCloseTextDocumentParams :: DidCloseTextDocumentParams;

abstract production didCloseTextDocumentNotification
top::DidCloseTextDocumentNotification::=
  params::DidCloseTextDocumentParams
{
  top.didCloseTextDocumentParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDidCloseTextDocumentNotification
Either<ResponseError DidCloseTextDocumentNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DidCloseTextDocumentParams>>
    = mapMaybe(parseDidCloseTextDocumentParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDidCloseTextDocumentNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didCloseTextDocumentNotification(params.fromJust.fromRight));
}

function parseDidCloseTextDocumentNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DidCloseTextDocumentParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DidCloseTextDocumentNotification"))
  else nothing();
}

function didCloseTextDocumentNotificationToJson
JSONValue ::= val::DidCloseTextDocumentNotification
{
  return val.jsonValue;
}
