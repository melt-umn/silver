grammar silver:compiler:extension:abella_compilation;

import silver:compiler:driver;
import silver:util:cmdargs;


synthesized attribute abellaGen::Boolean occurs on CmdArgs;
synthesized attribute abellaOutOption::Maybe<String> occurs on CmdArgs;


aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.abellaGen = false;
  top.abellaOutOption = nothing();
}

abstract production abellaCompileFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.abellaGen = true;
  top.abellaOutOption = rest.abellaOutOption;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--abella-compile", flag(abellaCompileFlag))];
  flagdescs <-
     ["\t--abella-compile : build the Abella encoding"];
}

aspect production compilation
top::Compilation ::= g::Grammars  r::Grammars  buildGrammar::String  benv::BuildEnv
{
  local outputLoc::String = benv.silverGen ++ "/thm/";
  top.postOps <-
      if top.config.abellaGen
      then [genAbella(top.config, grammarsToTranslate, outputLoc)]
      else [];
}


abstract production genAbella
top::DriverAction ::= a::Decorated CmdArgs specs::[Decorated RootSpec]
                      outputLoc::String
{
  local pr :: IOToken = printT("Generating Abella Encoding.\n", top.ioIn);

  top.io = writeAll(pr, a, specs, outputLoc);
  top.code = 0;
  top.order = 4;
}

function writeAll
IOToken ::= i::IOToken  a::Decorated CmdArgs  l::[Decorated RootSpec]
       outputLoc::String
{
  local now :: IOToken = writeSpec(i, head(l), outputLoc);
  local recurse :: IOToken = writeAll(now, a, tail(l), outputLoc);

  return if null(l) then i else recurse;
}

function writeSpec
IOToken ::= i::IOToken  r::Decorated RootSpec  outputLoc::String
{
  local path::String =
        outputLoc ++ grammarToPath(r.declaredName);

  local mkiotest::IOVal<Boolean> =
    isDirectoryT(path, i);
  
  local mkio::IOVal<Boolean> =
    if mkiotest.iovalue
    then mkiotest
    else mkdirT(path, mkiotest.io);

  local pr::IOToken =
    if mkio.iovalue
    then printT("\t[" ++ r.declaredName ++ "]\n", mkio.io)
    else exitT(-5, printT("\nUnrecoverable Error: Unable to create directory: " ++ path ++ "\n\n", mkio.io));

  local filename::String = path ++ "/definitions.thm";
  local interface_filename::String = path ++ "/thm_interface.svthmi";

  local wr::IOToken =
        if r.shouldOutput
        then let one::IOToken = writeFileT(filename, r.output, pr)
             in
                writeFileT(interface_filename, r.interface_output, one)
             end
        else printT(r.error_output, i);

  return wr;
}

