nonterminal WillSaveTextDocumentNotification with jsonValue, willSaveTextDocumentParams;

synthesized attribute willSaveTextDocumentParams :: WillSaveTextDocumentParams;

abstract production willSaveTextDocumentNotification
top::WillSaveTextDocumentNotification::=
  params::WillSaveTextDocumentParams
{
  top.willSaveTextDocumentParams = params;
  top.jsonValue =
    addKeyValuePairToJSONObject("params", params.jsonValue, 
    jsonObject([]));
}

function parseWillSaveTextDocumentNotification
Either<ResponseError WillSaveTextDocumentNotification> ::= val::JSONValue
{
  local params :: Maybe<Either<ResponseError WillSaveTextDocumentParams>>
    = mapMaybe(parseWillSaveTextDocumentParams, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseWillSaveTextDocumentNotificationError(params);

  return
  if err.isJust
  then left(err.fromJust)
  else right(willSaveTextDocumentNotification(params.fromJust.fromRight));
}

function parseWillSaveTextDocumentNotificationError
Maybe<ResponseError> ::= 
  params::Maybe<Either<ResponseError WillSaveTextDocumentParams>>
{
  return
  if !params.isJust|| params.fromJust.isLeft then just(lspInvalidParams("params not set or invalid on WillSaveTextDocumentNotification"))
  else nothing();
}

function willSaveTextDocumentNotificationToJson
JSONValue ::= val::WillSaveTextDocumentNotification
{
  return val.jsonValue;
}
