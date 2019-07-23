nonterminal InitializeResult with jsonValue, serverCapabilities;

synthesized attribute serverCapabilities :: ServerCapabilities;

abstract production initializeResult
top::InitializeResult::=
  capabilities::ServerCapabilities
{
  top.serverCapabilities = capabilities;
  top.jsonValue =
    addKeyValuePairToJSONObject("capabilities", capabilities.jsonValue, 
    jsonObject([]));
}

function parseInitializeResult
Either<ResponseError InitializeResult> ::= val::JSONValue
{
  local capabilities :: Maybe<Either<ResponseError ServerCapabilities>>
    = mapMaybe(parseServerCapabilities, getValueWithKey("capabilities", val));

  local err :: Maybe<ResponseError> = 
    parseInitializeResultError(capabilities);

  return
  if err.isJust
  then left(err.fromJust)
  else right(initializeResult(capabilities.fromJust.fromRight));
}

function parseInitializeResultError
Maybe<ResponseError> ::= 
  capabilities::Maybe<Either<ResponseError ServerCapabilities>>
{
  return
  if !capabilities.isJust|| capabilities.fromJust.isLeft then just(lspInvalidParams("capabilities not set or invalid on InitializeResult"))
  else nothing();
}

function initializeResultToJson
JSONValue ::= val::InitializeResult
{
  return val.jsonValue;
}
