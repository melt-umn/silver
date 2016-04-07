grammar silver:extension:doc:core;

synthesized attribute genFiles :: [Pair<String String>] with ++;

-- Used for getting doc comments on AGDcls
synthesized attribute docs :: [CommentItem] with ++;
attribute docs occurs on Grammar, Root, AGDcls, AGDcl;

-- A string that goes at the top of each file
synthesized attribute docsHeader :: String;
attribute docsHeader occurs on Grammar, Root, AGDcls, AGDcl;

-- Set to "true" if each file in the grammar should have its own markdown file
synthesized attribute docsSplit :: String;
attribute docsSplit occurs on Grammar, Root, AGDcls, AGDcl;

-- Set to "true" if no documentation should be generated for this grammar
synthesized attribute docsNoDoc :: Boolean;
attribute docsNoDoc occurs on Grammar, Root, AGDcls, AGDcl;

-- Set to the base website if hosting this documentation
--autocopy attribute baseUrl :: String;
--attribute baseUrl occurs on Grammar, AGDcls, AGDcl;

-- Declarations of documented AGDcls
synthesized attribute docDcls :: [Pair<String DocDclInfo>] with ++;
attribute docDcls occurs on Grammar, Root, AGDcls, AGDcl;

-- Environment of all documented AGDcls
inherited attribute docEnv :: TreeMap<String DocDclInfo>;
attribute docEnv occurs on Grammar, Root, AGDcls, AGDcl;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.docs := ags.docs;
  top.docsHeader = ags.docsHeader;
  top.docsSplit = ags.docsSplit;
  top.docsNoDoc = false;
  top.docDcls := ags.docDcls;

  ags.docEnv = top.docEnv;
  --ags.baseUrl = "http://www.http://melt.cs.umn.edu/docs"; -- TODO: Get this to not be hardcoded and use a @config item
}

aspect production nilAGDcls
top::AGDcls ::=
{
  top.docs := [];
  top.docsHeader = "";
  top.docsSplit = "";
  top.docsNoDoc = false;
  top.docDcls := [];
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
  top.docDcls := h.docDcls ++ t.docDcls;

  h.docEnv = top.docEnv;
  t.docEnv = top.docEnv;
}

aspect default production
top::AGDcl ::=
{
  top.docs := [];
  top.docsHeader = "";
  top.docsSplit = "";
  top.docsNoDoc = false;
  top.docDcls := [];
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
  top.docDcls := h.docDcls ++ t.docDcls;

  h.docEnv = top.docEnv;
  t.docEnv = top.docEnv;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.docs := [];
  top.docsHeader = "";
  top.docsSplit = "";
  top.docsNoDoc = false;
  top.docDcls := [];
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
  top.docDcls := c1.docDcls ++ c2.docDcls;

  c1.docEnv = top.docEnv;
  c2.docEnv = top.docEnv;
}

