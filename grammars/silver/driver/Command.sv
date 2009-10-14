grammar silver:driver;
import silver:util:command;
import silver:util;

synthesized attribute searchPath :: String;
attribute searchPath occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("-I", true)];
  uses <- ["\t-I <path> path to grammars\n"];

  local attribute fs :: [String];
  fs = makePaths(stripFlagChunks(findFlag("-I", top.flags)));

  top.searchPath = if null(fs) then "./" else folds(":", (fs));
}

function makePaths
[String] ::= l::[String]{
  return if null(l) then [] else [makePath(head(l))] ++ makePaths(tail(l));
}

function makePath
String ::= s::String{
  return s ++ (if substring(length(s)-1, length(s), s) != "/" then  "/" else "");
}

function stripFlagChunks
[String] ::= l::[Flag]{
  return if null(l) then [] else [head(l).chunk] ++ stripFlagChunks(tail(l));
}


synthesized attribute outName :: String;
attribute outName occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("-o", true)];
  uses <- ["\t-o <file> name of binary file\n"];

  local attribute fs1 :: [Flag];
  fs1 = findFlag("-o", top.flags);

  top.outName = if null(fs1) then "" else head(fs1).chunk;
}

synthesized attribute displayVersion :: Boolean;
attribute displayVersion occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("-v", false)];
  uses <- ["\t-v display version\n"];

  top.displayVersion = !null(findFlag("-v", top.flags));
}


synthesized attribute doClean :: Boolean;
attribute doClean occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("--clean", false)];
  uses <- ["\t--clean overwrite interface files\n"];

  top.doClean = !null(findFlag("--clean", top.flags));
}

synthesized attribute genLocation :: String;
attribute genLocation occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("-G", true)];
  uses <- ["\t-G <path> Location to store generate files (SILVER_GEN)\n"];

  local attribute fs2 :: [Flag];
  fs2 = findFlag("-G", top.flags);

  top.genLocation = if null(fs2) then "" else head(fs2).chunk;
}

