
function nativeSerialize
Either<String ByteArray> ::= x::a
{
  return error("Not impl");
} foreign {
  "java" : return "common.Reflection.nativeSerialize(%x%)";
}

function nativeDeserialize
runtimeTypeable a => Either<String a> ::= x::ByteArray
{
  return error("Not impl");
} foreign {
  "java" : return "common.Reflection.nativeDeserialize(%@0@%, %x%)";
}
