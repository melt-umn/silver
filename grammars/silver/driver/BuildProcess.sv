grammar silver:driver;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:env:env_parser;

imports silver:util;

imports silver:util:cmdargs;

inherited attribute rParser :: Function(ParseResult<Root> ::= String String);
inherited attribute iParser :: Function(ParseResult<IRootSpec> ::= String String);

{--
 - Controls the compiler.
 - Could be eliminated, and the production turned into a function nowadays.
 -}
nonterminal RunUnit with io, rParser, iParser;

abstract production run
top::RunUnit ::= iIn::IO args::[String]
{
  -- See Command.sv for some flags.
  production attribute flags::[Pair<String Flag>] with ++;
  flags := [];
  production attribute flagdescs::[String] with ++;
  flagdescs := [];
  local attribute usage::String;
  usage = "Usage: silver [options] grammar\n\nFlag options:\n" ++ implode("\n", sortBy(stringLte, flagdescs)) ++ "\n";
  
  -- Parse the command line
  production attribute a :: CmdArgs;
  a = interpretCmdArgs(flags, args);

  -- Grab a few environment variables
  local envGP :: IOVal<String> = envVar("GRAMMAR_PATH", iIn);
  local envSG :: IOVal<String> = envVar("SILVER_GEN", envGP.io);
  local envSH :: IOVal<String> = envVar("SILVER_HOME", envSG.io);

  -- A list of directories to search for grammars. (cmd line has priority over env)
  production attribute searchPaths :: [String];
  searchPaths = map(endWithSlash,  a.searchPath ++ explode(":", envGP.iovalue));
  
  -- Where Silver is installed. (env var should be set by RunSilver.jar)
  production attribute silverhome :: String;
  silverhome = endWithSlash(envSH.iovalue);
  
  -- The directory where generated files should be stored. (again, cmd line > env)
  production attribute silvergen :: String with ++;
  silvergen := endWithSlash(if a.genLocation == "" then envSG.iovalue else a.genLocation);

--------
-------- Phase 1: pre-compiling stuff
--------

  -- Operations to execute _before_ we parse and link the grammars.
  production attribute preOps :: [Unit] with ++;
  preOps := []; -- See Unit.sv

  -- Run the pre-ops.
  local attribute preIO :: IOVal<Integer>;
  preIO = runAll(envSH.io, unitMergeSort(preOps));

  -- Let's actually go see if we can find this grammar.
  local attribute grammarLocation :: IOVal<Maybe<String>>;
  grammarLocation = findGrammarLocation(grammarToPath(a.buildGrammar), searchPaths, preIO.io);

--------
-------- Phase 2: Begin actually compiling things
--------

  -- Begin compiling the target grammar, and then chase down dependencies as needed.
  production attribute unit :: CompilationUnit;
  unit = compileGrammars(grammarLocation.io, searchPaths, [a.buildGrammar], [], a.doClean, silvergen);
  unit.rParser = top.rParser;
  unit.iParser = top.iParser;
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
  
  -- depAnalysis.compiledList = RootSpecs needing translation
  -- depAnalysis.needGrammars = grammars names that need to be rechecked for errors, but not translated
  -- depAnalysis.interfaces = interfaces that are Just Fine and A-Okay as is

--------
-------- Phase 4: Check those grammars we're uncertain about, to make sure there are no semantic errors, but
--------  don't do translation on them. (TODO: technically this is the source of build bugs...)
--------

  -- Parse those grammars that depend on a changed grammar:
  production attribute reUnit :: CompilationUnit;
  reUnit = compileGrammars(unit.io, searchPaths, depAnalysis.needGrammars, unit.seenGrammars, true, silvergen);
  reUnit.rParser = top.rParser;
  reUnit.iParser = top.iParser;
  reUnit.compiledGrammars = grammarEnv;
  reUnit.config = a;

--------
-------- Now let's put the pieces together.
--------

  -- All the specs we're looking at:
  production attribute grammars :: [Decorated RootSpec];
  grammars = unit.compiledList ++ reUnit.compiledList ++ getSpecs(depAnalysis.interfaces);
  
  -- A nice environment for looking up a grammar: (used above, passed down into each grammar)
  production attribute grammarEnv :: EnvTree<Decorated RootSpec>;
  grammarEnv = directBuildTree(map(grammarPairing, grammars));
  
--------
-------- Translation:  grammars has up-to-date RootSpec for everything. Should be used by analysis.
--------               (e.g. typechecking/binding)
--------
--------               depAnalysis.compiledList is the list needing re-translation
--------

  --the operations that will be executed _after_ parsing and linking of the grammars has been done
  production attribute postOps :: [Unit] with ++;
  postOps := [];
  
  local attribute postIO :: IOVal<Integer>;
  postIO = runAll(reUnit.io, unitMergeSort(postOps));
  
  top.io = if a.cmdError.isJust -- problem interpreting args
           then exit(1, print("\n" ++ a.cmdError.fromJust ++ "\n\n" ++ usage, iIn))
           else if preIO.iovalue != 0 -- the preops tell us to quit.
           then exit(preIO.iovalue, preIO.io)
           else if null(a.cmdRemaining) -- no grammar left on cmd line
           then exit(1, print("\nNo grammar to build was specified!\n\n" ++ usage, preIO.io))
           else if length(a.cmdRemaining) > 1 -- more than just a grammar left
           then exit(1, print("\nUnable to interpret: " ++ implode(" ", a.cmdRemaining) ++ "\n\n" ++ usage, preIO.io))
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
 - command - command line arguments (turn warnings, etc on)
 - dependency analysis - translation, etc wants to know what to build
 - exports graph - it'd be nice for some of those future warnings
 - 


---}
