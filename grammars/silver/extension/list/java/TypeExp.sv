grammar silver:extension:list:java;

import silver:extension:list;
import silver:definition:type;
import silver:definition:env;
import silver:definition:core;
import silver:translation:java:type;

aspect production listTypeExp
top::TypeExp ::= el::TypeExp
{
  top.transType = "common.ConsCell";
}

