nonterminal DocumentHighlightKind with jsonValue, highlightType;

synthesized attribute highlightType :: Integer;

abstract production documentHighlightKindText
top::DocumentHighlightKind ::=
{
  top.highlightType = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production documentHighlightKindRead
top::DocumentHighlightKind ::=
{
  top.highlightType = 2;
  top.jsonValue = jsonInteger(2);
}

abstract production documentHighlightKindWrite
top::DocumentHighlightKind ::=
{
  top.highlightType = 3;
  top.jsonValue = jsonInteger(3);
}

function parseDocumentHighlightKind
Either<ResponseError DocumentHighlightKind> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeDocumentHighlightKind(intVal)
  | _ -> left(lspInvalidParams("DocumentHighlightKind not of type Integer"))
  end;
}

function makeDocumentHighlightKind
Either<ResponseError DocumentHighlightKind> ::= val::Integer
{
  return
  if val == 1 then right(documentHighlightKindText())
  else if val == 2 then right(documentHighlightKindRead())
  else if val == 3 then right(documentHighlightKindWrite())
  else left(lspInvalidParams("Invalid DocumentHighlightKind value"));
}
