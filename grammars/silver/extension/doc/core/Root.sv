grammar silver:extension:doc:core;

synthesized attribute genFiles :: [Pair<String String>] with ++;
synthesized attribute docs :: [DocItem] with ++;
attribute docs occurs on Grammar, Root, AGDcls, AGDcl;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.docs := ags.docs;
}

aspect production nilAGDcls
top::AGDcls ::=
{
  top.docs := [];
}

aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.docs := h.docs ++ t.docs;
}

aspect default production
top::AGDcl ::=
{
  top.docs := [];
}

aspect production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.docs := h.docs ++ t.docs;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.docs := [];
}

aspect production consGrammar
top::Grammar ::= c1::Root  c2::Grammar
{
  top.docs := c1.docs ++ c2.docs; -- TODO, define ordering
}

