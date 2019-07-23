nonterminal TextDocumentItem with jsonValue, uri, documentLanguageId, documentVersionNumber, documentText;

synthesized attribute documentLanguageId :: String;

abstract production textDocumentItem
top::TextDocumentItem::=
  uri::DocumentUri languageId::String version::Integer text::String
{
  top.uri = uri;
  top.documentLanguageId = languageId;
  top.documentVersionNumber = version;
  top.documentText = text;
  top.jsonValue =
    addKeyValuePairToJSONObject("uri", jsonString(uri), 
    addKeyValuePairToJSONObject("languageId", jsonString(languageId), 
    addKeyValuePairToJSONObject("version", jsonInteger(version), 
    addKeyValuePairToJSONObject("text", jsonString(text), 
    jsonObject([])))));
}

function parseTextDocumentItem
Either<ResponseError TextDocumentItem> ::= val::JSONValue
{
  local uri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("uri", val));
  local languageId :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("languageId", val));
  local version :: Maybe<Integer>
    = mapMaybe(jsonToInteger, getValueWithKey("version", val));
  local text :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("text", val));

  local err :: Maybe<ResponseError> = 
    parseTextDocumentItemError(uri, languageId, version, text);

  return
  if err.isJust
  then left(err.fromJust)
  else right(textDocumentItem(uri.fromJust, languageId.fromJust, version.fromJust, text.fromJust));
}

function parseTextDocumentItemError
Maybe<ResponseError> ::= 
  uri::Maybe<DocumentUri> languageId::Maybe<String> version::Maybe<Integer> text::Maybe<String>
{
  return
  if !uri.isJust then just(lspInvalidParams("uri not set on TextDocumentItem"))
  else if !languageId.isJust then just(lspInvalidParams("languageId not set on TextDocumentItem"))
  else if !version.isJust then just(lspInvalidParams("version not set on TextDocumentItem"))
  else if !text.isJust then just(lspInvalidParams("text not set on TextDocumentItem"))
  else nothing();
}

function textDocumentItemToJson
JSONValue ::= val::TextDocumentItem
{
  return val.jsonValue;
}
