nonterminal RenameOptions with jsonValue, testBeforeRename;

synthesized attribute testBeforeRename :: Maybe<Boolean>;

abstract production renameOptions
top::RenameOptions::=
  prepareProvider::Maybe<Boolean>
{
  top.testBeforeRename = prepareProvider;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("prepareProvider", mapMaybe(jsonBoolean, prepareProvider), 
    jsonObject([]));
}

function parseRenameOptions
Either<ResponseError RenameOptions> ::= val::JSONValue
{
  local prepareProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("prepareProvider", val));

  local err :: Maybe<ResponseError> = 
    parseRenameOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(renameOptions(prepareProvider));
}

function parseRenameOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function renameOptionsToJson
JSONValue ::= val::RenameOptions
{
  return val.jsonValue;
}
