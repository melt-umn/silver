grammar core;

------ String manipulation functions

function indexOf
Integer ::= needle::String haystack::String
{
  return error("Not Yet Implemented: indexOf");
} foreign {
  "java" : return "(new Integer((%haystack%.toString().indexOf(%needle%.toString()))))";
}

function substring
String ::= start::Integer endl::Integer str::String
{
  return error("Not Yet Implemented: substring");
} foreign {
  "java" : return "(new common.StringCatter(%str%.toString().substring(%start%, %endl%)))";
}

function isDigit
Boolean ::= str::String
{
  return error("Not Yet Implemented: isDigit");
} foreign {
  "java" : return "(common.Util.isDigit(%str%.toString()))";
}

function isAlpha
Boolean ::= str::String
{
  return error("Not Yet Implemented: isAlpha");
} foreign {
  "java" : return "(common.Util.isAlpha(%str%.toString()))";
}

function isSpace
Boolean ::= str::String
{
  return error("Not Yet Implemented: isSpace");
} foreign {
  "java" : return "(common.Util.isSpace(%str%.toString()))";
}

function isLower
Boolean ::= str::String
{
  return error("Not Yet Implemented: isLower");
} foreign {
  "java" : return "(common.Util.isLower(%str%.toString()))";
}

function isUpper
Boolean ::= str::String
{
  return error("Not Yet Implemented: isUpper");
} foreign {
  "java" : return "(common.Util.isUpper(%str%.toString()))";
}

