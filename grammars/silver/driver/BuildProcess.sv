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
  production attribute flags::[Pair<String Flag>] with ++;
  flags := [];
  production attribute flagdescs::[String] with ++;
  flagdescs := [];
  local attribute usage::String;
  usage = "Usage: silver [options] grammar\n\nFlag options:\n" ++ implode("\n", sortBy(stringLte, flagdescs)) ++ "\n";
  
  --parse the command line
  production attribute a :: CmdArgs;
  a = interpretCmdArgs(flags, args);

  local attribute envGP :: IOVal<String>;
  envGP = envVar("GRAMMAR_PATH", iIn);
  
  local attribute envSG :: IOVal<String>;
  envSG = envVar("SILVER_GEN", envGP.io);
  
  local attribute envSH :: IOVal<String>;
  envSH = envVar("SILVER_HOME", envSG.io);

  --a list of directories to search (cmdline, env vars)
  production attribute spath :: [String];
  spath = map(endWithSlash,  a.searchPath ++ explode(":", envGP.iovalue));
  
  production attribute silverhome :: String;
  silverhome = endWithSlash(envSH.iovalue);
  
  -- This is a collection so that in the future translations can have their own sub-directories
  production attribute silvergen :: String with ++;
  silvergen := endWithSlash(if a.genLocation == "" then envSG.iovalue else a.genLocation);

  --the grammar path ':' replaced by '/'
  local attribute gpath :: String;
  gpath = grammarToPath(a.buildGrammar);

--------
-------- Phase 1: pre-compiling stuff
--------

  -- operations to execute _before_ we parse and link the grammars.
  production attribute preOps :: [Unit] with ++;
  preOps := [];

  --the result of running the pre operations
  local attribute preIO :: IOVal<Integer>;
  preIO = runAll(envSH.io, unitMergeSort(preOps));

  --the directory which contains the grammar
  local attribute grammarLocation :: IOVal<Maybe<String>>;
  grammarLocation = findGrammarLocation(gpath, spath, preIO.io);

--------
-------- Phase 2: Begin actually compiling things
--------

  -- the grammars we need to compile - this is a dynamic process
  -- we give a starting point and it will find and compile
  -- the other grammars needed
  production attribute unit :: CompilationUnit;
  unit = compileGrammars(grammarLocation.io, spath, [a.buildGrammar], [], a.doClean, silvergen);
  unit.rParser = top.rParser;
  unit.iParser = top.iParser;
  unit.compiledGrammars = grammars;
 
  -- a list of the specs from all the grammars compiled EXCEPT the conditional build grammars! (and before recompiles!)
  local attribute grammarsBeforeCond :: [Decorated RootSpec];
  grammarsBeforeCond = unit.compiledList ++ getSpecs(unit.interfaces);

  production attribute condUnit :: CompilationUnit;
  condUnit = compileConditionals(unit.io, spath, collectGrammars(grammarsBeforeCond), a.doClean, grammarsBeforeCond, silvergen);
  condUnit.rParser = top.rParser;
  condUnit.iParser = top.iParser;
  condUnit.compiledGrammars = grammars;
  
  -- all of the interfaces that we parsed
  production attribute ifaces :: [Decorated Interface];
  ifaces = unit.interfaces ++ condUnit.interfaces;

--------
-------- Phase 3: We've compiled things, now figure out what we need to recompile (ONLY for analysis, not re-translation)
--------
  
  production attribute depAnalysis :: DependencyAnalysis;
  depAnalysis = dependencyAnalysis(ifaces);
  depAnalysis.compiledGrammars = unit.compiledList ++ condUnit.compiledList;
  depAnalysis.forceTaint := [];
  
  -- depAnalysis.compiledList = RootSpecs needing translation
  -- depAnalysis.needGrammars = grammars names that need to be rechecked for errors, but not translated
  -- depAnalysis.interfaces = interfaces that are Just Fine and A-Okay as is

  -- the names of the grammars that have been seen. 
  local attribute seenNames :: [String];
  seenNames = unit.seenGrammars ++ condUnit.seenGrammars;

  -- Note that we already have the latest translation of all the grammars. This just does semantic analysis to make sure they're still okay.
  production attribute reUnit :: CompilationUnit;
  reUnit = compileGrammars(condUnit.io, spath, depAnalysis.needGrammars, seenNames, true, silvergen);
  reUnit.rParser = top.rParser;
  reUnit.iParser = top.iParser;
  reUnit.compiledGrammars = grammars;

--------
-------- Now let's put the pieces together.
--------

  -- grammars not in the dependency tree formed by moduleNames on the root grammar
  -- this is interesting because translations must be sure to account for them (for example, in initialization)
  production attribute nonTreeRootSpecs :: [Decorated RootSpec];
  nonTreeRootSpecs = condUnit.compiledList ++ getSpecs(condUnit.interfaces);
  
  production attribute nonTreeGrammars :: [String];
  nonTreeGrammars = collectGrammars(nonTreeRootSpecs);

  -- a list of the specs from _all_ the grammars we've looked at
  production attribute grammars :: [Decorated RootSpec];
  grammars = unit.compiledList ++ reUnit.compiledList ++ getSpecs(depAnalysis.interfaces) ++ nonTreeRootSpecs;
  
--------
-------- Translation:  grammars has up-to-date RootSpec for everything. Should be used by analysis.
--------               (e.g. typechecking/binding)
--------
--------               depAnalysis.compiledList is the list needing re-translation
--------               HOWEVER, translations might need to add more (e.g. the root grammar for cond build Init calls)
--------               AND TO DO SO, they need to force the root grammar to be recompiled.
--------

  --the operations that will be executed _after_ parsing and linking of the grammars has been done
  production attribute postOps :: [Unit] with ++;
  postOps := [];
  
  local attribute postIO :: IOVal<Integer>;
  postIO = runAll(reUnit.io, unitMergeSort(postOps));
  
  top.io = if a.cmdError.isJust
           then exit(1, print("\n" ++ a.cmdError.fromJust ++ "\n\n" ++ usage, iIn))
           else if preIO.iovalue != 0 --the preops tell us to quit.
           then exit(preIO.iovalue, preIO.io)
           else if null(a.cmdRemaining)
           then exit(1, print("\nNo grammar to build was specified!\n\n" ++ usage, preIO.io))
           else if length(a.cmdRemaining) > 1
           then exit(1, print("\nUnable to interpret: " ++ implode(" ", a.cmdRemaining) ++ "\n\n" ++ usage, preIO.io))
           else if !grammarLocation.iovalue.isJust
           then exit(2, print("\nGrammar '" ++ a.buildGrammar ++ "' could not be located, make sure that the " ++ 
                              "grammar name is correct and it's location is on $GRAMMAR_PATH.\n\n", grammarLocation.io))
           else if null(unit.compiledList ++ condUnit.compiledList)
           then if null(grammars)
                then exit(3, print("\nGrammar '" ++ a.buildGrammar ++ "' was found at '" ++ grammarLocation.iovalue.fromJust 
                                                       ++ "' but there were no silver source files there!\n\n", grammarLocation.io))
                else exit(4, print("\nGrammar '" ++ a.buildGrammar ++ "' is up to date. Use --clean to force a recompile.\n\n",
                                                       grammarLocation.io))
           else exit(postIO.iovalue, postIO.io);
}

