nonterminal FileChangeType with jsonValue, fileChangeType;

synthesized attribute fileChangeType :: Integer;

abstract production fileChangeTypeCreated
top::FileChangeType ::=
{
  top.fileChangeType = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production fileChangeTypeChanged
top::FileChangeType ::=
{
  top.fileChangeType = 2;
  top.jsonValue = jsonInteger(2);
}

abstract production fileChangeTypeDeleted
top::FileChangeType ::=
{
  top.fileChangeType = 3;
  top.jsonValue = jsonInteger(3);
}

function parseFileChangeType
Either<ResponseError FileChangeType> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeFileChangeType(intVal)
  | _ -> left(lspInvalidParams("FileChangeType not of type Integer"))
  end;
}

function makeFileChangeType
Either<ResponseError FileChangeType> ::= val::Integer
{
  return
  if val == 1 then right(fileChangeTypeCreated())
  else if val == 2 then right(fileChangeTypeChanged())
  else if val == 3 then right(fileChangeTypeDeleted())
  else left(lspInvalidParams("Invalid FileChangeType value"));
}
