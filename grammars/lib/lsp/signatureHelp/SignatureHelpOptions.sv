nonterminal SignatureHelpOptions with jsonValue, signatureHelpTriggerCharacters;

synthesized attribute signatureHelpTriggerCharacters :: Maybe<[String]>;

abstract production signatureHelpOptions
top::SignatureHelpOptions::=
  triggerCharacters::Maybe<[String]>
{
  top.signatureHelpTriggerCharacters = triggerCharacters;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("triggerCharacters", mapMaybe(jsonArray, mapMaybeList(jsonString, triggerCharacters)), 
    jsonObject([]));
}

function parseSignatureHelpOptions
Either<ResponseError SignatureHelpOptions> ::= val::JSONValue
{
  local triggerCharacters :: Maybe<[String]>
    = mapMaybe(mapJsonArray(jsonToString, _), getValueWithKey("triggerCharacters", val));

  local err :: Maybe<ResponseError> = 
    parseSignatureHelpOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(signatureHelpOptions(triggerCharacters));
}

function parseSignatureHelpOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function signatureHelpOptionsToJson
JSONValue ::= val::SignatureHelpOptions
{
  return val.jsonValue;
}
