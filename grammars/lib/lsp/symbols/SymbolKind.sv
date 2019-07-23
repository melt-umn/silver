nonterminal SymbolKind with jsonValue, symbolKind;

synthesized attribute symbolKind :: Integer;

abstract production symbolKindFile
top::SymbolKind ::=
{
  top.symbolKind = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production symbolKindModule
top::SymbolKind ::=
{
  top.symbolKind = 2;
  top.jsonValue = jsonInteger(2);
}

abstract production symbolKindNamespace
top::SymbolKind ::=
{
  top.symbolKind = 3;
  top.jsonValue = jsonInteger(3);
}

abstract production symbolKindPackage
top::SymbolKind ::=
{
  top.symbolKind = 4;
  top.jsonValue = jsonInteger(4);
}

abstract production symbolKindClass
top::SymbolKind ::=
{
  top.symbolKind = 5;
  top.jsonValue = jsonInteger(5);
}

abstract production symbolKindMethod
top::SymbolKind ::=
{
  top.symbolKind = 6;
  top.jsonValue = jsonInteger(6);
}

abstract production symbolKindProperty
top::SymbolKind ::=
{
  top.symbolKind = 7;
  top.jsonValue = jsonInteger(7);
}

abstract production symbolKindField
top::SymbolKind ::=
{
  top.symbolKind = 8;
  top.jsonValue = jsonInteger(8);
}

abstract production symbolKindConstructor
top::SymbolKind ::=
{
  top.symbolKind = 9;
  top.jsonValue = jsonInteger(9);
}

abstract production symbolKindEnum
top::SymbolKind ::=
{
  top.symbolKind = 10;
  top.jsonValue = jsonInteger(10);
}

abstract production symbolKindInterface
top::SymbolKind ::=
{
  top.symbolKind = 11;
  top.jsonValue = jsonInteger(11);
}

abstract production symbolKindFunction
top::SymbolKind ::=
{
  top.symbolKind = 12;
  top.jsonValue = jsonInteger(12);
}

abstract production symbolKindVariable
top::SymbolKind ::=
{
  top.symbolKind = 13;
  top.jsonValue = jsonInteger(13);
}

abstract production symbolKindConstant
top::SymbolKind ::=
{
  top.symbolKind = 14;
  top.jsonValue = jsonInteger(14);
}

abstract production symbolKindString
top::SymbolKind ::=
{
  top.symbolKind = 15;
  top.jsonValue = jsonInteger(15);
}

abstract production symbolKindNumber
top::SymbolKind ::=
{
  top.symbolKind = 16;
  top.jsonValue = jsonInteger(16);
}

abstract production symbolKindBoolean
top::SymbolKind ::=
{
  top.symbolKind = 17;
  top.jsonValue = jsonInteger(17);
}

abstract production symbolKindArray
top::SymbolKind ::=
{
  top.symbolKind = 18;
  top.jsonValue = jsonInteger(18);
}

abstract production symbolKindObject
top::SymbolKind ::=
{
  top.symbolKind = 19;
  top.jsonValue = jsonInteger(19);
}

abstract production symbolKindKey
top::SymbolKind ::=
{
  top.symbolKind = 20;
  top.jsonValue = jsonInteger(20);
}

abstract production symbolKindNull
top::SymbolKind ::=
{
  top.symbolKind = 21;
  top.jsonValue = jsonInteger(21);
}

abstract production symbolKindEnumMember
top::SymbolKind ::=
{
  top.symbolKind = 22;
  top.jsonValue = jsonInteger(22);
}

abstract production symbolKindStruct
top::SymbolKind ::=
{
  top.symbolKind = 23;
  top.jsonValue = jsonInteger(23);
}

abstract production symbolKindEvent
top::SymbolKind ::=
{
  top.symbolKind = 24;
  top.jsonValue = jsonInteger(24);
}

abstract production symbolKindOperator
top::SymbolKind ::=
{
  top.symbolKind = 25;
  top.jsonValue = jsonInteger(25);
}

abstract production symbolKindTypeParameter
top::SymbolKind ::=
{
  top.symbolKind = 26;
  top.jsonValue = jsonInteger(26);
}

function parseSymbolKind
Either<ResponseError SymbolKind> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeSymbolKind(intVal)
  | _ -> left(lspInvalidParams("SymbolKind not of type Integer"))
  end;
}

function makeSymbolKind
Either<ResponseError SymbolKind> ::= val::Integer
{
  return
  if val == 1 then right(symbolKindFile())
  else if val == 2 then right(symbolKindModule())
  else if val == 3 then right(symbolKindNamespace())
  else if val == 4 then right(symbolKindPackage())
  else if val == 5 then right(symbolKindClass())
  else if val == 6 then right(symbolKindMethod())
  else if val == 7 then right(symbolKindProperty())
  else if val == 8 then right(symbolKindField())
  else if val == 9 then right(symbolKindConstructor())
  else if val == 10 then right(symbolKindEnum())
  else if val == 11 then right(symbolKindInterface())
  else if val == 12 then right(symbolKindFunction())
  else if val == 13 then right(symbolKindVariable())
  else if val == 14 then right(symbolKindConstant())
  else if val == 15 then right(symbolKindString())
  else if val == 16 then right(symbolKindNumber())
  else if val == 17 then right(symbolKindBoolean())
  else if val == 18 then right(symbolKindArray())
  else if val == 19 then right(symbolKindObject())
  else if val == 20 then right(symbolKindKey())
  else if val == 21 then right(symbolKindNull())
  else if val == 22 then right(symbolKindEnumMember())
  else if val == 23 then right(symbolKindStruct())
  else if val == 24 then right(symbolKindEvent())
  else if val == 25 then right(symbolKindOperator())
  else if val == 26 then right(symbolKindTypeParameter())
  else left(lspInvalidParams("Invalid SymbolKind value"));
}
