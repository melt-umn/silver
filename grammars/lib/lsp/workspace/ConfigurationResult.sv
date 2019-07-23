nonterminal ConfigurationResult with jsonValue;

abstract production configurationResultJSONValue
top::ConfigurationResult ::= result::JSONValue
{
  top.jsonValue = result;
}

function configurationResultToJson
JSONValue ::= val::ConfigurationResult
{
  return val.jsonValue;
}
