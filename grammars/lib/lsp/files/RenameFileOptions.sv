nonterminal RenameFileOptions with jsonValue, shouldOverwrite, ignoreIfExists;


abstract production renameFileOptions
top::RenameFileOptions::=
  overwrite::Maybe<Boolean> ignoreIfExists::Maybe<Boolean>
{
  top.shouldOverwrite = overwrite;
  top.ignoreIfExists = ignoreIfExists;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("overwrite", mapMaybe(jsonBoolean, overwrite), 
    maybeAddKeyValuePairToJSONObject("ignoreIfExists", mapMaybe(jsonBoolean, ignoreIfExists), 
    jsonObject([])));
}

function parseRenameFileOptions
Either<ResponseError RenameFileOptions> ::= val::JSONValue
{
  local overwrite :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("overwrite", val));
  local ignoreIfExists :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("ignoreIfExists", val));

  local err :: Maybe<ResponseError> = 
    parseRenameFileOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(renameFileOptions(overwrite, ignoreIfExists));
}

function parseRenameFileOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function renameFileOptionsToJson
JSONValue ::= val::RenameFileOptions
{
  return val.jsonValue;
}
