grammar silver:driver:util;

imports silver:definition:env;
imports silver:util only contains, rem, makeSet, containsAny;

{--
 - Turns a grammar name into a path, including trailing slash.
 -}
function grammarToPath
String ::= g::String
{
  return substitute(":", "/", g) ++ "/";
}

