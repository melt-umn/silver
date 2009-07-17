grammar silver:translation:java:core;
import silver:definition:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  -- nfName is prod. attr. that's full name of nt.
  -- afName is for a.

  top.javaClasses = [];
  top.setupInh := "";
  top.initProd := "";
  top.initAspect := "\t\t" ++ makeNTClassName(nfName) ++ ".occurs.add(\"" ++ afName ++ "\");\n";
  top.postInit := "";
}
