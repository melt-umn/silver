nonterminal RegisterCapabilityResult with jsonValue;

abstract production nullRegisterCapabilityResult
top::RegisterCapabilityResult ::=
{
  top.jsonValue = jsonNull();
}

function registerCapabilityResultToJson
JSONValue ::= val::RegisterCapabilityResult
{
  return val.jsonValue;
}
