grammar silver:compiler:translation:java:core;

aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  local ntfn :: String = nt.lookupType.fullName;
  local occursType :: String = if at.lookupAttribute.dcl.isSynthesized then "syn" else "inh";
  local ntgrammar :: String = substring(0, lastIndexOf(":", ntfn), ntfn);
  local ntname :: String = substring(lastIndexOf(":", ntfn)+1 , length(ntfn), ntfn);
  -- TODO: don't we have a 'grammarFromQName' function somewhere? or even an attribute on qname why not?
  
  top.setupInh := 
    if at.lookupAttribute.dcl.isAnnotation then
      ""
    else
      s"\t\t${makeNTName(ntfn)}.occurs_${occursType}[${head(occursCheck).attrOccursInitIndex}] = \"${at.lookupAttribute.fullName}\";\n";

  top.postInit :=
    if at.lookupAttribute.dcl.isAnnotation then
      ""
    else
      s"\t\tcommon.RTTIManager.registerOccurs(\"${ntfn}\", \"${at.lookupAttribute.fullName}\", ${head(occursCheck).attrOccursInitIndex});\n";

  top.valueWeaving :=
    if at.lookupAttribute.dcl.isAnnotation then
      ""
    else
      s"public static final int ${head(occursCheck).attrOccursIndexName} = " ++
      s"${makeName(ntgrammar)}.Init.count_${occursType}__ON__${ntname}++;\n";
}

aspect production errorAttributionDcl
top::AGDcl ::= msg::[Message] at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.setupInh := error("Internal compiler error: translation not defined in the presence of errors");
  top.valueWeaving := error("Internal compiler error: translation not defined in the presence of errors");
}
