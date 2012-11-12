grammar silver:driver:util;

imports silver:driver only Unit, order, Interface, rSpec;
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

{--
 - Takes a grammar name (already converted to a path) and searches the grammar
 - path for the first directory that matches.
 -}
function findGrammarLocation
IOVal<Maybe<String>> ::= path::String searchPaths::[String] iIn::IO
{
  local attribute exists :: IOVal<Boolean>;
  exists = isDirectory(head(searchPaths) ++ path, iIn);

  return if null(searchPaths)
         then ioval(iIn, nothing())
         else if exists.iovalue
              then ioval(exists.io, just(head(searchPaths) ++ path))
              else findGrammarLocation(path, tail(searchPaths), exists.io);
}

