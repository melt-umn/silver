grammar core:originsimpl;

function sexprify
String ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(common.OriginsUtil.sexprify(%nt%))";
}

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

function javaGetOrigin
Maybe<OriginInfo> ::= arg::a
{
  return error("Not impl");
} foreign {
  "java" : return "common.OriginsUtil.polyGetOrigin(%arg%)";
}

function javaGetNextOrigin
Maybe<OriginInfo> ::= arg::OriginLink
{
  return error("Not impl");
} foreign {
  "java" : return "common.OriginsUtil.polyGetNextOrigin(%arg%)";
}