grammar silver:translation:java:core;

import silver:util;

function makeName
String ::= str::String
{
  return substitute(".", ":", str);
}

function makeClassName
String ::= s::String{
  return makeClassNameHelp(split(":", s), "P");
}

function makeNTClassName
String ::= s::String {
  return makeClassNameHelp(split(":", s), "N");
}

function makeParserName
String ::= s::String{
  return"Parser_" ++ substitute("_", ":", s);
}

function makeClassNameHelp
String ::= s::[String] prfix::String{
  return if null(s) then "" else if null(tail(s)) then prfix ++ head(s) else (head(s) ++ "." ++ makeClassNameHelp(tail(s), prfix));
}

