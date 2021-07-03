
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

@{--
 - Write a bytearray to a file, replacing whatever is there already.
 -
 - @param file  The filename to write to.
 - @param contents  The bytearray to write to the file.
 - @param i  The "before" world-state token.
 - @return  The "after" world-state token.  May throw a java IO exception, which cannot be caught by Silver.
 -}
function writeByteFile
IO ::= file::String contents::ByteArray i::IO
{
  return error("Not Yet Implemented: writeByteFile");
} foreign {
  "java" : return "%i%.writeByteFile(%file%, %contents%)";
}

@{--
 - Read the entire contents of a file.  All instances of "\r\n" are replaced by "\n"
 - for compatibility reasons.
 -
 - @param s  The file to read.
 - @param i  The "before" world-state token.
 - @return  The contents of the file. May throw a java IO exception, which cannot be caught by Silver.
 -}
function readByteFile
IOVal<ByteArray> ::= s::String i::IO
{
  return error("Not Yet Implemented: readByteFile");
} foreign {
  "java" : return "%i%.readByteFile(%s%)";
}
