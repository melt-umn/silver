grammar silver:extension:doc:core;

nonterminal DocItem;
abstract production commentDocItem
top::DocItem ::= c::CommentItem
{}

abstract production configDocItem
top::DocItem ::= c::ConfigItem
{}

{--
 - Markdown files to generate. (filename, contents)
 -}
synthesized attribute genFiles :: [Pair<String String>] with ++;
{--
 - Documentation for a construct or list of constructs
 -}
synthesized attribute docs :: [DocItem] with ++; -- TODO: Should this be a collection attribute?
{--
 - Documentation for something below the AGDecl level
 -}
synthesized attribute doc :: String with ++; -- TODO: Should this be a collection attribute?

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

