nonterminal CreateFileOptions with jsonValue, shouldOverwrite, ignoreIfExists;


abstract production createFileOptions
top::CreateFileOptions::=
  overwrite::Maybe<Boolean> ignoreIfExists::Maybe<Boolean>
{
  top.shouldOverwrite = overwrite;
  top.ignoreIfExists = ignoreIfExists;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("overwrite", mapMaybe(jsonBoolean, overwrite), 
    maybeAddKeyValuePairToJSONObject("ignoreIfExists", mapMaybe(jsonBoolean, ignoreIfExists), 
    jsonObject([])));
}

function parseCreateFileOptions
Either<ResponseError CreateFileOptions> ::= val::JSONValue
{
  local overwrite :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("overwrite", val));
  local ignoreIfExists :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("ignoreIfExists", val));

  local err :: Maybe<ResponseError> = 
    parseCreateFileOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(createFileOptions(overwrite, ignoreIfExists));
}

function parseCreateFileOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function createFileOptionsToJson
JSONValue ::= val::CreateFileOptions
{
  return val.jsonValue;
}
