nonterminal CodeLensOptions with jsonValue, codeLensResolveProvider;

synthesized attribute codeLensResolveProvider :: Maybe<Boolean>;

abstract production codeLensOptions
top::CodeLensOptions::=
  resolveProvider::Maybe<Boolean>
{
  top.codeLensResolveProvider = resolveProvider;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("resolveProvider", mapMaybe(jsonBoolean, resolveProvider), 
    jsonObject([]));
}

function parseCodeLensOptions
Either<ResponseError CodeLensOptions> ::= val::JSONValue
{
  local resolveProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("resolveProvider", val));

  local err :: Maybe<ResponseError> = 
    parseCodeLensOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(codeLensOptions(resolveProvider));
}

function parseCodeLensOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function codeLensOptionsToJson
JSONValue ::= val::CodeLensOptions
{
  return val.jsonValue;
}
