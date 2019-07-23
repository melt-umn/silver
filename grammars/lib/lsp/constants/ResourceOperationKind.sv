nonterminal ResourceOperationKind with jsonValue, resourceOperation;

synthesized attribute resourceOperation :: String;

abstract production resourceOperationKindCreate
top::ResourceOperationKind ::=
{
  top.resourceOperation = "create";
  top.jsonValue = jsonString("create");
}

abstract production resourceOperationKindRename
top::ResourceOperationKind ::=
{
  top.resourceOperation = "rename";
  top.jsonValue = jsonString("rename");
}

abstract production resourceOperationKindDelete
top::ResourceOperationKind ::=
{
  top.resourceOperation = "delete";
  top.jsonValue = jsonString("delete");
}

function parseResourceOperationKind
Either<ResponseError ResourceOperationKind> ::= val::JSONValue
{
  return case val of
  | jsonString(str) -> makeResourceOperationKind(str)
  | _ -> left(lspInvalidParams("ResourceOperationKind not of type String"))
  end;
}

function makeResourceOperationKind
Either<ResponseError ResourceOperationKind> ::= val::String
{
  return
  if stringEq(val, "create") then right(resourceOperationKindCreate())
  else if stringEq(val, "rename") then right(resourceOperationKindRename())
  else if stringEq(val, "delete") then right(resourceOperationKindDelete())
  else left(lspInvalidParams("Invalid ResourceOperationKind value"));
}
