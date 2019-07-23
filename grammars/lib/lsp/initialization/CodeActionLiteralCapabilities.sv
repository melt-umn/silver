nonterminal CodeActionLiteralCapabilities with jsonValue, supportedCodeActionKinds;

synthesized attribute supportedCodeActionKinds :: Maybe<[CodeActionKind]>;

abstract production codeActionLiteralCapabilities
top::CodeActionLiteralCapabilities::=
  valueSet::Maybe<[CodeActionKind]>
{
  top.supportedCodeActionKinds = valueSet;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("valueSet", mapMaybe(jsonArray, mapMaybeList((.jsonValue), valueSet)), 
    jsonObject([]));
}

function parseCodeActionLiteralCapabilities
Either<ResponseError CodeActionLiteralCapabilities> ::= val::JSONValue
{
  local valueSet :: Maybe<[Either<ResponseError CodeActionKind>]>
    = mapMaybe(mapJsonArray(parseCodeActionKind, _), getValueWithKey("valueSet", val));

  local err :: Maybe<ResponseError> = 
    parseCodeActionLiteralCapabilitiesError(valueSet);

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeActionLiteralCapabilities(allFromRightMaybe(valueSet)));
}

function parseCodeActionLiteralCapabilitiesError
Maybe<ResponseError> ::= 
  valueSet::Maybe<[Either<ResponseError CodeActionKind>]>
{
  return
  if valueSet.isJust && anyLeft(valueSet.fromJust) then firstLeft(valueSet.fromJust)
  else nothing();
}

function codeActionLiteralCapabilitiesToJson
JSONValue ::= val::CodeActionLiteralCapabilities
{
  return val.jsonValue;
}
