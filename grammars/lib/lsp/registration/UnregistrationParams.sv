nonterminal UnregistrationParams with jsonValue, unregistrations;

synthesized attribute unregistrations :: [Unregistration];

abstract production unregistrationParams
top::UnregistrationParams::=
  unregisterations::[Unregistration]
{
  top.unregistrations = unregisterations;
  top.jsonValue =
    addKeyValuePairToJSONObject("unregisterations", jsonArray(map((.jsonValue), unregisterations)), 
    jsonObject([]));
}

function parseUnregistrationParams
Either<ResponseError UnregistrationParams> ::= val::JSONValue
{
  local unregisterations :: Maybe<[Either<ResponseError Unregistration>]>
    = mapMaybe(mapJsonArray(parseUnregistration, _), getValueWithKey("unregisterations", val));

  local err :: Maybe<ResponseError> = 
    parseUnregistrationParamsError(unregisterations);

  return
  if err.isJust
  then left(err.fromJust)
  else right(unregistrationParams(allFromRight(unregisterations.fromJust)));
}

function parseUnregistrationParamsError
Maybe<ResponseError> ::= 
  unregisterations::Maybe<[Either<ResponseError Unregistration>]>
{
  return
  if !unregisterations.isJust|| anyLeft(unregisterations.fromJust) then just(lspInvalidParams("unregisterations not set or invalid on UnregistrationParams"))
  else nothing();
}

function unregistrationParamsToJson
JSONValue ::= val::UnregistrationParams
{
  return val.jsonValue;
}
