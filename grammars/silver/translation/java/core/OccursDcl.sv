grammar silver:translation:java:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName '<' tlat::TypeList '>' 'occurs' 'on' nt::QName '<' tlnt::TypeList '>' ';'
{
  top.setupInh := "\t\t" ++ makeNTClassName(nt.lookupType.fullName) ++ ".occurs.add(\"" ++ a.lookupAttribute.fullName ++ "\");\n";
  
  local attribute ntgrammar :: String;
  ntgrammar = substring(0, lastIndexOf(":", nt.lookupType.fullName), nt.lookupType.fullName);
  local attribute ntname :: String;
  ntname = substring(lastIndexOf(":", nt.lookupType.fullName)+1 , length(nt.lookupType.fullName), nt.lookupType.fullName);
  
  top.valueWeaving := "public static final int " ++ head(occursCheck).attrOccursIndexName
                      ++ " = " ++ makeName(ntgrammar) ++ ".Init.count_" ++ a.lookupAttribute.dcl.attrOccursType ++ "__ON__" ++ ntname ++ "++;\n";
}
