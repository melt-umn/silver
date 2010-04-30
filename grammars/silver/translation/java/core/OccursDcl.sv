grammar silver:translation:java:core;
import silver:definition:core;
import silver:definition:env;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initAspect := "\t\t" ++ makeNTClassName(nt.lookupType.fullName) ++ ".occurs.add(\"" ++ a.lookupAttribute.fullName ++ "\");\n";
  top.postInit := "";
}
