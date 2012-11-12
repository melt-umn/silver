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

function parseArgs
ParseResult<Decorated CmdArgs> ::= args::[String]
{
  production attribute flags::[Pair<String Flag>] with ++;
  flags := [];
  production attribute flagdescs::[String] with ++;
  flagdescs := [];

  -- General rules of thumb:
  --  Use -- as your prefix
  --  Unless it's an OPTION, and it's commonly used, and it's obvious from context what it means
  -- e.g. -I my/grammars is obvious because it refers to a location to include.

  flags <- [pair("-I",        option(includeFlag)),
            pair("-o",        option(outFlag)),
            pair("-G",        option(genFlag)),
            pair("--version", flag(versionFlag)),
            pair("--clean",   flag(cleanFlag))
           ];
  -- Always start with \t, name options descriptively in <>, do not end with \n!
  flagdescs <- 
          ["\t-I <path>  : path to grammars (GRAMMAR_PATH)",
           "\t-o <file>  : name of binary file",
           "\t--version  : display version",
           "\t--clean  : overwrite interface files",
           "\t-G <path>  : Location to store generate files (SILVER_GEN)"
          ];
  
  local usage :: String = 
    "Usage: silver [options] grammar:to:build\n\nFlag options:\n" ++ implode("\n", sortBy(stringLte, flagdescs)) ++ "\n";
  
  -- Parse the command line
  local a :: CmdArgs = interpretCmdArgs(flags, args);
  
  return if a.cmdError.isJust -- problem interpreting args
         then parseFailed(a.cmdError.fromJust ++ "\n\n" ++ usage)
         else if null(a.cmdRemaining) -- no grammar left on cmd line
         then parseFailed("No grammar to build was specified.\n\n" ++ usage)
         else if length(a.cmdRemaining) > 1 -- more than just a grammar left
         then parseFailed("Unable to interpret arguments: " ++ implode(" ", a.cmdRemaining) ++ "\n\n" ++ usage)
         else parseSucceeded(a);
}

