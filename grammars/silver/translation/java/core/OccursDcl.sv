grammar silver:translation:java:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName '<' tlat::TypeList '>' 'occurs' 'on' nt::QName '<' tlnt::TypeList '>' ';'
{
  top.javaClasses = [];
  top.setupInh := "";
  -- TODO: this doesn't have any dependencies, maybe we can move it to setupInh?
  top.initProd := "\t\t" ++ makeNTClassName(nt.lookupType.fullName) ++ ".occurs.add(\"" ++ a.lookupAttribute.fullName ++ "\");\n";
  top.initValues := "";
  top.postInit := "";
}
