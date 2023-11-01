grammar silver:langutil:unparse;

imports silver:reflect:util;
imports silver:langutil;

function unparse
String ::= origText::String  x::a
{
  local ast::AST = reflect(x);
  ast.origText = origText;
  ast.parseTree = just(getParseTree(x));
  local astLoc::Location = ast.matchingOriginLoc.fromJust;
  return
    substring(0, astLoc.index, origText) ++
    ast.unparseWithLayout ++
    substring(astLoc.endIndex, length(origText), origText);
}

function getParseTree
AST ::= ast::a
{
  return
    case getOriginInfo(ast) of
    | just(parsedOriginInfo(l)) -> reflect(ast)
    | just(originOriginInfo(o, _)) -> getParseTree(o)
    | just(originAndRedexOriginInfo(o, _, _, _)) -> getParseTree(o)
    | _ -> error("Tree does not have a parsed origin: " ++ showOriginInfoChain(ast))
    end;
}

synthesized attribute unparseWithLayout::String occurs on AST, ASTs;
synthesized attribute matchingOriginLoc::Maybe<Location> occurs on AST;

inherited attribute origText::String occurs on AST, ASTs;
propagate origText on AST, ASTs;

inherited attribute parseTree<a>::Maybe<a>;
attribute parseTree<AST> occurs on AST;
attribute parseTree<ASTs> occurs on ASTs;

aspect default production
top::AST ::=
{
  top.unparseWithLayout = error("Can't unparse " ++ genericShow(top));
  top.matchingOriginLoc = nothing();
}

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  top.unparseWithLayout = children.unparseWithLayout;
  top.matchingOriginLoc = bind(top.parseTree, getParsedOriginLocation);
  children.parseTree =
    case fromMaybe(getParseTree(top), top.parseTree) of
    | nonterminalAST(p, c, _) when prodName == p -> just(c)
    | _ -> nothing()
    end;
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  top.unparseWithLayout = lexeme;
  top.matchingOriginLoc = just(location);
}

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.unparseWithLayout =
    h.unparseWithLayout ++
    case t of
    | consAST(h2, _) ->
      case h.matchingOriginLoc, h2.matchingOriginLoc of
      | just(l1), just(l2) -> substring(l1.endIndex, l2.index, top.origText)
      | _, _ -> ""
      end
    | nilAST() -> ""
    end ++
    t.unparseWithLayout;
  h.parseTree =
    case top.parseTree of
    | just(consAST(a, _)) -> just(a)
    | _ -> nothing()
    end;
  t.parseTree =
    case top.parseTree of
    | just(consAST(_, a)) -> just(a)
    | _ -> nothing()
    end;
}

aspect production nilAST
top::ASTs ::=
{
  top.unparseWithLayout = "";
}
