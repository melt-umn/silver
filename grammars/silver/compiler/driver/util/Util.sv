grammar silver:compiler:driver:util;

imports silver:compiler:definition:type;
imports silver:compiler:definition:env;

-- Note that CompileLiterateFile.sv.md needs to be updated too if this changes.
global allowedSilverFileExtensions::[String] = [".sv", ".ag", ".sv.md", ".ag.md"];

{--
 - Turns a grammar name into a path, including trailing slash.
 -}
function grammarToPath
String ::= g::String
{
  return substitute(":", "/", g) ++ "/";
}

@{--
 - Given a path (with terminating /) and list of (file names relative to that root, contents),
 - write these out.
 -}
function writeFiles
IO<()> ::= path::String s::[(String, String)]
{
  return traverse_(\ item::(String, String) -> writeFile(path ++ item.1, item.2), s);
}

function writeBinaryFiles
IO<()> ::= path::String s::[(String, ByteArray)]
{
  return traverse_(\ item::(String, ByteArray) -> writeBinaryFile(path ++ item.1, item.2), s);
}
