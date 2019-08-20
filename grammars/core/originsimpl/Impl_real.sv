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

function javaGetNextOrigin
Maybe<OriginInfo> ::= arg::OriginLink
{
  return error("Not impl");
} foreign {
  "java" : return "common.OriginsUtil.polyGetNextOrigin(%arg%)";
}