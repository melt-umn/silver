grammar silver:driver:util;

import silver:reflect;
import silver:langutil only pp;
import silver:langutil:pp only show;

import silver:definition:core only Grammar, grammarErrors, grammarName, importedDefs, grammarDependencies, globalImports, Message, err;
import silver:definition:flow:env only flowEnv, flowDefs, fromFlowDefs;
import silver:definition:flow:ast only nilFlow, consFlow, FlowDef;

import silver:definition:core only jarName;

{--
 - A representation of a grammar, from an unknown source. TODO: rename GrammarSpec
 -}
nonterminal RootSpec with
  -- compiler-wide inherited attributes
  config, compiledGrammars, productionFlowGraphs, grammarFlowTypes,
  -- synthesized attributes
  declaredName, moduleNames, exportedGrammars, optionalGrammars, condBuild, allGrammarDependencies,
  defs, grammarErrors, grammarSource, grammarTime, interfaceTime, recheckGrammars, translateGrammars, parsingErrors, jarName;

{--
 - Grammars that were read from source.
 -}
synthesized attribute translateGrammars :: [Decorated RootSpec];

{--
 - Parse errors present in this grammar (only for errorRootSpec!)
 -}
synthesized attribute parsingErrors :: [Pair<String [Message]>];

{--
 - Create a RootSpec from a real grammar, a set of .sv files.
 -}
abstract production grammarRootSpec
top::RootSpec ::= g::Grammar  grammarName::String  grammarSource::String  grammarTime::Integer
{
  g.grammarName = grammarName;
  
  -- Create the environments for this grammar
  g.env = toEnv(g.defs);
  g.globalImports = toEnv(
    if contains("core", g.moduleNames) || grammarName == "core" then g.importedDefs
    else g.importedDefs ++ head(searchEnvTree("core", top.compiledGrammars)).defs);
  
  -- This grammar, its direct imports, and only transitively close over exports and TRIGGERED conditional imports.
  -- i.e. these are the things that we really, truly depend upon. (in the sense that we get their symbols)
  local actualDependencies :: [String] =
    makeSet(computeDependencies(grammarName :: top.moduleNames, top.compiledGrammars));

  -- Compute flow information for this grammar, (closing over imports and options, too:)
  local depsPlusOptions :: [String] =
    makeSet(completeDependencyClosure(actualDependencies, top.compiledGrammars));
  g.grammarDependencies = actualDependencies;
  g.flowEnv = fromFlowDefs(foldr(consFlow, nilFlow(), gatherFlowEnv(depsPlusOptions, top.compiledGrammars)));
  
  -- Echo down global compiler info
  g.config = top.config;
  g.compiledGrammars = top.compiledGrammars;
  
  top.grammarSource = grammarSource;
  top.grammarTime = grammarTime;
  top.interfaceTime = grammarTime;
  top.recheckGrammars = [];
  top.translateGrammars = [top];

  top.declaredName = g.declaredName;
  top.moduleNames = makeSet(g.moduleNames ++ ["core"]); -- Ensure the prelude is in the deps, always
  top.exportedGrammars = g.exportedGrammars;
  top.optionalGrammars = g.optionalGrammars;
  top.condBuild = g.condBuild;
  top.allGrammarDependencies = actualDependencies;
  
  top.defs = g.defs;
  top.grammarErrors = g.grammarErrors;
  top.parsingErrors = [];

  top.jarName = g.jarName;
}

{--
 - Create a RootSpec from an interface file, representing a grammar.
 -}
abstract production interfaceRootSpec
top::RootSpec ::= p::GrammarProperties  interfaceTime::Integer
{
  top.grammarSource = p.grammarSource;
  top.grammarTime = p.grammarTime;
  top.interfaceTime = interfaceTime;
  
  local ood :: Boolean = isOutOfDate(interfaceTime, top.allGrammarDependencies, top.compiledGrammars);
  top.recheckGrammars = if ood then [p.declaredName] else [];
  top.translateGrammars = [];

  top.declaredName = p.declaredName; 
  top.moduleNames = p.moduleNames;
  top.exportedGrammars = p.exportedGrammars;
  top.optionalGrammars = p.optionalGrammars;
  top.condBuild = p.condBuild;
  top.allGrammarDependencies = p.allGrammarDependencies;

  top.defs = p.defs;
  top.grammarErrors = []; -- TODO: consider getting grammarName and comparing against declaredName?
  top.parsingErrors = [];

  top.jarName = \grammarName :: String -> nothing();
}

{--
 - A RootSpec that represents a failure to parse (part) of a grammar.
 -}
abstract production errorRootSpec
top::RootSpec ::= e::[ParseError]  grammarName::String  grammarSource::String  grammarTime::Integer
{
  top.grammarSource = grammarSource;
  top.grammarTime = grammarTime;
  top.interfaceTime = grammarTime;
  
  top.recheckGrammars = [];
  top.translateGrammars = [];

  top.declaredName = grammarName; 
  top.moduleNames = [];
  top.exportedGrammars = [];
  top.optionalGrammars = [];
  top.condBuild = [];
  top.allGrammarDependencies = [];

  top.defs = [];
  top.grammarErrors = [];
  top.parsingErrors = map(parseErrorToMessage(grammarSource, _), e);

  top.jarName = \grammarName :: String -> nothing();
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

{--
 - Representation of all properties of a grammar, to be serialized/deserialize to/from an interface
 - file.
 -}
nonterminal GrammarProperties with declaredName, grammarSource, grammarTime, moduleNames, exportedGrammars, optionalGrammars, condBuild, allGrammarDependencies, defs;

abstract production consGrammarProperties
top::GrammarProperties ::= h::GrammarProperty t::GrammarProperties
{
  top.grammarSource = fromMaybe(t.grammarSource, h.maybeGrammarSource);
  top.grammarTime = fromMaybe(t.grammarTime, h.maybeGrammarTime);
  top.declaredName = fromMaybe(t.declaredName, h.maybeDeclaredName);
  top.moduleNames = fromMaybe(t.moduleNames, h.maybeModuleNames);
  top.exportedGrammars = fromMaybe(t.exportedGrammars, h.maybeExportedGrammars);
  top.optionalGrammars = fromMaybe(t.optionalGrammars, h.maybeOptionalGrammars);
  top.condBuild = fromMaybe(t.condBuild, h.maybeCondBuild);
  top.allGrammarDependencies = fromMaybe(t.allGrammarDependencies, h.maybeAllGrammarDependencies);
  top.defs = fromMaybe(t.defs, h.maybeDefs);
}

abstract production nilGrammarProperties
top::GrammarProperties ::=
{
  top.grammarSource = error("Grammar property grammarSource missing from interface file");
  top.grammarTime = error("Grammar property grammarTime missing from interface file");
  top.declaredName = error("Grammar property declaredName missing from interface file");
  top.moduleNames = error("Grammar property moduleNames missing from interface file");
  top.exportedGrammars = error("Grammar property exportedGrammars missing from interface file");
  top.optionalGrammars = error("Grammar property optionalGrammars missing from interface file");
  top.condBuild = error("Grammar property condBuild missing from interface file");
  top.allGrammarDependencies = error("Grammar property allGrammarDependencies missing from interface file");
  top.defs = error("Grammar property defs missing from interface file");
}

synthesized attribute maybeGrammarSource::Maybe<String>;
synthesized attribute maybeGrammarTime::Maybe<Integer>;
synthesized attribute maybeDeclaredName::Maybe<String>;
synthesized attribute maybeModuleNames::Maybe<[String]>;
synthesized attribute maybeExportedGrammars::Maybe<[String]>;
synthesized attribute maybeOptionalGrammars::Maybe<[String]>;
synthesized attribute maybeCondBuild::Maybe<[[String]]>;
synthesized attribute maybeAllGrammarDependencies::Maybe<[String]>;
synthesized attribute maybeDefs::Maybe<[Def]>;

closed nonterminal GrammarProperty with maybeGrammarSource, maybeGrammarTime, maybeDeclaredName, maybeModuleNames, maybeExportedGrammars, maybeOptionalGrammars, maybeCondBuild, maybeAllGrammarDependencies, maybeDefs;

aspect default production
top::GrammarProperty ::=
{
  top.maybeGrammarSource = nothing();
  top.maybeGrammarTime = nothing();
  top.maybeDeclaredName = nothing();
  top.maybeModuleNames = nothing();
  top.maybeExportedGrammars = nothing();
  top.maybeOptionalGrammars = nothing();
  top.maybeCondBuild = nothing();
  top.maybeAllGrammarDependencies = nothing();
  top.maybeDefs = nothing();
}

abstract production grammarSourceGrammarProperty
top::GrammarProperty ::= val::String
{
  top.maybeGrammarSource = just(val);
}

abstract production grammarTimeGrammarProperty
top::GrammarProperty ::= val::Integer
{
  top.maybeGrammarTime = just(val);
}

abstract production declaredNameGrammarProperty
top::GrammarProperty ::= val::String
{
  top.maybeDeclaredName = just(val);
}

abstract production moduleNamesGrammarProperty
top::GrammarProperty ::= val::[String]
{
  top.maybeModuleNames = just(val);
}

abstract production exportedGrammarsGrammarProperty
top::GrammarProperty ::= val::[String]
{
  top.maybeExportedGrammars = just(val);
}

abstract production optionalGrammarsGrammarProperty
top::GrammarProperty ::= val::[String]
{
  top.maybeOptionalGrammars = just(val);
}

abstract production condBuildGrammarProperty
top::GrammarProperty ::= val::[[String]]
{
  top.maybeCondBuild = just(val);
}

abstract production allDepsGrammarProperty
top::GrammarProperty ::= val::[String]
{
  top.maybeAllGrammarDependencies = just(val);
}

abstract production defsGrammarProperty
top::GrammarProperty ::= val::[Def]
{
  top.maybeDefs = just(val);
}

{--
 - How RootSpecs are turned into interface files shouldn't change
 - depending on what the source it, so we give this function externally
 - to the productions, instead of as an attribute.
 -}
function unparseRootSpec
String ::= r::Decorated RootSpec
{
  production attribute grammarProperties :: [GrammarProperty] with ++;
  grammarProperties := [
   	grammarSourceGrammarProperty(r.grammarSource),
   	grammarTimeGrammarProperty(r.grammarTime),
    declaredNameGrammarProperty(r.declaredName),
   	moduleNamesGrammarProperty(r.moduleNames),
    exportedGrammarsGrammarProperty(r.exportedGrammars),
    optionalGrammarsGrammarProperty(r.optionalGrammars),
    condBuildGrammarProperty(r.condBuild),
   	allDepsGrammarProperty(r.allGrammarDependencies),
    defsGrammarProperty(r.defs)
  ];
  
  return
    case serialize(foldr(consGrammarProperties, nilGrammarProperties(), grammarProperties)) of
    | left(msg) -> error("Fatal internal error generating interface file: \n" ++ show(80, reflect(foldr(consGrammarProperties, nilGrammarProperties(), grammarProperties)).pp) ++ "\n" ++ msg)
    | right(txt) -> txt
    end;
}

{--
 - All grammar names mentioned by this root spec (not transitive!)
 -}
function mentionedGrammars
[String] ::= r::Decorated RootSpec
{
  return makeSet(r.moduleNames ++ concat(r.condBuild) ++ r.optionalGrammars);
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

