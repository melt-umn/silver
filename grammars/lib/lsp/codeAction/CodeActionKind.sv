nonterminal CodeActionKind with jsonValue, codeActionType;

synthesized attribute codeActionType :: String;

abstract production codeActionKindQuickFix
top::CodeActionKind ::=
{
  top.codeActionType = "quickfix";
  top.jsonValue = jsonString("quickfix");
}

abstract production codeActionKindRefactor
top::CodeActionKind ::=
{
  top.codeActionType = "refactor";
  top.jsonValue = jsonString("refactor");
}

abstract production codeActionKindRefactorExtract
top::CodeActionKind ::=
{
  top.codeActionType = "refactor.extract";
  top.jsonValue = jsonString("refactor.extract");
}

abstract production codeActionKindRefactorInline
top::CodeActionKind ::=
{
  top.codeActionType = "refactor.inline";
  top.jsonValue = jsonString("refactor.inline");
}

abstract production codeActionKindRefactorRewrite
top::CodeActionKind ::=
{
  top.codeActionType = "refactor.rewrite";
  top.jsonValue = jsonString("refactor.rewrite");
}

abstract production codeActionKindSource
top::CodeActionKind ::=
{
  top.codeActionType = "source";
  top.jsonValue = jsonString("source");
}

abstract production codeActionKindSourceOrganizeImports
top::CodeActionKind ::=
{
  top.codeActionType = "source.organizeImports";
  top.jsonValue = jsonString("source.organizeImports");
}

function parseCodeActionKind
Either<ResponseError CodeActionKind> ::= val::JSONValue
{
  return case val of
  | jsonString(str) -> makeCodeActionKind(str)
  | _ -> left(lspInvalidParams("CodeActionKind not of type String"))
  end;
}

function makeCodeActionKind
Either<ResponseError CodeActionKind> ::= val::String
{
  return
  if stringEq(val, "quickfix") then right(codeActionKindQuickFix())
  else if stringEq(val, "refactor") then right(codeActionKindRefactor())
  else if stringEq(val, "refactor.extract") then right(codeActionKindRefactorExtract())
  else if stringEq(val, "refactor.inline") then right(codeActionKindRefactorInline())
  else if stringEq(val, "refactor.rewrite") then right(codeActionKindRefactorRewrite())
  else if stringEq(val, "source") then right(codeActionKindSource())
  else if stringEq(val, "source.organizeImports") then right(codeActionKindSourceOrganizeImports())
  else left(lspInvalidParams("Invalid CodeActionKind value"));
}
