nonterminal DocumentLinkOptions with jsonValue, documentLinkResolveProvider;

synthesized attribute documentLinkResolveProvider :: Maybe<Boolean>;

abstract production documentLinkOptions
top::DocumentLinkOptions::=
  resolveProvider::Maybe<Boolean>
{
  top.documentLinkResolveProvider = resolveProvider;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("resolveProvider", mapMaybe(jsonBoolean, resolveProvider), 
    jsonObject([]));
}

function parseDocumentLinkOptions
Either<ResponseError DocumentLinkOptions> ::= val::JSONValue
{
  local resolveProvider :: Maybe<Boolean>
    = mapMaybe(jsonToBoolean, getValueWithKey("resolveProvider", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentLinkOptionsError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentLinkOptions(resolveProvider));
}

function parseDocumentLinkOptionsError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function documentLinkOptionsToJson
JSONValue ::= val::DocumentLinkOptions
{
  return val.jsonValue;
}
