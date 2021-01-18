grammar silver:compiler:extension:doc:core;

import silver:compiler:driver;

synthesized attribute genFiles :: [Pair<String String>] with ++;

-- Used for getting doc comments on AGDcls
synthesized attribute docs :: [CommentItem] with ++;
attribute docs occurs on Grammar, Root, AGDcls, AGDcl;

inherited attribute downDocConfig :: DocConfiguration occurs on Root, AGDcls, AGDcl;
synthesized attribute upDocConfig :: DocConfiguration occurs on Grammar, Root, AGDcls, AGDcl;

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
  ags.downDocConfig = newFileConfig(top.downDocConfig);
  top.upDocConfig = ags.upDocConfig;
  top.docDcls := ags.docDcls;
}

aspect production nilAGDcls
top::AGDcls ::=
{
  top.docs := [];
  top.upDocConfig = top.downDocConfig;
  top.docDcls := [];
}

aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.docs := h.docs ++ t.docs;
  h.downDocConfig = top.downDocConfig;
  t.downDocConfig = h.upDocConfig;
  top.upDocConfig = t.upDocConfig;
  top.docDcls := h.docDcls ++ t.docDcls;
}

aspect default production
top::AGDcl ::=
{
  top.upDocConfig = top.downDocConfig;
  top.docs := [];
}

aspect production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.docs := h.docs ++ t.docs;
  h.downDocConfig = top.downDocConfig;
  t.downDocConfig = h.upDocConfig;
  top.upDocConfig = t.upDocConfig;
  top.docDcls := h.docDcls ++ t.docDcls;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.docs := [];
  top.upDocConfig = nilConfig();
  top.docDcls := [];
}

aspect production consGrammar
top::Grammar ::= c1::Root  c2::Grammar
{
  top.docs := c1.docs ++ c2.docs;
  c1.downDocConfig = c2.upDocConfig;
  top.upDocConfig = c1.upDocConfig;
  top.docDcls := c1.docDcls ++ c2.docDcls;
}

-- consGrammar(FILE1, consGrammar(FILE2, nilGrammar()))