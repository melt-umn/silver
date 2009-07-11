grammar silver:translation:java:core;
import silver:definition:core;

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name '::' te::Type ';'
{
  top.javaClasses = [];
  top.setupInh = "";
  top.initProd = "";
  top.initAspect = "";
}


aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name '::' te::Type ';'
{
  top.javaClasses = [];
  top.setupInh = "";
  top.initProd = "";
  top.initAspect = "";
}



