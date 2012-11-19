grammar silver:driver;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:env:env_parser;

imports silver:driver:util;
exports silver:driver:util;
imports silver:util;
imports silver:util:cmdargs;

inherited attribute svParser :: SVParser;
inherited attribute sviParser :: SVIParser;

type SVParser = (ParseResult<Root> ::= String String);
type SVIParser = (ParseResult<IRoot> ::= String String);

{--
 - Run the silver compiler, as if invoked from the command line.
 -}
function cmdLineRun
IOVal<Integer> ::= args::[String]  svParser::SVParser  sviParser::SVIParser  ioin::IO
{
  local argResult :: ParseResult<Decorated CmdArgs> = parseArgs(args);
  local a :: Decorated CmdArgs = argResult.parseTree;

  -- Let's locally set up and verify the environment
  local envSH :: IOVal<String> = envVar("SILVER_HOME", ioin);
  local envGP :: IOVal<String> = envVar("GRAMMAR_PATH", envSH.io);
  local envSG :: IOVal<String> = envVar("SILVER_GEN", envGP.io);
  
  local silverHome :: String =
    endWithSlash(head(a.silverHomeOption ++ [envSH.iovalue]));
  local silverGen :: String =
    endWithSlash(head(a.genLocation ++ (if envSG.iovalue == "" then [] else [envSG.iovalue]) ++ [silverHome ++ "generated/"]));
  local grammarPath :: [String] =
    map(endWithSlash, a.searchPath ++ [silverHome ++ "grammars/"] ++ explode(":", envGP.iovalue) ++ ["."]);
  local buildGrammar :: String = head(a.buildGrammar);

  local check :: IOVal<[String]> =
    checkEnvironment(a, silverHome, silverGen, grammarPath, buildGrammar, envSG.io);
  
  -- Compile grammars. There's some tricky circular program data flow here:
  local firstPass :: IOVal<[Maybe<RootSpec>]> =
    compileGrammars(svParser, sviParser, grammarPath, silverGen, buildGrammar :: grammarList, true{-a.doClean-}, check.io);
  
  local analyzePass :: Pair<[Decorated RootSpec] [Maybe<Decorated RootSpec>]> =
    analyzeGrammars(a, firstPass.iovalue);
  
  local grammarList :: [String] =
    eatGrammars(1, [], analyzePass.snd);
  
  -- TODO: Find "out of date" grammars, rebuild them.

  local actions :: IOVal<Integer> =
    runActions(a, silverHome, silverGen, buildGrammar, analyzePass.fst, ioin);


  return if a.displayVersion then ioval(print("Silver Version 0.3.6-dev\n", ioin), 0)
  else if !argResult.parseSuccess then ioval(print(argResult.parseErrors, ioin), 1)
  else if !null(check.iovalue) then ioval(print(implode("\n", check.iovalue), check.io), 1)
  else actions;
}

function runActions
IOVal<Integer> ::=
  a::Decorated CmdArgs
  silverHome::String
  silverGen::String
  buildGrammar::String
  grammars::[Decorated RootSpec]
  ioin::IO
{
  production attribute grammarEnv :: EnvTree<Decorated RootSpec>;
  grammarEnv = directBuildTree(map(grammarPairing, grammars));
  
  -- Only those grammars that are used. (unit unconditionally builts conditionally built
  -- grammars. Here we produce a set that would not include them if they are not used.)
  production attribute grammarsDependedUpon :: [String];
  grammarsDependedUpon = expandAllDeps([buildGrammar], [], grammarEnv);
  
  -- This is a list of RootSpecs that need translating:
  production attribute grammarsToTranslate :: [Decorated RootSpec];
  grammarsToTranslate = keepGrammars(grammarsDependedUpon, grammars);

  production attribute postOps :: [Unit] with ++;
  postOps := [doInterfaces(grammarsToTranslate, silverGen)];
  postOps <- if a.noBindingChecking then [] else [printAllBindingErrors(grammars)]; 
  
  return runAll(ioin, sortUnits(postOps));
}


function eatGrammars
[String] ::= n::Integer  sofar::[String]  l::[Maybe<Decorated RootSpec>]
{
  local it :: Decorated RootSpec = head(l).fromJust;
  
  local directDeps :: [String] = mentionedGrammars(it);
  
  local newDeps :: [String] = rem(directDeps, sofar);
  
  return
    if n == 0 then
      []
    else if !head(l).isJust then
      eatGrammars(n-1, sofar, tail(l))
    else
      newDeps ++ eatGrammars(n-1+length(newDeps), newDeps ++ sofar, tail(l));
}

function analyzeGrammars
Pair<[Decorated RootSpec] [Maybe<Decorated RootSpec>]> ::=
  config::Decorated CmdArgs
  inputStream::[Maybe<RootSpec>]
{
  local decInputStream :: [Maybe<Decorated RootSpec>] =
    map(decorateGrammar(config, grammarEnv, finalGraphs, flowTypes, _), inputStream);
  
  production grammars :: [Decorated RootSpec] = foldr(foldMaybe, [], decInputStream);
  
  -- TODO Add additional input stream of re-parsed grammars that are tainted
  
  production grammarEnv :: EnvTree<Decorated RootSpec> =
    directBuildTree(map(grammarPairing, grammars));
  
  return pair(grammars, decInputStream);
}

-- TODO: look up a standard name for this, and put in std lib?
function foldMaybe
[a] ::= h::Maybe<a>  t::[a]
{
  return if h.isJust then h.fromJust :: t else t;
}
function decorateGrammar
Maybe<Decorated RootSpec> ::=
  config :: Decorated CmdArgs
  compiledGrammars :: EnvTree<Decorated RootSpec>
  productionFlowGraphs :: [ProductionGraph]
  grammarFlowTypes :: EnvTree<Pair<String String>>
  mrs :: Maybe<RootSpec>
{
  local rs :: RootSpec = mrs.fromJust;
  rs.config = config;
  rs.compiledGrammars = compiledGrammars;
  rs.productionFlowGraphs = productionFlowGraphs;
  rs.grammarFlowTypes = grammarFlowTypes;
  return if mrs.isJust then just(rs) else nothing();
}


{--
 - Keep only a selected set of grammars.
 - @param keep  The set of grammars to keep
 - @param d  The list of grammars to filter
 -}
function keepGrammars
[Decorated RootSpec] ::= keep::[String] d::[Decorated RootSpec]
{
  return if null(d) then [] else (if contains(head(d).declaredName, keep) then [head(d)] else []) ++ keepGrammars(keep, tail(d));
}

{--
 - Ensures a string ends with a forward slash. Safe to use if it already has one.
 -}
function endWithSlash
String ::= s::String
{
  return if endsWith("/", s) then s else s ++ "/";
}

{--
 - Returns a pair, suitable for building an environment
 -}
function grammarPairing
Pair<String Decorated RootSpec> ::= r::Decorated RootSpec
{
  return pair(r.declaredName, r);
}

