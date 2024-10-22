grammar silver:core;

@{--
 - hackUnparse takes any value, and produce a string that represents
 - that value.  It is mainly useful for debugging.
 -
 - @warning This is deprecated. Use silver:langutil:pp:show instead, or genericShow in polymorphic contexts/generated code.
 -}
function hackUnparse
String ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(common.Util.hackyhackyUnparse(%nt%))";
}

@{--
 - Take any value, and return a human-readable string representation.
 - This attempts to make use of the pp or unparse attributes, if a term/tree with either of those is provided.
 - Intended for debugging purposes or for error handling in generated code; prefer silver:langutil:pp:show when possible.
 -
 - Implementation note: this function makes use of silver:langutil:reflect:genericPP if that library is available,
 - else falling back to the less-sophisticated hackUnparse implementation in the runtime.
 -}
function genericShow
String ::= x::a
{
  return error("Not impl");
} foreign {
  "java" : return "(common.Util.genericShow(%x%))";
}
