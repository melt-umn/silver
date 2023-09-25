grammar silver:core;

@@{-

The miscellaneous IO functions.

-}

@@{- # IO Misc -}

@{--
 - Die with the stated error message and a stack trace.  Note that Silver stacks
 - may be hard to read (it's a lazy language.)
 -
 - @warning Does not return! Does not do any cleanup!
 -
 - @param msg  The path to list the contents of.
 - @return  Does not return.
 -}
function error
a ::= msg::String
{
  return error("Not Yet Implemented: error"); -- lol
} foreign {
  "java" : return "common.Util.error(%msg%.toString())";
}

@{--
 - Generate an integer unique to this run of this process.  Starts from 0 and just
 - counts up each call.
 -
 - @return  An integer unique to this process.
 -}
function genInt
Integer ::=
{
  return error("Not Yet Implemented: genInt");
} foreign {
  "java" : return "common.Util.genInt()";
}

@{--
 - Generates a random number between [0, 1)
 -}
function genRand
Float ::=
{
  return error("Not Yet Implemented: genRand");
} foreign {
  "java" : return "((float)Math.random())";
}

@{--
 - Print string when a value is demanded by the Silver runtime.
 - When this gets executed may be unpredictable.
 -
 - @param val  The value to evaluate to, after the IO action is performed.
 - @param str  The string to print.
 - @return val, unchanged.
 - @warning see @link[unsafeIO]
 -}
function unsafeTracePrint
a ::= val::a str::String
{
  return unsafeTrace(val, printT(str, unsafeIO()));
}

@{--
 - Print a stringification of a value when it is demanded by the Silver runtime.
 - When this gets executed may be unpredictable.
 -
 - @param val  The value to evaluate to, printed when evaluated.
 - @return  val, unchanged.
 - @warning see @link[unsafeIO]
 -}
function unsafeTraceDump
a ::= val::a
{
  return unsafeTrace(val, printlnT(hackUnparse(val), unsafeIO()));
}



-- Function for manipulating strings representing file and directory names.

function dirNameInFilePath
String ::= filePath::String
{
  return if indexOfLastSlash == -1 then filePath
         else substring(0, indexOfLastSlash, filePath);

  local attribute indexOfLastSlash :: Integer;
  indexOfLastSlash = lastIndexOf("/", filePath);
}

function fileNameInFilePath
String ::= filePath::String
{
  return if indexOfLastSlash == -1 then filePath
         else substring(indexOfLastSlash+1, length(filePath), filePath);

  local attribute indexOfLastSlash :: Integer;
  indexOfLastSlash = lastIndexOf("/", filePath);
}


function splitFileNameAndExtension
Pair<String String> ::= filePath::String
{
  return if indexOfLastDot == -1 then (filePath, "")
         else (substring(0, indexOfLastDot, filePath) ,
                   substring(indexOfLastDot+1, length(filePath), filePath));

  local attribute indexOfLastDot :: Integer;
  indexOfLastDot = lastIndexOf(".", filePath);
}
