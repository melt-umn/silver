nonterminal HoverResult with jsonValue;

abstract production nullHoverResult
top::HoverResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production hoverResultHover
top::HoverResult ::= result::Hover
{
  top.jsonValue = result.jsonValue;
}

function hoverResultToJson
JSONValue ::= val::HoverResult
{
  return val.jsonValue;
}
