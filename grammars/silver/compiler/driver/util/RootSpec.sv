grammar silver:compiler:driver:util;

import silver:reflect;
import silver:reflect:nativeserialize;
import silver:langutil;
import silver:langutil:pp only show;

import silver:compiler:definition:core only Grammar, grammarErrors, allFileErrors, grammarName, importedDefs, importedOccursDefs, grammarDependencies, globalImports;
import silver:compiler:definition:flow:env only flowEnv, flowDefs, specDefs, refDefs, fromFlowDefs;
import silver:compiler:definition:flow:ast only nilFlow, consFlow, FlowDef;

import silver:compiler:definition:core only jarName;

import silver:compiler:analysis:warnings:flow only warnMissingInh;
import silver:compiler:analysis:uniqueness;

{--
 - A representation of a grammar, from an unknown source. TODO: rename GrammarSpec
 -}
nonterminal RootSpec with
  -- compiler-wide inherited attributes
  config, compiledGrammars, productionFlowGraphs, grammarFlowTypes,
  -- driver-specific inherited attributes
  dependentGrammars,
  -- synthesized attributes
  declaredName, moduleNames, exportedGrammars, optionalGrammars, condBuild, allGrammarDependencies,
  defs, occursDefs, grammarErrors, grammarSource, grammarTime, dirtyGrammars, recompiledGrammars,
  parsingErrors, allFileErrors, jarName, generateLocation, serInterface;

flowtype RootSpec = decorate {config, compiledGrammars, productionFlowGraphs, grammarFlowTypes, dependentGrammars};

propagate productionFlowGraphs, grammarFlowTypes, exportedGrammars, optionalGrammars, condBuild, defs, occursDefs on RootSpec;

{--
 - Grammars (a, b) where b depends on a
 -}
inherited attribute dependentGrammars :: [(String, String)];

{--
 - Grammars that must be recompiled
 -}
monoid attribute dirtyGrammars :: [String];

{--
 - Grammars that have been recompiled
 -}
monoid attribute recompiledGrammars :: [Decorated RootSpec];

{--
 - Parse errors present in this grammar (only for errorRootSpec!)
 -}
synthesized attribute parsingErrors :: [Pair<String [Message]>];

{-- Where generated files are or should be created -}
synthesized attribute generateLocation :: String;

{-- The serialized interface file for this grammar -}
synthesized attribute serInterface :: ByteArray;

{--
 - Create a RootSpec from a real grammar, a set of Silver files.
 -}
abstract production grammarRootSpec
top::RootSpec ::= g::Grammar  oldInterface::Maybe<InterfaceItems>  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{
  g.grammarName = grammarName;
  
  -- Create the environments for this grammar
  g.env = toEnv(g.defs, g.occursDefs);

  -- silver:core gets implicitly imported in a new outermost scope, unless imported explicitly
  local coreGrammar::Decorated RootSpec = head(searchEnvTree("silver:core", top.compiledGrammars));
  local coreEnv::Env =
    if contains("silver:core", g.moduleNames) || grammarName == "silver:core"
    then emptyEnv()
    else toEnv(coreGrammar.defs, coreGrammar.occursDefs);
  g.globalImports = occursEnv(g.importedOccursDefs, newScopeEnv(g.importedDefs, coreEnv));
  
  -- This grammar, its direct imports, and only transitively close over exports and TRIGGERED conditional imports.
  -- i.e. these are the things that we really, truly depend upon. (in the sense that we get their symbols)
  local actualDependencies :: [String] =
    nub(computeDependencies(grammarName :: top.moduleNames, top.compiledGrammars));

  -- Compute flow information for this grammar, (closing over imports and options, too:)
  local depsPlusOptions :: [String] =
    nub(completeDependencyClosure(actualDependencies, top.compiledGrammars));
  local rootSpecs :: [Decorated RootSpec] = flatMap(searchEnvTree(_, top.compiledGrammars), depsPlusOptions);
  g.grammarDependencies = actualDependencies;
  g.flowEnv =
    flowEnv(
      flatMap((.specDefs), rootSpecs),
      flatMap((.refDefs), rootSpecs),
      flatMap((.sharedRefs), rootSpecs),
      foldr(consFlow, nilFlow(), flatMap((.flowDefs), rootSpecs)));
  
  nondecorated production newInterface::InterfaceItems = packInterfaceItems(top);
  top.serInterface =
    case nativeSerialize(newInterface) of
    | left(msg) -> error("Fatal internal error generating interface file: \n" ++ show(80, reflect(newInterface).pp) ++ "\n" ++ msg)
    | right(ser) -> ser
    end;

  -- Echo down global compiler info
  g.config = top.config;
  g.compiledGrammars = top.compiledGrammars;
  
  top.grammarSource = grammarSource;
  top.grammarTime = grammarTime;
  top.generateLocation = generateLocation;
  top.dirtyGrammars :=
    -- If the interface file is unchanged and we aren't running the flow analysis,
    -- we don't need to rebuild the dependent grammars.
    -- If we are running the flow analysis, then we unconditionally rebuild all
    -- dependent grammars to propagate changes in flow deps, since we ignore flow
    -- defs when comparing interface files.
    -- This also avoids a circularity issue: computing whether an interface file
    -- changed depends on error checking, which depends on computed flow types when
    -- the flow analysis is enabled, which depends on which grammars were compiled.
    if !top.config.warnMissingInh && oldInterface == just(newInterface)
    then []  -- Dependent grammars don't need to be re-translated
    else lookupAll(grammarName, top.dependentGrammars);
  {- Useful for debugging:
  top.dirtyGrammars <- unsafeTracePrint([],
    if oldInterface == just(newInterface)
    then s"Interface for ${grammarName} unchanged\n"
    else s"Interface for ${grammarName} changed\nDependent grammars: ${implode(", ", lookupAll(grammarName, top.dependentGrammars))}\n");-}

  top.recompiledGrammars := [top];

  top.declaredName = g.declaredName;
  top.moduleNames := nub(g.moduleNames ++ ["silver:core"]); -- Ensure the prelude is in the deps, always
  top.allGrammarDependencies := actualDependencies;

  top.grammarErrors = filter(\ fe::(String, [Message]) -> !null(fe.2), top.allFileErrors);
  top.parsingErrors = [];

  production attribute extraFileErrors::[(String, [Message])] with ++;
  extraFileErrors := [];

  -- Seed flow deps with {compiledGrammars, config}
  extraFileErrors <- if false then error(genericShow((top.compiledGrammars, top.config))) else [];

  top.allFileErrors = map(
    \ fe::(String, [Message]) -> case fe of (fileName, fileErrors) ->
      (fileName, fileErrors ++ concat(lookupAll(fileName, extraFileErrors)))
    end,
    g.allFileErrors);

  top.jarName := g.jarName;
}

{--
 - Create a RootSpec from an interface file, representing a grammar.
 -}
abstract production interfaceRootSpec
top::RootSpec ::= i::InterfaceItems  generateLocation::String
{
  top.grammarSource = i.maybeGrammarSource.fromJust;
  top.grammarTime = i.maybeGrammarTime.fromJust;
  top.generateLocation = generateLocation;
  
  local ood :: Boolean = isOutOfDate(i.maybeGrammarTime.fromJust, top.allGrammarDependencies, top.compiledGrammars);
  top.dirtyGrammars := if ood then [i.maybeDeclaredName.fromJust] else [];
  top.recompiledGrammars := [];

  top.declaredName = i.maybeDeclaredName.fromJust;
  propagate moduleNames, allGrammarDependencies;
  top.grammarErrors = []; -- TODO: consider getting grammarName and comparing against declaredName?
  top.parsingErrors = [];
  top.allFileErrors = [];

  top.jarName := nothing();
  top.serInterface =
    case nativeSerialize(^i) of
    | left(msg) -> error("Fatal internal error generating interface file: \n" ++ show(80, reflect(^i).pp) ++ "\n" ++ msg)
    | right(ser) -> ser
    end;
}

{--
 - A RootSpec that represents a failure to parse (part) of a grammar.
 -}
abstract production errorRootSpec
top::RootSpec ::= e::[ParseError]  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{
  top.grammarSource = grammarSource;
  top.grammarTime = grammarTime;
  top.generateLocation = generateLocation;
  
  top.dirtyGrammars := [];
  top.recompiledGrammars := [];

  top.declaredName = grammarName; 
  propagate moduleNames, allGrammarDependencies;
  top.grammarErrors = [];
  top.parsingErrors = map(parseErrorToMessage(grammarSource, _), e);
  top.allFileErrors = top.parsingErrors;

  top.jarName := nothing();
  top.serInterface = error("errorRootSpec demanded interface");
}

fun parseErrorToMessage Pair<String [Message]> ::= grammarSource::String  e::ParseError =
  case e of
  | syntaxError(str, locat, _, _) ->
      (locat.filename, 
        [err(locat,
          "Syntax error:\n" ++ str)])
  | unknownParseError(str, file) ->
      (file,
        [err(loc(grammarSource ++ file, -1, -1, -1, -1, -1, -1),
          "Unknown error while parsing:\n" ++ str)])
  end;

monoid attribute maybeGrammarSource::Maybe<String> with nothing(), orElse;
monoid attribute maybeGrammarTime::Maybe<Integer> with nothing(), orElse;
monoid attribute maybeDeclaredName::Maybe<String> with nothing(), orElse;
monoid attribute hasModuleNames::Boolean with false, ||;
monoid attribute hasExportedGrammars::Boolean with false, ||;
monoid attribute hasOptionalGrammars::Boolean with false, ||;
monoid attribute hasCondBuild::Boolean with false, ||;
monoid attribute hasAllGrammarDependencies::Boolean with false, ||;
monoid attribute hasDefs::Boolean with false, ||;
monoid attribute hasOccursDefs::Boolean with false, ||;

monoid attribute interfaceErrors::[String];

{--
 - Representation of all environment info for a grammar, to be serialized/deserialize to/from an interface
 - file.
 -}
nonterminal InterfaceItems with
  maybeGrammarSource, maybeGrammarTime, maybeDeclaredName,
  moduleNames, exportedGrammars, optionalGrammars, condBuild, allGrammarDependencies, defs, occursDefs, interfaceErrors,
  hasModuleNames, hasExportedGrammars, hasOptionalGrammars, hasCondBuild, hasAllGrammarDependencies, hasDefs, hasOccursDefs,
  compareTo, isEqual;

propagate
  maybeGrammarSource, maybeGrammarTime, maybeDeclaredName,
  moduleNames, exportedGrammars, optionalGrammars, condBuild, allGrammarDependencies, defs, occursDefs,
  hasModuleNames, hasExportedGrammars, hasOptionalGrammars, hasCondBuild, hasAllGrammarDependencies, hasDefs, hasOccursDefs,
  compareTo, isEqual
  on InterfaceItems; 

abstract production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors := [];
  top.interfaceErrors <- if !top.maybeGrammarSource.isJust then ["Missing item grammarSource"] else [];
  top.interfaceErrors <- if !top.maybeGrammarTime.isJust then ["Missing item grammarTime"] else [];
  top.interfaceErrors <- if !top.maybeDeclaredName.isJust then ["Missing item declaredName"] else [];
  top.interfaceErrors <- if !top.hasModuleNames then ["Missing item moduleNames"] else [];
  top.interfaceErrors <- if !top.hasExportedGrammars then ["Missing item exportedGrammars"] else [];
  top.interfaceErrors <- if !top.hasOptionalGrammars then ["Missing item optionalGrammars"] else [];
  top.interfaceErrors <- if !top.hasCondBuild then ["Missing item condBuild"] else [];
  top.interfaceErrors <- if !top.hasAllGrammarDependencies then ["Missing item allGrammarDependencies"] else [];
  top.interfaceErrors <- if !top.hasDefs then ["Missing item defs"] else [];
  top.interfaceErrors <- if !top.hasOccursDefs then ["Missing item occursDefs"] else [];
}

abstract production nilInterfaceItem
top::InterfaceItems ::=
{
  top.interfaceErrors := ["Missing all items"];
}

closed nonterminal InterfaceItem with
  maybeGrammarSource, maybeGrammarTime, maybeDeclaredName,
  moduleNames, exportedGrammars, optionalGrammars, condBuild, allGrammarDependencies, defs, occursDefs,
  hasModuleNames, hasExportedGrammars, hasOptionalGrammars, hasCondBuild, hasAllGrammarDependencies, hasDefs, hasOccursDefs,
  compareTo, isEqual;

propagate
  moduleNames, exportedGrammars, optionalGrammars, condBuild, allGrammarDependencies, defs, occursDefs,
  hasModuleNames, hasExportedGrammars, hasOptionalGrammars, hasCondBuild, hasAllGrammarDependencies, hasDefs, hasOccursDefs
  on InterfaceItem;

aspect default production
top::InterfaceItem ::=
{
  propagate
    maybeGrammarSource, maybeGrammarTime, maybeDeclaredName,
    moduleNames, exportedGrammars, optionalGrammars, condBuild, allGrammarDependencies, defs, occursDefs,
    hasModuleNames, hasExportedGrammars, hasOptionalGrammars, hasCondBuild, hasAllGrammarDependencies, hasDefs, hasOccursDefs;
}

abstract production grammarSourceInterfaceItem
top::InterfaceItem ::= val::String
{
  propagate isEqual;
  top.maybeGrammarSource := just(val);
}

abstract production grammarTimeInterfaceItem
top::InterfaceItem ::= val::Integer
{
  top.isEqual = true;  -- Ignore
  top.maybeGrammarTime := just(val);
}

abstract production declaredNameInterfaceItem
top::InterfaceItem ::= val::String
{
  propagate isEqual;
  top.maybeDeclaredName := just(val);
}

abstract production moduleNamesInterfaceItem
top::InterfaceItem ::= val::[String]
{
  propagate isEqual;
  top.moduleNames <- val;
  top.hasModuleNames <- true;
}

abstract production exportedGrammarsInterfaceItem
top::InterfaceItem ::= val::[String]
{
  propagate isEqual;
  top.exportedGrammars <- val;
  top.hasExportedGrammars <- true;
}

abstract production optionalGrammarsInterfaceItem
top::InterfaceItem ::= val::[String]
{
  propagate isEqual;
  top.optionalGrammars <- val;
  top.hasOptionalGrammars <- true;
}

abstract production condBuildInterfaceItem
top::InterfaceItem ::= val::[[String]]
{
  propagate isEqual;
  top.condBuild <- val;
  top.hasCondBuild <- true;
}

abstract production allDepsInterfaceItem
top::InterfaceItem ::= val::[String]
{
  propagate isEqual;
  top.allGrammarDependencies <- val;
  top.hasAllGrammarDependencies <- true;
}

abstract production defsInterfaceItem
top::InterfaceItem ::= val::[Def]
{
  propagate isEqual;
  top.defs <- val;
  top.hasDefs <- true;
}

abstract production occursDefsInterfaceItem
top::InterfaceItem ::= val::[OccursDclInfo]
{
  propagate isEqual;
  top.occursDefs <- val;
  top.hasOccursDefs <- true;
}

{--
 - How RootSpecs are turned into interface files shouldn't change
 - depending on what the source it, so we give this function externally
 - to the productions, instead of as an attribute.
 -}
function packInterfaceItems
InterfaceItems ::= r::Decorated RootSpec
{
  production attribute interfaceItems :: [InterfaceItem] with ++;
  interfaceItems := [
    grammarSourceInterfaceItem(r.grammarSource),
    grammarTimeInterfaceItem(r.grammarTime),
    declaredNameInterfaceItem(r.declaredName),
    moduleNamesInterfaceItem(r.moduleNames),
    exportedGrammarsInterfaceItem(r.exportedGrammars),
    optionalGrammarsInterfaceItem(r.optionalGrammars),
    condBuildInterfaceItem(r.condBuild),
    allDepsInterfaceItem(r.allGrammarDependencies),
    defsInterfaceItem(r.defs),
    occursDefsInterfaceItem(r.occursDefs)
  ];
  
  return foldr(consInterfaceItem, nilInterfaceItem(), interfaceItems);
}

{--
 - All grammar names mentioned by this root spec (not transitive!)
 -}
fun mentionedGrammars [String] ::= r::Decorated RootSpec =
  nub(r.moduleNames ++ concat(r.condBuild) ++ r.optionalGrammars);

-- We're comparing INTERFACE TIME against GRAMMAR TIME, just to emphasize what's going on here...
function isOutOfDate
Boolean ::= mine::Integer  l::[String]  e::EnvTree<Decorated RootSpec>
{
  local n :: [Decorated RootSpec] = searchEnvTree(head(l), e);

  return if null(l) then
    false
  else if null(n) || mine >= head(n).grammarTime then
    isOutOfDate(mine, tail(l), e)
  else
    true;
}

{--
 - Write out the interface file for root spec.
 - Note that this is normally done during translation, however in the language
 - server implementation we would like to just write out interface files
 - without translating.
 -}
function writeInterface
IO<()> ::= silverGen::String r::Decorated RootSpec
{
  local srcPath :: String = silverGen ++ "src/" ++ grammarToPath(r.declaredName);

  return do {
    eprintln("\t[" ++ r.declaredName ++ "]");
    isD::Boolean <- isDirectory(srcPath);
    unless(isD, do {
      mkDSuccess::Boolean <- mkdir(srcPath);
      unless(mkDSuccess, do {
      eprintln("Unrecoverable Error: Unable to create directory: " ++ srcPath);
        exit(-5);
      });
    });
    deleteDirFiles(srcPath);
    writeBinaryFile(srcPath ++ "Silver.svi", r.serInterface);
  };
}
