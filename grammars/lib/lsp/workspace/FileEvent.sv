nonterminal FileEvent with jsonValue, uri, fileEventType;

synthesized attribute fileEventType :: FileChangeType;

abstract production fileEvent
top::FileEvent::=
  uri::DocumentUri type::FileChangeType
{
  top.uri = uri;
  top.fileEventType = type;
  top.jsonValue =
    addKeyValuePairToJSONObject("uri", jsonString(uri), 
    addKeyValuePairToJSONObject("type", type.jsonValue, 
    jsonObject([])));
}

function parseFileEvent
Either<ResponseError FileEvent> ::= val::JSONValue
{
  local uri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("uri", val));
  local type :: Maybe<Either<ResponseError FileChangeType>>
    = mapMaybe(parseFileChangeType, getValueWithKey("type", val));

  local err :: Maybe<ResponseError> = 
    parseFileEventError(uri, type);

  return
  if err.isJust
  then left(err.fromJust)
  else right(fileEvent(uri.fromJust, type.fromJust.fromRight));
}

function parseFileEventError
Maybe<ResponseError> ::= 
  uri::Maybe<DocumentUri> type::Maybe<Either<ResponseError FileChangeType>>
{
  return
  if !uri.isJust then just(lspInvalidParams("uri not set on FileEvent"))
  else if !type.isJust|| type.fromJust.isLeft then just(lspInvalidParams("type not set or invalid on FileEvent"))
  else nothing();
}

function fileEventToJson
JSONValue ::= val::FileEvent
{
  return val.jsonValue;
}
