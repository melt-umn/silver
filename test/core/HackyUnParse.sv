grammar core;

function hackUnparse
String ::= nt::AnyType
{
  return error("Not impl");
} foreign {
  "java" : return "(common.Util.hackyhackyUnparse(%nt%))";
}


