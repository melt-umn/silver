grammar silver:langutil:unparse;

imports silver:reflect:util;
imports silver:langutil;

@{--
 - Unparse a tree, preserving layout from its parse tree via origin tracking.
 - The productions in the tree should only consist of nonterminal and terminal symbols;
 - in a language with seperate concrete and abstract syntax, this may require defining a
 - translation from abstract back to concrete syntax.
 -
 - This is intended for use in e.g. refactoring tools, where transformations can be applied
 - on the tree, but one would like to turn the tree back into a string without affecting
 - layout in otherwise-unchanged portions of the tree.
 - 
 - @param origText  The original text that was parsed to create the origin of tree.
 - @param tree  The concrete syntax tree to unparse.
 - @return The unparse of the tree, with layout from origText inserted in unchanged portions of the tree.
 -}
function unparse
String ::= origText::String  tree::a
{
  local ast::AST = reflect(tree);
  ast.origText = origText;
  ast.parseTree = just(getParseTree(tree));
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
synthesized attribute defaultPreLayout::Maybe<String> occurs on AST, ASTs;
synthesized attribute defaultPostLayout::Maybe<String> occurs on AST, ASTs;
inherited attribute childIndex::Integer occurs on ASTs;
inherited attribute childLayout::[(Integer, String)] occurs on ASTs;

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
  top.defaultPreLayout = nothing();
  top.defaultPostLayout = nothing();
}

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  top.unparseWithLayout = children.unparseWithLayout;
  top.matchingOriginLoc = do {
    tree :: AST <- top.parseTree;
    return
      case getParsedOriginLocation(tree) of
      | just(l) -> l
      | nothing() ->
        error("Tree does not have a parsed origin: " ++ showOriginInfoChain(tree))
      end;
  };
  children.parseTree =
    case fromMaybe(getParseTree(top), top.parseTree) of
    | nonterminalAST(p, c, _) when prodName == p -> just(c)
    | _ -> nothing()
    end;
  top.defaultPreLayout = children.defaultPreLayout;
  top.defaultPostLayout = children.defaultPostLayout;

  -- Map of productions and child indices to default layout after the child
  production attribute prodChildLayout::[(String, Integer, String)] with ++;
  prodChildLayout := [];

  children.childIndex = 0;
  children.childLayout = lookupAll(prodName, prodChildLayout);
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  top.unparseWithLayout = lexeme;
  top.matchingOriginLoc = just(location);

  -- Map of terminal names to default layout after the terminal
  production attribute termPreLayout::[(String, String)] with ++;
  termPreLayout := [];
  -- Map of terminal names to default layout before the terminal
  production attribute termPostLayout::[(String, String)] with ++;
  termPostLayout := [];

  top.defaultPreLayout = lookup(terminalName, termPreLayout);
  top.defaultPostLayout = lookup(terminalName, termPostLayout);
}

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  local layoutStr::String = fromMaybe("",
    case t of
    | consAST(h2, _) -> alt(
        do {
          l1::Location <- h.matchingOriginLoc;
          l2::Location <- h2.matchingOriginLoc;
          return substring(l1.endIndex, l2.index, top.origText);
        },
        alt(lookup(top.childIndex, top.childLayout),
          alt(h.defaultPostLayout, t.defaultPreLayout)))
    | nilAST() -> empty
    end);
  top.unparseWithLayout =
    h.unparseWithLayout ++
    layoutStr ++
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
  top.defaultPreLayout = h.defaultPreLayout;
  top.defaultPostLayout = alt(
    t.defaultPostLayout,
    if t.unparseWithLayout == "" then h.defaultPostLayout else empty);
  t.childIndex = top.childIndex + 1;
  t.childLayout = top.childLayout;
}

aspect production nilAST
top::ASTs ::=
{
  top.unparseWithLayout = "";
  top.defaultPreLayout = nothing();
  top.defaultPostLayout = nothing();
}
