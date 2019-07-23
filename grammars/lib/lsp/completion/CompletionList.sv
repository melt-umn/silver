nonterminal CompletionList with jsonValue, isCompletionIncomplete, completionItems;

synthesized attribute isCompletionIncomplete :: Boolean;
synthesized attribute completionItems :: [CompletionItem];

abstract production completionList
top::CompletionList::=
  isIncomplete::Boolean items::[CompletionItem]
{
  top.isCompletionIncomplete = isIncomplete;
  top.completionItems = items;
  top.jsonValue =
    addKeyValuePairToJSONObject("isIncomplete", jsonBoolean(isIncomplete), 
    addKeyValuePairToJSONObject("items", jsonArray(map((.jsonValue), items)), 
    jsonObject([])));
}

function parseCompletionList
Either<ResponseError CompletionList> ::= val::JSONValue
{
  local isIncomplete :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("isIncomplete", val));
  local items :: Maybe<[Either<ResponseError CompletionItem>]>
    = mapMaybe(mapJsonArray(parseCompletionItem, _), getValueWithKey("items", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionListError(isIncomplete, items);

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionList(isIncomplete.fromJust, allFromRight(items.fromJust)));
}

function parseCompletionListError
Maybe<ResponseError> ::= 
  isIncomplete::Maybe<Boolean> items::Maybe<[Either<ResponseError CompletionItem>]>
{
  return
  if !isIncomplete.isJust then just(lspInvalidParams("isIncomplete not set on CompletionList"))
  else if !items.isJust|| anyLeft(items.fromJust) then just(lspInvalidParams("items not set or invalid on CompletionList"))
  else nothing();
}

function completionListToJson
JSONValue ::= val::CompletionList
{
  return val.jsonValue;
}
