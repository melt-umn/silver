nonterminal MessageActionItem with jsonValue, actionDisplayTitle;

synthesized attribute actionDisplayTitle :: String;

abstract production messageActionItem
top::MessageActionItem::=
  title::String
{
  top.actionDisplayTitle = title;
  top.jsonValue =
    addKeyValuePairToJSONObject("title", jsonString(title), 
    jsonObject([]));
}

function parseMessageActionItem
Either<ResponseError MessageActionItem> ::= val::JSONValue
{
  local title :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("title", val));

  local err :: Maybe<ResponseError> = 
    parseMessageActionItemError(title);

  return
  if err.isJust
  then left(err.fromJust)
  else right(messageActionItem(title.fromJust));
}

function parseMessageActionItemError
Maybe<ResponseError> ::= 
  title::Maybe<String>
{
  return
  if !title.isJust then just(lspInvalidParams("title not set on MessageActionItem"))
  else nothing();
}

function messageActionItemToJson
JSONValue ::= val::MessageActionItem
{
  return val.jsonValue;
}
