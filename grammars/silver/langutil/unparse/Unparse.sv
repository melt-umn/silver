grammar silver:langutil:unparse;

imports silver:reflect:util;
imports silver:langutil;
imports silver:langutil:pp;

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
String ::= width::Integer  origText::String  tree::a
{
  local ast::AST = reflect(tree);
  ast.origText = origText;
  ast.parseTree = just(getParseTree(tree));
  ast.indent = fromMaybe(countIndent(preLayout), layoutIndent(preLayout));
  local astLoc::Location = ast.matchingOriginLoc.fromJust;

  local preLayout::String = substring(0, astLoc.index, origText);
  local postLayout::String = substring(astLoc.endIndex, length(origText), origText);

  return show(width,
    text(preLayout) ++
    maybeNest(ast.indent,
      ast.unparseWithLayout ++
      layoutPP(ast.indent, postLayout)));
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

synthesized attribute unparseWithLayout::Document occurs on AST, ASTs;
synthesized attribute matchingOriginLoc::Maybe<Location> occurs on AST;
synthesized attribute defaultPreLayout::Maybe<Document> occurs on AST, ASTs;
synthesized attribute defaultPostLayout::Maybe<Document> occurs on AST, ASTs;
inherited attribute childIndex::Integer occurs on ASTs;
inherited attribute childLayout::[(Integer, Document)] occurs on ASTs;
inherited attribute childIndent::[(Integer, Integer)] occurs on ASTs;
propagate childLayout, childIndent on ASTs;

inherited attribute origText::String occurs on AST, ASTs;
propagate origText on AST, ASTs;

inherited attribute parseTree<a>::Maybe<a>;
attribute parseTree<AST> occurs on AST;
attribute parseTree<ASTs> occurs on ASTs;

inherited attribute indent::Integer occurs on AST, ASTs;

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
  children.indent = top.indent;
  top.defaultPreLayout = children.defaultPreLayout;
  top.defaultPostLayout = children.defaultPostLayout;

  children.childIndex = 0;

  -- Map of productions and child indices to default layout after the child
  production attribute prodChildLayout::[(String, Integer, Document)] with ++;
  prodChildLayout := [];
  children.childLayout = lookupAll(prodName, prodChildLayout);

  -- Map of productions and child indices to default indentation in the child
  production attribute prodChildIndent::[(String, Integer, Integer)] with ++;
  prodChildIndent := [];
  children.childIndent = lookupAll(prodName, prodChildIndent);
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  top.unparseWithLayout = text(lexeme);
  top.matchingOriginLoc = just(location);

  -- Map of terminal names to default layout after the terminal
  production attribute termPreLayout::[(String, Document)] with ++;
  termPreLayout := [];
  -- Map of terminal names to default layout before the terminal
  production attribute termPostLayout::[(String, Document)] with ++;
  termPostLayout := [];

  top.defaultPreLayout = lookup(terminalName, termPreLayout);
  top.defaultPostLayout = lookup(terminalName, termPostLayout);
}

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  local origLayout::Maybe<String> =
    case t of
    | consAST(h2, _) ->
        do {
          l1::Location <- h.matchingOriginLoc;
          l2::Location <- h2.matchingOriginLoc;
          return substring(l1.endIndex, l2.index, top.origText);
        }
    | nilAST() -> empty
    end;
  h.indent = top.indent;
  t.indent = fromMaybe(top.indent, bind(origLayout, layoutIndent));
  top.unparseWithLayout =
    h.unparseWithLayout ++
    maybeNest(
      if top.parseTree.isJust
      then t.indent - top.indent
      else fromMaybe(0, lookup(t.childIndex, top.childIndent)),
      fromMaybe(pp"",
        alt(
          map(layoutPP(t.indent, _), origLayout),
          alt(lookup(top.childIndex, top.childLayout),
            alt(h.defaultPostLayout, t.defaultPreLayout)))) ++
      t.unparseWithLayout);
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
    if t.unparseWithLayout == pp"" then h.defaultPostLayout else empty);
  t.childIndex = top.childIndex + 1;
}

aspect production nilAST
top::ASTs ::=
{
  top.unparseWithLayout = pp"";
  top.defaultPreLayout = nothing();
  top.defaultPostLayout = nothing();
}

-- Count the number of spaces at the start of a line
fun countIndent  Integer ::= s::String = length(takeWhile(eq(" ", _), explode("", s)));

fun layoutIndent  Maybe<Integer> ::= layoutStr::String =
  case explode("\n", layoutStr) of
  | [] -> nothing()
  | [_] -> nothing()
  | lines -> just(countIndent(last(lines)))
  end;

fun layoutPP  Document ::= indent::Integer layoutStr::String =
  concat(
    case explode("\n", layoutStr) of
    | [] -> []
    | pre :: lines -> text(pre) ::
        map(\ l::String ->
          maybeNest(
            countIndent(l) - indent,
            realLine() ++ text(concat(dropWhile(eq(" ", _), explode("", l))))),
          lines)
    end);

fun maybeNest  Document ::= n::Integer d::Document = if n == 0 then d else nest(n, d);
