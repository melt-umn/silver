grammar core;

synthesized attribute io :: IO;
synthesized attribute iovalue<a> :: a;

nonterminal IOVal<a> with io, iovalue<a>;

abstract production ioval
top::IOVal<a> ::= i::IO v::a
{
  top.io = i;
  top.iovalue = v;
}


------ IO Actions:

function print
IO ::= s::String i::IO
{
  return error("Not Yet Implemented: print");
} foreign {
  "java" : return "common.Util.io(%i%, common.Util.print(%s%.toString()))";
}

function exit
IO ::= val::Integer i::IO
{
  return error("Not Yet Implemented: exit");
} foreign {
  "java" : return "common.Util.io(%i%, common.Util.exit(%val%.intValue()))";
}

function mkdir
IOVal<Boolean> ::= s::String i::IO
{
  return error("Not Yet Implemented: mkdir");
} foreign {
  "java" : return "new core.Pioval(%i%, common.Util.mkdir(%s%.toString()))";
}

function system
IOVal<Integer> ::= s::String i::IO
{
  return error("Not Yet Implemented: system");
} foreign {
  "java" : return "new core.Pioval(%i%, common.Util.system(%s%.toString()))";
}

function writeFile
IO ::= file::String contents::String i::IO
{
  return error("Not Yet Implemented: writeFile");
} foreign {
  "java" : return "common.Util.io(%i%, common.Util.writeFile(%file%.toString(), %contents%))";
}

function appendFile
IO ::= file::String contents::String i::IO
{
  return error("Not Yet Implemented: appendFile");
} foreign {
  "java" : return "common.Util.io(%i%, common.Util.appendFile(%file%.toString(), %contents%))";
}

------- IO Read Actions:

function fileTime
IOVal<Integer> ::= s::String i::IO
{
  return error("Not Yet Implemented: fileTime");
} foreign {
  "java" : return "new core.Pioval(%i%, common.Util.fileTime(%s%.toString()))";
}

function isFile
IOVal<Boolean> ::= s::String i::IO
{
  return error("Not Yet Implemented: isFile");
} foreign {
  "java" : return "new core.Pioval(%i%, common.Util.isFile(%s%.toString()))";
}

function isDirectory
IOVal<Boolean> ::= s::String i::IO
{
  return error("Not Yet Implemented: isDirectory");
} foreign {
  "java" : return "new core.Pioval(%i%, common.Util.isDirectory(%s%.toString()))";
}

function readFile
IOVal<String> ::= s::String i::IO
{
  return error("Not Yet Implemented: readFile");
} foreign {
  "java" : return "new core.Pioval(%i%, common.Util.readFile(%s%.toString()))";
}

function cwd
IOVal<String> ::= i::IO
{
  return error("Not Yet Implemented: cwd");
} foreign {
  "java" : return "new core.Pioval(%i%, common.Util.cwd())";
}

function envVar
IOVal<String> ::= s::String i::IO
{
  return error("Not Yet Implemented: envVar");
} foreign {
  "java" : return "new core.Pioval(%i%, common.Util.env(%s%.toString()))";
}

function listContents
IOVal<[String]> ::= s::String i::IO
{
  return error("Not Yet Implemented: listContents");
} foreign {
  "java" : return "new core.Pioval(%i%, common.Util.listContents(%s%.toString()))";
}

------ IO Misc.

function error
a ::= msg::String
{
  return error("Not Yet Implemented: error"); -- lol
} foreign {
  "java" : return "common.Util.error(%msg%.toString())";
}

function unsafeIO
IO ::= 
{
  return error("Not Yet Implemented: unsafeIO");
} foreign {
  "java" : return "null";
}

function genInt
Integer ::= 
{
  return error("Not Yet Implemented: genInt");
} foreign {
  "java" : return "common.Util.genInt()";
}

function unsafeTrace
a ::= val::a act::IO
{
  return error("Not Yet Implemented: unsafeTrace");
} foreign {
  "java" : return "(%act%==null?%val%:null)"; -- This isn't the best way to do this...
}

