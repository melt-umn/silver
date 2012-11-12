grammar silver:driver;

--imports silver:definition:flow:env;
imports silver:definition:flow:ast;
import silver:util:raw:treemap as rtm;

{--
 - Responsible for the control-flow that figures out how to obtain a grammar's symbols.
 -}
nonterminal Grammar with config, io, rSpec, svParser, compiledGrammars, found, interfaces, sviParser, productionFlowGraphs, grammarFlowTypes;

synthesized attribute rSpec :: Decorated RootSpec;
synthesized attribute found :: Boolean;

{--
 - Hunts down a grammar and obtains its symbols, either by building or from an interface file.
 -}
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
	
  -- Compile all the individual files in the grammar
  production attribute cu :: Roots; -- See GrammarSources.sv
  cu = compileFiles(pr, grammarName, files, grammarLocation.iovalue.fromJust);
  cu.svParser = top.svParser;
  -- Create the values for grammar-wide inherited attributes.
  cu.env = toEnv(cu.rSpec.defs);
  cu.globalImports = toEnv(cu.rSpec.importedDefs);
  -- This grammar, its direct imports, and only transitively close over exports and TRIGGERED conditional imports.
  local actualDependencies :: [String] = makeSet(computeDependencies(grammarName :: cu.rSpec.moduleNames, top.compiledGrammars));
  cu.grammarDependencies = actualDependencies;
  -- deps for flow analysis reasons: now, close over imports and options, as well as exports and TRIGGERED conditional imports.
  local depsPlusOptions :: [String] = makeSet(completeDependencyClosure(actualDependencies, top.compiledGrammars));
  cu.flowEnv = fromFlowDefs(foldr(consFlow, nilFlow(), gatherFlowEnv(depsPlusOptions, top.compiledGrammars)));
  cu.productionFlowGraphs = top.productionFlowGraphs;
  cu.grammarFlowTypes = top.grammarFlowTypes;
  -- Echo the compilation-wide ones:
  cu.compiledGrammars = top.compiledGrammars;
  cu.config = top.config;

  -- **OR** the result of reading the interface.
  production attribute inf :: IOInterface; -- See GrammarInterface.sv
  inf = compileInterface(pr, "Silver.svi", genPath ++ "src/" ++ gramPath);
  inf.sviParser = top.sviParser;

  top.found = grammarLocation.iovalue.isJust && !null(files);
  top.io =  if top.found then (if !clean && hasInterface.iovalue then inf.io else cu.io) else grammarLocation.io;
  top.interfaces = if top.found && !clean && hasInterface.iovalue then inf.interfaces else [];
  top.rSpec = if top.found then (if !clean && hasInterface.iovalue then head(inf.interfaces).rSpec else cu.rSpec) else emptyRootSpec();
}

{--
 - Determined whether a file name should be considered a Silver source file.
 -}
function isValidSilverFile
Boolean ::= f::String
{
  return endsWith(".sv", f) && !startsWith(".", f);
}

{--
 - Determines whether an interface file is newer or older than the grammar.
 -}
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

{--
 - Determines the maximum modification time of all files in a directory.
 - Including the directory itself, to detect file deletions.
 -}
function fileTimes
IOVal<Integer> ::= i::IO dir::String is::[String]
{
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

function gatherFlowEnv
[FlowDef] ::= deps::[String]  e::EnvTree<Decorated RootSpec>
{
  return if null(deps) then []
         else case searchEnvTree(head(deps), e) of
              | r :: _ -> r.flowDefs ++ gatherFlowEnv(tail(deps), e)
              | [] -> gatherFlowEnv(tail(deps), e)
              end;
}


