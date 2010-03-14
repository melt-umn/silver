grammar fftest;


function customPrint
IO ::= s::String i::IO
{
  return error("Not Yet Implemented: customPrint");
} foreign {
  "java" : return "(common.Util.io(%i%, common.Util.print(%s%.toString())))";
}

-- Expose Java's String.replaceAll function that does regex-based string replacement
function replaceAllRegex
String ::= regex::String replacement::String original::String
{
  return error("NYI");
} foreign {
  "java": return "new common.StringCatter(%original%.toString().replaceAll(%regex%.toString(), %replacement%.toString()))";
}

function main
IO ::= args::String i::IO
{
  local attribute ref :: Function( IO ::= String IO);
  ref = customPrint;

  return ref( replaceAllRegex("[a-z]*", "", "Hi This String Should JUST Be CAPITALS!\n"), i);
}
