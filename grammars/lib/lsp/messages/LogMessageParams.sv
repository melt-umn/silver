nonterminal LogMessageParams with jsonValue, typeOfMessage, message;


abstract production logMessageParams
top::LogMessageParams::=
  type::MessageType message::String
{
  top.typeOfMessage = type;
  top.message = message;
  top.jsonValue =
    addKeyValuePairToJSONObject("type", type.jsonValue, 
    addKeyValuePairToJSONObject("message", jsonString(message), 
    jsonObject([])));
}

function parseLogMessageParams
Either<ResponseError LogMessageParams> ::= val::JSONValue
{
  local type :: Maybe<Either<ResponseError MessageType>>
    = mapMaybe(parseMessageType, getValueWithKey("type", val));
  local message :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("message", val));

  local err :: Maybe<ResponseError> = 
    parseLogMessageParamsError(type, message);

  return
  if err.isJust
  then left(err.fromJust)
  else right(logMessageParams(type.fromJust.fromRight, message.fromJust));
}

function parseLogMessageParamsError
Maybe<ResponseError> ::= 
  type::Maybe<Either<ResponseError MessageType>> message::Maybe<String>
{
  return
  if !type.isJust|| type.fromJust.isLeft then just(lspInvalidParams("type not set or invalid on LogMessageParams"))
  else if !message.isJust then just(lspInvalidParams("message not set on LogMessageParams"))
  else nothing();
}

function logMessageParamsToJson
JSONValue ::= val::LogMessageParams
{
  return val.jsonValue;
}
