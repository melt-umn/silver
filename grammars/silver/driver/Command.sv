grammar silver:driver;

attribute genLocation, doClean, displayVersion, searchPath, outName, buildGrammar, silverHomeOption, noBindingChecking occurs on CmdArgs;

synthesized attribute searchPath :: [String];
synthesized attribute outName :: [String];
synthesized attribute genLocation :: [String];
synthesized attribute silverHomeOption :: [String];

synthesized attribute displayVersion :: Boolean;
synthesized attribute doClean :: Boolean;

synthesized attribute buildGrammar :: [String];

synthesized attribute noBindingChecking :: Boolean;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.doClean = false;
  top.displayVersion = false;
  top.outName = [];
  top.searchPath = [];
  top.genLocation = [];
  top.silverHomeOption = [];
  top.buildGrammar= l;
  top.noBindingChecking = false;
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
  top.outName = s :: forward.outName;
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
  top.genLocation = s :: forward.genLocation;
  forwards to rest;
}
abstract production homeFlag
top::CmdArgs ::= s::String rest::CmdArgs
{
  top.silverHomeOption = s :: forward.silverHomeOption;
  forwards to rest;
}
abstract production nobindingFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.noBindingChecking = true;
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
            pair("--silver-home", option(homeFlag)),
            pair("--version", flag(versionFlag)),
            pair("--clean",   flag(cleanFlag)),
            pair("--dont-analyze", flag(nobindingFlag))
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
  production a :: CmdArgs = interpretCmdArgs(flags, args);
  
  production attribute errors :: [String] with ++;
  errors := if a.cmdError.isJust then [a.cmdError.fromJust] else [];
  
  errors <- 
    if length(a.cmdRemaining) > 1 then ["Unable to interpret arguments: " ++ implode(" ", a.cmdRemaining)]
    else if null(a.cmdRemaining) then ["No grammar to build was specified.\n\n"]
    else if length(a.outName) > 1 then ["Multiple options given for -o flag: " ++ implode(" ", a.outName)]
    else if length(a.genLocation) > 1 then ["Multiple options given for -G flag: " ++ implode(" ", a.genLocation)]
    else if length(a.silverHomeOption) > 1 then ["Multiple options given for --silver-home flag: " ++ implode(" ", a.silverHomeOption)]
    else [];
  
  return if !null(errors)
         then parseFailed(implode("\n", errors) ++ "\n\n" ++ usage)
         else parseSucceeded(a);
}

function checkEnvironment
IOVal<[String]> ::=
  a::Decorated CmdArgs
  silverHome::String
  silverGen::String
  grammarPath::[String]
  buildGrammar::String
  ioin::IO
{
  local isGenDir :: IOVal<Boolean> = isDirectory(silverGen, ioin);
  local isGramDir :: IOVal<Boolean> = isDirectory(silverHome ++ "grammars/", isGenDir.io);

  local errors :: [String] =
    if silverHome == "/"
    then ["Missing SILVER_HOME or --silver-home <path>.\nThis should have been set up at install time, and/or supplied by RunSilver.jar"]
    else if !isGenDir.iovalue
         then if silverGen == silverHome ++ "generated/"
         then ["Missing SILVER_GEN or -G <path>.\nThis should have been inferable, but " ++ silverGen ++ " is not a directory."]
         else ["Supplied SILVER_GEN location " ++ silverGen ++ " is not a directory."]
    else if !isGramDir.iovalue
    then ["Missing standard library grammars: tried " ++ silverHome ++ "grammar/ but failed."]
    else if indexOf("/", buildGrammar) != -1 -- basic sanity check
    then ["Build grammar appears to contain slashes: " ++ buildGrammar]
    else [];

  return ioval(isGramDir.io, errors);
}

