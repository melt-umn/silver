grammar silver:extension:doc:driver;

import silver:extension:doc:core;

import silver:driver;
import silver:definition:env;
import silver:definition:core;

import silver:util;
import silver:util:cmdargs;

synthesized attribute docGeneration :: Boolean occurs on CmdArgs;
synthesized attribute docOutOption :: [String] occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.docGeneration = false;
  top.docOutOption = [];
}
abstract production docFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.docGeneration = true;
  forwards to rest;
}
abstract production docOutFlag
top::CmdArgs ::= loc::String rest::CmdArgs
{
  top.docOutOption = loc :: rest.docOutOption;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--doc", flag(docFlag)),
            pair("--doc-out", option(docOutFlag))];
  flagdescs <- ["\t--doc     : build the documentation",
                "\t--doc-out : output location for documentation"];
}

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  local outputLoc::String =
    if null(top.config.docOutOption)
    then benv.silverGen
    else head(top.config.docOutOption) ++ "/"; -- TODO: check only one item for docOutOption is provided
  top.postOps <- if top.config.docGeneration then 
                 [genDoc(top.config, grammarsToTranslate, outputLoc)]
                 else [];
}

abstract production genDoc
top::Unit ::= a::Decorated CmdArgs  specs::[Decorated RootSpec]  outputLoc::String
{
  local pr :: IO = print("Generating Documentation.\n", top.ioIn);

  top.io = writeAll(pr, a, specs, outputLoc);
  top.code = 0;
  top.order = 4;
}

function writeAll
IO ::= i::IO  a::Decorated CmdArgs  l::[Decorated RootSpec]  outputLoc::String
{
  local now :: IO = writeSpec(i, head(l), outputLoc);
  local recurse :: IO = writeAll(now, a, tail(l), outputLoc);

  return if null(l) then i else recurse;
}

function writeSpec
IO ::= i::IO  r::Decorated RootSpec  outputLoc::String
{
  local path :: String = outputLoc ++ "doc/" ++ grammarToPath(r.declaredName);

  local mkiotest :: IOVal<Boolean> =
    isDirectory(path, i);
  
  local mkio :: IOVal<Boolean> =
    if mkiotest.iovalue
    then mkiotest
    else mkdir(path, mkiotest.io);
  
  local pr :: IO =
    if mkio.iovalue
    then print("\t[" ++ r.declaredName ++ "]\n", mkio.io)
    else exit(-5, print("\nUnrecoverable Error: Unable to create directory: " ++ path ++ "\n\n", mkio.io));
  
  local rm :: IO = deleteStaleDocs(pr, outputLoc, r.declaredName);

  local wr :: IO = writeFiles(path, r.genFiles, rm);

  return wr;
}

{--
 - Given a path (with terminating /) and list of (file names relative to that root, contents),
 - write these out.
 -}
function writeFiles
IO ::= path::String s::[Pair<String String>] i::IO
{
  return if null(s) then i else writeFile(path ++ head(s).fst, head(s).snd, writeFiles(path, tail(s), i));
}

-- Copied from 
function deleteStaleDocs
IO ::= iIn::IO outputLoc::String gram::String
{
  local docPath :: String = outputLoc ++ "doc/" ++ grammarToPath(gram);
  local docFiles :: IOVal<[String]> = listContents(docPath, iIn);
  
  return deleteStaleDataFiles(docFiles.io, docPath, docFiles.iovalue);
         
}
