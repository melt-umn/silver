grammar silver:translation:java:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName '<' tlat::TypeList '>' 'occurs' 'on' nt::QName '<' tlnt::TypeList '>' ';'
{
  top.setupInh := "\t\t" ++ makeNTClassName(nt.lookupType.fullName) ++ ".occurs.add(\"" ++ a.lookupAttribute.fullName ++ "\");\n";
}
