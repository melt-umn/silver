grammar silver:modification:ffi:util;

import silver:definition:core;
import silver:definition:env;
import silver:modification:ffi;
import silver:util;

function percentSubst
String ::= s::String names::[String] results::[String]
{
  return if null(names) then s
         else percentSubst(substitute(head(results), "%" ++ head(names) ++ "%", s), tail(names), tail(results));
}

function mapSignature
[String] ::= f::Function(String ::= Decorated TypeRep String String) ns::[Decorated NamedSignatureElement] extra::String
{
  return if null(ns) then [] else f(head(ns).typerep, head(ns).elementName, extra) :: mapSignature(f, tail(ns), extra);
}

function cleanStringLexeme
String ::= s::String
{
  -- peel off outer quotes
  return cleanStringEscapes(substring(1,length(s)-1, s));
}

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
