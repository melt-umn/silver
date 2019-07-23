nonterminal RenameResult with jsonValue;

abstract production nullRenameResult
top::RenameResult ::=
{
  top.jsonValue = jsonNull();
}

abstract production renameResultWorkspaceEdit
top::RenameResult ::= result::WorkspaceEdit
{
  top.jsonValue = result.jsonValue;
}

function renameResultToJson
JSONValue ::= val::RenameResult
{
  return val.jsonValue;
}
