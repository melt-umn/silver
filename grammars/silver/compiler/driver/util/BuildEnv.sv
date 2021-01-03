grammar silver:compiler:driver:util;

nonterminal BuildEnv with silverHome, silverGen, grammarPath, silverHostGen, defaultSilverGen, defaultGrammarPath;

{-- Find jars, standard library -}
synthesized attribute silverHome :: String;
{-- Write files, look for already built grammars -}
synthesized attribute silverGen :: String;
{-- Search for source files -}
synthesized attribute grammarPath :: [String];
{-- Search for already built grammars. **Always includes silverGen!** -}
synthesized attribute silverHostGen :: [String];

synthesized attribute defaultSilverGen :: String;
synthesized attribute defaultGrammarPath :: String; -- Just the stdlib, so not actually just the default value

{--
 - Build environment information.
 - Note: each of these paths always terminates with /
 - TODO: consider moving svParser, args to this structure too? (e.g. clean flag, build grammar)
 - maybe also buildGrammar?
 -}
abstract production buildEnv
top::BuildEnv ::= silverHome::String  silverGen::String  grammarPath::[String]  silverHostGen::[String]
{
  top.silverHome = silverHome;
  top.silverGen = silverGen;
  top.grammarPath = grammarPath;
  top.silverHostGen = silverHostGen;
  
  -- So that this exists in exactly one location:
  top.defaultSilverGen = silverHome ++ "generated/";
  top.defaultGrammarPath = silverHome ++ "grammars/";
}

-- Takes environment and values from args and determines buildEnv according to correct priorities.
function fromArgsAndEnv
BuildEnv ::=
  SILVER_HOME::String -- empty string or value
  SILVER_GEN::String -- empty string or value
  GRAMMAR_PATH::[String] -- any number of values
  SILVER_HOST_GEN::[String] -- any number of values
  homeArg::[String] -- empty list or one value
  genArg::[String] -- empty list or one value
  pathArg::[String] -- any number of values
{
  -- If provided with one, use that, otherwise always use the environment value (if empty, use that)
  local silverHome :: String =
    endWithSlash(head(homeArg ++ [SILVER_HOME]));
  
  -- Use the argument, or if NON-EMPTY, use the environment. Otherwise, use the inferred value from silverHome.
  local silverGen :: String =
    endWithSlash(head(genArg ++ (if SILVER_GEN == "" then [] else [SILVER_GEN]) ++ [benv.defaultSilverGen]));
  
  -- Use the arguments (all of them), followed by environment (if any?), followed by STDLIB, and CWD.
  local grammarPath :: [String] =
    map(endWithSlash,
      pathArg ++
      GRAMMAR_PATH ++
      [benv.defaultGrammarPath] ++
      ["."]);
  
  -- Always search generated first, and what the environment provides us with.
  local silverHostGen :: [String] =
    silverGen :: map(endWithSlash, SILVER_HOST_GEN);

  local benv :: BuildEnv = buildEnv(silverHome, silverGen, grammarPath, silverHostGen);
  
  return benv;
}

{--
 - Ensures a string ends with a forward slash. Safe to use if it already has one.
 -}
function endWithSlash
String ::= s::String
{
  return if endsWith("/", s) then s else s ++ "/";
}


