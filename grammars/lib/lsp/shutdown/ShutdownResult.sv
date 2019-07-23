nonterminal ShutdownResult with jsonValue;

abstract production nullShutdownResult
top::ShutdownResult ::=
{
  top.jsonValue = jsonNull();
}

function shutdownResultToJson
JSONValue ::= val::ShutdownResult
{
  return val.jsonValue;
}
