nonterminal ConfigurationParams with jsonValue, configurationItems;

synthesized attribute configurationItems :: [ConfigurationItem];

abstract production configurationParams
top::ConfigurationParams::=
  items::[ConfigurationItem]
{
  top.configurationItems = items;
  top.jsonValue =
    addKeyValuePairToJSONObject("items", jsonArray(map((.jsonValue), items)), 
    jsonObject([]));
}

function parseConfigurationParams
Either<ResponseError ConfigurationParams> ::= val::JSONValue
{
  local items :: Maybe<[Either<ResponseError ConfigurationItem>]>
    = mapMaybe(mapJsonArray(parseConfigurationItem, _), getValueWithKey("items", val));

  local err :: Maybe<ResponseError> = 
    parseConfigurationParamsError(items);

  return
  if err.isJust
  then left(err.fromJust)
  else right(configurationParams(allFromRight(items.fromJust)));
}

function parseConfigurationParamsError
Maybe<ResponseError> ::= 
  items::Maybe<[Either<ResponseError ConfigurationItem>]>
{
  return
  if !items.isJust|| anyLeft(items.fromJust) then just(lspInvalidParams("items not set or invalid on ConfigurationParams"))
  else nothing();
}

function configurationParamsToJson
JSONValue ::= val::ConfigurationParams
{
  return val.jsonValue;
}
