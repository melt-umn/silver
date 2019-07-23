nonterminal DeleteFileOptions with jsonValue, deleteRecursively, ignoreIfDoesNotExist;

synthesized attribute deleteRecursively :: Maybe<Boolean>;
synthesized attribute ignoreIfDoesNotExist :: Maybe<Boolean>;

abstract production deleteFileOptions
top::DeleteFileOptions::=
  recursive::Maybe<Boolean> ignoreIfNotExists::Maybe<Boolean>
{
  top.deleteRecursively = recursive;
  top.ignoreIfDoesNotExist = ignoreIfNotExists;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("recursive", mapMaybe(jsonBoolean, recursive), 
    maybeAddKeyValuePairToJSONObject("ignoreIfNotExists", mapMaybe(jsonBoolean, ignoreIfNotExists), 
    jsonObject([])));
}

function parseDeleteFileOptions
Either<ResponseError DeleteFileOptions> ::= val::JSONValue
{
  local recursive :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("recursive", val));
  local ignoreIfNotExists :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("ignoreIfNotExists", val));

  local err :: Maybe<ResponseError> = 
    parseDeleteFileOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(deleteFileOptions(recursive, ignoreIfNotExists));
}

function parseDeleteFileOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function deleteFileOptionsToJson
JSONValue ::= val::DeleteFileOptions
{
  return val.jsonValue;
}
