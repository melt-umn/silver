nonterminal VersionedTextDocumentIdentifier with jsonValue, uri, documentVersion;

synthesized attribute documentVersion :: Integer;

abstract production versionedTextDocumentIdentifier
top::VersionedTextDocumentIdentifier::=
  uri::DocumentUri version::Integer
{
  top.uri = uri;
  top.documentVersion = version;
  top.jsonValue =
    addKeyValuePairToJSONObject("uri", jsonString(uri), 
    addKeyValuePairToJSONObject("version", jsonInteger(version), 
    jsonObject([])));
}

function parseVersionedTextDocumentIdentifier
Either<ResponseError VersionedTextDocumentIdentifier> ::= val::JSONValue
{
  local uri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("uri", val));
  local version :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("version", val));

  local err :: Maybe<ResponseError> = 
    parseVersionedTextDocumentIdentifierError(uri, version);

  return
  if err.isJust
  then left(err.fromJust)
  else right(versionedTextDocumentIdentifier(uri.fromJust, version.fromJust));
}

function parseVersionedTextDocumentIdentifierError
Maybe<ResponseError> ::= 
  uri::Maybe<DocumentUri> version::Maybe<Integer>
{
  return
  if !uri.isJust then just(lspInvalidParams("uri not set on VersionedTextDocumentIdentifier"))
  else if !version.isJust then just(lspInvalidParams("version not set on VersionedTextDocumentIdentifier"))
  else nothing();
}

function versionedTextDocumentIdentifierToJson
JSONValue ::= val::VersionedTextDocumentIdentifier
{
  return val.jsonValue;
}
