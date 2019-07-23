nonterminal ShutdownRequest with jsonValue, shutdownParams;

synthesized attribute shutdownParams :: Maybe<JSONValue>;

abstract production shutdownRequest
top::ShutdownRequest::=
  params::Maybe<JSONValue>
{
  top.shutdownParams = params;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("params", params, 
    jsonObject([]));
}

function parseShutdownRequest
Either<ResponseError ShutdownRequest> ::= val::JSONValue
{
  local params :: Maybe<JSONValue>
    = mapMaybe(identity, getValueWithKey("params", val));

  local err :: Maybe<ResponseError> = 
    parseShutdownRequestError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(shutdownRequest(params));
}

function parseShutdownRequestError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function shutdownRequestToJson
JSONValue ::= val::ShutdownRequest
{
  return val.jsonValue;
}
