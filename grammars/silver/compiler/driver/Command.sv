grammar silver:compiler:driver;

attribute genLocation, doClean, displayVersion, warnError, forceOrigins, noOrigins, noRedex, tracingOrigins, searchPath, outName, buildGrammars, silverHomeOption, noBindingChecking occurs on CmdArgs;

synthesized attribute searchPath :: [String];
synthesized attribute outName :: [String];
synthesized attribute genLocation :: [String];
synthesized attribute silverHomeOption :: [String];

synthesized attribute displayVersion :: Boolean;
synthesized attribute doClean :: Boolean;
synthesized attribute warnError :: Boolean;
synthesized attribute forceOrigins :: Boolean;
synthesized attribute noOrigins :: Boolean;
synthesized attribute noRedex :: Boolean;
synthesized attribute tracingOrigins :: Boolean;

synthesized attribute buildGrammars :: [String];

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
  top.buildGrammars = l;
  top.noBindingChecking = false;
  top.forceOrigins = false;
  top.noOrigins = false;
  top.noRedex = false;
  top.tracingOrigins = false;
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
abstract production forceOriginsFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.forceOrigins = true;
  forwards to rest;
}
abstract production noOriginsFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.noOrigins = true;
  forwards to rest;
}
abstract production tracingOriginsFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.tracingOrigins = true;
  forwards to rest;
}
abstract production noRedexFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.noRedex = true;
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
  production attribute flags::[FlagSpec] with ++;
  flags := [];

  -- General rules of thumb:
  --  Use -- as your prefix
  --  Unless it's an OPTION, and it's commonly used, and it's obvious from context what it means
  -- e.g. -I my/grammars is obvious because it refers to a location to include.

  flags <-
    [ flagSpec(name="-I", paramString=just("<path>"),
        help="path to grammars (GRAMMAR_PATH)",
        flagParser=option(includeFlag))
    , flagSpec(name="-o", paramString=just("<file>"),
        help="name of binary file",
        flagParser=option(outFlag))
    , flagSpec(name="-G", paramString=just("<path>"),
        help="location to store generate files (SILVER_GEN)",
        flagParser=option(genFlag))
    , flagSpec(name="--silver-home", paramString=nothing(),
        help="set the location of the silver repo (SILVER_HOME)",
        flagParser=option(homeFlag))
    , flagSpec(name="--version", paramString=nothing(),
        help="display version",
        flagParser=flag(versionFlag))
    , flagSpec(name="--clean", paramString=nothing(),
        help="overwrite interface files",
        flagParser=flag(cleanFlag))
    , flagSpec(name="--dont-analyze", paramString=nothing(),
        help="", -- TODO
        flagParser=flag(nobindingFlag))
    , flagSpec(name="--warn-error", paramString=nothing(),
        help="treat warnings as errors",
        flagParser=flag(warnErrorFlag))
    , flagSpec(name="--no-origins", paramString=nothing(),
        help="treat all nonterminals as un`tracked`",
        flagParser=flag(noOriginsFlag))
    , flagSpec(name="--force-origins", paramString=nothing(),
        help="treat all nonterminals as tracked",
        flagParser=flag(forceOriginsFlag))
    , flagSpec(name="--no-redex", paramString=nothing(),
        help="do not collect redex information",
        flagParser=flag(noRedexFlag))
    , flagSpec(name="--tracing-origins", paramString=nothing(),
        help="attach source locations as origin notes to trace control flow",
        flagParser=flag(tracingOriginsFlag))
    ];
  
  local usage :: String = 
    s"Usage: silver [options] [grammar:to:build ...]\n\nFlag options:\n" ++
    flagSpecsToHelpText(flags);
  
  -- Parse the command line
  local cmdArgs :: CmdArgs = interpretCmdArgs(flags, args);
  
  production attribute errors :: [String] with ++;
  errors := if cmdArgs.cmdError.isJust then [cmdArgs.cmdError.fromJust] else [];
  
  errors <- 
    if length(cmdArgs.outName) > 1 then ["Multiple options given for -o flag: " ++ implode(" ", cmdArgs.outName)]
    else if length(cmdArgs.genLocation) > 1 then ["Multiple options given for -G flag: " ++ implode(" ", cmdArgs.genLocation)]
    else if length(cmdArgs.silverHomeOption) > 1 then ["Multiple options given for --silver-home flag: " ++ implode(" ", cmdArgs.silverHomeOption)]
    else if cmdArgs.noOrigins && cmdArgs.forceOrigins then ["Can't specify --no-origins and --force-origins"]
    else [];
  
  return if !null(errors)
         then left(implode("\n", errors) ++ "\n\n" ++ usage)
         else right(cmdArgs);
}

-- This uses Either backwards. TODO: flip order? "right is correct" also TODO: use RunError?
function determineBuildEnv
IOVal<Either<BuildEnv [String]>> ::= a::Decorated CmdArgs  ioin::IOToken
{
  -- Let's locally set up and verify the environment
  local envSH :: IOVal<String> = envVarT("SILVER_HOME", ioin);
  local envGP :: IOVal<String> = envVarT("GRAMMAR_PATH", envSH.io);
  local envSHG :: IOVal<String> = envVarT("SILVER_HOST_GEN", envGP.io);
  local envSG :: IOVal<String> = envVarT("SILVER_GEN", envSHG.io);
  
  -- If SILVER_HOME isn't set, determine it from where this jar is
  local derivedSH :: IOVal<String> =
    if envSH.iovalue == "" then
      determineDefaultSilverHome(envSG.io)
    else
      ioval(envSG.io, envSH.iovalue);

  local benv :: BuildEnv = 
    fromArgsAndEnv(
      -- TODO: maybe we should use the java platform separator here?
      derivedSH.iovalue, envSG.iovalue,
      explode(":", envGP.iovalue), explode(":", envSHG.iovalue),
      a.silverHomeOption, a.genLocation, a.searchPath);

  -- Let's do some checks on the environment
  local checkenv :: IOVal<[String]> = checkEnvironment(benv, derivedSH.io);
  
  return if null(checkenv.iovalue) then
    ioval(checkenv.io, left(benv))
  else
    ioval(checkenv.io, right(checkenv.iovalue));
}

function checkEnvironment
IOVal<[String]> ::= benv::BuildEnv ioin::IOToken
{
  local isGenDir :: IOVal<Boolean> = isDirectoryT(benv.silverGen, ioin);
  local isGramDir :: IOVal<Boolean> = isDirectoryT(benv.defaultGrammarPath, isGenDir.io);

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
  benv::BuildEnv
  buildGrammars::[String]
  ioin::IOToken
{
  local errors :: [String] =
    if null(buildGrammars) then ["No grammar(s) to build were specified.\n"]
    else flatMap(\ buildGrammar::String ->
      if indexOf("/", buildGrammar) != -1 -- basic sanity check
      then ["Build grammar appears to contain slashes: " ++ buildGrammar ++ "\n"]
      else if indexOf(".", buildGrammar) != -1 -- also, now
      then ["Build grammar appears to contain dots: " ++ buildGrammar ++ "\n"]
      else [],
      buildGrammars);
  -- TODO: presently, we check whether we find this grammar elsewhere. Maybe it should be here? not sure.

  return ioval(ioin, errors);
}

-- This code has to live in the generated jar for the program, as putting it in the
-- standard library may someday return the location of the standard library jar instead
-- of us
function determineDefaultSilverHome
IOVal<String> ::=  i::IOToken
{
  return error("NYI");
} foreign {
  -- This grabs the path to this jar (using Init.class as the thing to find the path to)
  -- Then goes up two levels (HOME/jars/file.jar to HOME) and returns that.
  -- If anything goes wrong, we crash.
  "java" : return "new silver.core.Pioval(%i%, common.Util.determineSilverHomePath(Init.class))";
}

