
function javaDup
a ::= arg::a rules::[OtxNote]
{
  return error("Not impl");
} foreign {
  "java" : return "(((common.Node)%arg%).duplicate(%rules%))";
}