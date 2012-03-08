grammar silver:definition:core;

nonterminal ModuleStmts with config, grammarName, file, location, pp, errors, moduleNames, defs, exportedGrammars, condBuild, compiledGrammars, grammarDependencies;
nonterminal ModuleStmt with config, grammarName, file, location, pp, errors, moduleNames, defs, exportedGrammars, condBuild, compiledGrammars, grammarDependencies;

nonterminal ImportStmt with config, grammarName, file, location, pp, errors, moduleNames, defs, compiledGrammars, grammarDependencies;
nonterminal ImportStmts with config, grammarName, file, location, pp, errors, moduleNames, defs, compiledGrammars, grammarDependencies;

nonterminal ModuleExpr with config, grammarName, file, location, pp, errors, moduleNames, defs, compiledGrammars, grammarDependencies;
nonterminal ModuleName with config, grammarName, file, location, pp, errors, moduleNames, defs, compiledGrammars, grammarDependencies;

nonterminal NameList with config, grammarName, file, location, pp, names;

nonterminal WithElems with config, grammarName, file, location, pp, envMaps;
nonterminal WithElem with config, grammarName, file, location, pp, envMaps;

{--
 - A list of QName strings. Used for 'only' and 'hiding'.
 -}
synthesized attribute names :: [String];
{--
 - A renaming mapping used for 'with'.
 -}
synthesized attribute envMaps :: [Pair<String String>];

-- TODO: eliminate, fold into ModuleName, make filter parameters inh attrs.
nonterminal Module with defs, errors;

abstract production module 
top::Module ::= compiledGrammars::EnvTree<Decorated RootSpec>
                grammarDependencies::[String]
                gram::Decorated QName
                asPrepend::String
                onlyFilter::[String]
                hidingFilter::[String]
                withRenames::[Pair<String String>]
{
  -- TODO: starting with 'gram.grammarName' in seen is a hack:
  -- what we really need is some way to eliminate duplicate imports.
  production attribute med :: ModuleExportedDefs;
  med = moduleExportedDefs(gram.location, compiledGrammars, grammarDependencies, [gram.name], [gram.grammarName]);
  
  local attribute d1 :: Defs;
  d1 = if null(onlyFilter) then med.defs else filterDefsInclude(med.defs, onlyFilter);

  local attribute d2 :: Defs;
  d2 = if null(hidingFilter) then d1 else filterDefsExclude(d1, hidingFilter);

  local attribute d3 :: Defs;
  d3 = if null(withRenames) then d2 else mapRenameDefs(d2, withRenames);

  local attribute d4 :: Defs;
  d4 = if asPrepend == "" then d3 else mapPrependDefs(d3, asPrepend ++ ":");

  top.defs = d4;		  
  top.errors := med.errors;
}

-- recurses through exportedGrammars, grabbing all definitions
nonterminal ModuleExportedDefs with defs, errors;

abstract production moduleExportedDefs
top::ModuleExportedDefs ::= l::Location compiledGrammars::EnvTree<Decorated RootSpec> grammarDependencies::[String] need::[String] seen::[String]
{
  production attribute recurse :: ModuleExportedDefs;
  recurse = moduleExportedDefs(l, compiledGrammars, grammarDependencies, new_need, new_seen);
  
  local attribute gram :: String;
  gram = head(need);
  
  local attribute new_seen :: [String];
  new_seen = cons(gram, seen);
  
  production attribute rs :: [Decorated RootSpec];
  rs = searchEnvTree(gram, compiledGrammars);
  
  production attribute add_to_need :: [String] with ++;
  add_to_need := head(rs).exportedGrammars ++ triggeredGrammars(grammarDependencies, head(rs).condBuild);
  
  local attribute new_need :: [String];
  new_need = rem(makeSet(tail(need) ++ add_to_need), new_seen);
  
  top.defs = if null(need) || null(rs) then emptyDefs() else appendDefs(head(rs).defs, recurse.defs);
  top.errors := if null(need) then [] else 
             if null(rs) then [err(l, "Grammar '" ++ gram ++ "' cannot be found.")] else recurse.errors;
}

function triggeredGrammars
[String] ::= grammarDependencies::[String]  trig::[[String]]
{
  return if null(trig) then []
         else if contains(head(tail(head(trig))), grammarDependencies) then head(head(trig)) :: triggeredGrammars(grammarDependencies, tail(trig))
         else triggeredGrammars(grammarDependencies, tail(trig));
}

--------------
-- ImportStmts

concrete production importStmt
top::ImportStmt ::= 'import' m::ModuleExpr ';'
{
  top.pp = "import " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := m.errors;
  top.moduleNames = m.moduleNames;
  top.defs = m.defs;
}

concrete production nilImportStmts
top::ImportStmts ::=
{
  top.pp = "";
  top.location = loc(top.file, -1,-1);

  top.errors := [];

  top.moduleNames = [];
  top.defs = emptyDefs();
}

concrete production consImportStmts
top::ImportStmts ::= h::ImportStmt t::ImportStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.errors := h.errors ++ t.errors;

  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.defs = appendDefs(h.defs, t.defs);
}

abstract production appendImportStmts
top::ImportStmts ::= h::ImportStmts t::ImportStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.errors := h.errors ++ t.errors;

  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.defs = appendDefs(h.defs, t.defs);
}

--------------
-- ModuleStmts

concrete production nilModuleStmts 
top::ModuleStmts ::=
{
  top.pp = "";
  top.location = loc(top.file, -1,-1);

  top.errors := [];

  top.moduleNames = [];
  top.defs = emptyDefs();
  top.exportedGrammars = [];
  top.condBuild = [];
}

concrete production consModulesStmts
top::ModuleStmts ::= h::ModuleStmt t::ModuleStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.errors := h.errors ++ t.errors;

  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.defs = appendDefs(h.defs, t.defs);
  top.exportedGrammars = h.exportedGrammars ++ t.exportedGrammars;
  top.condBuild = h.condBuild ++ t.condBuild;
}

concrete production importsStmt
top::ModuleStmt ::= 'imports' m::ModuleExpr ';'
{
  top.pp = "imports " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := m.errors;

  top.moduleNames = m.moduleNames;
  top.defs = m.defs;
  top.exportedGrammars = [];
  top.condBuild = [];
}

concrete production exportsStmt
top::ModuleStmt ::= 'exports' m::ModuleName ';'
{
  top.pp = "exports " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := m.errors;

  top.moduleNames = m.moduleNames;
  top.defs = emptyDefs();
  top.exportedGrammars = m.moduleNames;
  top.condBuild = [];
}

concrete production buildsStmt
top::ModuleStmt ::= 'build' m::QName 'with' c::QName ';'
{
  top.pp = "build " ++ m.pp ++ " with " ++ c.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  -- TODO: should check to make sure these grammars are found, somehow?
  top.errors := [];

  top.moduleNames = [];
  top.defs = emptyDefs();
  top.exportedGrammars = [];
  top.condBuild = [[m.name, c.name]]; -- c -> m
}
  

-----------------------
-- ModuleName

concrete production moduleName
top::ModuleName ::= pkg::QName
{
  top.pp = pkg.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, top.grammarDependencies, pkg, "", [], [], []);

  top.errors := m.errors;
  top.defs = m.defs;
}

-----------------------
-- ModuleExpr

concrete production moduleAll
top::ModuleExpr ::= pkg::QName
{
  top.pp = pkg.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, top.grammarDependencies, pkg, "", [], [], []);

  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleAllWith
top::ModuleExpr ::= pkg::QName 'with' wc::WithElems
{
  top.pp = pkg.pp ++ " with " ++ wc.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, top.grammarDependencies, pkg, "", [], [], wc.envMaps);

  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleOnly
top::ModuleExpr ::= pkg::QName 'only' ns::NameList
{
  top.pp = pkg.pp ++ " only " ++ ns.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, top.grammarDependencies, pkg, "", ns.names, [], []);

  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleOnlyWith
top::ModuleExpr ::= pkg::QName 'only' ns::NameList 'with' wc::WithElems
{
  top.pp = pkg.pp ++ " only " ++ ns.pp ++ " with " ++ wc.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, top.grammarDependencies, pkg, "", ns.names, [], wc.envMaps);

  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleHiding
top::ModuleExpr ::= pkg::QName 'hiding' ns::NameList
{
  top.pp = pkg.pp ++ " hiding " ++ ns.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, top.grammarDependencies, pkg, "", [], ns.names, []);

  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleHidingWith
top::ModuleExpr ::= pkg::QName 'hiding' ns::NameList 'with' wc::WithElems 
{
  top.pp = pkg.pp ++ " hiding " ++ ns.pp ++ " with " ++ wc.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, top.grammarDependencies, pkg, "", [], ns.names, wc.envMaps);

  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleAs
top::ModuleExpr ::= pkg1::QName 'as' pkg2::QName
{
  top.pp = pkg1.pp ++ " as " ++ pkg2.pp;
  top.location = pkg1.location;
  top.moduleNames = [pkg1.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, top.grammarDependencies, pkg1, pkg2.name, [], [], []);

  top.errors := m.errors;
  top.defs = m.defs;
}

------------
-- WithElems

concrete production withElemsOne
top::WithElems ::= we::WithElem
{
  top.pp = we.pp;
  top.location = we.location;
  top.envMaps = we.envMaps;
}

concrete production withElemsCons
top::WithElems  ::= h::WithElem ',' t::WithElems
{
  top.pp = h.pp ++ ", " ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);
  top.envMaps = h.envMaps ++ t.envMaps;
}

-- TODO: Should this just be 'Name', at least for the initial
concrete production withElement
top::WithElem ::= n::QName 'as' newname::QName 
{
  top.pp = n.pp ++ " as " ++ newname.pp;
  top.location = loc(top.file, $2.line, $2.column);
  top.envMaps = [pair(n.name, newname.name)];
}

-----------
-- NameList

concrete production nameListOne
top::NameList ::= n::QName
{
  top.pp = n.pp;
  top.location = n.location;
  top.names = [n.name];
}

concrete production nameListCons
top::NameList ::= h::QName ',' t::NameList
{
  top.pp = h.pp ++ ", " ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);
  top.names = [h.name] ++ t.names;
}

