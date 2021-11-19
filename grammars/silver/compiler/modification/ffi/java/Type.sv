grammar silver:compiler:modification:ffi:java;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:type;
import silver:compiler:translation:java:type;
import silver:compiler:modification:ffi;

aspect production foreignType
top::Type ::= fn::String  transType::String  params::[Type]
{
  top.transType = transType;
  top.transClassType = transType;
  top.transTypeRep =
    foldl(
      \ c::String a::Type -> s"new common.AppTypeRep(${c}, ${transTypeRepWith(a, top.skolemTypeReps)})",
      s"new common.BaseTypeRep(\"${fn}\")", params);
  top.transTypeName = implode("_", substitute(":", "_", fn) :: map(transTypeNameWith(_, top.boundVariables), params));
}

