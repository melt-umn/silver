grammar silver:translation:java:core;

attribute javaClasses,setupInh,initProd,initValues,postInit,initWeaving,valueWeaving occurs on RootSpec;


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


aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
  top.initWeaving := "";
  top.valueWeaving := "";
}

aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.javaClasses = c1.javaClasses;
  top.setupInh := c1.setupInh;
  top.initProd := c1.initProd;
  top.initValues := c1.initValues;
  top.postInit := c1.postInit;
  top.initWeaving := c1.initWeaving;
  top.valueWeaving := c1.valueWeaving;
}

aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.javaClasses = c1.javaClasses ++ c2.javaClasses;
  top.setupInh := c1.setupInh ++ c2.setupInh;  
  top.initProd := c1.initProd ++ c2.initProd;  
  top.initValues := c1.initValues ++ c2.initValues;
  top.postInit := c1.postInit ++ c2.postInit;
  top.initWeaving := c1.initWeaving ++ c2.initWeaving;
  top.valueWeaving := c1.valueWeaving ++ c2.valueWeaving;
}
