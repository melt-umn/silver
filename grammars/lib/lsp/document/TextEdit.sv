nonterminal TextEdit with jsonValue, range, textToInsert;

synthesized attribute textToInsert :: String;

abstract production textEdit
top::TextEdit::=
  range::Range newText::String
{
  top.range = range;
  top.textToInsert = newText;
  top.jsonValue =
    addKeyValuePairToJSONObject("range", range.jsonValue, 
    addKeyValuePairToJSONObject("newText", jsonString(newText), 
    jsonObject([])));
}

function parseTextEdit
Either<ResponseError TextEdit> ::= val::JSONValue
{
  local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));
  local newText :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("newText", val));

  local err :: Maybe<ResponseError> = 
    parseTextEditError(range, newText);

  return
  if err.isJust
  then left(err.fromJust)
  else right(textEdit(range.fromJust.fromRight, newText.fromJust));
}

function parseTextEditError
Maybe<ResponseError> ::= 
  range::Maybe<Either<ResponseError Range>> newText::Maybe<String>
{
  return
  if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on TextEdit"))
  else if !newText.isJust then just(lspInvalidParams("newText not set on TextEdit"))
  else nothing();
}

function textEditToJson
JSONValue ::= val::TextEdit
{
  return val.jsonValue;
}
