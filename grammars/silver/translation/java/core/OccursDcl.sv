grammar silver:translation:java:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QNameWithTL 'occurs' 'on' nt::QNameWithTL ';'
{
  top.setupInh := "\t\t" ++ makeNTClassName(nt.qname.lookupType.fullName) ++ ".occurs_" ++ at.qname.lookupAttribute.dcl.attrOccursType ++ "[" ++ head(occursCheck).attrOccursIndex ++ "] = \"" ++ at.qname.lookupAttribute.fullName ++ "\";\n";
  
  local attribute ntgrammar :: String;
  ntgrammar = substring(0, lastIndexOf(":", nt.qname.lookupType.fullName), nt.qname.lookupType.fullName);
  -- TODO: don't we have a 'grammarFromQName' function somewhere? or even an attribute on qname why not?
  
  local attribute ntname :: String;
  ntname = substring(lastIndexOf(":", nt.qname.lookupType.fullName)+1 , length(nt.qname.lookupType.fullName), nt.qname.lookupType.fullName);
  -- yeah this too
  
  top.valueWeaving := "public static final int " ++ head(occursCheck).attrOccursIndexName
                      ++ " = " ++ makeName(ntgrammar) ++ ".Init.count_" ++ at.qname.lookupAttribute.dcl.attrOccursType ++ "__ON__" ++ ntname ++ "++;\n";
}
