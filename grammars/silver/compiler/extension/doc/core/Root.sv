grammar silver:compiler:extension:doc:core;

import silver:compiler:driver;

synthesized attribute genFiles :: [Pair<String String>] with ++;

-- Used for getting doc comments on AGDcls
synthesized attribute docs :: [CommentItem] with ++;
attribute docs occurs on Grammar, Root, AGDcls, AGDcl;

-- Set to "true" if each file in the grammar should have its own markdown file
synthesized attribute docsSplit :: String;
attribute docsSplit occurs on Grammar, Root, AGDcls, AGDcl;

-- Set to "true" if no documentation should be generated for this grammar
synthesized attribute docsNoDoc :: Boolean;
attribute docsNoDoc occurs on Grammar, Root, AGDcls, AGDcl;

-- Declarations of documented AGDcls
synthesized attribute docDcls :: [Pair<String DocDclInfo>] with ++;
attribute docDcls occurs on Grammar, Root, AGDcls, AGDcl;

-- Environment of all documented AGDcls
autocopy attribute docEnv :: Map<String DocDclInfo>;
attribute docEnv occurs on Grammar, Root, AGDcls, AGDcl;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.docs := ags.docs;
  top.docsSplit = ags.docsSplit;
  top.docsNoDoc = false;
  top.docDcls := ags.docDcls;
}

aspect production nilAGDcls
top::AGDcls ::=
{
  top.docs := [];
  top.docsSplit = "";
  top.docsNoDoc = false;
  top.docDcls := [];
}

aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.docs := h.docs ++ t.docs;
  top.docsSplit = if "" == h.docsSplit
                  then t.docsSplit
                  else h.docsSplit;
  top.docsNoDoc = h.docsNoDoc || t.docsNoDoc;
  top.docDcls := h.docDcls ++ t.docDcls;
}

aspect default production
top::AGDcl ::=
{
  top.docs := [];
  top.docsSplit = "";
  top.docsNoDoc = false;
  top.docDcls := [];
}

aspect production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.docs := h.docs ++ t.docs;

  top.docsSplit = if "" == h.docsSplit
                  then t.docsSplit
                  else h.docsSplit;

  top.docsNoDoc = h.docsNoDoc || t.docsNoDoc;
  top.docDcls := h.docDcls ++ t.docDcls;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.docs := [];
  top.docsSplit = "";
  top.docsNoDoc = false;
  top.docDcls := [];
}

aspect production consGrammar
top::Grammar ::= c1::Root  c2::Grammar
{
  top.docs := c1.docs ++ c2.docs;

  top.docsSplit = if "" == c1.docsSplit
                  then c2.docsSplit
                  else c1.docsSplit;

  top.docsNoDoc = c1.docsNoDoc || c2.docsNoDoc;
  top.docDcls := c1.docDcls ++ c2.docDcls;
}

