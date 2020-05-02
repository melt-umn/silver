grammar silver:extension:silverconstruction;

imports silver:reflect;
imports silver:metatranslation;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  directAntiquoteProductions <-
    ["silver:extension:silverconstruction:antiquoteExpr",
     "silver:extension:silverconstruction:antiquoteExprInhs",
     "silver:extension:silverconstruction:antiquoteTypeExpr",
     "silver:extension:silverconstruction:antiquoteQName",
     "silver:extension:silverconstruction:antiquoteQNameAttrOccur",
     "silver:extension:silverconstruction:antiquoteName"];
  
  -- "Indirect" antiquote productions
  antiquoteTranslation <-
    case prodName, children, annotations of
    | "silver:extension:silverconstruction:antiquote_qName",
      consAST(_, consAST(_, consAST(a, consAST(_, nilAST())))),
      consNamedAST(namedAST("core:location", locAST), nilNamedAST()) ->
        case reify(a) of
        | right(e) ->
          just(
            mkFullFunctionInvocation(
              givenLocation,
              baseExpr(qName(givenLocation, "silver:metatranslation:makeQName"), location=givenLocation),
              [e, locAST.translation],
              []))
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
    | "silver:extension:silverconstruction:antiquote_qName", _, _ ->
        error(s"Unexpected antiquote production arguments: ${show(80, top.pp)}")
    | "silver:extension:silverconstruction:antiquote_name",
      consAST(_, consAST(_, consAST(a, consAST(_, nilAST())))),
      consNamedAST(namedAST("core:location", locAST), nilNamedAST()) ->
        case reify(a) of
        | right(e) ->
          just(
            mkFullFunctionInvocation(
              givenLocation,
              baseExpr(qName(givenLocation, "silver:metatranslation:makeName"), location=givenLocation),
              [e, locAST.translation],
              []))
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
    | "silver:extension:silverconstruction:antiquote_name", _, _ ->
        error(s"Unexpected antiquote production arguments: ${show(80, top.pp)}")
    | _, _, _ -> nothing()
    end;
}
