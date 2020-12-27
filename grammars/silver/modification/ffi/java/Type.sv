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
    foldl(
      \ c::String a::Type -> s"new common.AppTypeRep(${c}, ${a.transTypeRep})",
      s"new common.BaseTypeRep(\"${fn}\")", params);
  top.transFreshTypeRep =
    foldl(
      \ c::String a::Type -> s"new common.AppTypeRep(${c}, ${a.transFreshTypeRep})",
      s"new common.BaseTypeRep(\"${fn}\")", params);
  top.transTypeName = implode("_", substitute(":", "_", fn) :: map((.transTypeName), params));
}

