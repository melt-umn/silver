nonterminal TextDocumentSyncKind with jsonValue, documentSyncType;

synthesized attribute documentSyncType :: Integer;

abstract production textDocumentSyncKindNone
top::TextDocumentSyncKind ::=
{
  top.documentSyncType = 0;
  top.jsonValue = jsonInteger(0);
}

abstract production textDocumentSyncKindFull
top::TextDocumentSyncKind ::=
{
  top.documentSyncType = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production textDocumentSyncKindIncremental
top::TextDocumentSyncKind ::=
{
  top.documentSyncType = 2;
  top.jsonValue = jsonInteger(2);
}

function parseTextDocumentSyncKind
Either<ResponseError TextDocumentSyncKind> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeTextDocumentSyncKind(intVal)
  | _ -> left(lspInvalidParams("TextDocumentSyncKind not of type Integer"))
  end;
}

function makeTextDocumentSyncKind
Either<ResponseError TextDocumentSyncKind> ::= val::Integer
{
  return
  if val == 0 then right(textDocumentSyncKindNone())
  else if val == 1 then right(textDocumentSyncKindFull())
  else if val == 2 then right(textDocumentSyncKindIncremental())
  else left(lspInvalidParams("Invalid TextDocumentSyncKind value"));
}
