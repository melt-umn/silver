nonterminal SaveOptions with jsonValue, includeTextOnSave;

synthesized attribute includeTextOnSave :: Maybe<Boolean>;

abstract production saveOptions
top::SaveOptions::=
  includeText::Maybe<Boolean>
{
  top.includeTextOnSave = includeText;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("includeText", mapMaybe(jsonBoolean, includeText), 
    jsonObject([]));
}

function parseSaveOptions
Either<ResponseError SaveOptions> ::= val::JSONValue
{
  local includeText :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("includeText", val));

  local err :: Maybe<ResponseError> = 
    parseSaveOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(saveOptions(includeText));
}

function parseSaveOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function saveOptionsToJson
JSONValue ::= val::SaveOptions
{
  return val.jsonValue;
}
