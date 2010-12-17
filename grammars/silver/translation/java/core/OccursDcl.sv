grammar silver:translation:java:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName '<' tlat::TypeList '>' 'occurs' 'on' nt::QName '<' tlnt::TypeList '>' ';'
{
  top.javaClasses = [];
  top.setupInh := "\t\t" ++ makeNTClassName(nt.lookupType.fullName) ++ ".occurs.add(\"" ++ a.lookupAttribute.fullName ++ "\");\n";
  top.initProd := "";
  top.initValues := "";
  top.postInit := "";
}
