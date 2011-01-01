grammar silver:translation:java:core;

-- TODO: Should have made javaClasses a collection, too, while I was at it.
-- TODO: also, make it a pair now that we can!

{--
 - Java classes to generate. (filename, contents)
 -}
synthesized attribute javaClasses :: [[String]];
{--
 - Early initializers:
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
 - Late initializers. Decorators, so far.
 -}
synthesized attribute postInit :: String with ++;
synthesized attribute translation :: String;

attribute javaClasses occurs on RootSpec;
attribute setupInh occurs on RootSpec;
attribute initProd occurs on RootSpec;
attribute initValues occurs on RootSpec;
attribute postInit occurs on RootSpec;

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
}

aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.javaClasses = c1.javaClasses;
  top.setupInh := c1.setupInh;
  top.initProd := c1.initProd;
  top.initValues := c1.initValues;
  top.postInit := c1.postInit;
}

aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.javaClasses = c1.javaClasses ++ c2.javaClasses;
  top.setupInh := c1.setupInh ++ c2.setupInh;  
  top.initProd := c1.initProd ++ c2.initProd;  
  top.initValues := c1.initValues ++ c2.initValues;
  top.postInit := c1.postInit ++ c2.postInit;
}
