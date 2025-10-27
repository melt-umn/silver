grammar silver:core;

-- Mark this tree and its children as being in the main file or an external file for CodeProber.
fun setTreeIsInMainFile IO<Unit> ::= b::Boolean x::a =
  stateIOUnit(setTreeIsInMainFileT(b, x, _));

function setTreeIsInMainFileT
IOToken ::= b::Boolean x::a i::IOToken
{
  return error("foreign function");
} foreign {
  "java" : return "%i%.setTreeIsInMainFile(%x%, %b%)";
}
