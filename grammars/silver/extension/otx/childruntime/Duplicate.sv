
function javaDup
a ::= arg::a rules::[OtxRule]
{
  return error("Not impl");
} foreign {
  "java" : return "(((common.Node)%arg%).duplicate(%rules%))";
}