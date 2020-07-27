grammar core:originsimpl;

function sexprify
String ::= nt::a
{
  return error("Not impl");
} foreign {
  "java" : return "(common.OriginsUtil.sexprify(%nt%))";
}

function javaGetOrigin
Maybe<OriginInfo> ::= arg::a
{
  return error("Not impl");
} foreign {
  "java" : return "common.OriginsUtil.polyGetOrigin(%arg%)";
}

function javaGetOriginLink
Maybe<a> ::= arg::OriginInfo
{
  return error("Not impl");
} foreign {
  "java" : return "common.OriginsUtil.getOriginLink(%arg%)";
}

tracked nonterminal AmbientOriginNT;