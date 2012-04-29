grammar silver:driver;

imports silver:definition:flow:env;
imports silver:definition:flow:ast;

{--
 - Responsible for the control-flow that figures out how to obtain a grammar's symbols.
 -}
nonterminal Grammar with config, io, rSpec, rParser, compiledGrammars, found, interfaces, iParser;

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
  cu.rParser = top.rParser;
  -- Create the values for grammar-wide inherited attributes.
  cu.env = toEnv(cu.rSpec.defs);
  cu.globalImports = toEnv(cu.rSpec.importedDefs);
  cu.grammarDependencies = computeDependencies(cu.rSpec.moduleNames, top.compiledGrammars);
  cu.flowEnv = fromFlowDefs(foldr(consFlow, nilFlow(), gatherFlowEnv(cu.grammarDependencies, top.compiledGrammars)));
  -- Echo the compilation-wide ones:
  cu.compiledGrammars = top.compiledGrammars;
  cu.config = top.config;

  -- **OR** the result of reading the interface.
  production attribute inf :: IOInterface; -- See GrammarInterface.sv
  inf = compileInterface(pr, "Silver.svi", genPath ++ "src/" ++ gramPath);
  inf.iParser = top.iParser;

  top.found = grammarLocation.iovalue.isJust && !null(files);
  top.io =  if top.found then (if !clean && hasInterface.iovalue then inf.io else cu.io) else grammarLocation.io;
  top.interfaces = if top.found && !clean && hasInterface.iovalue then inf.interfaces else [];
  top.rSpec = if top.found then (if !clean && hasInterface.iovalue then head(inf.interfaces).rSpec else cu.rSpec) else emptyRootSpec();
}


{--
 - Expand an initial set of modules names to all exported dependencies,
 - direct, indirect, or conditionally triggered.
 -}
function computeDependencies
[String] ::= init::[String] e::EnvTree<Decorated RootSpec>
{
  return expandCondBuilds(expandExports(init, [], e), [], [], e);
}

{--
 - Find all exported grammars
 - @param need  The initial set of imported grammars
 - @param seen  Initially []
 - @param e  All built grammars
 - @return  The initial set, plus any grammar directly or indirectly exported by it
 -}
function expandExports
[String] ::= need::[String]  seen::[String]  e::EnvTree<Decorated RootSpec>
{
  local attribute g :: [Decorated RootSpec];
  g = searchEnvTree(head(need), e);

  return if null(need) then seen
         -- If the grammar has already been taken care of, or doesn't exist, discard it.
         else if contains(head(need), seen) || null(g) then expandExports(tail(need), seen, e)
         -- Otherwise, tack its exported list to the need list, and add this grammar to the taken care of list.
         else expandExports(tail(need) ++ head(g).exportedGrammars, head(need) :: seen, e);
}

{--
 - Find all exported grammars - including any triggered CONDITIONALLY
 - @param need  The initial set of imported grammars (ALL are assumed to be found in e, now.)
 - @param seen  Initially []
 - @param triggers  Initially []
 - @param e  All built grammars
 - @return  The initial set, plus any grammar directly or indirectly exported by it
 -}
function expandCondBuilds
[String] ::= need::[String]  seen::[String]  triggers::[[String]]  e::EnvTree<Decorated RootSpec>
{
  -- Map each grammar name to its triggers, and concat.
  local attribute newtriggers :: [[String]];
  newtriggers = foldr(append, triggers, map((.condBuild), map(head, map(searchEnvTree(_, e), need))));

  local attribute newset :: [String];
  newset = need ++ seen;

  -- Find out about any new triggers as a result of adding 'need' to the set, plus need's triggers
  local attribute triggered :: [String];
  triggered = noninductiveExpansion(newset, newtriggers);

  return if null(need) || null(triggered) then newset
         -- If new triggers fire, continue with the new triggers as need:
         -- And don't forget anything exported by those triggers.
         else expandCondBuilds(expandExports(triggered, newset, e), newset, newtriggers, e);
}

{--
 - Does one iteration of expanding optionals.
 - What does that mean? Well, it means there may be exports / cond builds that aren't yet included.
 -}
function expandOptionalsIter
[String] ::= need::[String]  seen::[String]  e::EnvTree<Decorated RootSpec>
{
  local attribute g :: [Decorated RootSpec];
  g = searchEnvTree(head(need), e);

  return if null(need) then seen
         -- If the grammar has already been taken care of, or doesn't exist, discard it.
         else if contains(head(need), seen) || null(g) then expandOptionalsIter(tail(need), seen, e)
         -- Otherwise, tack its exported list to the need list, and add this grammar to the taken care of list.
         else expandOptionalsIter(tail(need) ++ head(g).optionalGrammars, head(need) :: seen, e);
}

{--
 - Follow all optionals, exports, and condbuilds to a full set.
 -}
function computeOptionalDeps
[String] ::= init::[String]  e::EnvTree<Decorated RootSpec>
{
  local eoi :: [String] = expandOptionalsIter(init, [], e);
  
  return if null(rem(eoi, init)) then init
         else computeOptionalDeps(computeDependencies(eoi, e), e);
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


