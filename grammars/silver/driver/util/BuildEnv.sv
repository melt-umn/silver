grammar silver:driver:util;

nonterminal BuildEnv with silverHome, silverGen, grammarPath, defaultSilverGen, defaultGrammarPath;

synthesized attribute silverHome :: String;
synthesized attribute silverGen :: String;
synthesized attribute grammarPath :: [String];

synthesized attribute defaultSilverGen :: String;
synthesized attribute defaultGrammarPath :: String; -- Just the stdlib, so not actually just the default value

{--
 - Build environment information.
 - Note: each of these paths always terminates with /
 -}
abstract production buildEnv
top::BuildEnv ::= silverHome::String  silverGen::String  grammarPath::[String]
{
  top.silverHome = silverHome;
  top.silverGen = silverGen;
  top.grammarPath = grammarPath;
  
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

  local benv :: BuildEnv = buildEnv(silverHome, silverGen, grammarPath);
  
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


