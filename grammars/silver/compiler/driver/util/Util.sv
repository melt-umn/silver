grammar silver:compiler:driver:util;

imports silver:compiler:definition:type;
imports silver:compiler:definition:env;
imports silver:util only contains, rem, makeSet, containsAny;

{--
 - Turns a grammar name into a path, including trailing slash.
 -}
function grammarToPath
String ::= g::String
{
  return substitute(":", "/", g) ++ "/";
}

