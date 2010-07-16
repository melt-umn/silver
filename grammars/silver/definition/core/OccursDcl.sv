grammar silver:definition:core;
import silver:definition:env;

concrete production attributionDcl
top::AGDcl ::= 'attribute' a::QName 'occurs' 'on' nt::QName ';'
{
  top.pp = "attribute " ++ a.pp ++ " occurs on " ++ nt.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.moduleNames = [];

  -- TODO: we should decide which location to use (a or nt) better somehow
  top.defs = addOccursDcl(top.grammarName, a.location, nt.lookupType.fullName, a.lookupAttribute.fullName, emptyDefs());

  top.errors := a.lookupAttribute.errors ++ nt.lookupType.errors;
}
