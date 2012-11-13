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
type SVIParser = (ParseResult<IRootSpec> ::= String String);

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
    
  return if a.displayVersion then ioval(print("Silver Version 0.3.6-dev\n", ioin), 0)
  else if !argResult.parseSuccess then ioval(print(argResult.parseErrors, ioin), 1)
  else if !null(check.iovalue) then ioval(print(implode("\n", check.iovalue), check.io), 1)
  else run(a, svParser, sviParser, silverHome, silverGen, grammarPath, buildGrammar, check.io);
}

function run
IOVal<Integer> ::=
  a::Decorated CmdArgs
  svParser::SVParser
  sviParser::SVIParser
  silverHome::String
  silverGen::String
  grammarPath::[String]
  buildGrammar::String
  ioin::IO
{
  -- To improve error checking, we try to find the build grammar explicitly
  local attribute grammarLocation :: IOVal<Maybe<String>>;
  grammarLocation = findGrammarLocation(grammarToPath(buildGrammar), grammarPath, ioin);

  -- The grammar location as string. Used for extensions. TODO remove
  production attribute grammarLocationString :: String = fromMaybe(".", grammarLocation.iovalue);

  -- Begin compiling the target grammar, and then chase down dependencies as needed.
  production attribute unit :: CompilationUnit;
  unit = compileGrammars(grammarLocation.io, grammarPath, [buildGrammar], [], a.doClean, silverGen);
  unit.svParser = svParser;
  unit.sviParser = sviParser;
  unit.compiledGrammars = grammarEnv;
  unit.config = a;
  -- Let's pause a moment and note what the result of the above is:
  -- 1: unit.compiledList  ==  grammars actually parsed.
  -- 2: unit.interfaces  ==  grammars that we went with the interface files semi-optimistically.
  -- 3: unit.seenGrammars  ==  the names of all of the above, together.
 
  production attribute depAnalysis :: DependencyAnalysis;
  depAnalysis = dependencyAnalysis(unit.interfaces, unit.compiledList);
  -- Again, the results of the above are:
  -- depAnalysis.compiledList = RootSpecs needing translation
  -- depAnalysis.needGrammars = grammars names that need to be rechecked for errors, but not translated
  -- depAnalysis.interfaces = interfaces that are Just Fine and A-Okay as is

  -- Parse those grammars that depend on a changed grammar:
  production attribute reUnit :: CompilationUnit;
  reUnit = compileGrammars(unit.io, grammarPath, depAnalysis.needGrammars, unit.seenGrammars, true, silverGen);
  reUnit.svParser = svParser;
  reUnit.sviParser = sviParser;
  reUnit.compiledGrammars = grammarEnv;
  reUnit.config = a;
  -- Once more,
  -- 1: reUnit.compiledList  ==  parsed versions of grammar that we aren't translating.
  -- (2: reUnit.interfaces  ==  [])
  -- (3: reUnit.seenGrammars  ==  unit.seenGrammars)

--------
-------- Phase 5: Let's pull a few things that are very useful for post-ops to use here:
--------

  -- All the specs we're looking at, for analysis:
  production attribute grammars :: [Decorated RootSpec];
  grammars = unit.compiledList ++ reUnit.compiledList ++ getSpecs(depAnalysis.interfaces);
  
  -- A nice environment for looking up a grammar: (used above, passed down into each grammar)
  production attribute grammarEnv :: EnvTree<Decorated RootSpec>;
  grammarEnv = directBuildTree(map(grammarPairing, grammars));
  
  -- Only those grammars that are used. (unit unconditionally builts conditionally built
  -- grammars. Here we produce a set that would not include them if they are not used.)
  production attribute grammarsDependedUpon :: [String];
  grammarsDependedUpon = expandAllDeps([buildGrammar], [], grammarEnv);
  
  -- This is a list of RootSpecs that need translating:
  production attribute grammarsToTranslate :: [Decorated RootSpec];
  grammarsToTranslate = keepGrammars(grammarsDependedUpon, depAnalysis.compiledList);
  
--------
-------- Phase 6: Translation.  Makes use of the above production attributes.
--------

  --the operations that will be executed _after_ parsing and linking of the grammars has been done
  production attribute postOps :: [Unit] with ++;
  postOps := [doInterfaces(grammarsToTranslate, silverGen)];
  postOps <- if a.noBindingChecking then [] else [printAllBindingErrors(grammars)]; 
  
  local attribute postIO :: IOVal<Integer>;
  postIO = runAll(reUnit.io, sortUnits(postOps));
  
  return
    if !grammarLocation.iovalue.isJust
    then ioval(print("\nGrammar '" ++ buildGrammar ++ "' could not be located, make sure that the " ++ 
                       "grammar name is correct and it's location is on $GRAMMAR_PATH.\n\n", grammarLocation.io), 2)
    else if null(unit.compiledList)
    then if null(grammars)
         then ioval(print("\nGrammar '" ++ buildGrammar ++ "' was found at '" ++ grammarLocation.iovalue.fromJust ++
                            "' but there were no silver source files there!\n\n", grammarLocation.io), 3)
         else ioval(print("\nGrammar '" ++ buildGrammar ++ "' is up to date. Use --clean to force a recompile.\n\n",
                            grammarLocation.io), 4)
    else postIO;
}


{---
Some notes on "compiler state":

Things that are copied down from driver to asts:
 - compiledGrammars - all root specs that are being built.
 - config - command line arguments (turn warnings, etc on)
 - dependency analysis - translation, etc wants to know what to build
 - exports graph - it'd be nice for some of those future warnings
 - 

---}


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

