nonterminal InsertTextFormat with jsonValue, textInsertionFormat;

synthesized attribute textInsertionFormat :: Integer;

abstract production insertTextFormatPlainText
top::InsertTextFormat ::=
{
  top.textInsertionFormat = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production insertTextFormatSnippet
top::InsertTextFormat ::=
{
  top.textInsertionFormat = 2;
  top.jsonValue = jsonInteger(2);
}

function parseInsertTextFormat
Either<ResponseError InsertTextFormat> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeInsertTextFormat(intVal)
  | _ -> left(lspInvalidParams("InsertTextFormat not of type Integer"))
  end;
}

function makeInsertTextFormat
Either<ResponseError InsertTextFormat> ::= val::Integer
{
  return
  if val == 1 then right(insertTextFormatPlainText())
  else if val == 2 then right(insertTextFormatSnippet())
  else left(lspInvalidParams("Invalid InsertTextFormat value"));
}
