grammar silver:compiler:driver:util;

import silver:reflect;
import silver:reflect:nativeserialize;
import silver:langutil only pp;
import silver:langutil:pp only show;

import silver:compiler:definition:core only Grammar, grammarErrors, grammarName, importedDefs, importedOccursDefs, grammarDependencies, globalImports, Message, err;
import silver:compiler:definition:flow:env only flowEnv, flowDefs, specDefs, refDefs, fromFlowDefs;
import silver:compiler:definition:flow:ast only nilFlow, consFlow, FlowDef;

import silver:compiler:definition:core only jarName;

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
  defs, occursDefs, grammarErrors, grammarSource, grammarTime, interfaceTime, dirtyGrammars, recompiledGrammars,
  parsingErrors, jarName, generateLocation;

flowtype RootSpec = decorate {config, compiledGrammars, productionFlowGraphs, grammarFlowTypes, dependentGrammars};

propagate exportedGrammars, optionalGrammars, condBuild, defs, occursDefs on RootSpec;

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

{--
 - Create a RootSpec from a real grammar, a set of Silver files.
 -}
abstract production grammarRootSpec
top::RootSpec ::= g::Grammar  oldInterface::Maybe<InterfaceItems>  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{
  g.grammarName = grammarName;
  
  -- Create the environments for this grammar
  g.env = occursEnv(g.occursDefs, toEnv(g.defs));
  g.globalImports =
    occursEnv(
      if contains("silver:core", g.moduleNames) || grammarName == "silver:core" then g.importedOccursDefs
      else g.importedOccursDefs ++ head(searchEnvTree("silver:core", top.compiledGrammars)).occursDefs,
      toEnv(
        if contains("silver:core", g.moduleNames) || grammarName == "silver:core" then g.importedDefs
        else g.importedDefs ++ head(searchEnvTree("silver:core", top.compiledGrammars)).defs));
  
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
    fromFlowDefs(
      flatMap((.specDefs), rootSpecs),
      flatMap((.refDefs), rootSpecs),
      foldr(consFlow, nilFlow(), flatMap((.flowDefs), rootSpecs)));
  
  production newInterface::InterfaceItems = packInterfaceItems(top);
  production serInterface::ByteArray =
    case nativeSerialize(new(newInterface)) of
    | left(msg) -> error("Fatal internal error generating interface file: \n" ++ show(80, reflect(new(newInterface)).pp) ++ "\n" ++ msg)
    | right(txt) -> txt
    end;

  -- Echo down global compiler info
  g.config = top.config;
  g.compiledGrammars = top.compiledGrammars;
  
  top.grammarSource = grammarSource;
  top.grammarTime = grammarTime;
  top.interfaceTime = grammarTime;
  top.generateLocation = generateLocation;
  top.dirtyGrammars :=
    if oldInterface == just(newInterface)
    then unsafeTracePrint([], s"Interface for ${grammarName} matches\n")  -- Dependent grammars don't need to be re-translated
    else unsafeTracePrint(lookupAll(grammarName, top.dependentGrammars), s"Interface for ${grammarName} changed ${toString(oldInterface.isJust)}\nDependent grammars: ${implode(", ", lookupAll(grammarName, top.dependentGrammars))}\n");
  top.recompiledGrammars := [top];

  top.declaredName = g.declaredName;
  top.moduleNames := nub(g.moduleNames ++ ["silver:core"]); -- Ensure the prelude is in the deps, always
  top.allGrammarDependencies := actualDependencies;
  top.grammarErrors = g.grammarErrors;
  top.parsingErrors = [];

  top.jarName := g.jarName;
}

{--
 - Create a RootSpec from an interface file, representing a grammar.
 -}
abstract production interfaceRootSpec
top::RootSpec ::= i::InterfaceItems  interfaceTime::Integer  generateLocation::String
{
  top.grammarSource = i.maybeGrammarSource.fromJust;
  top.grammarTime = i.maybeGrammarTime.fromJust;
  top.interfaceTime = interfaceTime;
  top.generateLocation = generateLocation;
  
  local ood :: Boolean = isOutOfDate(interfaceTime, top.allGrammarDependencies, top.compiledGrammars);
  top.dirtyGrammars := if ood then [i.maybeDeclaredName.fromJust] else [];
  top.recompiledGrammars := [];

  top.declaredName = i.maybeDeclaredName.fromJust;
  propagate moduleNames, allGrammarDependencies;
  top.grammarErrors = []; -- TODO: consider getting grammarName and comparing against declaredName?
  top.parsingErrors = [];

  top.jarName := nothing();
}

{--
 - A RootSpec that represents a failure to parse (part) of a grammar.
 -}
abstract production errorRootSpec
top::RootSpec ::= e::[ParseError]  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{
  top.grammarSource = grammarSource;
  top.grammarTime = grammarTime;
  top.interfaceTime = grammarTime;
  top.generateLocation = generateLocation;
  
  top.dirtyGrammars := [];
  top.recompiledGrammars := [];

  top.declaredName = grammarName; 
  propagate moduleNames, allGrammarDependencies;
  top.grammarErrors = [];
  top.parsingErrors = map(parseErrorToMessage(grammarSource, _), e);

  top.jarName := nothing();
}

function parseErrorToMessage
Pair<String [Message]> ::= grammarSource::String  e::ParseError
{
  return case e of
  | syntaxError(str, locat, _, _) ->
      pair(locat.filename, 
        [err(locat,
          "Syntax error:\n" ++ str)])
  | unknownParseError(str, file) ->
      pair(file,
        [err(loc(grammarSource ++ file, -1, -1, -1, -1, -1, -1),
          "Unknown error while parsing:\n" ++ str)])
  end;
}

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
function mentionedGrammars
[String] ::= r::Decorated RootSpec
{
  return nub(r.moduleNames ++ concat(r.condBuild) ++ r.optionalGrammars);
}

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

