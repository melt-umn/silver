grammar silver:translation:java:core;

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  local ntfn :: String = nt.lookupType.fullName;
  local occursType :: String = if at.lookupAttribute.dcl.isSynthesized then "syn" else "inh";

  top.setupInh := "\t\t" ++ makeNTClassName(ntfn) ++ ".occurs_" ++ occursType ++ "[" ++ head(occursCheck).attrOccursIndex ++ "] = \"" ++ at.lookupAttribute.fullName ++ "\";\n";
  
  local ntgrammar :: String = substring(0, lastIndexOf(":", ntfn), ntfn);
  local ntname :: String = substring(lastIndexOf(":", ntfn)+1 , length(ntfn), ntfn);
  -- TODO: don't we have a 'grammarFromQName' function somewhere? or even an attribute on qname why not?
  
  top.valueWeaving := 
    "public static final int " ++ head(occursCheck).attrOccursIndexName ++ " = " ++
    makeName(ntgrammar) ++ ".Init.count_" ++ occursType ++ "__ON__" ++ ntname ++ "++;\n";
}

