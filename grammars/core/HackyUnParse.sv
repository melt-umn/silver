grammar core;

function hackUnparse
String ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(common.Util.hackyhackyUnparse(%nt%))";
}


