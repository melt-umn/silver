nonterminal UnregisterCapabilityResult with jsonValue;

abstract production nullUnregisterCapabilityResult
top::UnregisterCapabilityResult ::=
{
  top.jsonValue = jsonNull();
}

function unregisterCapabilityResultToJson
JSONValue ::= val::UnregisterCapabilityResult
{
  return val.jsonValue;
}
