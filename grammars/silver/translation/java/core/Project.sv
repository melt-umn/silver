grammar silver:translation:java:core;

imports silver:translation:java:type;

imports silver:definition:core;
imports silver:definition:type:syntax;

imports silver:definition:env;
imports silver:definition:type;


import silver:util;

function makeName
String ::= str::String
{
  return substitute(".", ":", str);
}

function makeClassName
String ::= s::String
{
  return substituteLast(".P", ".", substitute(".", ":", s));
}

function makeNTClassName
String ::= s::String
{
  return substituteLast(".N", ".", substitute(".", ":", s));
}

function makeParserName
String ::= s::String
{
  return "Parser_" ++ substitute("_", ":", s);
}

function substituteLast
String ::= s::String r::String str::String
{
  local attribute i::Integer;
  i = lastIndexOf(r, str);
  
  return if i == -1 then str
         else substring(0,i,str) ++ s ++ substring(i+length(r), length(str), str);
}

