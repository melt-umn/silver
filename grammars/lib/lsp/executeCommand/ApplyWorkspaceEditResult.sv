nonterminal ApplyWorkspaceEditResult with jsonValue;

abstract production applyWorkspaceEditResultApplyWorkspaceEditResponse
top::ApplyWorkspaceEditResult ::= result::ApplyWorkspaceEditResponse
{
  top.jsonValue = result.jsonValue;
}

function applyWorkspaceEditResultToJson
JSONValue ::= val::ApplyWorkspaceEditResult
{
  return val.jsonValue;
}
