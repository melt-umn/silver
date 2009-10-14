grammar silver:translation:java:core;
import silver:definition:core;
import silver:definition:env;

-- Argh. Should have made javaClasses a collection, too, while I was at it..
synthesized attribute javaClasses :: [[String]];
synthesized attribute setupInh :: String with ++;
synthesized attribute initProd :: String with ++;
synthesized attribute initAspect :: String with ++;
synthesized attribute postInit :: String with ++;
synthesized attribute translation :: String;

attribute javaClasses occurs on RootSpec;
attribute setupInh occurs on RootSpec;
attribute initProd occurs on RootSpec;
attribute initAspect occurs on RootSpec;
attribute postInit occurs on RootSpec;

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initAspect := "";
  top.postInit := "";
}

aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.javaClasses = c1.javaClasses;
  top.setupInh := c1.setupInh;
  top.initProd := c1.initProd;
  top.initAspect := c1.initAspect;
  top.postInit := c1.postInit;
}

aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.javaClasses = c1.javaClasses ++ c2.javaClasses;
  top.setupInh := c1.setupInh ++ c2.setupInh;  
  top.initProd := c1.initProd ++ c2.initProd;  
  top.initAspect := c1.initAspect ++ c2.initAspect;
  top.postInit := c1.postInit ++ c2.postInit;
}
