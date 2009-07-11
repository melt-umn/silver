grammar silver:translation:java:core;
import silver:definition:core;
import silver:definition:env;

synthesized attribute javaClasses :: [[String]];
synthesized attribute setupInh :: String;
synthesized attribute initProd :: String;
synthesized attribute initAspect :: String;
synthesized attribute translation :: String;

attribute javaClasses occurs on RootSpec;
attribute setupInh occurs on RootSpec;
attribute initProd occurs on RootSpec;
attribute initAspect occurs on RootSpec;

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.javaClasses = [];
  top.setupInh = "";
  top.initProd = "";
  top.initAspect = "";
}

aspect production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{
  top.javaClasses = c1.javaClasses;
  top.setupInh = c1.setupInh;
  top.initProd = c1.initProd;
  top.initAspect = c1.initAspect;
}

aspect production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.javaClasses = c1.javaClasses ++ c2.javaClasses;
  top.setupInh = c1.setupInh ++ c2.setupInh;  
  top.initProd = c1.initProd ++ c2.initProd;  
  top.initAspect = c1.initAspect ++ c2.initAspect;  
}
