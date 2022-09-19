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
  flags <- [flagSpec(name="--abella-compile",
                     paramString=nothing(),
                     help="build the Abella encoding",
                     flagParser=flag(abellaCompileFlag))];
}

aspect production compilation
top::Compilation ::= g::Grammars  r::Grammars  buildGrammar::[String]  benv::BuildEnv
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
  top.run = do {
      eprintln("Generating Abella Encoding.");
      traverse_(writeSpec(_, outputLoc), specs);
      return 0;
  };

  top.order = 4;
}

function writeSpec
IO<()> ::= r::Decorated RootSpec  outputLoc::String
{
  local path::String =
        outputLoc ++ grammarToPath(r.declaredName);

  local filename::String = path ++ "/definitions.thm";
  local interface_filename::String = path ++ "/thm_interface.svthmi";

  return
     if r.shouldOutput
     then do {eprintln("\t[" ++ r.declaredName ++ "]");
             isD::Boolean <- isDirectory(path);
             unless(isD, do {
                mkDSuccess::Boolean <- mkdir(path);
                   unless(mkDSuccess, do {
                      eprintln("Unrecoverable Error:  Unable to create " ++
                               "directory:  " ++ path);
                      exit(-5);
                   });
             });
             writeFile(filename, r.output);
             writeFile(interface_filename, r.interface_output);
         }
     else do {eprint(r.error_output);};
}

