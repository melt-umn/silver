nonterminal ConfigurationItem with jsonValue, configurationScope, configurationSection;

synthesized attribute configurationScope :: Maybe<DocumentUri>;
synthesized attribute configurationSection :: Maybe<String>;

abstract production configurationItem
top::ConfigurationItem::=
  scopeUri::Maybe<DocumentUri> section::Maybe<String>
{
  top.configurationScope = scopeUri;
  top.configurationSection = section;
  top.jsonValue =
    maybeAddKeyValuePairToJSONObject("scopeUri", mapMaybe(jsonString, scopeUri), 
    maybeAddKeyValuePairToJSONObject("section", mapMaybe(jsonString, section), 
    jsonObject([])));
}

function parseConfigurationItem
Either<ResponseError ConfigurationItem> ::= val::JSONValue
{
  local scopeUri :: Maybe<DocumentUri>
    = mapMaybe(jsonToString, getValueWithKey("scopeUri", val));
  local section :: Maybe<String>
    = mapMaybe(jsonToString, getValueWithKey("section", val));

  local err :: Maybe<ResponseError> = 
    parseConfigurationItemError();

  return
  if err.isJust
  then left(err.fromJust)
  else right(configurationItem(scopeUri, section));
}

function parseConfigurationItemError
Maybe<ResponseError> ::= 
  
{
  return
  nothing();
}

function configurationItemToJson
JSONValue ::= val::ConfigurationItem
{
  return val.jsonValue;
}
