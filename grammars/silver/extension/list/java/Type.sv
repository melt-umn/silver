grammar silver:extension:list:java;

import silver:extension:list;
import silver:definition:type;
import silver:definition:env;
import silver:definition:core;
import silver:translation:java:type;

aspect production listType
top::Type ::= el::Type
{
  top.transType = "common.ConsCell";
  top.transTypeRep = s"new common.ListTypeRep(${el.transTypeRep})";
  top.transFreshTypeRep = s"new common.ListTypeRep(${el.transFreshTypeRep})";
}

