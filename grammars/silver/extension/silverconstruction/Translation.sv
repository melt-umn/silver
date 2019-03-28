grammar silver:extension:silverconstruction;

imports silver:reflect;
imports silver:langutil:hostEmbedding;

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
            applicationExpr(
              baseExpr(
                makeQName("silver:extension:silverconstruction:makeQName", givenLocation),
                location=givenLocation),
              '(',
              foldAppExprs(givenLocation, [e, locAST.translation]),
              ')', location=givenLocation))
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
    | "silver:extension:silverconstruction:escape_qName", _, _ ->
        error(s"Unexpected escape production arguments: ${show(80, top.pp)}")
    | "silver:extension:silverconstruction:escape_name",
      consAST(a, nilAST()), consNamedAST(namedAST("core:location", locAST), nilNamedAST()) ->
        case reify(a) of
        | right(e) ->
          just(
            applicationExpr(
              baseExpr(
                makeQName("silver:extension:silverconstruction:makeName", givenLocation),
                location=givenLocation),
              '(',
              foldAppExprs(givenLocation, [e, locAST.translation]),
              ')', location=givenLocation))
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
    | "silver:extension:silverconstruction:escape_name", _, _ ->
        error(s"Unexpected escape production arguments: ${show(80, top.pp)}")
    | _, _, _ -> nothing()
    end;
}
