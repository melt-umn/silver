grammar ide;

global ideVersion :: String = "0.0.1";

function getVersion
String ::= 
{
  return ideVersion;
}
