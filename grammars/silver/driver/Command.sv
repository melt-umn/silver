grammar silver:driver;

synthesized attribute searchPath :: [String];
synthesized attribute outName :: String;
synthesized attribute displayVersion :: Boolean;
synthesized attribute doClean :: Boolean;
synthesized attribute genLocation :: String;
synthesized attribute buildGrammar :: String;

attribute genLocation, doClean, displayVersion, searchPath, outName, buildGrammar occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.doClean = false;
  top.displayVersion = false;
  top.outName = "";
  top.searchPath = ["./"];
  top.genLocation = "";
  top.buildGrammar= head(l);
}
abstract production versionFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.displayVersion = true;
  forwards to rest;
}
abstract production cleanFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.doClean = true;
  forwards to rest;
}
abstract production outFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.outName = s;
  forwards to rest;
}
abstract production includeFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.searchPath = s :: forward.searchPath;
  forwards to rest;
}
abstract production genFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.genLocation = s;
  forwards to rest;
}


aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("-I",      option(includeFlag)),
            pair("-o",      option(outFlag)),
            pair("-v",      flag(versionFlag)),
            pair("--clean", flag(cleanFlag)),
            pair("-G",      option(genFlag))
           ];
  flagdescs <- 
          ["\t-I <path>  : path to grammars (GRAMMAR_PATH)\n",
           "\t-o <file>  : name of binary file\n",
           "\t-v  : display version\n",
           "\t--clean  : overwrite interface files\n",
           "\t-G <path>  : Location to store generate files (SILVER_GEN)\n"
          ];
}

