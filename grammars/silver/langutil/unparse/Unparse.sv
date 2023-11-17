grammar silver:langutil:unparse;

imports silver:reflect:util;
imports silver:langutil;
imports silver:langutil:pp;
imports silver:util:treemap as map;

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
Document ::= origText::String  tree::a
{
  local parseTree::AST = getParseTree(tree);
  parseTree.origText = origText;
  parseTree.lineIndent = map:fromList(enumerate(map(countIndent, explode("\n", origText))));

  local ast::AST = reflect(tree);
  ast.origText = origText;
  ast.lineIndent = parseTree.lineIndent;
  ast.parseTree = just(parseTree);

  local preLayout::String = substring(0, parseTree.originLoc.index, origText);
  local postLayout::String = substring(parseTree.originLoc.endIndex, length(origText), origText);

  return
    text(preLayout) ++
    maybeNest(parseTree.indent,
      ast.unparseWithLayout ++
      layoutPP(parseTree.indent, postLayout));
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

-- Attributes computed on parseTree
inherited attribute origText::String occurs on AST, ASTs;
inherited attribute lineIndent::map:Map<Integer Integer> occurs on AST, ASTs;
inherited attribute initialIndent::Integer occurs on ASTs;
propagate origText, lineIndent on AST, ASTs;

synthesized attribute originLoc::Location occurs on AST;
synthesized attribute indent::Integer occurs on AST;
flowtype indent {lineIndent} on AST;
monoid attribute startColumns::[Integer] occurs on ASTs;
propagate startColumns on ASTs;
synthesized attribute origNest::Integer occurs on ASTs;
synthesized attribute origLayoutPP::Document occurs on ASTs;

-- Attributes computed on the unparse AST
inherited attribute parseTree<a>::Maybe<a>;  -- This is like a destruct attribute except it's a Maybe
attribute parseTree<Decorated AST with {origText, lineIndent}> occurs on AST;
attribute parseTree<Decorated ASTs with {origText, lineIndent, initialIndent}> occurs on ASTs;

synthesized attribute unparseWithLayout::Document occurs on AST, ASTs;
synthesized attribute defaultPreLayout::Maybe<Document> occurs on AST, ASTs;
synthesized attribute defaultPostLayout::Maybe<Document> occurs on AST, ASTs;
inherited attribute childIndex::Integer occurs on ASTs;
inherited attribute childLayout::[(Integer, Document)] occurs on ASTs;
inherited attribute childIndent::[(Integer, Integer)] occurs on ASTs;
propagate childLayout, childIndent on ASTs;

aspect default production
top::AST ::=
{
  top.originLoc = error(genericShow(top) ++ " cannot appear in a parse tree");
  top.indent =
    case map:lookup(top.originLoc.line - 1, top.lineIndent) of
    | i :: _ -> i
    | [] -> error(s"Line ${toString(top.originLoc.line)} out of bounds for supplied text!")
    end;
  top.unparseWithLayout = error("Can't unparse " ++ genericShow(top));
  top.defaultPreLayout = nothing();
  top.defaultPostLayout = nothing();
}

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  -- On parseTree
  local isBox::Boolean = all(map(gte(_, top.originLoc.column), children.startColumns));
  top.unparseWithLayout =
    if isBox
    then box(group(children.unparseWithLayout))
    else group(children.unparseWithLayout);
  top.originLoc =
    case getParsedOriginLocation(top) of
    | just(l) -> l
    | nothing() -> error("Tree does not have a parsed origin: " ++ showOriginInfoChain(top))
    end;
  children.initialIndent = if isBox then top.originLoc.column else top.indent;

  -- On unparse AST
  local parseTree::AST = getParseTree(top);
  parseTree.origText = top.origText;
  parseTree.lineIndent = top.lineIndent;
  children.parseTree =
    case fromMaybe(parseTree, top.parseTree) of
    | nonterminalAST(p, c, _) when prodName == p -> just(c)
    | _ -> nothing()
    end;
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
  top.originLoc = location;

  top.unparseWithLayout = text(lexeme);

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
  -- On parseTree
  top.startColumns <- [h.originLoc.column];
  top.origLayoutPP =
    case t of
    | consAST(h2, _) ->
        layoutPP(h2.indent,
          substring(h.originLoc.endIndex, h2.originLoc.index, top.origText))
    | nilAST() -> pp""
    end;
  top.origNest =
    case t of
    | consAST(h2, _) when h2.originLoc.line > h.originLoc.line ->
      h2.indent - top.initialIndent
    | _ -> 0
    end;
  t.initialIndent =
    case t of
    | consAST(h2, _) when h2.originLoc.line > h.originLoc.line -> h2.indent
    | _ -> top.initialIndent
    end;

  -- On unparse AST
  top.unparseWithLayout =
    h.unparseWithLayout ++
    maybeNest(
      fromMaybe(0,
        alt(map((.origNest), top.parseTree),
          lookup(t.childIndex, top.childIndent))),
      fromMaybe(pp"",
        alt(map((.origLayoutPP), top.parseTree),
          alt(lookup(top.childIndex, top.childLayout),
            alt(case t of consAST(_, _) -> h.defaultPostLayout | _ -> empty end,
              t.defaultPreLayout)))) ++
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
    case t of nilAST() -> h.defaultPostLayout | _ -> empty end);
  t.childIndex = top.childIndex + 1;
}

aspect production nilAST
top::ASTs ::=
{
  top.origLayoutPP = pp"";
  top.origNest = 0;

  top.unparseWithLayout = pp"";
  top.defaultPreLayout = nothing();
  top.defaultPostLayout = nothing();
}

-- Count the number of spaces at the start of a line
fun countIndent  Integer ::= s::String = length(takeWhile(eq(" ", _), explode("", s)));

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
