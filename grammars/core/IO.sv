grammar core;

{--
 - The resulting world-state token of an IO action.
 -}
synthesized attribute io :: IO;
{--
 - The resulting value of an IO action.
 -}
synthesized attribute iovalue<a> :: a;

{--
 - A container for the results of IO actions.
 -
 - @param a  The type of value returned by the IO action.
 -}
nonterminal IOVal<a> with io, iovalue<a>;

{--
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


------ IO Actions:

{--
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
  "java" : return "common.Util.io(%i%, common.Util.print(%s%.toString()))";
}

{--
 - Terminates with the specified error code.
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
  "java" : return "common.Util.io(%i%, common.Util.exit(%val%.intValue()))";
}

{--
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
  "java" : return "new core.Pioval(%i%, common.Util.mkdir(%s%.toString()))";
}

{--
 - Executes a shell command.  ONLY WORKS ON LINUX (or rather, doesn't work on windows.)
 - Specifically executes 'bash -c'. 
 -
 - Avoid using this if possible.  If you need an IO action not present, request it, please.
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
  "java" : return "new core.Pioval(%i%, common.Util.system(%s%.toString()))";
}

{--
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
  "java" : return "common.Util.io(%i%, common.Util.writeFile(%file%.toString(), %contents%))";
}

{--
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
  "java" : return "common.Util.io(%i%, common.Util.appendFile(%file%.toString(), %contents%))";
}

------- IO Read Actions:

{--
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
  "java" : return "new core.Pioval(%i%, common.Util.fileTime(%s%.toString()))";
}

{--
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
  "java" : return "new core.Pioval(%i%, common.Util.isFile(%s%.toString()))";
}

{--
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
  "java" : return "new core.Pioval(%i%, common.Util.isDirectory(%s%.toString()))";
}

{--
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
  "java" : return "new core.Pioval(%i%, common.Util.readFile(%s%.toString()))";
}

{--
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
  "java" : return "new core.Pioval(%i%, common.Util.cwd())";
}

{--
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
  "java" : return "new core.Pioval(%i%, common.Util.env(%s%.toString()))";
}

{--
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
  "java" : return "new core.Pioval(%i%, common.Util.listContents(%s%.toString()))";
}

{--
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
  "java" : return "new core.Pioval(%i%, common.Util.deleteFile(%s%.toString()))";
}

------ IO Misc.

{--
 - Die with the stated error message and a stack trace.  Note that Silver stacks
 - may be hard to read (it's a lazy language.)
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

{--
 - Create a bogus world-state token, for use with unsafeTrace.
 -
 - @return  A fake world-state token.
 - @see unsafeTrace
 -}
function unsafeIO
IO ::= 
{
  return error("Not Yet Implemented: unsafeIO");
} foreign {
  "java" : return "null";
}

{--
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

{--
 - Execute an IO action when a value is demanded by the Silver runtime.
 - When this gets executed may be unpredictable.
 -
 - @param val  The value to evaluate to, after the IO action is performed.
 - @param act  The world-state token to demand and consume.
 - @return  val, unchanged.
 - @see unsafeIO
 -}
function unsafeTrace
a ::= val::a act::IO
{
  return error("Not Yet Implemented: unsafeTrace");
} foreign {
  "java" : return "(%act%==null?%val%:null)"; -- This isn't the best way to do this...
}



-- Function for manipulating strings representing file and directory names.

function dirNameInFilePath
String ::= filePath::String
{
 return if   indexOfLastSlash == -1 -- slash not found
        then filePath
        else substring(0, indexOfLastSlash, filePath) ;

 local attribute indexOfLastSlash :: Integer ;
 indexOfLastSlash = lastIndexOf("/", filePath) ;
}

function fileNameInFilePath
String ::= filePath::String
{
 return if   indexOfLastSlash == -1 -- slash not found
        then filePath
        else substring(indexOfLastSlash+1, length(filePath), filePath) ;

 local attribute indexOfLastSlash :: Integer ;
 indexOfLastSlash = lastIndexOf("/", filePath) ;
}


function splitFileNameAndExtension
Pair<String String> ::= filePath::String
{
 return if   indexOfLastDot == -1 -- dot not found
        then pair( filePath, "" )
        else pair( substring(0, indexOfLastDot, filePath) ,
                   substring(indexOfLastDot+1, length(filePath), filePath) ) ;

 local attribute indexOfLastDot :: Integer ;
 indexOfLastDot = lastIndexOf(".", filePath) ;
}
