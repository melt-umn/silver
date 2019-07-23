nonterminal CompletionTriggerKind with jsonValue, completionTriggerReason;

synthesized attribute completionTriggerReason :: Integer;

abstract production completionTriggerKindInvoked
top::CompletionTriggerKind ::=
{
  top.completionTriggerReason = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production completionTriggerKindTriggerCharacter
top::CompletionTriggerKind ::=
{
  top.completionTriggerReason = 2;
  top.jsonValue = jsonInteger(2);
}

abstract production completionTriggerKindTriggerForIncompleteCompletions
top::CompletionTriggerKind ::=
{
  top.completionTriggerReason = 3;
  top.jsonValue = jsonInteger(3);
}

function parseCompletionTriggerKind
Either<ResponseError CompletionTriggerKind> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeCompletionTriggerKind(intVal)
  | _ -> left(lspInvalidParams("CompletionTriggerKind not of type Integer"))
  end;
}

function makeCompletionTriggerKind
Either<ResponseError CompletionTriggerKind> ::= val::Integer
{
  return
  if val == 1 then right(completionTriggerKindInvoked())
  else if val == 2 then right(completionTriggerKindTriggerCharacter())
  else if val == 3 then right(completionTriggerKindTriggerForIncompleteCompletions())
  else left(lspInvalidParams("Invalid CompletionTriggerKind value"));
}
