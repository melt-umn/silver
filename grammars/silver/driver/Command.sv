grammar silver:driver;

attribute genLocation, doClean, displayVersion, warnError, searchPath, outName, buildGrammar, silverHomeOption, noBindingChecking occurs on CmdArgs;

synthesized attribute searchPath :: [String];
synthesized attribute outName :: [String];
synthesized attribute genLocation :: [String];
synthesized attribute silverHomeOption :: [String];

synthesized attribute displayVersion :: Boolean;
synthesized attribute doClean :: Boolean;
synthesized attribute warnError :: Boolean;

synthesized attribute buildGrammar :: [String];

synthesized attribute noBindingChecking :: Boolean;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.doClean = false;
  top.displayVersion = false;
  top.warnError = false;
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
abstract production warnErrorFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnError = true;
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
Either<String  Decorated CmdArgs> ::= args::[String]
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
            pair("--dont-analyze", flag(nobindingFlag)),
            pair("--warn-error", flag(warnErrorFlag))
           ];
  -- Always start with \t, name options descriptively in <>, do not end with \n!
  flagdescs <- 
          ["\t-I <path>  : path to grammars (GRAMMAR_PATH)",
           "\t-o <file>  : name of binary file",
           "\t--version  : display version",
           "\t--clean  : overwrite interface files",
           "\t-G <path>  : Location to store generate files (SILVER_GEN)",
           "\t--warn-error  : treat warnings as errors"
          ];
  
  local usage :: String = 
    "Usage: silver [options] grammar:to:build\n\nFlag options:\n" ++ implode("\n", sortBy(stringLte, flagdescs)) ++ "\n";
  
  -- Parse the command line
  production a :: CmdArgs = interpretCmdArgs(flags, args);
  
  production attribute errors :: [String] with ++;
  errors := if a.cmdError.isJust then [a.cmdError.fromJust] else [];
  
  errors <- 
    if length(a.cmdRemaining) > 1 then ["Unable to interpret arguments: " ++ implode(" ", a.cmdRemaining)]
    else if length(a.outName) > 1 then ["Multiple options given for -o flag: " ++ implode(" ", a.outName)]
    else if length(a.genLocation) > 1 then ["Multiple options given for -G flag: " ++ implode(" ", a.genLocation)]
    else if length(a.silverHomeOption) > 1 then ["Multiple options given for --silver-home flag: " ++ implode(" ", a.silverHomeOption)]
    else [];
  
  return if !null(errors)
         then left(implode("\n", errors) ++ "\n\n" ++ usage)
         else right(a);
}

function determineBuildEnv
IOVal<Either<BuildEnv [String]>> ::= a::Decorated CmdArgs  ioin::IO
{
  -- Let's locally set up and verify the environment
  local envSH :: IOVal<String> = envVar("SILVER_HOME", ioin);
  local envGP :: IOVal<String> = envVar("GRAMMAR_PATH", envSH.io);
  local envSG :: IOVal<String> = envVar("SILVER_GEN", envGP.io);
  
  local benv :: BuildEnv = 
    fromArgsAndEnv(
      -- TODO: maybe we should use the java platform separator here?
      envSH.iovalue, envSG.iovalue, explode(":", envGP.iovalue),
      a.silverHomeOption, a.genLocation, a.searchPath);

  -- Let's do some checks on the environment
  local checkenv :: IOVal<[String]> = checkEnvironment(benv, envSG.io);
  
  return if null(checkenv.iovalue) then
    ioval(checkenv.io, left(benv))
  else
    ioval(checkenv.io, right(checkenv.iovalue));
}

function checkEnvironment
IOVal<[String]> ::= benv::BuildEnv ioin::IO
{
  local isGenDir :: IOVal<Boolean> = isDirectory(benv.silverGen, ioin);
  local isGramDir :: IOVal<Boolean> = isDirectory(benv.silverHome ++ "grammars/", isGenDir.io);

  local errors :: [String] =
    if benv.silverHome == "/" -- because we called 'endWithSlash' on empty string
    then ["Missing SILVER_HOME or --silver-home <path>.\nThis should have been set up by the 'silver' script.\n"]
    else if !isGenDir.iovalue
         then if benv.silverGen == benv.defaultSilverGen
         then ["Missing SILVER_GEN or -G <path>.\nThis should have been inferable, but " ++ benv.silverGen ++ " is not a directory.\n"]
         else ["Supplied SILVER_GEN location " ++ benv.silverGen ++ " is not a directory.\n"]
    else if !isGramDir.iovalue
    then ["Missing standard library grammars: tried " ++ benv.defaultGrammarPath ++ " but this did not exist.\n"]
    else [];
    -- TODO: We should probably check everything in grammarPath?
    -- TODO: Maybe look for 'core' specifically?

  return ioval(isGramDir.io, errors);
}

function checkPreBuild
IOVal<[String]> ::=
  a::Decorated CmdArgs
  benv::BuildEnv
  buildGrammar::String
  ioin::IO
{
  local errors :: [String] =
    if null(a.cmdRemaining) then ["No grammar to build was specified.\n"]
    else if indexOf("/", buildGrammar) != -1 -- basic sanity check
    then ["Build grammar appears to contain slashes: " ++ buildGrammar ++ "\n"]
    else if indexOf(".", buildGrammar) != -1 -- also, now
    then ["Build grammar appears to contain dots: " ++ buildGrammar ++ "\n"]
    else [];
  -- TODO: presently, we check whether we find this grammar elsewhere. Maybe it should be here? not sure.

  return ioval(ioin, errors);
}

