import core:monad;

function sexprify
String ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(common.Origins.sexprify(%nt%))";
}