nonterminal CompletionItemResolveResult with jsonValue;

abstract production completionItemResolveResultCompletionItem
top::CompletionItemResolveResult ::= result::CompletionItem
{
  top.jsonValue = result.jsonValue;
}

function completionItemResolveResultToJson
JSONValue ::= val::CompletionItemResolveResult
{
  return val.jsonValue;
}
