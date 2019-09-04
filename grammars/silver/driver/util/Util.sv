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

{--
 - Takes a grammar name (already converted to a path) and searches the grammar
 - path for the first directory that matches.
 -}
function findGrammarLocation
IOVal<Maybe<String>> ::= path::String searchPaths::[String] iIn::IO
{
  local exists :: IOVal<Maybe<String>> =
    findGrammarInLocation(path, head(searchPaths), iIn);

  return 
    if null(searchPaths) then ioval(iIn, nothing())
    else if exists.iovalue.isJust then exists
    else findGrammarLocation(path, tail(searchPaths), exists.io);
}

{--
 - Looks to see if the grammar can be found in 'inPath'
 - Tries (in order) for edu:umn:cs
 - edu/umn/cs/
 - edu.umn/cs/
 - edu.umn.cs/
 -}
function findGrammarInLocation
IOVal<Maybe<String>> ::= gram::String inPath::String iIn::IO
{
  -- Find the first / in the grammar name (turned path) we're looking for.
  local idx :: Integer = indexOf("/", gram);
  
  -- Replace the first / with a .
  local nextGram :: String = substring(0, idx, gram) ++ "." ++ substring(idx + 1, length(gram), gram);
  
  local exists :: IOVal<Boolean> = isDirectory(inPath ++ gram, iIn);
  
  return 
    if idx == -1 then ioval(iIn, nothing())
    else if exists.iovalue then ioval(exists.io, just(inPath ++ gram))
    else findGrammarInLocation(nextGram, inPath, exists.io);
}

