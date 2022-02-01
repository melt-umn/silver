grammar silver:compiler:extension:doc:driver;

import silver:compiler:extension:doc:core;

import silver:compiler:driver;
import silver:compiler:definition:env;
import silver:compiler:definition:core;

import silver:util:cmdargs;

synthesized attribute docGeneration :: Boolean occurs on CmdArgs;
synthesized attribute printUndoc :: Boolean occurs on CmdArgs;
synthesized attribute countUndoc :: Boolean occurs on CmdArgs;
synthesized attribute parseDocs :: Boolean occurs on CmdArgs;
synthesized attribute docOutOption :: Maybe<String> occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.docGeneration = false;
  top.printUndoc = false;
  top.countUndoc = false;
  top.parseDocs = false;
  top.docOutOption = nothing();
}

abstract production docFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.docGeneration = true;
  top.parseDocs = true;
  forwards to rest;
}

abstract production printUndocFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.printUndoc = true;
  top.parseDocs = true;
  forwards to rest;
}

abstract production countUndocFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.countUndoc = true;
  top.parseDocs = true;
  forwards to rest;
}

abstract production docOutFlag
top::CmdArgs ::= loc::String rest::CmdArgs
{
  top.docOutOption = case rest.docOutOption of
    | nothing() -> just(loc)
    | _ -> error("Duplicate arguments for docOutOption")
  end;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--doc", flag(docFlag)),
            pair("--print-undoc", flag(printUndocFlag)),
            pair("--count-undoc", flag(countUndocFlag)),
            pair("--doc-out", option(docOutFlag))];
  flagdescs <- ["\t--doc       : build the documentation",
                "\t--count-undoc : print names of undocumented items",
                "\t--print-undoc : print names of undocumented items",
                "\t--doc-out   : output location for documentation"];
}

aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  local outputLoc::String = fromMaybe(benv.silverGen ++ "/doc/", top.config.docOutOption) ++ "/";
  top.postOps <- if top.config.docGeneration then 
                 [genDoc(top.config, grammarsToTranslate, outputLoc)]
                 else [];
  top.postOps <- if top.config.printUndoc || top.config.countUndoc then 
                 [printUndoc(top.config, grammarsToTranslate)]
                 else [];
}

abstract production printUndoc
top::DriverAction ::= a::Decorated CmdArgs  specs::[Decorated RootSpec]
{
  local report :: String = "\nUndocumented Items Report:\n" ++ implode("\n",
    flatMap((\x::Decorated RootSpec -> case x of 
      | grammarRootSpec(g, _, _, _, _) ->
          if (length(g.documentedNamed)+length(g.undocumentedNamed))!=0
          then [s" - [${g.grammarName}]: ${toString(length(g.documentedNamed))}" ++
                s"/${toString(toInteger(length(g.undocumentedNamed)+length(g.documentedNamed)))} " ++ 
                s"(${toString((toFloat(length(g.documentedNamed))/toFloat(length(g.undocumentedNamed)+length(g.documentedNamed)))*toFloat(100))}%) items documented"
            ++ (if a.printUndoc && (length(g.undocumentedNamed)!=0)
                then ", missing: " ++ implode(", ", g.undocumentedNamed)
                else ".")]
          else []
      | _ -> []
      end), specs));

  top.io = printlnT(report, top.ioIn);
  top.code = 0;
  top.order = 5;
}


abstract production genDoc
top::DriverAction ::= a::Decorated CmdArgs  specs::[Decorated RootSpec]  outputLoc::String
{
  local pr :: IOToken = eprintlnT("Generating Documentation.", top.ioIn);

  top.io = writeAll(pr, a, specs, outputLoc);
  top.code = 0;
  top.order = 4;
}

function writeAll
IOToken ::= i::IOToken  a::Decorated CmdArgs  l::[Decorated RootSpec]  outputLoc::String
{
  local now :: IOToken = writeSpec(i, head(l), outputLoc);
  local recurse :: IOToken = writeAll(now, a, tail(l), outputLoc);

  return if null(l) then i else recurse;
}

function writeSpec
IOToken ::= i::IOToken  r::Decorated RootSpec  outputLoc::String
{
  local path :: String = outputLoc ++ grammarToPath(r.declaredName);

  local mkiotest :: IOVal<Boolean> =
    isDirectoryT(path, i);
  
  local mkio :: IOVal<Boolean> =
    if mkiotest.iovalue
    then mkiotest
    else mkdirT(path, mkiotest.io);
  
  local pr :: IOToken =
    if mkio.iovalue
    then eprintlnT("\t[" ++ r.declaredName ++ "]", mkio.io)
    else exitT(-5, eprintlnT("\nUnrecoverable Error: Unable to create directory: " ++ path ++ "\n", mkio.io));
  
  local rm :: IOToken = deleteStaleDocs(pr, outputLoc, r.declaredName);

  local wr :: IOToken = writeFiles(path, r.genFiles, rm);

  return wr;
}

@{--
 - Given a path (with terminating /) and list of (file names relative to that root, contents),
 - write these out.
 -}
function writeFiles
IOToken ::= path::String s::[Pair<String String>] i::IOToken
{
  return if null(s) then i else writeFileT(path ++ head(s).fst, head(s).snd, writeFiles(path, tail(s), i));
}

-- Copied from 
function deleteStaleDocs
IOToken ::= iIn::IOToken outputLoc::String gram::String
{
  local docPath :: String = outputLoc ++ grammarToPath(gram);
  
  return deleteDirFilesT(docPath, iIn).io;
}
