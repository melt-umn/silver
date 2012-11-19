grammar silver:translation:java:core;

attribute genFiles,setupInh,initProd,initValues,postInit,initWeaving,valueWeaving occurs on Root, AGDcls, AGDcl, GrammarPart, Grammar;

{--
 - Java classes to generate. (filename, contents)
 -}
synthesized attribute genFiles :: [Pair<String String>] with ++;
{--
 - Early initializers: occurs.add, local's inh attr map creation, decorators.add, collection object creation
 -}
synthesized attribute setupInh :: String with ++;
{--
 - Initialize the attributes maps for each production.
 - note to be confused with "production attribute" dcls.
 -}
synthesized attribute initProd :: String with ++;
{--
 - Global values.
 -}
synthesized attribute initValues :: String with ++;
{--
 - Late initializers. Decorator application (late because we want all attribute equations to be posted first!!)
 -}
synthesized attribute postInit :: String with ++;

synthesized attribute translation :: String;
{--
 - Initial values for early weaving. e.g. counter for # attributes on NT
 -}
synthesized attribute initWeaving :: String with ++;
{--
 - Values computed by early weaving. e.g. index of attribute in NT arrays
 -}
synthesized attribute valueWeaving :: String with ++;


aspect production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  top.genFiles := ags.genFiles;
  top.setupInh := ags.setupInh;
  top.initProd := ags.initProd;
  top.initValues := ags.initValues;
  top.postInit := ags.postInit;
  top.initWeaving := ags.initWeaving;
  top.valueWeaving := ags.valueWeaving;
}

aspect production nilAGDcls
top::AGDcls ::=
{
  top.genFiles := [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
  top.initWeaving := "";
  top.valueWeaving := "";
}

aspect production consAGDcls
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.genFiles := h.genFiles ++ t.genFiles;
  top.setupInh := h.setupInh ++ t.setupInh;
  top.initProd := h.initProd ++ t.initProd;
  top.initValues := h.initValues ++ t.initValues;
  top.postInit := h.postInit ++ t.postInit;
  top.initWeaving := h.initWeaving ++ t.initWeaving;
  top.valueWeaving := h.valueWeaving ++ t.valueWeaving;
}

aspect default production
top::AGDcl ::=
{
  top.genFiles := [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
  top.initWeaving := "";
  top.valueWeaving := "";
}

aspect production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.genFiles := h.genFiles ++ t.genFiles;
  top.setupInh := h.setupInh ++ t.setupInh;
  top.initProd := h.initProd ++ t.initProd;
  top.initValues := h.initValues ++ t.initValues;
  top.postInit := h.postInit ++ t.postInit;
  top.initWeaving := h.initWeaving ++ t.initWeaving;
  top.valueWeaving := h.valueWeaving ++ t.valueWeaving;
}

aspect production grammarPart
top::GrammarPart ::= c1::Root  fn::String
{
  top.genFiles := c1.genFiles;
  top.setupInh := c1.setupInh;
  top.initProd := c1.initProd;
  top.initValues := c1.initValues;
  top.postInit := c1.postInit;
  top.initWeaving := c1.initWeaving;
  top.valueWeaving := c1.valueWeaving;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.genFiles := [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
  top.initWeaving := "";
  top.valueWeaving := "";
}

aspect production consGrammar
top::Grammar ::= c1::GrammarPart  c2::Grammar
{
  top.genFiles := c1.genFiles ++ c2.genFiles;
  top.setupInh := c1.setupInh ++ c2.setupInh;  
  top.initProd := c1.initProd ++ c2.initProd;  
  top.initValues := c1.initValues ++ c2.initValues;
  top.postInit := c1.postInit ++ c2.postInit;
  top.initWeaving := c1.initWeaving ++ c2.initWeaving;
  top.valueWeaving := c1.valueWeaving ++ c2.valueWeaving;
}

