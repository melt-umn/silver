nonterminal ShowMessageResult with jsonValue;

abstract production nullShowMessageResult
top::ShowMessageResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production showMessageResultMessageActionItem
top::ShowMessageResult ::= result::MessageActionItem
{
  top.jsonValue = result.jsonValue;
}

function showMessageResultToJson
JSONValue ::= val::ShowMessageResult
{
  return val.jsonValue;
}
