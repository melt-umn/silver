nonterminal DidChangeTextDocumentNotification with jsonValue, didChangeTextDocumentParams;

synthesized attribute didChangeTextDocumentParams :: DidChangeTextDocumentParams;

abstract production didChangeTextDocumentNotification
top::DidChangeTextDocumentNotification::=
  params::DidChangeTextDocumentParams
{
  top.didChangeTextDocumentParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseDidChangeTextDocumentNotification
Either<ResponseError DidChangeTextDocumentNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError DidChangeTextDocumentParams>>
    = mapMaybe(parseDidChangeTextDocumentParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseDidChangeTextDocumentNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(didChangeTextDocumentNotification(params.fromJust.fromRight));
}

function parseDidChangeTextDocumentNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError DidChangeTextDocumentParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on DidChangeTextDocumentNotification"))
  else nothing();
}

function didChangeTextDocumentNotificationToJson
JSONValue ::= val::DidChangeTextDocumentNotification
{
  return val.jsonValue;
}
