nonterminal DocumentLinkResolveResult with jsonValue;

abstract production documentLinkResolveResultDocumentLink
top::DocumentLinkResolveResult ::= result::DocumentLink
{
  top.jsonValue = result.jsonValue;
}

function documentLinkResolveResultToJson
JSONValue ::= val::DocumentLinkResolveResult
{
  return val.jsonValue;
}
