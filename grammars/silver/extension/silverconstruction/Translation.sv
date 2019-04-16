grammar silver:extension:silverconstruction;

imports silver:reflect;
imports silver:hostEmbedding;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  directEscapeProductions <-
    ["silver:extension:silverconstruction:escapeExpr",
     "silver:extension:silverconstruction:escapeTypeExpr",
     "silver:extension:silverconstruction:escapeQName",
     "silver:extension:silverconstruction:escapeName"];
  
  -- "Indirect" escape productions
  escapeTranslation <-
    case prodName, children, annotations of
    | "silver:extension:silverconstruction:escape_qName",
      consAST(a, nilAST()), consNamedAST(namedAST("core:location", locAST), nilNamedAST()) ->
        case reify(a) of
        | right(e) ->
          just(
            mkFullFunctionInvocation(
              givenLocation,
              baseExpr(qName(givenLocation, "silver:hostEmbedding:makeQName"), location=givenLocation),
              [e, locAST.translation],
              []))
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
    | "silver:extension:silverconstruction:escape_qName", _, _ ->
        error(s"Unexpected escape production arguments: ${show(80, top.pp)}")
    | "silver:extension:silverconstruction:escape_name",
      consAST(a, nilAST()), consNamedAST(namedAST("core:location", locAST), nilNamedAST()) ->
        case reify(a) of
        | right(e) ->
          just(
            mkFullFunctionInvocation(
              givenLocation,
              baseExpr(qName(givenLocation, "silver:hostEmbedding:makeName"), location=givenLocation),
              [e, locAST.translation],
              []))
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
    | "silver:extension:silverconstruction:escape_name", _, _ ->
        error(s"Unexpected escape production arguments: ${show(80, top.pp)}")
    | _, _, _ -> nothing()
    end;
}
