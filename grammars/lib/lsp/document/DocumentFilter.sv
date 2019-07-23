nonterminal DocumentFilter with jsonValue, languageId, uriScheme, globPattern;

synthesized attribute languageId :: Maybe<String>;
synthesized attribute uriScheme :: Maybe<String>;
synthesized attribute globPattern :: Maybe<String>;

abstract production documentFilter
top::DocumentFilter::=
  language::Maybe<String> scheme::Maybe<String> pattern::Maybe<String>
{
  top.languageId = language;
  top.uriScheme = scheme;
  top.globPattern = pattern;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("language", mapMaybe(jsonString, language), 
    maybeAddKeyValuePairToJSONObject("scheme", mapMaybe(jsonString, scheme), 
    maybeAddKeyValuePairToJSONObject("pattern", mapMaybe(jsonString, pattern), 
    jsonObject([]))));
}

function parseDocumentFilter
Either<ResponseError DocumentFilter> ::= val::JSONValue
{
  local language :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("language", val));
  local scheme :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("scheme", val));
  local pattern :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("pattern", val));

  local err :: Maybe<ResponseError> = 
    parseDocumentFilterError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(documentFilter(language, scheme, pattern));
}

function parseDocumentFilterError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function documentFilterToJson
JSONValue ::= val::DocumentFilter
{
  return val.jsonValue;
}
