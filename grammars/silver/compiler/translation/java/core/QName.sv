grammar silver:compiler:translation:java:core;

attribute attrOccursIndex, attrOccursInitIndex occurs on QNameAttrOccur;

aspect production qNameAttrOccur
top::QNameAttrOccur ::= at::QName
{
  top.attrOccursIndex = resolvedDcl.attrOccursIndex;
  top.attrOccursInitIndex = resolvedDcl.attrOccursInitIndex;
}
