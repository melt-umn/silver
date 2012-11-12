grammar silver:driver;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:env:env_parser;

imports silver:driver:util;
exports silver:driver:util;
imports silver:util;
imports silver:util:cmdargs;

inherited attribute svParser :: (ParseResult<Root> ::= String String);
inherited attribute sviParser :: (ParseResult<IRootSpec> ::= String String);

{--
 - Controls the compiler.
 - Could be eliminated, and the production turned into a function nowadays.
 -}
nonterminal RunUnit with io, svParser, sviParser;

abstract production run
top::RunUnit ::= iIn::IO args::[String]
{
  local argResult :: ParseResult<Decorated CmdArgs> = parseArgs(args);

  -- Parse the command line
  production attribute a :: Decorated CmdArgs;
  a = argResult.parseTree;

  -- Grab a few environment variables
  local envGP :: IOVal<String> = envVar("GRAMMAR_PATH", iIn);
  local envSG :: IOVal<String> = envVar("SILVER_GEN", envGP.io);
  local envSH :: IOVal<String> = envVar("SILVER_HOME", envSG.io);

  -- A list of directories to search for grammars. (cmd line has priority over env)
  production attribute grammarPath :: [String];
  grammarPath = map(endWithSlash,  a.searchPath ++ explode(":", envGP.iovalue));
  
  -- Where Silver is installed. (env var should be set by RunSilver.jar)
  production attribute silverHome :: String;
  silverHome = endWithSlash(envSH.iovalue);
  
  -- The directory where generated files should be stored. (again, cmd line > env)
  production attribute silverGen :: String with ++;
  silverGen := endWithSlash(if a.genLocation == "" then envSG.iovalue else a.genLocation);

--------
-------- Phase 1: Pre-ops. Things that go on before we start parsing.
--------

  -- Operations to execute _before_ we parse and link the grammars.
  production attribute preOps :: [Unit] with ++;
  preOps := [checkSilverHome(silverHome), checkSilverGen(silverGen)] ++
    if a.displayVersion then [printVersion()] else [];

  -- Run the pre-ops.
  local attribute preIO :: IOVal<Integer>;
  preIO = runAll(envSH.io, sortUnits(preOps));

  -- Let's actually go see if we can find this grammar.
  local attribute grammarLocation :: IOVal<Maybe<String>>;
  grammarLocation = findGrammarLocation(grammarToPath(a.buildGrammar), grammarPath, preIO.io);

  -- The grammar location as string. Used for extensions.
  production attribute grammarLocationString :: String = fromMaybe(".", grammarLocation.iovalue);

--------
-------- Phase 2: Begin actually compiling things
--------

  -- Begin compiling the target grammar, and then chase down dependencies as needed.
  production attribute unit :: CompilationUnit;
  unit = compileGrammars(grammarLocation.io, grammarPath, [a.buildGrammar], [], a.doClean, silverGen);
  unit.svParser = top.svParser;
  unit.sviParser = top.sviParser;
  unit.compiledGrammars = grammarEnv;
  unit.config = a;
  
  -- Let's pause a moment and note what the result of the above is:
  -- 1: unit.compiledList  ==  grammars actually parsed.
  -- 2: unit.interfaces  ==  grammars that we went with the interface files semi-optimistically.
  -- 3: unit.seenGrammars  ==  the names of all of the above, together.
 
--------
-------- Phase 3: We've compiled things, now figure out what we need to recompile (ONLY for analysis, not re-translation)
--------
  
  production attribute depAnalysis :: DependencyAnalysis;
  depAnalysis = dependencyAnalysis(unit.interfaces, unit.compiledList);
  
  -- Again, the results of the above are:
  -- depAnalysis.compiledList = RootSpecs needing translation
  -- depAnalysis.needGrammars = grammars names that need to be rechecked for errors, but not translated
  -- depAnalysis.interfaces = interfaces that are Just Fine and A-Okay as is

--------
-------- Phase 4: Check those grammars we're uncertain about, to make sure there are no semantic errors, but
--------  don't do translation on them. (TODO: technically this is the source of build bugs...)
--------

  -- Parse those grammars that depend on a changed grammar:
  production attribute reUnit :: CompilationUnit;
  reUnit = compileGrammars(unit.io, grammarPath, depAnalysis.needGrammars, unit.seenGrammars, true, silverGen);
  reUnit.svParser = top.svParser;
  reUnit.sviParser = top.sviParser;
  reUnit.compiledGrammars = grammarEnv;
  reUnit.config = a;
  
  -- Once more,
  -- 1: reUnit.compiledList  ==  parsed versions of grammar that we aren't translating.
  -- (2: reUnit.interfaces  ==  EMPTY.)
  -- (3: reUnit.seenGrammars  ==  should be the same set as unit.seenGrammars)

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
  grammarsDependedUpon = expandAllDeps([a.buildGrammar], [], grammarEnv);
  
  -- This is a list of RootSpecs that need translating:
  production attribute grammarsToTranslate :: [Decorated RootSpec];
  grammarsToTranslate = keepGrammars(grammarsDependedUpon, depAnalysis.compiledList);
  
--------
-------- Phase 6: Translation.  Makes use of the above production attributes.
--------

  --the operations that will be executed _after_ parsing and linking of the grammars has been done
  production attribute postOps :: [Unit] with ++;
  postOps := [doInterfaces(grammarsToTranslate, silverGen)];
  
  local attribute postIO :: IOVal<Integer>;
  postIO = runAll(reUnit.io, sortUnits(postOps));
  
  top.io = if !argResult.parseSuccess -- problem interpreting args
           then exit(1, print(argResult.parseErrors, iIn))
           else if preIO.iovalue != 0 -- the preops tell us to quit.
           then exit(preIO.iovalue, preIO.io)
           else if !grammarLocation.iovalue.isJust
           then exit(2, print("\nGrammar '" ++ a.buildGrammar ++ "' could not be located, make sure that the " ++ 
                              "grammar name is correct and it's location is on $GRAMMAR_PATH.\n\n", grammarLocation.io))
           else if null(unit.compiledList)
           then if null(grammars)
                then exit(3, print("\nGrammar '" ++ a.buildGrammar ++ "' was found at '" ++ grammarLocation.iovalue.fromJust 
                                                       ++ "' but there were no silver source files there!\n\n", grammarLocation.io))
                else exit(4, print("\nGrammar '" ++ a.buildGrammar ++ "' is up to date. Use --clean to force a recompile.\n\n",
                                                       grammarLocation.io))
           else exit(postIO.iovalue, postIO.io);
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

