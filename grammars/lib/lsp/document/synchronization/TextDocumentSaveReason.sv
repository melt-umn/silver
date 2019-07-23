nonterminal TextDocumentSaveReason with jsonValue, saveReason;

synthesized attribute saveReason :: Integer;

abstract production textDocumentSaveReasonManual
top::TextDocumentSaveReason ::=
{
  top.saveReason = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production textDocumentSaveReasonAfterDelay
top::TextDocumentSaveReason ::=
{
  top.saveReason = 2;
  top.jsonValue = jsonInteger(2);
}

abstract production textDocumentSaveReasonFocusOut
top::TextDocumentSaveReason ::=
{
  top.saveReason = 3;
  top.jsonValue = jsonInteger(3);
}

function parseTextDocumentSaveReason
Either<ResponseError TextDocumentSaveReason> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeTextDocumentSaveReason(intVal)
  | _ -> left(lspInvalidParams("TextDocumentSaveReason not of type Integer"))
  end;
}

function makeTextDocumentSaveReason
Either<ResponseError TextDocumentSaveReason> ::= val::Integer
{
  return
  if val == 1 then right(textDocumentSaveReasonManual())
  else if val == 2 then right(textDocumentSaveReasonAfterDelay())
  else if val == 3 then right(textDocumentSaveReasonFocusOut())
  else left(lspInvalidParams("Invalid TextDocumentSaveReason value"));
}
