grammar silver:compiler:modification:ffi:util;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:type;
import silver:compiler:modification:ffi;

fun substituteAll String ::= s::String names::[String] results::[String] =
  if null(names) then s
  else substituteAll(substitute(head(names), head(results), s), tail(names), tail(results));

fun wrapStrictNotation String ::= s::String = "%" ++ s ++ "%";
fun wrapLazyNotation String ::= s::String = "%?" ++ s ++ "?%";
fun wrapContextNotation String ::= i::Integer = "%@" ++ toString(i) ++ "@%";

fun cleanStringLexeme String ::= s::String = cleanStringEscapes(substring(1,length(s)-1, s));

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
