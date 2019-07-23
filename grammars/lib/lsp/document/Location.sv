  nonterminal Location with jsonValue, uri, range;


  abstract production location
  top::Location::=
    uri::DocumentUri range::Range
  {
    top.uri = uri;
    top.range = range;
    top.jsonValue =
      addKeyValuePairToJSONObject("uri", jsonString(uri), 
      addKeyValuePairToJSONObject("range", range.jsonValue, 
      jsonObject([])));
  }

  function parseLocation
  Either<ResponseError Location> ::= val::JSONValue
  {
    local uri :: Maybe<DocumentUri>
      = mapMaybe(jsonToString, getValueWithKey("uri", val));
    local range :: Maybe<Either<ResponseError Range>>
    = mapMaybe(parseRange, getValueWithKey("range", val));

  local err :: Maybe<ResponseError> = 
    parseLocationError(uri, range);

  return
  if err.isJust
  then left(err.fromJust)
  else right(location(uri.fromJust, range.fromJust.fromRight));
}

function parseLocationError
Maybe<ResponseError> ::= 
  uri::Maybe<DocumentUri> range::Maybe<Either<ResponseError Range>>
{
  return
  if !uri.isJust then just(lspInvalidParams("uri not set on Location"))
  else if !range.isJust|| range.fromJust.isLeft then just(lspInvalidParams("range not set or invalid on Location"))
  else nothing();
}

function locationToJson
JSONValue ::= val::Location
{
  return val.jsonValue;
}
