grammar silver:definition:core;
import silver:definition:env;
import silver:util;

concrete production root1
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls{
  forwards to root(gdcl, ms, ims, ags);
}

concrete production root2
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts{
  forwards to root(gdcl, ms, ims, agDclsOne(agDclNone()));
}

concrete production root3
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ags::AGDcls{
  forwards to root(gdcl, ms, importStmtsNone(), ags);
}

concrete production root4
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts{
  forwards to root(gdcl, ms, importStmtsNone(), agDclsOne(agDclNone()));
}

concrete production root5
top::Root ::= gdcl::GrammarDcl ims::ImportStmts ags::AGDcls{
  forwards to root(gdcl, moduleStmtsNone(), ims, ags);
}

concrete production root6
top::Root ::= gdcl::GrammarDcl ims::ImportStmts{
  forwards to root(gdcl, moduleStmtsNone(), ims, agDclsOne(agDclNone()));
}

concrete production root7
top::Root ::= gdcl::GrammarDcl ags::AGDcls{
  forwards to root(gdcl, moduleStmtsNone(), importStmtsNone(), ags);
}

concrete production root8
top::Root ::= gdcl::GrammarDcl{
  forwards to root(gdcl, moduleStmtsNone(), importStmtsNone(), agDclsOne(agDclNone()));
}

concrete production root9
top::Root ::= ims::ImportStmts ags::AGDcls{
  forwards to root(grammarDcl(top.grammarName), moduleStmtsNone(), ims, ags);
}

concrete production root10
top::Root ::= ims::ImportStmts{
  forwards to root(grammarDcl(top.grammarName), moduleStmtsNone(), ims, agDclsOne(agDclNone()));
}

concrete production root11
top::Root ::= ags::AGDcls{
  forwards to root(grammarDcl(top.grammarName), moduleStmtsNone(), importStmtsNone(), ags);
}

concrete production root12
top::Root ::= {
  forwards to root(grammarDcl(top.grammarName), moduleStmtsNone(), importStmtsNone(), agDclsOne(agDclNone()));
}

abstract production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  production attribute allImports :: ImportStmts with importStmtsAppend;
  allImports := if top.grammarName == "core" || contains("core", ims.moduleNames)
		then ims 
		else importStmtsCons(importStmt('import', moduleAll(qNameId(nameId(terminal(Id_t, "core")))), ';'), ims);

  allImports.compiledGrammars = top.compiledGrammars;
  allImports.grammarName = top.grammarName;
  allImports.file = top.file;

  top.pp = gdcl.pp ++ "\n\n" ++ ms.pp ++ "\n\n" ++ ims.pp ++ "\n\n" ++ ags.pp;
  top.location = gdcl.location;
  top.declaredName = gdcl.declaredName;
  top.impliedName = top.grammarName;

  top.moduleNames = allImports.moduleNames ++ ms.moduleNames ++ ags.moduleNames;

  top.defs = ags.defs;

  top.importedDefs = ms.importedDefs;
  top.exportedGrammars = ms.exportedGrammars;
  top.condBuild = ms.condBuild;

  top.errors := gdcl.errors ++ ms.errors ++ allImports.errors ++ ags.errors;
  top.warnings := gdcl.warnings ++ ms.warnings ++ allImports.warnings ++ ags.warnings;
  
  -- Entire grammar is in one, local, scope. Then file imports. Then grammar-wide imports.
  ags.env = appendEnv(top.env, newScopeEnv(allImports.importedDefs, top.globalImports));
}

abstract production grammarDcl
top::GrammarDcl ::= s::String{
  top.pp = "grammar " ++ s;
  top.location = loc(top.file, 1, 1);
  top.declaredName = s;
  top.errors := if s == top.grammarName then [] else [err(top.location, "Grammar declaration is incorrect: " ++ s)];
  top.warnings := [];
}

concrete production grammarDcl_c
top::GrammarDcl ::= 'grammar' qn::QName ';'
{
  forwards to grammarDcl(qn.name);
}


abstract production agDclNone
top::AGDcl ::=
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);

  top.defs = emptyDefs();
  top.errors := [];
  top.warnings := [];
  top.moduleNames = [];
}

concrete production agDclsOne
top::AGDcls ::= ag::AGDcl
{
  top.pp = ag.pp;
  top.location = ag.location;

  top.defs = ag.defs;
  top.errors := ag.errors;
  top.warnings := ag.warnings;
  top.moduleNames = ag.moduleNames;
}

concrete production agDclsCons
top::AGDcls ::= h::AGDcl t::AGDcls
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;
  top.warnings := h.warnings ++ t.warnings;
  top.moduleNames = h.moduleNames ++ t.moduleNames;
}

abstract production agDclsAppend
top::AGDcls ::= h::AGDcls t::AGDcls
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;
  top.warnings := h.warnings ++ t.warnings;
  top.moduleNames = h.moduleNames ++ t.moduleNames;
}

abstract production agDclAppend
top::AGDcl ::= h::AGDcl t::AGDcl
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;
  top.warnings := h.warnings ++ t.warnings;
  top.moduleNames = h.moduleNames ++ t.moduleNames;
}
