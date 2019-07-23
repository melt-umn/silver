nonterminal WatchKind with jsonValue, fileWatchKind;

synthesized attribute fileWatchKind :: Integer;

abstract production watchKindCreate
top::WatchKind ::=
{
  top.fileWatchKind = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production watchKindChange
top::WatchKind ::=
{
  top.fileWatchKind = 2;
  top.jsonValue = jsonInteger(2);
}

abstract production watchKindDelete
top::WatchKind ::=
{
  top.fileWatchKind = 4;
  top.jsonValue = jsonInteger(4);
}

function parseWatchKind
Either<ResponseError WatchKind> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeWatchKind(intVal)
  | _ -> left(lspInvalidParams("WatchKind not of type Integer"))
  end;
}

function makeWatchKind
Either<ResponseError WatchKind> ::= val::Integer
{
  return
  if val == 1 then right(watchKindCreate())
  else if val == 2 then right(watchKindChange())
  else if val == 4 then right(watchKindDelete())
  else left(lspInvalidParams("Invalid WatchKind value"));
}
