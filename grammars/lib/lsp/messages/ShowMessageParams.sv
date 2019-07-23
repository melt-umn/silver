nonterminal ShowMessageParams with jsonValue, typeOfMessage, message;

synthesized attribute typeOfMessage :: MessageType;

abstract production showMessageParams
top::ShowMessageParams::=
  type::MessageType message::String
{
  top.typeOfMessage = type;
  top.message = message;
  top.jsonValue =
    addKeyValuePairToJSONObject("type", type.jsonValue, 
    addKeyValuePairToJSONObject("message", jsonString(message), 
    jsonObject([])));
}

function parseShowMessageParams
Either<ResponseError ShowMessageParams> ::= val::JSONValue
{
  local type :: Maybe<Either<ResponseError MessageType>>
    = mapMaybe(parseMessageType, getValueWithKey("type", val));
  local message :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("message", val));

  local err :: Maybe<ResponseError> = 
    parseShowMessageParamsError(type, message);

  return
  if err.isJust
  then left(err.fromJust)
  else right(showMessageParams(type.fromJust.fromRight, message.fromJust));
}

function parseShowMessageParamsError
Maybe<ResponseError> ::= 
  type::Maybe<Either<ResponseError MessageType>> message::Maybe<String>
{
  return
  if !type.isJust|| type.fromJust.isLeft then just(lspInvalidParams("type not set or invalid on ShowMessageParams"))
  else if !message.isJust then just(lspInvalidParams("message not set on ShowMessageParams"))
  else nothing();
}

function showMessageParamsToJson
JSONValue ::= val::ShowMessageParams
{
  return val.jsonValue;
}
