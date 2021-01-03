grammar silver:compiler:extension:silverconstruction;

imports silver:reflect;
imports silver:compiler:metatranslation;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  directAntiquoteProductions <-
    ["silver:compiler:extension:silverconstruction:antiquoteExpr",
     "silver:compiler:extension:silverconstruction:antiquoteExprInhs",
     "silver:compiler:extension:silverconstruction:antiquoteTypeExpr",
     "silver:compiler:extension:silverconstruction:antiquotePattern",
     "silver:compiler:extension:silverconstruction:antiquoteQName",
     "silver:compiler:extension:silverconstruction:antiquoteQNameAttrOccur",
     "silver:compiler:extension:silverconstruction:antiquoteName"];
  
  -- "Indirect" antiquote productions
  antiquoteTranslation <-
    case prodName, children, annotations of
    | "silver:compiler:extension:silverconstruction:antiquote_qName",
      consAST(_, consAST(_, consAST(a, consAST(_, nilAST())))),
      consNamedAST(namedAST("core:location", locAST), nilNamedAST()) ->
        case reify(a) of
        | right(e) ->
          just(
            mkFullFunctionInvocation(
              givenLocation,
              baseExpr(qName(givenLocation, "silver:compiler:metatranslation:makeQName"), location=givenLocation),
              [e, locAST.translation],
              []))
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
    | "silver:compiler:extension:silverconstruction:antiquote_qName", _, _ ->
        error(s"Unexpected antiquote production arguments: ${show(80, top.pp)}")
    | "silver:compiler:extension:silverconstruction:antiquote_name",
      consAST(_, consAST(_, consAST(a, consAST(_, nilAST())))),
      consNamedAST(namedAST("core:location", locAST), nilNamedAST()) ->
        case reify(a) of
        | right(e) ->
          just(
            mkFullFunctionInvocation(
              givenLocation,
              baseExpr(qName(givenLocation, "silver:compiler:metatranslation:makeName"), location=givenLocation),
              [e, locAST.translation],
              []))
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
    | "silver:compiler:extension:silverconstruction:antiquote_name", _, _ ->
        error(s"Unexpected antiquote production arguments: ${show(80, top.pp)}")
    | _, _, _ -> nothing()
    end;
}
