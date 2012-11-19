grammar silver:translation:java:core;

import silver:driver:util;

attribute javaClasses,setupInh,initProd,initValues,postInit,initWeaving,valueWeaving occurs on RootSpec, GrammarPart, Grammar;


-- TODO: Should have made javaClasses a collection, too, while I was at it.
-- TODO: also, make it a pair now that we can!

{--
 - Java classes to generate. (filename, contents)
 -}
synthesized attribute javaClasses :: [[String]];
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

aspect production interfaceRootSpec
top::RootSpec ::= _
{
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
  top.initWeaving := "";
  top.valueWeaving := "";
}

aspect production grammarRootSpec
top::RootSpec ::= c1::Grammar  _
{
  top.javaClasses = c1.javaClasses;
  top.setupInh := c1.setupInh;
  top.initProd := c1.initProd;
  top.initValues := c1.initValues;
  top.postInit := c1.postInit;
  top.initWeaving := c1.initWeaving;
  top.valueWeaving := c1.valueWeaving;
}

aspect production grammarPart
top::GrammarPart ::= c1::Root  fn::String
{
  top.javaClasses = c1.javaClasses;
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
  top.javaClasses = [];
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
  top.javaClasses = c1.javaClasses ++ c2.javaClasses;
  top.setupInh := c1.setupInh ++ c2.setupInh;  
  top.initProd := c1.initProd ++ c2.initProd;  
  top.initValues := c1.initValues ++ c2.initValues;
  top.postInit := c1.postInit ++ c2.postInit;
  top.initWeaving := c1.initWeaving ++ c2.initWeaving;
  top.valueWeaving := c1.valueWeaving ++ c2.valueWeaving;
}

