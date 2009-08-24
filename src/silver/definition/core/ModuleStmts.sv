grammar silver:definition:core;
import silver:definition:env;
import silver:util;

--grammar g as a only o hiding h with w;
--apply only
--apply hiding
--apply with
--apply as
abstract production module 
top::Module ::= c::[Decorated RootSpec] g::Decorated QName a::String o::[String] h::[String] w::[EnvMap] {

  top.errors := if !null(mitem) then [] else [err(g.location, "Grammar '" ++ g.name ++ "' cannot be found.")];
  top.warnings = [];

  production attribute mitem :: [Decorated RootSpec];
  mitem = getRootSpec(g.name, c);

  local attribute d :: Decorated Defs;
  d = if g.name == top.grammarName then head(mitem).defs else head(mitem).exportedDefs;  

  local attribute d1 :: Decorated Defs;
  d1 = if null(o) then d else filterDefs(keepFilter(o, getFullNames(o, d)), d);

  local attribute d2 :: Decorated Defs;
  d2 = if null(h) then d1 else filterDefs(removeFilter(h, getFullNames(h, d)), d1);

  local attribute d3 :: Decorated Defs;
  d3 = if null(w) then d2 else applyMappings(w, d2);

  local attribute d4 :: Decorated Defs;
  d4 = if a == "" then d3 else mapDefs(prependMap(a), d3);

  top.defs = if null(mitem) 
	     then emptyDefs() 
	     else d4;		  
}

abstract production fullNameFilter
top::EnvFilter ::= n::[String]
{
  local attribute item :: Decorated EnvItem;
  item = top.inEnvItem;

  top.keep = item.isFullNameDeclaration && contains(item.itemName, n);
}

function getFullNames
[String] ::= s::[String] d::Decorated Defs {
  return getNames(toItems(filterDefs(fullNameFilter(s), d)));
}

function getNames
[String] ::= e::[Decorated EnvItem] {
  return if null(e) then [] else [head(e).fullName] ++ getNames(tail(e));
}

concrete production importStmt
top::ImportStmt ::= 'import' m::ModuleExpr ';'{
  top.pp = "import " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := m.errors;
  top.warnings := m.warnings;
  top.moduleNames = m.moduleNames;
  top.importedDefs = m.defs;
}

abstract production importStmtsNone 
top::ImportStmts ::=
{
  top.pp = "";
  top.location = loc(top.file, -1,-1);

  top.errors := [];
  top.warnings := [];

  top.moduleNames = [];
  top.importedDefs = emptyDefs();
}

concrete production importStmtsOne 
top::ImportStmts ::= im::ImportStmt
{
  top.pp = im.pp;
  top.location = im.location;

  top.errors := im.errors;
  top.warnings := im.warnings;

  top.moduleNames = im.moduleNames;
  top.importedDefs = im.importedDefs;
}

concrete production importStmtsCons
top::ImportStmts ::= h::ImportStmt t::ImportStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.errors := h.errors ++ t.errors;
  top.warnings := t.warnings ++ t.warnings;

  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.importedDefs = appendDefs(h.importedDefs, t.importedDefs);
}

abstract production importStmtsAppend
top::ImportStmts ::= h::ImportStmts t::ImportStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.errors := h.errors ++ t.errors;
  top.warnings := t.warnings ++ t.warnings;

  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.importedDefs = appendDefs(h.importedDefs, t.importedDefs);
}


abstract production moduleStmtsNone 
top::ModuleStmts ::=
{
  top.pp = "";
  top.location = loc(top.file, -1,-1);

  top.errors := [];
  top.warnings := [];

  top.moduleNames = [];
  top.importedDefs = emptyDefs();
  top.exportedDefs = emptyDefs();
}

concrete production moduleStmtsOne 
top::ModuleStmts ::= m::ModuleStmt
{
  top.pp = m.pp;
  top.location = m.location;

  top.errors := m.errors;
  top.warnings := m.warnings;

  top.moduleNames = m.moduleNames;
  top.importedDefs = m.importedDefs;
  top.exportedDefs = m.exportedDefs;
}

concrete production moduleStmtsCons
top::ModuleStmts ::= h::ModuleStmt t::ModuleStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.errors := h.errors ++ t.errors;
  top.warnings := h.warnings ++ t.warnings;

  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.importedDefs = appendDefs(h.importedDefs, t.importedDefs);
  top.exportedDefs = appendDefs(h.exportedDefs, t.exportedDefs);
}

concrete production importsStmt
top::ModuleStmt ::= 'imports' m::ModuleExpr ';'{
  top.pp = "imports " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := m.errors;
  top.warnings := m.warnings;

  top.moduleNames = m.moduleNames;
  top.importedDefs = m.defs;
  top.exportedDefs = emptyDefs();
}

concrete production exportsStmt
top::ModuleStmt ::= 'exports' m::ModuleName ';'{
  top.pp = "exports " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := m.errors;
  top.warnings := m.warnings;

  top.moduleNames = m.moduleNames;
  top.exportedDefs = m.defs;
  top.importedDefs = emptyDefs();
}

concrete production moduleName
top::ModuleName ::= pkg::QName
{
  top.pp = pkg.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Decorated Module;
  m = decorate module(top.compiledGrammars, pkg, "", [], [], []) with {grammarName = top.grammarName;};

  top.warnings := m.warnings;
  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleAll
top::ModuleExpr ::= pkg::QName
{
  top.pp = pkg.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Decorated Module;
  m = decorate module(top.compiledGrammars, pkg, "", [], [], []) with {grammarName = top.grammarName;};

  top.warnings := m.warnings;
  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleAllWith
top::ModuleExpr ::= pkg::QName 'with' wc::WithElems
{
  top.pp = pkg.pp ++ " with " ++ wc.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Decorated Module;
  m = decorate module(top.compiledGrammars, pkg, "", [], [], wc.envMaps) with {grammarName = top.grammarName;};

  top.warnings := m.warnings;
  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleOnly
top::ModuleExpr ::= pkg::QName 'only' ns::NameList
{
  top.pp = pkg.pp ++ " only " ++ ns.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Decorated Module;
  m = decorate module(top.compiledGrammars, pkg, "", ns.names, [], []) with {grammarName = top.grammarName;};

  top.warnings := m.warnings;
  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleOnlyWith
top::ModuleExpr ::= pkg::QName 'only' ns::NameList 'with' wc::WithElems
{
  top.pp = pkg.pp ++ " only " ++ ns.pp ++ " with " ++ wc.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Decorated Module;
  m = decorate module(top.compiledGrammars, pkg, "", ns.names, [], wc.envMaps) with {grammarName = top.grammarName;};

  top.warnings := m.warnings;
  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleHiding
top::ModuleExpr ::= pkg::QName 'hiding' ns::NameList
{
  top.pp = pkg.pp ++ " hiding " ++ ns.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Decorated Module;
  m = decorate module(top.compiledGrammars, pkg, "", [], ns.names, []) with {grammarName = top.grammarName;};

  top.warnings := m.warnings;
  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleHidingWith
top::ModuleExpr ::= pkg::QName 'hiding' ns::NameList 'with' wc::WithElems 
{
  top.pp = pkg.pp ++ " hiding " ++ ns.pp ++ " with " ++ wc.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Decorated Module;
  m = decorate module(top.compiledGrammars, pkg, "", [], ns.names, wc.envMaps) with {grammarName = top.grammarName;};

  top.warnings := m.warnings;
  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleAs
top::ModuleExpr ::= pkg1::QName 'as' pkg2::QName
{
  top.pp = pkg1.pp ++ " as " ++ pkg2.pp;
  top.location = pkg1.location;
  top.moduleNames = [pkg1.name];

  production attribute m :: Decorated Module;
  m = decorate module(top.compiledGrammars, pkg1, pkg2.name, [], [], []) with {grammarName = top.grammarName;};

  top.warnings := m.warnings;
  top.errors := m.errors;
  top.defs = m.defs;
}


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

concrete production withElement
top::WithElem ::= n::QName 'as' newname::QName 
{
  top.pp = n.pp ++ " as " ++ newname.pp;
  top.location = loc(top.file, $2.line, $2.column);
  top.envMaps = [renameMap(n.name, newname.name)];
}


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

function applyMappings
Decorated Defs ::= maps::[EnvMap] old::Decorated Defs
{
  return if null(maps)
	 then old
	 else mapDefs(head(maps), applyMappings(tail(maps), old));
}

function applyFilters
Decorated Defs ::= fils::[EnvFilter] old::Decorated Defs
{
  return if null(fils) 
	 then old
	 else filterDefs(head(fils), applyFilters(tail(fils), old));
}
