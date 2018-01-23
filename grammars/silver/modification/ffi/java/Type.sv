grammar silver:modification:ffi:java;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:translation:java:type;
import silver:modification:ffi;

aspect production foreignType
top::Type ::= fn::String params::[Type]
{
  top.transType = "Object";
  top.transClassType = "Object";
  top.transTypeRep =
    s"new common.BaseTypeRep(\"${fn}\", new common.TypeRep[] {${implode(", ", map((.transTypeRep), params))}})";
  top.transFreshTypeRep =
    s"new common.BaseTypeRep(\"${fn}\", new common.TypeRep[] {${implode(", ", map((.transFreshTypeRep), params))}})";
}

