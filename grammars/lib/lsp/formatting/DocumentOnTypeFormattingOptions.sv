nonterminal DocumentOnTypeFormattingOptions with jsonValue, onTypeFormatFirstTriggerCharacter, onTypeFormatRestTriggerCharacters;

synthesized attribute onTypeFormatFirstTriggerCharacter :: String;
synthesized attribute onTypeFormatRestTriggerCharacters :: Maybe<[String]>;

abstract production documentOnTypeFormattingOptions
top::DocumentOnTypeFormattingOptions::=
  firstTriggerCharacter::String moreTriggerCharacter::Maybe<[String]>
{
  top.onTypeFormatFirstTriggerCharacter = firstTriggerCharacter;
  top.onTypeFormatRestTriggerCharacters = moreTriggerCharacter;
  top.jsonValue =
    addKeyValuePairToJSONObject("firstTriggerCharacter", jsonString(firstTriggerCharacter), 
    maybeAddKeyValuePairToJSONObject("moreTriggerCharacter", mapMaybe(jsonArray, mapMaybeList(jsonString, moreTriggerCharacter)), 
    jsonObject([])));
}

function parseDocumentOnTypeFormattingOptions
Either<ResponseError DocumentOnTypeFormattingOptions> ::= val::JSONValue
{
  local firstTriggerCharacter :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("firstTriggerCharacter", val));
  local moreTriggerCharacter :: Maybe<[String]>
    = mapMaybe(mapJsonArray(jsonToString, _), getValueWithKey("moreTriggerCharacter", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentOnTypeFormattingOptionsError(firstTriggerCharacter);

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentOnTypeFormattingOptions(firstTriggerCharacter.fromJust, moreTriggerCharacter));
}

function parseDocumentOnTypeFormattingOptionsError
Maybe<ResponseError> ::= 
  firstTriggerCharacter::Maybe<String>
{
  return
  if !firstTriggerCharacter.isJust then just(lspInvalidParams("firstTriggerCharacter not set on DocumentOnTypeFormattingOptions"))
  else nothing();
}

function documentOnTypeFormattingOptionsToJson
JSONValue ::= val::DocumentOnTypeFormattingOptions
{
  return val.jsonValue;
}
