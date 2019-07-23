nonterminal MarkupKind with jsonValue, markupKind;

synthesized attribute markupKind :: String;

abstract production markupKindPlainText
top::MarkupKind ::=
{
  top.markupKind = "plaintext";
  top.jsonValue = jsonString("plaintext");
}

abstract production markupKindMarkdown
top::MarkupKind ::=
{
  top.markupKind = "markdown";
  top.jsonValue = jsonString("markdown");
}

function parseMarkupKind
Either<ResponseError MarkupKind> ::= val::JSONValue
{
  return case val of
  | jsonString(str) -> makeMarkupKind(str)
  | _ -> left(lspInvalidParams("MarkupKind not of type String"))
  end;
}

function makeMarkupKind
Either<ResponseError MarkupKind> ::= val::String
{
  return
  if stringEq(val, "plaintext") then right(markupKindPlainText())
  else if stringEq(val, "markdown") then right(markupKindMarkdown())
  else left(lspInvalidParams("Invalid MarkupKind value"));
}
