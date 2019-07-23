nonterminal FailureHandlingKind with jsonValue, failureHandling;

synthesized attribute failureHandling :: String;

abstract production failureHandlingKindAbort
top::FailureHandlingKind ::=
{
  top.failureHandling = "abort";
  top.jsonValue = jsonString("abort");
}

abstract production failureHandlingKindTransactional
top::FailureHandlingKind ::=
{
  top.failureHandling = "transactional";
  top.jsonValue = jsonString("transactional");
}

abstract production failureHandlingKindTextOnlyTransactional
top::FailureHandlingKind ::=
{
  top.failureHandling = "textOnlyTransactional";
  top.jsonValue = jsonString("textOnlyTransactional");
}

abstract production failureHandlingKindUndo
top::FailureHandlingKind ::=
{
  top.failureHandling = "undo";
  top.jsonValue = jsonString("undo");
}

function parseFailureHandlingKind
Either<ResponseError FailureHandlingKind> ::= val::JSONValue
{
  return case val of
  | jsonString(str) -> makeFailureHandlingKind(str)
  | _ -> left(lspInvalidParams("FailureHandlingKind not of type String"))
  end;
}

function makeFailureHandlingKind
Either<ResponseError FailureHandlingKind> ::= val::String
{
  return
  if stringEq(val, "abort") then right(failureHandlingKindAbort())
  else if stringEq(val, "transactional") then right(failureHandlingKindTransactional())
  else if stringEq(val, "textOnlyTransactional") then right(failureHandlingKindTextOnlyTransactional())
  else if stringEq(val, "undo") then right(failureHandlingKindUndo())
  else left(lspInvalidParams("Invalid FailureHandlingKind value"));
}
