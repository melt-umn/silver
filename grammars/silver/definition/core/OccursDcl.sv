grammar silver:definition:core;
import silver:definition:env;

concrete production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nt.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  top.defs = addOccursDcl(a.lookupAttribute.fullName, nt.lookupType.fullName, emptyDefs());

  top.errors := a.lookupAttribute.errors ++ nt.lookupType.errors;
}
