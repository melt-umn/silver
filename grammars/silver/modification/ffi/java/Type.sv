grammar silver:modification:ffi:java;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:translation:java:type;
import silver:modification:ffi;

aspect production foreignType
top::Type ::= fn::String  transType::String  params::[Type]
{
  top.transType = transType;
  top.transClassType = transType;
  top.transTypeRep =
    s"new common.BaseTypeRep(\"${fn}\", new common.TypeRep[] {${implode(", ", map((.transTypeRep), params))}})";
  top.transFreshTypeRep =
    s"new common.BaseTypeRep(\"${fn}\", new common.TypeRep[] {${implode(", ", map((.transFreshTypeRep), params))}})";
}

