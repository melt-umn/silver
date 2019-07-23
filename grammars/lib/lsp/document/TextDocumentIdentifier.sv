nonterminal TextDocumentIdentifier with jsonValue, uri;


abstract production textDocumentIdentifier
top::TextDocumentIdentifier::=
  uri::DocumentUri
{
  top.uri = uri;
  top.jsonValue =
    addKeyValuePairToJSONObject("uri", jsonString(uri), 
    jsonObject([]));
}

function parseTextDocumentIdentifier
Either<ResponseError TextDocumentIdentifier> ::= val::JSONValue
{
  local uri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("uri", val));

  local err :: Maybe<ResponseError> = 
    parseTextDocumentIdentifierError(uri);

  return
  if err.isJust
  then left(err.fromJust)
  else right(textDocumentIdentifier(uri.fromJust));
}

function parseTextDocumentIdentifierError
Maybe<ResponseError> ::= 
  uri::Maybe<DocumentUri>
{
  return
  if !uri.isJust then just(lspInvalidParams("uri not set on TextDocumentIdentifier"))
  else nothing();
}

function textDocumentIdentifierToJson
JSONValue ::= val::TextDocumentIdentifier
{
  return val.jsonValue;
}
