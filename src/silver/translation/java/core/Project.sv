grammar silver:translation:java:core;

import silver:util;

function makeName
String ::= str::String
{
  return substitute(".", ":", str);
}

function makeClassName
String ::= s::String{
  return makeClassNameHelp(split(":", s));
}

function makeParserName
String ::= s::String{
  return"Parser_" ++ substitute("_", ":", s);
}

function makeClassNameHelp
String ::= s::[String]{
  return if null(s) then "" else if null(tail(s)) then "P" ++ head(s) else (head(s) ++ "." ++ makeClassNameHelp(tail(s)));
}
