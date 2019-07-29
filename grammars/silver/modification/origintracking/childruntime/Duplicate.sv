
function javaDup
a ::= arg::a redex::b rules::[OriginNote]
{
  return error("Not impl");
} foreign {
  "java" : return "(((common.Node)%arg%).duplicate(%redex%, %rules%))";
}

function javaDupNullRedex
a ::= arg::a rules::[OriginNote]
{
  return error("Not impl");
} foreign {
  "java" : return "(((common.Node)%arg%).duplicate(null, %rules%))";
}

function javaCopy
a ::= arg::a redex::b rules::[OriginNote]
{
  return error("Not impl");
} foreign {
  "java" : return "(((common.Node)%arg%).copy(%redex%, %rules%))";
}