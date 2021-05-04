grammar silver:compiler:extension:doc:core;

import silver:compiler:driver;

synthesized attribute genFiles :: [Pair<String String>] with ++;

-- Used for getting doc comments on AGDcls
synthesized attribute docs :: [CommentItem] with ++;
attribute docs occurs on Grammar, Root, AGDcls, AGDcl, ClassBodyItem, InstanceBodyItem, ClassBody, InstanceBody;

inherited attribute downDocConfig :: [DocConfigSetting] occurs on Grammar, Root, AGDcls, AGDcl, ClassBodyItem, InstanceBodyItem, ClassBody, InstanceBody;
synthesized attribute upDocConfig :: [DocConfigSetting] with ++ occurs on Grammar, Root, AGDcls, AGDcl, ClassBodyItem, InstanceBodyItem, ClassBody, InstanceBody;
synthesized attribute localDocConfig :: [DocConfigSetting] occurs on Root;

synthesized attribute undocumentedNamed :: [String] occurs on Root, Grammar;
synthesized attribute documentedNamed :: [String] occurs on Root, Grammar;

-- Declarations of documented AGDcls
synthesized attribute docDcls :: [Pair<String DocDclInfo>] with ++;
attribute docDcls occurs on Grammar, Root, AGDcls, AGDcl, ClassBodyItem, InstanceBodyItem, ClassBody, InstanceBody;

-- Environment of all documented AGDcls
autocopy attribute docEnv :: tm:Map<String DocDclInfo>;
attribute docEnv occurs on Grammar, Root, AGDcls, AGDcl, ClassBodyItem, InstanceBodyItem, ClassBody, InstanceBody;

aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.docs := ags.docs;
  ags.downDocConfig = filter((\x::DocConfigSetting -> x.fileScope), ags.upDocConfig) ++ top.downDocConfig;
  top.localDocConfig = ags.downDocConfig;
  top.upDocConfig := filter((\x::DocConfigSetting -> !x.fileScope), ags.upDocConfig);
  top.docDcls := ags.docDcls;
  top.undocumentedNamed = flatMap((.undocNames), top.docs);
  top.documentedNamed = flatMap((.docNames), top.docs);
}

aspect production nilAGDcls
top::AGDcls ::=
{
  top.docs := [];
  top.upDocConfig := [];
  top.docDcls := [];
}

aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.docs := h.docs ++ t.docs;
  h.downDocConfig = top.downDocConfig;
  t.downDocConfig = top.downDocConfig;
  top.upDocConfig := h.upDocConfig ++ t.upDocConfig;
  top.docDcls := h.docDcls ++ t.docDcls;
}

aspect default production
top::AGDcl ::=
{
  top.upDocConfig := [];
  top.docs := [mkUndocumentedItem(s"<default AGDcl production `${head(explode("(", hackUnparse(top)))}`(fwd)@${top.location.unparse}>", top)];
  top.docDcls := [];
  top.docUnparse = head(explode("\n", top.unparse)) ++ "\n{{< hint danger >}}\nNo docUnparse defined for `" ++ hackUnparse(top) ++ "`\n{{< /hint >}}\n\n";
}

aspect production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.docs := h.docs ++ t.docs;
  h.downDocConfig = top.downDocConfig;
  t.downDocConfig = top.downDocConfig;
  top.upDocConfig := h.upDocConfig ++ t.upDocConfig;
  top.docDcls := h.docDcls ++ t.docDcls;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.docs := [];
  top.upDocConfig := [];
  top.docDcls := [];
  top.undocumentedNamed = [];
  top.documentedNamed = [];
}

aspect production consGrammar
top::Grammar ::= c1::Root  c2::Grammar
{
  top.docs := c1.docs ++ c2.docs;
  top.upDocConfig := c1.upDocConfig ++ c2.upDocConfig;
  c1.downDocConfig = top.downDocConfig;
  c2.downDocConfig = top.downDocConfig;
  top.docDcls := c1.docDcls ++ c2.docDcls;
  top.undocumentedNamed = c1.undocumentedNamed ++ c2.undocumentedNamed;
  top.documentedNamed = c1.documentedNamed ++ c2.documentedNamed;
}

-- consGrammar(FILE1, consGrammar(FILE2, nilGrammar()))