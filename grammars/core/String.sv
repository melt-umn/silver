grammar core;


function implode
String ::= sep::String lst::[String]
{
  return if null(lst)
         then ""
         else head(lst) ++ if null(tail(lst))
                           then ""
                           else sep ++ implode(sep, tail(lst));
}
function explode
[String] ::= sep::String str::String
{
  local attribute i :: Integer;
  i = indexOf(sep, str);

  return if i == -1
         then [str]
         else [substring(0, i, str)] ++ explode(sep, substring(i+length(sep), length(str), str));
}

function stringEq -- useful since we don't have sections in Silver. 
Boolean ::= s1::String s2::String
{
  return s1 == s2;
}
function stringLte -- useful since we don't have sections in Silver. 
Boolean ::= s1::String s2::String
{
  return s1 <= s2;
}

------ String manipulation functions

function indexOf
Integer ::= needle::String haystack::String
{
  return error("Not Yet Implemented: indexOf");
} foreign {
  "java" : return "(new Integer((%haystack%.toString().indexOf(%needle%.toString()))))";
}

function lastIndexOf
Integer ::= needle::String haystack::String
{
  return error("Not Yet Implemented: lastIndexOf");
} foreign {
  "java" : return "(new Integer((%haystack%.toString().lastIndexOf(%needle%.toString()))))";
}

function substring
String ::= start::Integer endl::Integer str::String
{
  return error("Not Yet Implemented: substring");
} foreign {
  "java" : return "(new common.StringCatter(%str%.toString().substring(%start%, %endl%)))";
}

function startsWith
Boolean ::= pre::String s::String
{
  return error("Not Yet Implemented: startsWith");
} foreign {
  "java" : return "(new Boolean(%s%.toString().startsWith(%pre%.toString())))";
}

function endsWith
Boolean ::= post::String s::String
{
  return error("Not Yet Implemented: endsWith");
} foreign {
  "java" : return "(new Boolean(%s%.toString().endsWith(%post%.toString())))";
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

