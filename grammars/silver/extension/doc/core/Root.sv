grammar silver:extension:doc:core;

synthesized attribute genFiles :: [Pair<String String>] with ++;

synthesized attribute docs :: [CommentItem] with ++;
attribute docs occurs on Grammar, Root, AGDcls, AGDcl;

synthesized attribute docsHeader :: String;
attribute docsHeader occurs on Grammar, Root, AGDcls, AGDcl;

synthesized attribute docsSplit :: String;
attribute docsSplit occurs on Grammar, Root, AGDcls, AGDcl;

synthesized attribute docsNoDoc :: Boolean;
attribute docsNoDoc occurs on Grammar, Root, AGDcls, AGDcl;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.docs := ags.docs;
  top.docsHeader = ags.docsHeader;
  top.docsSplit = ags.docsSplit;
  top.docsNoDoc = false;
}

aspect production nilAGDcls
top::AGDcls ::=
{
  top.docs := [];
  top.docsHeader = "";
  top.docsSplit = "";
  top.docsNoDoc = false;
}

aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.docs := h.docs ++ t.docs;
  top.docsHeader = if "" == h.docsHeader
				   then t.docsHeader
				   else h.docsHeader;
  top.docsSplit = if "" == h.docsSplit
				  then t.docsSplit
				  else h.docsSplit;
  top.docsNoDoc = h.docsNoDoc || t.docsNoDoc;
}

aspect default production
top::AGDcl ::=
{
  top.docs := [];
  top.docsHeader = "";
  top.docsSplit = "";
  top.docsNoDoc = false;
}

aspect production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.docs := h.docs ++ t.docs;
  top.docsHeader = if "" == h.docsHeader
				   then t.docsHeader
				   else h.docsHeader;
  top.docsSplit = if "" == h.docsSplit
				  then t.docsSplit
				  else h.docsSplit;
  top.docsNoDoc = h.docsNoDoc || t.docsNoDoc;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.docs := [];
  top.docsHeader = "";
  top.docsSplit = "";
  top.docsNoDoc = false;
}

aspect production consGrammar
top::Grammar ::= c1::Root  c2::Grammar
{
  top.docs := c1.docs ++ c2.docs; -- TODO, define ordering
  top.docsHeader = if "" == c1.docsHeader
				   then c2.docsHeader
				   else c1.docsHeader;
  top.docsSplit = if "" == c1.docsSplit
				  then c2.docsSplit
				  else c1.docsSplit;
  top.docsNoDoc = c1.docsNoDoc || c2.docsNoDoc;
}

