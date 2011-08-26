grammar silver:modification:ffi:util;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:modification:ffi;
import silver:util;

function substituteAll
String ::= s::String names::[String] results::[String]
{
  return if null(names) then s
         else substituteAll(substitute(head(names), head(results), s), tail(names), tail(results));
}

function wrapStrictNotation
String ::= s::String
{
  return "%" ++ s ++ "%";
}
function wrapLazyNotation
String ::= s::String
{
  return "%?" ++ s ++ "?%";
}

function cleanStringLexeme
String ::= s::String
{
  -- peel off outer quotes
  return cleanStringEscapes(substring(1,length(s)-1, s));
}

--TODO is this necessary? I... don't think it is.
function cleanStringEscapes
String ::= s::String
{
  local attribute i :: Integer;
  i = indexOf("\\", s);
  
  local attribute c :: String;
  c = substring(i+1, i+2, s);

  return if i == -1
         then s
         else substring(0, i, s) ++ 
              (if c == "n" then "\n"
               else if c == "t" then "\t"
               else c) ++
              cleanStringEscapes(substring(i+2, length(s), s));
}
