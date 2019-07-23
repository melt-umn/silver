nonterminal CompletionItemKindCapabilities with jsonValue, supportedCompletionItemValues;

synthesized attribute supportedCompletionItemValues :: Maybe<[CompletionItemKind]>;

abstract production completionItemKindCapabilities
top::CompletionItemKindCapabilities::=
  valueSet::Maybe<[CompletionItemKind]>
{
  top.supportedCompletionItemValues = valueSet;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("valueSet", mapMaybe(jsonArray, mapMaybeList((.jsonValue), valueSet)), 
    jsonObject([]));
}

function parseCompletionItemKindCapabilities
Either<ResponseError CompletionItemKindCapabilities> ::= val::JSONValue
{
  local valueSet :: Maybe<[Either<ResponseError CompletionItemKind>]>
    = mapMaybe(mapJsonArray(parseCompletionItemKind, _), getValueWithKey("valueSet", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionItemKindCapabilitiesError(valueSet);

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionItemKindCapabilities(allFromRightMaybe(valueSet)));
}

function parseCompletionItemKindCapabilitiesError
Maybe<ResponseError> ::= 
  valueSet::Maybe<[Either<ResponseError CompletionItemKind>]>
{
  return
  if valueSet.isJust && anyLeft(valueSet.fromJust) then firstLeft(valueSet.fromJust)
  else nothing();
}

function completionItemKindCapabilitiesToJson
JSONValue ::= val::CompletionItemKindCapabilities
{
  return val.jsonValue;
}
