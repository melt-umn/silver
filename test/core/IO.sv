grammar core;

--synthesized attribute io :: IO;
synthesized attribute ioval<`a> :: `a;

nonterminal IOVal<`a> with io, ioval<`a>;

abstract production ioval
top::IOVal<`a> ::= i::IO v::`a
{
  top.io = i;
  top.ioval = v;
}


------ IO Actions:

function print
IO ::= s::String i::IO
{
  return error("Not Yet Implemented: print");
} foreign {
  "java" : return "(common.Util.io(%i%, common.Util.print(%s%.toString())))";
}

function exit
IO ::= val::Integer i::IO
{
  return error("Not Yet Implemented: exit");
} foreign {
  "java" : return "(common.Util.io(%i%, common.Util.exit(%val%.intValue())))";
}

function mkdir
IOBoolean ::= s::String i::IO
{
  return error("Not Yet Implemented: mkdir");
} foreign {
  "java" : return "(new core.PioBoolean(%i%, common.Util.mkdir(%s%.toString())))";
}

function system
IOInteger ::= s::String i::IO
{
  return error("Not Yet Implemented: system");
} foreign {
  "java" : return "(new core.PioInteger(%i%, common.Util.system(%s%.toString())))";
}

function writeFile
IO ::= file::String contents::String i::IO
{
  return error("Not Yet Implemented: writeFile");
} foreign {
  "java" : return "(common.Util.io(%i%, common.Util.writeFile(%file%.toString(), %contents%)))";
}

function appendFile
IO ::= file::String contents::String i::IO
{
  return error("Not Yet Implemented: appendFile");
} foreign {
  "java" : return "(common.Util.io(%i%, common.Util.appendFile(%file%.toString(), %contents%)))";
}

------- IO Read Actions:

function fileTime
IOInteger ::= s::String i::IO
{
  return error("Not Yet Implemented: fileTime");
} foreign {
  "java" : return "(new core.PioInteger(%i%, common.Util.fileTime(%s%.toString())))";
}

function isFile
IOBoolean ::= s::String i::IO
{
  return error("Not Yet Implemented: isFile");
} foreign {
  "java" : return "(new core.PioBoolean(%i%, common.Util.isFile(%s%.toString())))";
}

function isDirectory
IOBoolean ::= s::String i::IO
{
  return error("Not Yet Implemented: isDirectory");
} foreign {
  "java" : return "(new core.PioBoolean(%i%, common.Util.isDirectory(%s%.toString())))";
}

function readFile
IOString ::= s::String i::IO
{
  return error("Not Yet Implemented: readFile");
} foreign {
  "java" : return "(new core.PioString(%i%, common.Util.readFile(%s%.toString())))";
}

function cwd
IOString ::= i::IO
{
  return error("Not Yet Implemented: cwd");
} foreign {
  "java" : return "(new core.PioString(%i%, common.Util.cwd()))";
}

function envVar
IOString ::= s::String i::IO
{
  return error("Not Yet Implemented: envVar");
} foreign {
  "java" : return "(new core.PioString(%i%, common.Util.env(%s%.toString())))";
}

function listContents
IOStringList ::= s::String i::IO
{
  return error("Not Yet Implemented: listContents");
} foreign {
  "java" : return "(new core.PioStringList(%i%, common.Util.listContents(%s%.toString())))";
}

------ IO Misc.

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
  "java" : return "(common.Util.genInt())";
}

function unsafeTrace
`a ::= val::`a act::IO
{
  return error("Not Yet Implemented: unsafeTrace");
} foreign {
  "java" : return "(%act%==null?%val%:null)"; -- This isn't the best way to do this...
}

