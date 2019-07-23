nonterminal DiagnosticSeverity with jsonValue, diagnosticSeverity;

synthesized attribute diagnosticSeverity :: Integer;

abstract production diagnosticSeverityError
top::DiagnosticSeverity ::=
{
  top.diagnosticSeverity = 1;
  top.jsonValue = jsonInteger(1);
}

abstract production diagnosticSeverityWarning
top::DiagnosticSeverity ::=
{
  top.diagnosticSeverity = 2;
  top.jsonValue = jsonInteger(2);
}

abstract production diagnosticSeverityInformation
top::DiagnosticSeverity ::=
{
  top.diagnosticSeverity = 3;
  top.jsonValue = jsonInteger(3);
}

abstract production diagnosticSeverityHint
top::DiagnosticSeverity ::=
{
  top.diagnosticSeverity = 4;
  top.jsonValue = jsonInteger(4);
}

function parseDiagnosticSeverity
Either<ResponseError DiagnosticSeverity> ::= val::JSONValue
{
  return case val of
  | jsonInteger(intVal) -> makeDiagnosticSeverity(intVal)
  | _ -> left(lspInvalidParams("DiagnosticSeverity not of type Integer"))
  end;
}

function makeDiagnosticSeverity
Either<ResponseError DiagnosticSeverity> ::= val::Integer
{
  return
  if val == 1 then right(diagnosticSeverityError())
  else if val == 2 then right(diagnosticSeverityWarning())
  else if val == 3 then right(diagnosticSeverityInformation())
  else if val == 4 then right(diagnosticSeverityHint())
  else left(lspInvalidParams("Invalid DiagnosticSeverity value"));
}
