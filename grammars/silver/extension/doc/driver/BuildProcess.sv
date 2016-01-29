grammar silver:extension:doc:driver;

import silver:extension:doc:core;

import silver:driver;
import silver:definition:env;
import silver:definition:core;

import silver:util;
import silver:util:cmdargs;

synthesized attribute docGeneration :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.docGeneration = false;
}
abstract production docFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.docGeneration = true;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--doc", flag(docFlag))];
  flagdescs <- ["\t--doc  : build the documentation"];
}
aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  top.postOps <- if top.config.docGeneration then 
                 [genDoc(top.config, grammarsToTranslate, benv.silverGen)]
                 else []; 
}

abstract production genDoc
top::Unit ::= a::Decorated CmdArgs  specs::[Decorated RootSpec]  silverGen::String
{
  local pr :: IO = print("Generating Documentation.\n", top.ioIn);

  top.io = writeAll(pr, a, specs, silverGen);
  top.code = 0;
  top.order = 4;
}

function writeAll
IO ::= i::IO  a::Decorated CmdArgs  l::[Decorated RootSpec]  silverGen::String
{
  local now :: IO = writeSpec(i, head(l), silverGen);
  local recurse :: IO = writeAll(now, a, tail(l), silverGen);

  return if null(l) then i else recurse;
}

function writeSpec
IO ::= i::IO  r::Decorated RootSpec  silverGen::String
{
  local printio :: IO = print("\t[" ++ r.declaredName ++ "]\n", i);
  local path :: String = silverGen ++ "doc/" ++ grammarToPath(r.declaredName); 

  return writeFiles(path, r.genFiles, printio);
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