grammar silver:compiler:translation:java:core;

imports silver:compiler:translation:java:type;

imports silver:compiler:definition:core;
imports silver:compiler:definition:type:syntax;

imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:definition:flow:env;

imports silver:compiler:analysis:uniqueness;
imports silver:compiler:analysis:typechecking:core only finalType;

function makeName
String ::= str::String
{
  return substitute(":", ".", str);
}
function makeIdName
String ::= str::String
{
  return substitute(":", "_", str);
}

function makeProdName
String ::= s::String
{
  return substituteLast(".", ".P", makeName(s));
}

function makeNTName
String ::= s::String
{
  return substituteLast(".", ".N", makeName(s));
}

function makeAnnoName
String ::= s::String
{
  return substituteLast(".", ".A", makeName(s));
}

function makeTerminalName
String ::= s::String
{
  return substituteLast(".", ".T", makeName(s));
}

function makeParserName
String ::= s::String
{
  return "Parser_" ++ makeIdName(s);
}

function makeClassName
String ::= s::String
{
  return substituteLast(".", ".C", makeName(s));
}

function makeInstanceName
String ::= g::String s::String t::Type
{
  return substituteLast(".", ".I", makeName(g ++ ":" ++ substitute(":", "_", s))) ++ "_" ++ transTypeName(t);
}

function substituteLast
String ::= r::String s::String str::String
{
  local attribute i::Integer;
  i = lastIndexOf(r, str);
  
  return if i == -1 then str
         else substring(0,i,str) ++ s ++ substring(i+length(r), length(str), str);
}

