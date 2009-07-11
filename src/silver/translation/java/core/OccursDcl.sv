grammar silver:translation:java:core;
import silver:definition:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  top.javaClasses = [];
  top.setupInh = "";
  top.initProd = "";
  top.initAspect = "";
}
