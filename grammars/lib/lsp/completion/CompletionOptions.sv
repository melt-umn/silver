nonterminal CompletionOptions with jsonValue, completionResolveProvider, completionTriggerCharacters;

synthesized attribute completionResolveProvider :: Maybe<Boolean>;
synthesized attribute completionTriggerCharacters :: Maybe<[String]>;

abstract production completionOptions
top::CompletionOptions::=
  resolveProvider::Maybe<Boolean> triggerCharacters::Maybe<[String]>
{
  top.completionResolveProvider = resolveProvider;
  top.completionTriggerCharacters = triggerCharacters;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("resolveProvider", mapMaybe(jsonBoolean, resolveProvider), 
    maybeAddKeyValuePairToJSONObject("triggerCharacters", mapMaybe(jsonArray, mapMaybeList(jsonString, triggerCharacters)), 
    jsonObject([])));
}

function parseCompletionOptions
Either<ResponseError CompletionOptions> ::= val::JSONValue
{
  local resolveProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("resolveProvider", val));
  local triggerCharacters :: Maybe<[String]>
    = mapMaybe(mapJsonArray(jsonToString, _), getValueWithKey("triggerCharacters", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionOptions(resolveProvider, triggerCharacters));
}

function parseCompletionOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function completionOptionsToJson
JSONValue ::= val::CompletionOptions
{
  return val.jsonValue;
}
