grammar silver:driver;
import silver:definition:core;
import silver:definition:env;
import silver:definition:env:env_parser;

import silver:util;

import silver:util:cmdargs;

function grammarToPath
String ::= g::String
{
  return substitute(":", "/", g) ++ "/";
}

synthesized attribute rSpec :: Decorated RootSpec;
synthesized attribute found :: Boolean;

inherited attribute rParser :: Function(ParseResult<Root> ::= String String);
inherited attribute iParser :: Function(ParseResult<IRootSpec> ::= String String);

nonterminal RunUnit with io, rParser, iParser;

--the entry point for silver build process.  It should be a function but aspects currently are not supported on functions.
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

  -- a hook for extensions to add extra grammars - like list, pattern matching.
  production attribute extraGrammars :: [[String]] with ++;
  extraGrammars := [];

  -- the extra grammars after they have been compiled.
  local attribute extraUnit :: CompilationUnit;
  extraUnit = compileAllExtra(extraGrammars, [], []);
  extraUnit.rParser = top.rParser;
  extraUnit.iParser = top.iParser;
  extraUnit.compiledGrammars = grammars;

  -- the grammars we need to compile - this is a dynamic process
  -- we give a starting point and it will find and compile
  -- the other grammars needed
  production attribute unit :: CompilationUnit;
  unit = compileGrammars(grammarLocation.io, spath, [a.buildGrammar] ++ extraUnit.needGrammars, extraUnit.seenGrammars, a.doClean, silvergen);
  unit.rParser = top.rParser;
  unit.iParser = top.iParser;
  unit.compiledGrammars = grammars;
 
  -- a list of the specs from all the grammars compiled EXCEPT the conditional build grammars! (and before recompiles!)
  local attribute grammarsBeforeCond :: [Decorated RootSpec];
  grammarsBeforeCond = unit.compiledList ++ getSpecs(unit.interfaces) ++ extraUnit.compiledList;

  production attribute condUnit :: CompilationUnit;
  condUnit = compileConditionals(unit.io, spath, collectGrammars(grammarsBeforeCond), a.doClean, grammarsBeforeCond, silvergen);
  condUnit.rParser = top.rParser;
  condUnit.iParser = top.iParser;
  condUnit.compiledGrammars = grammars;
  
  --all of the interfaces that we parsed (or faked due to extra gramamrs)
  production attribute ifaces :: [Decorated Interface];
  ifaces = unit.interfaces ++ extraUnit.interfaces ++ condUnit.interfaces;

--------
-------- Phase 3: We've compiled things, now figure out what we need to recompile (ONLY for analysis, not re-translation)
--------
  
  production attribute depAnalysis :: DependencyAnalysis;
  depAnalysis = dependencyAnalysis(ifaces);
  depAnalysis.compiledGrammars = unit.compiledList ++ extraUnit.compiledList ++ condUnit.compiledList;
  depAnalysis.forceTaint := [];
  
  -- depAnalysis.compiledList = RootSpecs needing translation
  -- depAnalysis.needGrammars = grammars names that need to be rechecked for errors, but not translated
  -- depAnalysis.interfaces = interfaces that are Just Fine and A-Okay as is

  -- the names of the grammars that have been seen. 
  local attribute seenNames :: [String];
  seenNames = unit.seenGrammars ++ extraUnit.seenGrammars ++ condUnit.seenGrammars;

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
  nonTreeRootSpecs = condUnit.compiledList ++ getSpecs(condUnit.interfaces) ++ extraUnit.compiledList;
  
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


synthesized attribute compiledList :: [Decorated RootSpec];
synthesized attribute needGrammars :: [String];
synthesized attribute seenGrammars :: [String];
synthesized attribute interfaces :: [Decorated Interface];

nonterminal CompilationUnit with io, compiledGrammars, rParser, iParser, compiledList, needGrammars, seenGrammars, interfaces;



{--
 - After an initial compile session, this triggers any conditional compile requests, and
 - finishes them all off.
 -}
abstract production compileConditionals
top::CompilationUnit ::= iIn::IO sPath::[String] seen::[String] clean::Boolean sofar::[Decorated RootSpec] genPath::String
{
  local attribute foundGrammar :: [String];
  foundGrammar = noninductiveExpansion(seen, normalizeCondBuilds(sofar));

  -- the current grammar
  production attribute now :: CompilationUnit;
  now = compileGrammars(iIn, sPath, foundGrammar, seen, clean, genPath);
  now.rParser = top.rParser;
  now.iParser = top.iParser;
  now.compiledGrammars = top.compiledGrammars;

  top.seenGrammars = if null(foundGrammar) then seen else recurse.seenGrammars;
  top.needGrammars = [];

  --the recursion
  production attribute recurse :: CompilationUnit;
  recurse = compileConditionals(now.io, sPath, now.seenGrammars, clean, now.compiledList ++ getSpecs(now.interfaces) ++ sofar, genPath);
  recurse.rParser = top.rParser;
  recurse.iParser = top.iParser;
  recurse.compiledGrammars = top.compiledGrammars;

  top.io = if null(foundGrammar) then iIn else recurse.io;

  top.compiledList = if null(foundGrammar)
		     then []
		     else now.compiledList ++ recurse.compiledList;

  top.interfaces = if null(foundGrammar)
		   then []
		   else now.interfaces ++ recurse.interfaces;
}


--This production compiles a list of grammars (each String in the list
--represents an entire grammar).  It does not track down new grammars, 
--that is handled by the compileGrammars production.  It produces a list of
--compiled Grammars and a list of grammars that it needs to be found.
--it takes in as an inherited attribute a list of found grammars.
--idealy there is a found grammar for every needed grammar.
abstract production compileAllExtra
top::CompilationUnit ::= grams::[[String]] need::[String] seen::[String]
{
  local attribute g :: String;
  g = head(tail(head(grams)));  
  
  local attribute gn :: String;
  gn = head(head(grams));

  -- the root of the grammar we are compiling
  local attribute r :: Root;
  r = top.rParser(g, "internal " ++ gn).parseTree; -- Since this is an "internal grammar" I'll assume it never parse errors.
  r.grammarName = gn;
  r.compiledGrammars = top.compiledGrammars;
  r.globalImports = toEnv(r.importedDefs);
  r.env = toEnv(r.defs);
  r.file = gn;

  --the root spec
  local attribute rs :: Decorated RootSpec;
  rs = rootSpecRoot(r);

  local attribute inf :: Interface;
  inf = rootSpecInterface(rs);

  --the set of grammars that we have seen and do not need to be compiled.
  local attribute new_seen :: [String];
  new_seen = [r.declaredName] ++ seen;

  --this is the set of grammars that we need compileGrammars to track down for us.
  local attribute new_need :: [String];
  new_need = makeSet(rs.moduleNames ++ need);

  --the recursion.
  local attribute recurse :: CompilationUnit;
  recurse = compileAllExtra(tail(grams), new_need, new_seen);
  recurse.rParser = top.rParser;
  recurse.iParser = top.iParser;
  recurse.compiledGrammars = top.compiledGrammars;
 
  top.compiledList = if null(grams) then [] else [rs] ++ recurse.compiledList;

  --This is kindof tricky.  We keeping passing down a growing list of need and seens (look at recurse)
  --then at the end we mod need by seen.  these are the grammars we have not seen in the extra grammars
  --and thus we need someone else to find for us.
  top.needGrammars = if null(grams) then rem(need, seen) else recurse.needGrammars;  
  top.seenGrammars = if null(grams) then seen else recurse.seenGrammars;
  top.interfaces = if null(grams) then [] else [inf] ++ recurse.interfaces;
}

--this production compiles the given grammars and dynamically adds new grammars to compile to the list.
--grammars will only be compiled once.
abstract production compileGrammars
top::CompilationUnit ::= iIn::IO sPath::[String] need::[String] seen::[String] clean::Boolean genPath::String
{
  -- the current grammar
  production attribute now :: Grammar;
  now = compileGrammar(iIn, head(need), sPath, clean, genPath);
  now.rParser = top.rParser;
  now.iParser = top.iParser;
  now.compiledGrammars = top.compiledGrammars;

  local attribute new_seen :: [String];
  new_seen = [head(need)] ++  seen;

  -- This line first removes from the the new grammarIncludes the ones we have already
  -- compiled (new_seen) and then appends them to the list of grammars we still need to
  -- parse.  It then makes a set of the strings so we do not compile the same grammar
  -- twice.
  local attribute new_need :: [String];
  new_need = makeSet(rem(now.rSpec.moduleNames, new_seen) ++ tail(need));

  top.seenGrammars = if null(need) then seen else recurse.seenGrammars;
  top.needGrammars = [];

  --the recursion
  production attribute recurse :: CompilationUnit;
  recurse = compileGrammars(now.io, sPath, new_need, new_seen, clean, genPath);
  recurse.rParser = top.rParser;
  recurse.iParser = top.iParser;
  recurse.compiledGrammars = top.compiledGrammars;
 
  top.io = if null(need) then iIn else recurse.io;

  top.compiledList = if null(need)  
		     then [] 
		     else if !now.found || !null(now.interfaces)
			  then recurse.compiledList
			  else [now.rSpec] ++ recurse.compiledList;

  top.interfaces = if null(need)
		   then [] 
		   else if !now.found 
			then recurse.interfaces
			else now.interfaces ++ recurse.interfaces;
}

nonterminal Grammar with io, rSpec, rParser, compiledGrammars, found, interfaces, iParser;
abstract production compileGrammar
top::Grammar ::= iIn::IO grammarName::String sPath::[String] clean::Boolean genPath::String
{
  --the grammar path ':' replaced by '/'
  local attribute gramPath :: String;
  gramPath = grammarToPath(grammarName);

  -- the location (if found) of the grammar
  local attribute grammarLocation :: IOVal<Maybe<String>>;
  grammarLocation = findGrammarLocation(gramPath, sPath, iIn);

  -- the list of files from the grammar directory
  local attribute temp_files :: IOVal<[String]>;
  temp_files = listContents(grammarLocation.iovalue.fromJust, grammarLocation.io);

  -- the list of silver files for the grammar
  local attribute files :: [String];
  files = filter(isValidSilverFile, temp_files.iovalue);

  local attribute hasInterface :: IOVal<Boolean>;
  hasInterface = isValidInterface(temp_files.io, genPath ++ "src/" ++ gramPath ++ "Silver.svi", grammarLocation.iovalue.fromJust, files);

  local attribute pr :: IO;
  pr = print("Compiling Grammar: " ++ grammarName ++ "\n", hasInterface.io); 
	
  --the result of compiling all of the files.
  production attribute cu :: Roots;
  cu = compileFiles(pr, grammarName, files, grammarLocation.iovalue.fromJust);
  cu.rParser = top.rParser;
  cu.env = toEnv(cu.defs);
  cu.globalImports = toEnv(cu.importedDefs);
  cu.compiledGrammars = top.compiledGrammars;

  production attribute inf :: IOInterface;
  inf = compileInterface(pr, "Silver.svi", genPath ++ "src/" ++ gramPath);
  inf.iParser = top.iParser;
  inf.compiledGrammars = top.compiledGrammars;

  top.found = grammarLocation.iovalue.isJust && !null(files);
  top.interfaces = if top.found && !clean && hasInterface.iovalue then inf.interfaces else [];
  top.io =  if top.found then (if !clean && hasInterface.iovalue then inf.io else cu.io) else grammarLocation.io;
  top.rSpec = if top.found then (if !clean && hasInterface.iovalue then head(inf.interfaces).rSpec else cu.rSpec) else emptyRootSpec();
}


function isValidInterface
IOVal<Boolean> ::= iIn::IO ifacefile::String grammarPath::String fs::[String]
{
  local attribute hasInterface :: IOVal<Boolean>;
  hasInterface = isFile(ifacefile, iIn);

  local attribute modTime :: IOVal<Integer>;
  modTime = fileTime(ifacefile, hasInterface.io);

  local attribute maxTime :: IOVal<Integer>;
  maxTime = fileTimes(modTime.io, grammarPath, fs);

  return if !hasInterface.iovalue then ioval(hasInterface.io, false) else ioval(maxTime.io, modTime.iovalue > maxTime.iovalue);
}


function fileTimes
IOVal<Integer> ::= i::IO dir::String is::[String]{
  local attribute ft :: IOVal<Integer>;
  ft = fileTime(dir ++ head(is), i);

  local attribute rest :: IOVal<Integer>;
  rest = fileTimes(ft.io, dir, tail(is));

  return if null(is)
         then fileTime(dir, i) -- check the directory itself. Catches deleted files.
         else if ft.iovalue > rest.iovalue
              then ioval(rest.io, ft.iovalue)
              else rest;
}

synthesized attribute lastModified :: Integer;
synthesized attribute interfaceFile :: String;
synthesized attribute interfaceLocation :: String;
nonterminal Interface with rSpec, lastModified, interfaceFile, interfaceLocation;
nonterminal IOInterface with io, interfaces, iParser, compiledGrammars;

abstract production compileInterface
top::IOInterface ::= iIn::IO f::String genPath::String{

  local attribute modTime :: IOVal<Integer>;
  modTime = fileTime(genPath ++ f, iIn);

  local attribute i :: IO;
  i = print("\t[" ++ genPath ++ f ++ "]\n", modTime.io);

  local attribute text :: IOVal<String>;
  text = readFile(genPath ++ f, i);

  local attribute ir :: IRootSpec;
  ir = top.iParser(text.iovalue, f).parseTree; -- I'm assuming that interface files never parse error, so we aren't making this pretty.

  local attribute inf :: Interface; 
  inf = fullInterface(modTime.iovalue, f, genPath, ir.spec);

  top.interfaces = [inf];
  top.io = text.io;
}

abstract production rootSpecInterface
top::Interface ::= r::Decorated RootSpec{
  top.lastModified = 0;
  top.interfaceFile = "_NULL_";
  top.interfaceLocation = "_NULL_";
  top.rSpec = r;
}

abstract production fullInterface
top::Interface ::= i::Integer f::String l::String r::Decorated RootSpec{
  top.lastModified = i;
  top.interfaceFile = f;
  top.interfaceLocation = l;
  top.rSpec = r;
}


--compiles a list of files (assumed to be a complete grammar) and generate a summary of that grammar
nonterminal Roots with env, io, rSpec, rParser, defs, compiledGrammars, importedDefs, globalImports;
abstract production compileFiles
top::Roots ::= iIn::IO gn::String files::[String] gpath::String
{
  --the text of the file.
  local attribute text :: IOVal<String>;
  text = readFile(gpath ++ head(files), print("\t[" ++ gpath ++ head(files) ++ "]\n", iIn));

  production attribute r :: Root;
  r = parseTreeOrDieWithoutStackTrace(top.rParser(text.iovalue, head(files)));
  r.env = top.env;
  r.globalImports = top.globalImports;
  r.file = head(files);
  r.compiledGrammars = top.compiledGrammars;
  r.grammarName = gn;

  --the rest of the files.
  production attribute recurse :: Roots ;
  recurse = compileFiles(text.io, gn, tail(files), gpath) ;
  recurse.rParser = top.rParser;
  recurse.env = top.env;
  recurse.compiledGrammars = top.compiledGrammars;
  recurse.globalImports = top.globalImports;

  top.rSpec = if null(files) then emptyRootSpec() else consRootSpec(r, recurse.rSpec); 
  top.io = if null(files)
           then iIn
           else recurse.io;
  top.defs = if null(files) then emptyDefs() else appendDefs(r.defs, recurse.defs);
  top.importedDefs = if null(files) then emptyDefs() else appendDefs(r.importedDefs, recurse.importedDefs);
}


function endWithSlash
String ::= s::String
{
  return if endsWith("/", s) then s else s ++ "/";
}

function isValidSilverFile
Boolean ::= f::String
{
  return endsWith(".sv", f) && !startsWith(".", f);
}

--takes in a grammar path and a list of possible locations and returns the correct location if any.
function findGrammarLocation
IOVal<Maybe<String>> ::= path::String searchPaths::[String] iIn::IO
{
  local attribute exists :: IOVal<Boolean>;
  exists = isDirectory(head(searchPaths) ++ path, iIn);

  return if null(searchPaths)
         then ioval(iIn, nothing())
         else if exists.iovalue
              then ioval(exists.io, just(head(searchPaths) ++ path))
              else findGrammarLocation(path, tail(searchPaths), exists.io);
}



