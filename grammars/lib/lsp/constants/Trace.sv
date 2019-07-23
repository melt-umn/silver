nonterminal Trace with jsonValue, traceSetting;

synthesized attribute traceSetting :: String;

abstract production traceOff
top::Trace ::=
{
  top.traceSetting = "off";
  top.jsonValue = jsonString("off");
}

abstract production traceMessage
top::Trace ::=
{
  top.traceSetting = "message";
  top.jsonValue = jsonString("message");
}

abstract production traceVerbose
top::Trace ::=
{
  top.traceSetting = "verbose";
  top.jsonValue = jsonString("verbose");
}

function parseTrace
Either<ResponseError Trace> ::= val::JSONValue
{
  return case val of
  | jsonString(str) -> makeTrace(str)
  | _ -> left(lspInvalidParams("Trace not of type String"))
  end;
}

function makeTrace
Either<ResponseError Trace> ::= val::String
{
  return
  if stringEq(val, "off") then right(traceOff())
  else if stringEq(val, "message") then right(traceMessage())
  else if stringEq(val, "verbose") then right(traceVerbose())
  else left(lspInvalidParams("Invalid Trace value"));
}
