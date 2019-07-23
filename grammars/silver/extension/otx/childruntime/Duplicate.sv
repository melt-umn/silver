
function javaDup
a ::= arg::a rule::b
{
  return error("Not impl");
} foreign {
  "java" : return "(((common.Node)%arg%).duplicate(%rule%))";
}