nonterminal CompletionResult with jsonValue;

abstract production nullCompletionResult
top::CompletionResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production completionResultCompletionItemList
top::CompletionResult ::= result::[CompletionItem]
{
  top.jsonValue = jsonArray(map((.jsonValue), result));
}

abstract production completionResultCompletionList
top::CompletionResult ::= result::CompletionList
{
  top.jsonValue = result.jsonValue;
}

function completionResultToJson
JSONValue ::= val::CompletionResult
{
  return val.jsonValue;
}
