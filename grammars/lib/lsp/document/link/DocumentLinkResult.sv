nonterminal DocumentLinkResult with jsonValue;

abstract production nullDocumentLinkResult
top::DocumentLinkResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production documentLinkResultDocumentLinkList
top::DocumentLinkResult ::= result::[DocumentLink]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

function documentLinkResultToJson
JSONValue ::= val::DocumentLinkResult
{
  return val.jsonValue;
}
