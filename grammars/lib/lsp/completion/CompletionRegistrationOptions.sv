nonterminal CompletionRegistrationOptions with jsonValue, charactersToTriggerCompletionOn, charactersToCommitCompletionOn, providesCompletionResolve;

synthesized attribute charactersToTriggerCompletionOn :: Maybe<[String]>;
synthesized attribute charactersToCommitCompletionOn :: Maybe<[String]>;
synthesized attribute providesCompletionResolve :: Maybe<Boolean>;

abstract production completionRegistrationOptions
top::CompletionRegistrationOptions::=
  triggerCharacters::Maybe<[String]> allCommitCharacters::Maybe<[String]> resolveProvider::Maybe<Boolean>
{
  top.charactersToTriggerCompletionOn = triggerCharacters;
  top.charactersToCommitCompletionOn = allCommitCharacters;
  top.providesCompletionResolve = resolveProvider;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("triggerCharacters", mapMaybe(jsonArray, mapMaybeList(jsonString, triggerCharacters)), 
    maybeAddKeyValuePairToJSONObject("allCommitCharacters", mapMaybe(jsonArray, mapMaybeList(jsonString, allCommitCharacters)), 
    maybeAddKeyValuePairToJSONObject("resolveProvider", mapMaybe(jsonBoolean, resolveProvider), 
    jsonObject([]))));
}

function parseCompletionRegistrationOptions
Either<ResponseError CompletionRegistrationOptions> ::= val::JSONValue
{
  local triggerCharacters :: Maybe<[String]>
    = mapMaybe(mapJsonArray(jsonToString, _), getValueWithKey("triggerCharacters", val));
  local allCommitCharacters :: Maybe<[String]>
    = mapMaybe(mapJsonArray(jsonToString, _), getValueWithKey("allCommitCharacters", val));
  local resolveProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("resolveProvider", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionRegistrationOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionRegistrationOptions(triggerCharacters, allCommitCharacters, resolveProvider));
}

function parseCompletionRegistrationOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function completionRegistrationOptionsToJson
JSONValue ::= val::CompletionRegistrationOptions
{
  return val.jsonValue;
}
