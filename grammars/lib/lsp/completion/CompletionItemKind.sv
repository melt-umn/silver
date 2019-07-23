nonterminal CompletionItemKind with jsonValue, completionItemKind;

synthesized attribute completionItemKind :: Integer;

abstract production completionItemKindText
top::CompletionItemKind ::=
{
  top.completionItemKind = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production completionItemKindMethod
top::CompletionItemKind ::=
{
  top.completionItemKind = 2;
  top.jsonValue = jsonInteger(2);
}

abstract production completionItemKindFunction
top::CompletionItemKind ::=
{
  top.completionItemKind = 3;
  top.jsonValue = jsonInteger(3);
}

abstract production completionItemKindConstructor
top::CompletionItemKind ::=
{
  top.completionItemKind = 4;
  top.jsonValue = jsonInteger(4);
}

abstract production completionItemKindField
top::CompletionItemKind ::=
{
  top.completionItemKind = 5;
  top.jsonValue = jsonInteger(5);
}

abstract production completionItemKindVariable
top::CompletionItemKind ::=
{
  top.completionItemKind = 6;
  top.jsonValue = jsonInteger(6);
}

abstract production completionItemKindClass
top::CompletionItemKind ::=
{
  top.completionItemKind = 7;
  top.jsonValue = jsonInteger(7);
}

abstract production completionItemKindInterface
top::CompletionItemKind ::=
{
  top.completionItemKind = 8;
  top.jsonValue = jsonInteger(8);
}

abstract production completionItemKindModule
top::CompletionItemKind ::=
{
  top.completionItemKind = 9;
  top.jsonValue = jsonInteger(9);
}

abstract production completionItemKindProperty
top::CompletionItemKind ::=
{
  top.completionItemKind = 10;
  top.jsonValue = jsonInteger(10);
}

abstract production completionItemKindUnit
top::CompletionItemKind ::=
{
  top.completionItemKind = 11;
  top.jsonValue = jsonInteger(11);
}

abstract production completionItemKindValue
top::CompletionItemKind ::=
{
  top.completionItemKind = 12;
  top.jsonValue = jsonInteger(12);
}

abstract production completionItemKindEnum
top::CompletionItemKind ::=
{
  top.completionItemKind = 13;
  top.jsonValue = jsonInteger(13);
}

abstract production completionItemKindKeyword
top::CompletionItemKind ::=
{
  top.completionItemKind = 14;
  top.jsonValue = jsonInteger(14);
}

abstract production completionItemKindSnippet
top::CompletionItemKind ::=
{
  top.completionItemKind = 15;
  top.jsonValue = jsonInteger(15);
}

abstract production completionItemKindColor
top::CompletionItemKind ::=
{
  top.completionItemKind = 16;
  top.jsonValue = jsonInteger(16);
}

abstract production completionItemKindFile
top::CompletionItemKind ::=
{
  top.completionItemKind = 17;
  top.jsonValue = jsonInteger(17);
}

abstract production completionItemKindReference
top::CompletionItemKind ::=
{
  top.completionItemKind = 18;
  top.jsonValue = jsonInteger(18);
}

abstract production completionItemKindFolder
top::CompletionItemKind ::=
{
  top.completionItemKind = 19;
  top.jsonValue = jsonInteger(19);
}

abstract production completionItemKindEnumMember
top::CompletionItemKind ::=
{
  top.completionItemKind = 20;
  top.jsonValue = jsonInteger(20);
}

abstract production completionItemKindConstant
top::CompletionItemKind ::=
{
  top.completionItemKind = 21;
  top.jsonValue = jsonInteger(21);
}

abstract production completionItemKindStruct
top::CompletionItemKind ::=
{
  top.completionItemKind = 22;
  top.jsonValue = jsonInteger(22);
}

abstract production completionItemKindEvent
top::CompletionItemKind ::=
{
  top.completionItemKind = 23;
  top.jsonValue = jsonInteger(23);
}

abstract production completionItemKindOperator
top::CompletionItemKind ::=
{
  top.completionItemKind = 24;
  top.jsonValue = jsonInteger(24);
}

abstract production completionItemKindTypeParameter
top::CompletionItemKind ::=
{
  top.completionItemKind = 25;
  top.jsonValue = jsonInteger(25);
}

function parseCompletionItemKind
Either<ResponseError CompletionItemKind> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeCompletionItemKind(intVal)
  | _ -> left(lspInvalidParams("CompletionItemKind not of type Integer"))
  end;
}

function makeCompletionItemKind
Either<ResponseError CompletionItemKind> ::= val::Integer
{
  return
  if val == 1 then right(completionItemKindText())
  else if val == 2 then right(completionItemKindMethod())
  else if val == 3 then right(completionItemKindFunction())
  else if val == 4 then right(completionItemKindConstructor())
  else if val == 5 then right(completionItemKindField())
  else if val == 6 then right(completionItemKindVariable())
  else if val == 7 then right(completionItemKindClass())
  else if val == 8 then right(completionItemKindInterface())
  else if val == 9 then right(completionItemKindModule())
  else if val == 10 then right(completionItemKindProperty())
  else if val == 11 then right(completionItemKindUnit())
  else if val == 12 then right(completionItemKindValue())
  else if val == 13 then right(completionItemKindEnum())
  else if val == 14 then right(completionItemKindKeyword())
  else if val == 15 then right(completionItemKindSnippet())
  else if val == 16 then right(completionItemKindColor())
  else if val == 17 then right(completionItemKindFile())
  else if val == 18 then right(completionItemKindReference())
  else if val == 19 then right(completionItemKindFolder())
  else if val == 20 then right(completionItemKindEnumMember())
  else if val == 21 then right(completionItemKindConstant())
  else if val == 22 then right(completionItemKindStruct())
  else if val == 23 then right(completionItemKindEvent())
  else if val == 24 then right(completionItemKindOperator())
  else if val == 25 then right(completionItemKindTypeParameter())
  else left(lspInvalidParams("Invalid CompletionItemKind value"));
}
