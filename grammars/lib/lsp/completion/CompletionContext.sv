nonterminal CompletionContext with jsonValue, completionTriggerKind, completionTriggerCharacter;

synthesized attribute completionTriggerKind :: CompletionTriggerKind;
synthesized attribute completionTriggerCharacter :: Maybe<String>;

abstract production completionContext
top::CompletionContext::=
  triggerKind::CompletionTriggerKind triggerCharacter::Maybe<String>
{
  top.completionTriggerKind = triggerKind;
  top.completionTriggerCharacter = triggerCharacter;
  top.jsonValue =
    addKeyValuePairToJSONObject("triggerKind", triggerKind.jsonValue, 
    maybeAddKeyValuePairToJSONObject("triggerCharacter", mapMaybe(jsonString, triggerCharacter), 
    jsonObject([])));
}

function parseCompletionContext
Either<ResponseError CompletionContext> ::= val::JSONValue
{
  local triggerKind :: Maybe<Either<ResponseError CompletionTriggerKind>>
    = mapMaybe(parseCompletionTriggerKind, getValueWithKey("triggerKind", val));
  local triggerCharacter :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("triggerCharacter", val));

  local err :: Maybe<ResponseError> = 
    parseCompletionContextError(triggerKind);

  return
  if err.isJust
  then left(err.fromJust)
  else right(completionContext(triggerKind.fromJust.fromRight, triggerCharacter));
}

function parseCompletionContextError
Maybe<ResponseError> ::= 
  triggerKind::Maybe<Either<ResponseError CompletionTriggerKind>>
{
  return
  if !triggerKind.isJust|| triggerKind.fromJust.isLeft then just(lspInvalidParams("triggerKind not set or invalid on CompletionContext"))
  else nothing();
}

function completionContextToJson
JSONValue ::= val::CompletionContext
{
  return val.jsonValue;
}
