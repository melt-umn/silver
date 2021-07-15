grammar silver:compiler:extension:abella_compilation;

import silver:compiler:driver;
import silver:compiler:definition:env;
import silver:compiler:definition:core;

import silver:util:cmdargs;


synthesized attribute abellaGen::Boolean occurs on CmdArgs;
synthesized attribute abellaOutOption::Maybe<String> occurs on CmdArgs;
synthesized attribute abellaLibraryLocation::Maybe<String> occurs on CmdArgs;


aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.abellaGen = false;
  top.abellaOutOption = nothing();
  top.abellaLibraryLocation = nothing();
}

abstract production abellaCompileFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.abellaGen = true;
  top.abellaOutOption = rest.abellaOutOption;
  top.abellaLibraryLocation = rest.abellaLibraryLocation;
  forwards to rest;
}

abstract production abellaLibraryFlag
top::CmdArgs ::= loc::String rest::CmdArgs
{
  top.abellaGen = rest.abellaGen;
  top.abellaOutOption = rest.abellaOutOption;
  top.abellaLibraryLocation =
      case rest.abellaLibraryLocation of
      | nothing() -> just(loc)
      | _ -> error("Duplicate arguments for Abella library location")
      end;
  forwards to rest;
}

aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--abella-compile", flag(abellaCompileFlag)),
            pair("--abella-library", option(abellaLibraryFlag))];
  flagdescs <-
     ["\t--abella-compile : build the Abella encoding",
      "\t--abella-library : location of Abella library files (required when encoding to Abella"];
}

aspect production compilation
top::Compilation ::= g::Grammars  r::Grammars  buildGrammar::String  benv::BuildEnv
{
  local outputLoc::String = benv.silverGen ++ "/thm/";
  local libraryLoc::String =
        case top.config.abellaLibraryLocation of
        | nothing() ->
          error("Must give location of Abella library files to encode to Abella")
        | just(path) -> path
        end;
  top.postOps <-
      if top.config.abellaGen
      then [genAbella(top.config, grammarsToTranslate, outputLoc, libraryLoc)]
      else [];
}


abstract production genAbella
top::DriverAction ::= a::Decorated CmdArgs specs::[Decorated RootSpec]
                      outputLoc::String libraryLoc::String
{
  local pr :: IO = print("Generating Abella Encoding.\n", top.ioIn);

  top.io = writeAll(pr, a, specs, outputLoc, libraryLoc);
  top.code = 0;
  top.order = 4;
}

function writeAll
IO ::= i::IO  a::Decorated CmdArgs  l::[Decorated RootSpec]
       outputLoc::String  libraryLoc::String
{
  local now :: IO = writeSpec(i, head(l), outputLoc, libraryLoc);
  local recurse :: IO = writeAll(now, a, tail(l), outputLoc, libraryLoc);

  return if null(l) then i else recurse;
}

function writeSpec
IO ::= i::IO  r::Decorated RootSpec  outputLoc::String  libraryLoc::String
{
  local path::String =
        outputLoc ++ grammarToPath(r.declaredName);

  local mkiotest::IOVal<Boolean> =
    isDirectory(path, i);
  
  local mkio::IOVal<Boolean> =
    if mkiotest.iovalue
    then mkiotest
    else mkdir(path, mkiotest.io);
  
  local pr::IO =
    if mkio.iovalue
    then print("\t[" ++ r.declaredName ++ "]\n", mkio.io)
    else exit(-5, print("\nUnrecoverable Error: Unable to create directory: " ++ path ++ "\n\n", mkio.io));

  local importsString::String =
        "Kind bool   type.\n" ++
        "Import \"" ++ libraryLoc ++ "bools\".\n" ++
        "Kind nat   type.\n" ++
        "Import \"" ++ libraryLoc ++ "integer_addition\".\n" ++
        "Import \"" ++ libraryLoc ++ "integer_multiplication\".\n" ++
        "Import \"" ++ libraryLoc ++ "integer_comparison\".\n" ++
        "Import \"" ++ libraryLoc ++ "lists\".\n" ++
        "Import \"" ++ libraryLoc ++ "strings\".\n" ++
        "Kind $pair   type -> type -> type.\n" ++
        "Import \"" ++ libraryLoc ++ "pairs\".\n" ++
        "Kind $attrVal   type -> type.\n" ++
        "Import \"" ++ libraryLoc ++ "attr_val\".\n\n";

  local filename::String = path ++ "/definitions.thm";

  local wr::IO =
        if r.shouldOutput
        then writeFile(filename, importsString ++ r.output, pr)
        else i;

  return wr;
}

