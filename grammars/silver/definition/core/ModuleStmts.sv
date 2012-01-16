grammar silver:definition:core;

--grammar g as a only o hiding h with w;
--apply only
--apply hiding
--apply with
--apply as
abstract production module 
top::Module ::= c::[Decorated RootSpec] g::Decorated QName a::String o::[String] h::[String] w::[Pair<String String>]
{
  production attribute med :: ModuleExportedDefs;
  med = moduleExportedDefs(c, [g.name], []);
  med.importLocation = g.location;
  
  local attribute d :: Defs;
  d = med.defs;  

  local attribute d1 :: Defs;
  d1 = if null(o) then d else filterDefsInclude(d, o); -- only

  local attribute d2 :: Defs;
  d2 = if null(h) then d1 else filterDefsExclude(d1, h); -- hiding

  local attribute d3 :: Defs;
  d3 = if null(w) then d2 else mapRenameDefs(d2, w); -- with

  local attribute d4 :: Defs;
  d4 = if a == "" then d3 else mapPrependDefs(d3, a ++ ":"); -- as

  top.defs = d4;		  
  top.errors := med.errors;
}

-- recurses through exportedGrammars, grabbing all definitions

inherited attribute importLocation :: Location;
nonterminal ModuleExportedDefs with defs, errors, importLocation;
abstract production moduleExportedDefs
top::ModuleExportedDefs ::= compiled::[Decorated RootSpec] need::[String] seen::[String]
{
  production attribute recurse :: ModuleExportedDefs;
  recurse = moduleExportedDefs(compiled, new_need, new_seen);
  recurse.importLocation = top.importLocation;
  
  local attribute gram :: String;
  gram = head(need);
  
  local attribute new_seen :: [String];
  new_seen = cons(gram, seen);
  
  production attribute rs :: [Decorated RootSpec];
  rs = getRootSpec(gram, compiled);
  
  production attribute add_to_need :: [String] with ++;
  add_to_need := head(rs).exportedGrammars;
  
  local attribute new_need :: [String];
  new_need = rem(makeSet(tail(need) ++ add_to_need), new_seen);
  
  top.defs = if null(need) || null(rs) then emptyDefs() else appendDefs(head(rs).defs, recurse.defs);
  top.errors := if null(need) then [] else 
             if null(rs) then [err(top.importLocation, "Grammar '" ++ gram ++ "' cannot be found.")] else recurse.errors;
}

-----------------------
-- ImportStmts

concrete production importStmt
top::ImportStmt ::= 'import' m::ModuleExpr ';'
{
  top.pp = "import " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := m.errors;
  top.moduleNames = m.moduleNames;
  top.importedDefs = m.defs;
}

abstract production importStmtsNone 
top::ImportStmts ::=
{
  top.pp = "";
  top.location = loc(top.file, -1,-1);

  top.errors := [];

  top.moduleNames = [];
  top.importedDefs = emptyDefs();
}

concrete production importStmtsOne 
top::ImportStmts ::= im::ImportStmt
{
  top.pp = im.pp;
  top.location = im.location;

  top.errors := im.errors;

  top.moduleNames = im.moduleNames;
  top.importedDefs = im.importedDefs;
}

concrete production importStmtsCons
top::ImportStmts ::= h::ImportStmt t::ImportStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.errors := h.errors ++ t.errors;

  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.importedDefs = appendDefs(h.importedDefs, t.importedDefs);
}

abstract production importStmtsAppend
top::ImportStmts ::= h::ImportStmts t::ImportStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.errors := h.errors ++ t.errors;

  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.importedDefs = appendDefs(h.importedDefs, t.importedDefs);
}

-----------------------
-- ModuleStmts

abstract production moduleStmtsNone 
top::ModuleStmts ::=
{
  top.pp = "";
  top.location = loc(top.file, -1,-1);

  top.errors := [];

  top.moduleNames = [];
  top.importedDefs = emptyDefs();
  top.exportedGrammars = [];
  top.condBuild = [];
}

concrete production moduleStmtsOne 
top::ModuleStmts ::= m::ModuleStmt
{
  top.pp = m.pp;
  top.location = m.location;

  top.errors := m.errors;

  top.moduleNames = m.moduleNames;
  top.importedDefs = m.importedDefs;
  top.exportedGrammars = m.exportedGrammars;
  top.condBuild = m.condBuild;
}

concrete production moduleStmtsCons
top::ModuleStmts ::= h::ModuleStmt t::ModuleStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.errors := h.errors ++ t.errors;

  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.importedDefs = appendDefs(h.importedDefs, t.importedDefs);
  top.exportedGrammars = h.exportedGrammars ++ t.exportedGrammars;
  top.condBuild = h.condBuild ++ t.condBuild;
}

concrete production importsStmt
top::ModuleStmt ::= 'imports' m::ModuleExpr ';'{
  top.pp = "imports " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := m.errors;

  top.moduleNames = m.moduleNames;
  top.importedDefs = m.defs;
  top.exportedGrammars = [];
  top.condBuild = [];
}

concrete production exportsStmt
top::ModuleStmt ::= 'exports' m::ModuleName ';'{
  top.pp = "exports " ++ m.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := m.errors;

  top.moduleNames = m.moduleNames;
  top.importedDefs = emptyDefs();
  top.exportedGrammars = m.moduleNames;
  top.condBuild = [];
}

concrete production buildsStmt
top::ModuleStmt ::= 'build' m::QName 'with' c::QName ';'{
  top.pp = "build " ++ m.pp ++ " with " ++ c.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  -- TODO: should check to make sure these grammars are found, somehow?
  top.errors := [];

  top.moduleNames = [];
  top.importedDefs = emptyDefs();
  top.exportedGrammars = [];
  top.condBuild = [[m.name, c.name]]; -- c -> m
}
  

-----------------------
-- ModuleExpr

concrete production moduleName
top::ModuleName ::= pkg::QName
{
  top.pp = pkg.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, pkg, "", [], [], []);
  m.grammarName = top.grammarName;

  top.errors := m.errors;
  top.defs = m.defs;
}

concrete production moduleAll
top::ModuleExpr ::= pkg::QName
{
  top.pp = pkg.pp;
  top.location = pkg.location;
  top.moduleNames = [pkg.name];

  production attribute m :: Module;
  m = module(top.compiledGrammars, pkg, "", [], [], []);
  m.grammarName = top.grammarName;

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
  m = module(top.compiledGrammars, pkg, "", [], [], wc.envMaps);
  m.grammarName = top.grammarName;

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
  m = module(top.compiledGrammars, pkg, "", ns.names, [], []);
  m.grammarName = top.grammarName;

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
  m = module(top.compiledGrammars, pkg, "", ns.names, [], wc.envMaps);
  m.grammarName = top.grammarName;

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
  m = module(top.compiledGrammars, pkg, "", [], ns.names, []);
  m.grammarName = top.grammarName;

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
  m = module(top.compiledGrammars, pkg, "", [], ns.names, wc.envMaps);
  m.grammarName = top.grammarName;

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
  m = module(top.compiledGrammars, pkg1, pkg2.name, [], [], []);
  m.grammarName = top.grammarName;

  top.errors := m.errors;
  top.defs = m.defs;
}

synthesized attribute envMaps :: [Pair<String String>] occurs on WithElems, WithElem;

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
  top.envMaps = [pair(n.name, newname.name)];
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

