grammar silver:driver;
import silver:definition:core;
import silver:definition:env;
import silver:util;
import silver:util:command;


--units of work.
nonterminal Unit with ioIn, io, code, order;
synthesized attribute code :: Integer;
synthesized attribute order :: Integer;

aspect production run
top::RunUnit ::= iIn::IO args::String
{
  preOps <- if a.displayVersion then [printVersion()] else [::Unit];
  postOps <- [doInterfaces(spath, unit.compiledList ++ reUnit.compiledList, needRecompileNames, a.doClean)];
}

abstract production printVersion
top::Unit ::= 
{
  top.order = 0;
  top.io = print("Silver Version 0.2\n", top.ioIn);
  top.code = -1;
}



abstract production doInterfaces
top::Unit ::= sPath::[String] u::[Decorated RootSpec] rc::[String] clean::Boolean
{
  top.order = 3;
  top.io = writeInterfaces(top.ioIn, u, "Silver.svi", sPath, rc, clean);
  top.code = 0;
}

function writeInterfaces
IO ::= iIn::IO r::[Decorated RootSpec] f::String sPath::[String] rc::[String] clean::Boolean{
  return if null(r) then iIn else writeInterfaces(writeInterface(iIn, head(r), f, sPath, rc, clean), tail(r), f, sPath, rc, clean);
}

function writeInterface
IO ::= iIn::IO r::Decorated RootSpec f::String sPath::[String] rc::[String] clean::Boolean{ 

  --the grammar path ':' replaced by '/'
  local attribute gPath :: String;
  gPath = getGrammarPath(r.impliedName) ++ "/";

  -- the location (if found) of the grammar
  local attribute grammarLocation :: MaybeIOStr;
  grammarLocation = findGrammarLocation(iIn, gPath, sPath);

  -- the list of files from the grammar directory
  local attribute temp_files :: IOStringList;
  temp_files = listContents(grammarLocation.sValue, grammarLocation.io);

  -- the list of silver files for the grammar
  local attribute files :: [String];
  files = filterFiles(convert(temp_files.stringList));

  local attribute hasInterface :: IOBoolean;
  hasInterface = isValidInterface(temp_files.io, "Silver.svi", grammarLocation.sValue, files);

  return if clean || !hasInterface.bValue || contains(r.impliedName, rc)
	 then writeFile(grammarLocation.sValue ++ f, r.unparse, print("Writing interface for grammar " ++ r.impliedName ++ "\n\t[" ++ grammarLocation.sValue ++ f ++ "]\n", hasInterface.io))
	 else hasInterface.io;
}
