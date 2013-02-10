grammar silver:translation:java:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  top.setupInh := "\t\t" ++ makeNTClassName(nt.lookupType.fullName) ++ ".occurs_" ++ at.lookupAttribute.dcl.attrOccursType ++ "[" ++ head(occursCheck).attrOccursIndex ++ "] = \"" ++ at.lookupAttribute.fullName ++ "\";\n";
  
  local attribute ntgrammar :: String;
  ntgrammar = substring(0, lastIndexOf(":", nt.lookupType.fullName), nt.lookupType.fullName);
  -- TODO: don't we have a 'grammarFromQName' function somewhere? or even an attribute on qname why not?
  
  local attribute ntname :: String;
  ntname = substring(lastIndexOf(":", nt.lookupType.fullName)+1 , length(nt.lookupType.fullName), nt.lookupType.fullName);
  -- yeah this too
  
  top.valueWeaving := "public static final int " ++ head(occursCheck).attrOccursIndexName
                      ++ " = " ++ makeName(ntgrammar) ++ ".Init.count_" ++ at.lookupAttribute.dcl.attrOccursType ++ "__ON__" ++ ntname ++ "++;\n";
}

