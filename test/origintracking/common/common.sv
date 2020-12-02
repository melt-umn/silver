imports core;
imports silver:testing;
imports lib:extcore;
imports silver:reflect;

exports silver:testing;
exports lib:extcore;
exports silver:reflect;

function identityHashCode
Integer ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(System.identityHashCode(%nt%))";
}