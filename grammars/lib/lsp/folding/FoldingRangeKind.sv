nonterminal FoldingRangeKind with jsonValue, foldingKind;

synthesized attribute foldingKind :: String;

abstract production foldingRangeKindComment
top::FoldingRangeKind ::=
{
  top.foldingKind = "comment";
  top.jsonValue = jsonString("comment");
}

abstract production foldingRangeKindImports
top::FoldingRangeKind ::=
{
  top.foldingKind = "imports";
  top.jsonValue = jsonString("imports");
}

abstract production foldingRangeKindRegion
top::FoldingRangeKind ::=
{
  top.foldingKind = "region";
  top.jsonValue = jsonString("region");
}

function parseFoldingRangeKind
Either<ResponseError FoldingRangeKind> ::= val::JSONValue
{
  return case val of
  | jsonString(str) -> makeFoldingRangeKind(str)
  | _ -> left(lspInvalidParams("FoldingRangeKind not of type String"))
  end;
}

function makeFoldingRangeKind
Either<ResponseError FoldingRangeKind> ::= val::String
{
  return
  if stringEq(val, "comment") then right(foldingRangeKindComment())
  else if stringEq(val, "imports") then right(foldingRangeKindImports())
  else if stringEq(val, "region") then right(foldingRangeKindRegion())
  else left(lspInvalidParams("Invalid FoldingRangeKind value"));
}
