grammar silver:driver;

{--
 - Responsible for the control-flow that figures out how to obtain a grammar's symbols.
 -}
nonterminal Grammar with io, rSpec, rParser, compiledGrammars, found, interfaces, iParser;

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
	
  --the result of compiling all of the files.
  production attribute cu :: Roots; -- See GrammarSources.sv
  cu = compileFiles(pr, grammarName, files, grammarLocation.iovalue.fromJust);
  cu.rParser = top.rParser;
  cu.env = toEnv(cu.defs);
  cu.globalImports = toEnv(cu.importedDefs);
  cu.compiledGrammars = top.compiledGrammars;

  -- OR the result of reading the interface.
  production attribute inf :: IOInterface; -- See GrammarInterface.sv
  inf = compileInterface(pr, "Silver.svi", genPath ++ "src/" ++ gramPath);
  inf.iParser = top.iParser;
  inf.compiledGrammars = top.compiledGrammars;

  top.found = grammarLocation.iovalue.isJust && !null(files);
  top.interfaces = if top.found && !clean && hasInterface.iovalue then inf.interfaces else [];
  top.io =  if top.found then (if !clean && hasInterface.iovalue then inf.io else cu.io) else grammarLocation.io;
  top.rSpec = if top.found then (if !clean && hasInterface.iovalue then head(inf.interfaces).rSpec else cu.rSpec) else emptyRootSpec();
}

