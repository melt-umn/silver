grammar core;

{--
 - Fold a list of strings into one string, by interspersing a separator.
 -
 - @param sep  The separator to place between each string.
 - @param lst  The list of string to collapse.
 - @return  The combined string.
 -}
function implode
String ::= sep::String lst::[String]
{
  return if null(lst)
         then ""
         else head(lst) ++ if null(tail(lst))
                           then ""
                           else sep ++ implode(sep, tail(lst));
}

{--
 - Split a string into a list of strings by a separator.
 -
 - @param sep  The separator between each of the resulting strings.
 - @param str  The original string.
 - @return  The list of strings separated by sep in the original string.
 -}
function explode
[String] ::= sep::String str::String
{
  local attribute i :: Integer;
  i = indexOf(sep, str);

  return if i == -1
         then [str]
         else [substring(0, i, str)] ++ explode(sep, substring(i+length(sep), length(str), str));
}

{--
 - String equality test.  Useful for some "...By" higher order functions.
 -}
function stringEq
Boolean ::= s1::String s2::String
{
  return s1 == s2;
}
{--
 - String <= test.  Useful for some "...By" higher order functions. (like sortBy)
 -}
function stringLte
Boolean ::= s1::String s2::String
{
  return s1 <= s2;
}


{--
 - Find the index of a needle in the haystack.  (Indices are 0-based.)
 -
 - @param needle  The string to find.
 - @param haystack  The string to find it in.
 - @return  The index the string occurs at, or -1 if not found.
 -}
function indexOf
Integer ::= needle::String haystack::String
{
  return error("Not Yet Implemented: indexOf");
} foreign {
  "java" : return "(new Integer((%haystack%.toString().indexOf(%needle%.toString()))))";
}

{--
 - Find the LAST index of a needle in the haystack.  (Indices are 0-based.)
 -
 - @param needle  The string to find.
 - @param haystack  The string to find it in.
 - @return  The index the string occurs at, or -1 if not found.
 -}
function lastIndexOf
Integer ::= needle::String haystack::String
{
  return error("Not Yet Implemented: lastIndexOf");
} foreign {
  "java" : return "(new Integer((%haystack%.toString().lastIndexOf(%needle%.toString()))))";
}

{--
 - Return a substring of the original.  Indices are 0-based.
 -
 - @param start  The 0-based index to start at. Inclusive.
 - @param endl  The 0-based index to end before.  (Exclusive.)
 - @param str  The original string.
 - @return  The resulting substring.
 -}
function substring
String ::= start::Integer endl::Integer str::String
{
  return error("Not Yet Implemented: substring");
} foreign {
  "java" : return "(new common.StringCatter(%str%.toString().substring(%start%, %endl%)))";
}

{--
 - Tests if one string is a prefix of another
 -
 - @param pre  The prefix.
 - @param s  The string to check the prefix of.
 - @return  true if pre is a prefix of s.  false otherwise.
 -}
function startsWith
Boolean ::= pre::String s::String
{
  return error("Not Yet Implemented: startsWith");
} foreign {
  "java" : return "(new Boolean(%s%.toString().startsWith(%pre%.toString())))";
}

{--
 - Tests if one string is a postfix of another
 -
 - @param post  The postfix.
 - @param s  The string to check the postfix of.
 - @return  true if post is a postfix of s.  false otherwise.
 -}
function endsWith
Boolean ::= post::String s::String
{
  return error("Not Yet Implemented: endsWith");
} foreign {
  "java" : return "(new Boolean(%s%.toString().endsWith(%post%.toString())))";
}

{--
 - Tests if all characters of a string are digits.  Partially unicode aware.
 - See java's Character.isDigit(char).
 -
 - @param str  The string to check
 - @return  true if all characters are digits.  false otherwise.
 -}
function isDigit
Boolean ::= str::String
{
  return error("Not Yet Implemented: isDigit");
} foreign {
  "java" : return "(common.Util.isDigit(%str%.toString()))";
}

{--
 - Tests if all characters of a string are letters.  Partially unicode aware.
 - See java's Character.isLetter(char).
 -
 - @param str  The string to check
 - @return  true if all characters are letters.  false otherwise.
 -}
function isAlpha
Boolean ::= str::String
{
  return error("Not Yet Implemented: isAlpha");
} foreign {
  "java" : return "(common.Util.isAlpha(%str%.toString()))";
}

{--
 - Tests if all characters of a string are whitespace.  Partially unicode aware.
 - See java's Character.isWhitespace(char).
 -
 - Includes space, tab, newline, carriage return, and more.
 -
 - @param str  The string to check
 - @return  true if all characters are whitespace.  false otherwise.
 -}
function isSpace
Boolean ::= str::String
{
  return error("Not Yet Implemented: isSpace");
} foreign {
  "java" : return "(common.Util.isSpace(%str%.toString()))";
}

{--
 - Tests if all characters of a string are lower case.  Partially unicode aware.
 - See java's Character.isLowerCase(char).
 -
 - @param str  The string to check
 - @return  true if all characters are lower case.  false otherwise.
 -}
function isLower
Boolean ::= str::String
{
  return error("Not Yet Implemented: isLower");
} foreign {
  "java" : return "(common.Util.isLower(%str%.toString()))";
}

{--
 - Tests if all characters of a string are upper case.  Partially unicode aware.
 - See java's Character.isUpperCase(char).
 -
 - @param str  The string to check
 - @return  true if all characters are upper case.  false otherwise.
 -}
function isUpper
Boolean ::= str::String
{
  return error("Not Yet Implemented: isUpper");
} foreign {
  "java" : return "(common.Util.isUpper(%str%.toString()))";
}

function stringConcat
String ::= s1::String s2::String
{ return s1 ++ s2 ; }
