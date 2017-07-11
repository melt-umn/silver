grammar silver:modification:ffi:java;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:translation:java:type;
import silver:modification:ffi;

aspect production foreignTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.transType = "Object";
  top.transClassType = "Object";
}

