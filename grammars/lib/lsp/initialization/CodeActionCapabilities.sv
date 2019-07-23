nonterminal CodeActionCapabilities with jsonValue, dynamicRegistration, codeActionLiteralCapabilities;

synthesized attribute codeActionLiteralCapabilities :: Maybe<CodeActionLiteralCapabilities>;

abstract production codeActionCapabilities
top::CodeActionCapabilities::=
  dynamicRegistration::Maybe<Boolean> codeActionLiteralSupport::Maybe<CodeActionLiteralCapabilities>
{
  top.dynamicRegistration = dynamicRegistration;
  top.codeActionLiteralCapabilities = codeActionLiteralSupport;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("dynamicRegistration", mapMaybe(jsonBoolean, dynamicRegistration), 
    maybeAddKeyValuePairToJSONObject("codeActionLiteralSupport", mapMaybe((.jsonValue), codeActionLiteralSupport), 
    jsonObject([])));
}

function parseCodeActionCapabilities
Either<ResponseError CodeActionCapabilities> ::= val::JSONValue
{
  local dynamicRegistration :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("dynamicRegistration", val));
  local codeActionLiteralSupport :: Maybe<Either<ResponseError CodeActionLiteralCapabilities>>
    = mapMaybe(parseCodeActionLiteralCapabilities, getValueWithKey("codeActionLiteralSupport", val));

  local err :: Maybe<ResponseError> = 
    parseCodeActionCapabilitiesError(codeActionLiteralSupport);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeActionCapabilities(dynamicRegistration, rightMaybe(codeActionLiteralSupport)));
}

function parseCodeActionCapabilitiesError
Maybe<ResponseError> ::= 
  codeActionLiteralSupport::Maybe<Either<ResponseError CodeActionLiteralCapabilities>>
{
  return
  if codeActionLiteralSupport.isJust && codeActionLiteralSupport.fromJust.isLeft then just(codeActionLiteralSupport.fromJust.fromLeft)
  else nothing();
}

function codeActionCapabilitiesToJson
JSONValue ::= val::CodeActionCapabilities
{
  return val.jsonValue;
}
