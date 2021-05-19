grammar silver:core;

@@{-

The silver IO system is a IO-token based system.

# IO Types -}

@{--
 - The type representing the world-state token of an IO action.
 -}
synthesized attribute io :: IO;

@{--
 - The resulting value of an IO action.
 -}
synthesized attribute iovalue<a> :: a;

@{--
 - A container for the results of IO actions.
 -
 - @param a  The type of value returned by the IO action.
 -}
nonterminal IOVal<a> with io, iovalue<a>;

@{--
 - The sole constructor of IOVal results.
 -
 - @param i  The resulting world-state token.
 - @param v  The resulting value.
 -}
abstract production ioval
top::IOVal<a> ::= i::IO v::a
{
  top.io = i;
  top.iovalue = v;
}

@{--
 - IO is the IO Token used to sequence actions.
 -}
type IO foreign = "common.IOToken";

@@{- # IO Actions -}

@{--
 - Displays a string on standard out. Newlines are NOT automatically added.
 -
 - @param s  The string to print.
 - @param i  The "before" world-state token.
 - @return  The "after" world-state token.
 -}
function print
IO ::= s::String i::IO
{
  return error("Not Yet Implemented: print");
} foreign {
  "java" : return "%i%.print(%s%)";
}

@{--
 - Read a line from standard input.
 -
 - @param i  The "before" world-state token.
 - @return  The "after" world-state token.
 -}
function readLineStdin
IOVal<String> ::= i::IO
{
  return error ("Not Yet Implemented: getStr");
} foreign {
  "java" : return "%i%.readLineStdin()";
}

@{--
 - Terminates with the specified error code.
 -
 - @warning Never returns! Does not do any cleanup!
 -
 - @param val  The error code to terminate with. (0 is considered "success")
 - @param i  The "before" world-state token.
 - @return  Does not actually return!
 -}
function exit
IO ::= val::Integer i::IO
{
  return error("Not Yet Implemented: exit");
} foreign {
  "java" : return "%i%.exit(%val%)";
}

@{--
 - Creates a directory, including any parents that need to be created along the way.
 - Similar to 'mkdir -p'. If it fails, it may create only some of them.
 -
 - @param s  The path to create.
 - @param i  The "before" world-state token.
 - @return  true if completely successful.  false if an error occurred along the way.
 -}
function mkdir
IOVal<Boolean> ::= s::String i::IO
{
  return error("Not Yet Implemented: mkdir");
} foreign {
  "java" : return "%i%.mkdir(%s%)";
}

@{--
 - Executes a shell command.  Specifically executes 'bash -c'.  So, not fully cross-platform.
 -
 - @warning Avoid using this if possible.  If you need an IO action not present, request it, please.
 -
 - Access to command's output is not directly available, but it is run in a shell. You can
 - redirect to a file and read that.
 -
 - @param s  The string for the shell to execute.
 - @param i  The "before" world-state token.
 - @return  The exit value of the subprocess.
 -}
function system
IOVal<Integer> ::= s::String i::IO
{
  return error("Not Yet Implemented: system");
} foreign {
  "java" : return "%i%.system(%s%)";
}

@{--
 - Write a string to a file, replacing whatever is there already.
 -
 - @param file  The filename to write to.
 - @param contents  The string to write to the file.
 - @param i  The "before" world-state token.
 - @return  The "after" world-state token.  May throw a java IO exception, which cannot be caught by Silver.
 -}
function writeFile
IO ::= file::String contents::String i::IO
{
  return error("Not Yet Implemented: writeFile");
} foreign {
  "java" : return "%i%.writeFile(%file%, %contents%)";
}

@{--
 - Append a string to a file.
 -
 - @param file  The filename to append to.
 - @param contents  The string to append to the file.
 - @param i  The "before" world-state token.
 - @return  The "after" world-state token.  May throw a java IO exception, which cannot be caught by Silver.
 -}
function appendFile
IO ::= file::String contents::String i::IO
{
  return error("Not Yet Implemented: appendFile");
} foreign {
  "java" : return "%i%.appendFile(%file%, %contents%)";
}

@{--
 - The time, in seconds since 1970, when this file (or directory) was last modified.
 -
 - @param s  The file to query.
 - @param i  The "before" world-state token.
 - @return  The modification time of this file. Or 0 if file was not found.
 -}
function fileTime
IOVal<Integer> ::= s::String i::IO
{
  return error("Not Yet Implemented: fileTime");
} foreign {
  "java" : return "%i%.fileTime(%s%)";
}

@{--
 - Checks if a file is an ordinary file.  (non-directory, non-special)
 -
 - @param s  The file to query.
 - @param i  The "before" world-state token.
 - @return  true if if the file is ordinary.  false otherwise.
 -}
function isFile
IOVal<Boolean> ::= s::String i::IO
{
  return error("Not Yet Implemented: isFile");
} foreign {
  "java" : return "%i%.isFile(%s%)";
}

@{--
 - Checks if a path is a directory.
 -
 - @param s  The path to query.
 - @param i  The "before" world-state token.
 - @return  true if if the exists and is a directory. false otherwise.
 -}
function isDirectory
IOVal<Boolean> ::= s::String i::IO
{
  return error("Not Yet Implemented: isDirectory");
} foreign {
  "java" : return "%i%.isDirectory(%s%)";
}

@{--
 - Read the entire contents of a file.  All instances of "\r\n" are replaced by "\n"
 - for compatibility reasons.
 -
 - @param s  The file to read.
 - @param i  The "before" world-state token.
 - @return  The contents of the file. May throw a java IO exception, which cannot be caught by Silver.
 -}
function readFile
IOVal<String> ::= s::String i::IO
{
  return error("Not Yet Implemented: readFile");
} foreign {
  "java" : return "%i%.readFile(%s%)";
}

@{--
 - Return the current working directory.
 -
 - @param i  The "before" world-state token.
 - @return  The current working directory of the process.
 -}
function cwd
IOVal<String> ::= i::IO
{
  return error("Not Yet Implemented: cwd");
} foreign {
  "java" : return "%i%.cwd()";
}

@{--
 - Obtain the value of an environment variable.
 -
 - @param s  The name of the environment variable to read.
 - @param i  The "before" world-state token.
 - @return  The variables string.  Empty string if the key doesn't exist.
 -}
function envVar
IOVal<String> ::= s::String i::IO
{
  return error("Not Yet Implemented: envVar");
} foreign {
  "java" : return "%i%.envVar(%s%)";
}

@{--
 - List the contents of a directory. Returns empty list if not a directory or
 - other IO error.
 -
 - @param s  The path to list the contents of.
 - @param i  The "before" world-state token.
 - @return  All files and directories in the named directory. Or [] on error.
 -}
function listContents
IOVal<[String]> ::= s::String i::IO
{
  return error("Not Yet Implemented: listContents");
} foreign {
  "java" : return "%i%.listContents(%s%)";
}

@{--
 - Delete a file, or an empty directory.
 -
 - @param s  The path to file to delete.
 - @param i  The "before" world-state token.
 - @return  true if the file is deleted successfully.  false otherwise.
 -}
function deleteFile
IOVal<Boolean> ::= s::String i::IO
{
  return error("Not Yet Implemented: deleteFile");
} foreign {
  "java" : return "%i%.deleteFile(%s%)";
}

@{--
 - Delete a set of files.
 -
 - @param s  The list of paths to files to delete.
 - @param i  The "before" world-state token.
 - @return  true if all files are deleted successfully.  false otherwise.
 -}
function deleteFiles
IOVal<Boolean> ::= s::[String] i::IO
{
  return error("Not Yet Implemented: deleteFiles");
} foreign {
  "java" : return "%i%.deleteFiles(%s%)";
}

@{--
 - Empty a directory of all normal files (i.e. leaving subdirectories alone)
 -
 - @param s  The path to the directory to empty
 - @param i  The "before" world-state token.
 - @return  true if contents are deleted successfully.  false otherwise.
 -}
function deleteDirFiles
IOVal<Boolean> ::= s::String i::IO
{
  return error("Not Yet Implemented: deleteDirFiles");
} foreign {
  "java" : return "%i%.deleteDirFiles(%s%)";
}

@{--
 - Delete a non-empty directory and all subdirectories and files.
 -
 - @param s  The path to the directory to delete
 - @param i  The "before" world-state token.
 - @return  The IO token. Errors (other than non-existence of the path) are fatal.
 -}
function deleteTree
IO ::= s::String  i::IO
{
  return error("Not Yet Implemented: deleteTree");
} foreign {
  "java" : return "%i%.deleteTree(%s%)";
}

@{--
 - Copy a file from src to dst.
 -
 - @param src  The path of the file to copy.
 - @param dst  The path of the file to write, or the directory to copy the file to.
 - @param i  The "before" world-state token.
 - @return  the IO token. Errors are fatal.
 -}
function copyFile
IO ::= src::String  dst::String  i::IO
{
  return error("Not Yet Implemented: copyFile");
} foreign {
  "java" : return "%i%.copyFile(%src%, %dst%)";
}

@{--
 - Update a file's modification time to the current time.
 - 
 - @param file  The file to update the modification time of.
 - @param i  The IO token.
 - @return The IO token. Errors are suppressed.
 -}
function touchFile
IO ::= file::String i::IO
{
  return error("Not Yet Implemented: touchFile");
} foreign {
  "java" : return "%i%.touchFile(%file%)";
}

@{--
 - Update a set of files' modification time to the current time.
 - 
 - @param files  The list of files to update the modification time of.
 - @param i  The IO token.
 - @return The IO token. Errors are suppressed.
 -}
function touchFiles
IO ::= files::[String] i::IO
{
  return error("Not Yet Implemented: touchFiles");
} foreign {
  "java" : return "%i%.touchFiles(%files%)";
}


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
 - Create a bogus world-state token, for use with @link[unsafeTrace].
 -
 - @return  A fake world-state token.
 -}
function unsafeIO
IO ::=
{
  return error("Not Yet Implemented: unsafeIO");
} foreign {
  "java" : return "common.IOToken.singleton";
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
 - Execute an IO action when a value is demanded by the Silver runtime.
 - When this gets executed may be unpredictable.
 -
 - @param val  The value to evaluate to, after the IO action is performed.
 - @param act  The world-state token to demand and consume.
 - @return  val, unchanged.
 - @warning see @link[unsafeIO]
 -}
function unsafeTrace
a ::= val::a act::IO
{
  return error("Not Yet Implemented: unsafeTrace");
} foreign {
  "java" : return "common.Util.io(%act%, %val%)";
}

@{--
 - Print string when a value is demanded by the Silver runtime.
 - When this gets executed may be unpredictable.
 -
 - @param val  The value to evaluate to, after the IO action is performed.
 - @param str  The string to print.
 - @return  val, unchanged.
 - @warning see @link[unsafeIO]
 -}
function unsafeTracePrint
a ::= val::a str::String
{
  return unsafeTrace(val, print(str, unsafeIO()));
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
  return unsafeTrace(val, print(hackUnparse(val), unsafeIO()));
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
  return if indexOfLastDot == -1 then pair(filePath, "")
         else pair(substring(0, indexOfLastDot, filePath) ,
                   substring(indexOfLastDot+1, length(filePath), filePath));

  local attribute indexOfLastDot :: Integer;
  indexOfLastDot = lastIndexOf(".", filePath);
}

