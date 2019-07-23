nonterminal ShowMessageRequestParams with jsonValue, typeOfMessage, message, messageActionItems;

synthesized attribute messageActionItems :: Maybe<[MessageActionItem]>;

abstract production showMessageRequestParams
top::ShowMessageRequestParams::=
  type::MessageType message::String actions::Maybe<[MessageActionItem]>
{
  top.typeOfMessage = type;
  top.message = message;
  top.messageActionItems = actions;
  top.jsonValue =
    addKeyValuePairToJSONObject("type", type.jsonValue, 
    addKeyValuePairToJSONObject("message", jsonString(message), 
    maybeAddKeyValuePairToJSONObject("actions", mapMaybe(jsonArray, mapMaybeList((.jsonValue), actions)), 
    jsonObject([]))));
}

function parseShowMessageRequestParams
Either<ResponseError ShowMessageRequestParams> ::= val::JSONValue
{
  local type :: Maybe<Either<ResponseError MessageType>>
    = mapMaybe(parseMessageType, getValueWithKey("type", val));
  local message :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("message", val));
  local actions :: Maybe<[Either<ResponseError MessageActionItem>]>
    = mapMaybe(mapJsonArray(parseMessageActionItem, _), getValueWithKey("actions", val));

  local err :: Maybe<ResponseError> = 
    parseShowMessageRequestParamsError(type, message, actions);

  return
  if err.isJust
  then left(err.fromJust)
  else right(showMessageRequestParams(type.fromJust.fromRight, message.fromJust, allFromRightMaybe(actions)));
}

function parseShowMessageRequestParamsError
Maybe<ResponseError> ::= 
  type::Maybe<Either<ResponseError MessageType>> message::Maybe<String> actions::Maybe<[Either<ResponseError MessageActionItem>]>
{
  return
  if !type.isJust|| type.fromJust.isLeft then just(lspInvalidParams("type not set or invalid on ShowMessageRequestParams"))
  else if !message.isJust then just(lspInvalidParams("message not set on ShowMessageRequestParams"))
  else if actions.isJust && anyLeft(actions.fromJust) then firstLeft(actions.fromJust)
  else nothing();
}

function showMessageRequestParamsToJson
JSONValue ::= val::ShowMessageRequestParams
{
  return val.jsonValue;
}
