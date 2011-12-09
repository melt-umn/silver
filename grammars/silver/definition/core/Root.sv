grammar silver:definition:core;

concrete production root1
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  forwards to root(gdcl, ms, ims, ags);
}

concrete production root2
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts
{
  forwards to root(gdcl, ms, ims, agDclsOne(agDclNone()));
}

concrete production root3
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ags::AGDcls
{
  forwards to root(gdcl, ms, importStmtsNone(), ags);
}

concrete production root4
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts
{
  forwards to root(gdcl, ms, importStmtsNone(), agDclsOne(agDclNone()));
}

concrete production root5
top::Root ::= gdcl::GrammarDcl ims::ImportStmts ags::AGDcls
{
  forwards to root(gdcl, moduleStmtsNone(), ims, ags);
}

concrete production root6
top::Root ::= gdcl::GrammarDcl ims::ImportStmts
{
  forwards to root(gdcl, moduleStmtsNone(), ims, agDclsOne(agDclNone()));
}

concrete production root7
top::Root ::= gdcl::GrammarDcl ags::AGDcls
{
  forwards to root(gdcl, moduleStmtsNone(), importStmtsNone(), ags);
}

concrete production root8
top::Root ::= gdcl::GrammarDcl
{
  forwards to root(gdcl, moduleStmtsNone(), importStmtsNone(), agDclsOne(agDclNone()));
}

abstract production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  production attribute allImports :: ImportStmts with importStmtsAppend;
  allImports := if top.grammarName == "core" || contains("core", ims.moduleNames)
		then ims 
		else importStmtsCons(importStmt('import', moduleAll(qNameId(nameIdLower(terminal(IdLower_t, "core")))), ';'), ims);

  allImports.compiledGrammars = top.compiledGrammars;
  allImports.grammarName = top.grammarName;
  allImports.file = top.file;

  top.pp = gdcl.pp ++ "\n\n" ++ ms.pp ++ "\n\n" ++ ims.pp ++ "\n\n" ++ ags.pp;
  top.location = gdcl.location;
  top.declaredName = gdcl.declaredName;

  top.moduleNames = allImports.moduleNames ++ ms.moduleNames ++ ags.moduleNames;

  top.defs = ags.defs;

  top.importedDefs = ms.importedDefs;
  top.exportedGrammars = ms.exportedGrammars;
  top.condBuild = ms.condBuild;

  top.errors := gdcl.errors ++ ms.errors ++ allImports.errors ++ ags.errors;
  top.warnings := ags.warnings;
  
  -- We have an mismatch in how the environment gets put together:
  --  Outermost, we have grammar-wide imports in one sope.  That's top.globalImports here.
  --  THEN, we have this particular file's list of local imports. That's allImports.importedDefs here.
  --  THEN, we have the grammar-wide definitions, from the whole grammr. That's top.env here.
  -- So we're kind of injecting local imports in between two grammar-wide things there.
  ags.env = appendEnv(top.env, newScopeEnv(allImports.importedDefs, top.globalImports));
}

concrete production noGrammarDcl
top::GrammarDcl ::=
{
  top.pp = "";
  top.location = loc(top.file, 1, 1);
  top.declaredName = top.grammarName;
  top.errors := [];
}

concrete production grammarDcl_c
top::GrammarDcl ::= 'grammar' qn::QName ';'
{
  top.pp = "grammar " ++ qn.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);
  top.declaredName = qn.name;
  top.errors := 
    if qn.name == top.grammarName then []
    else [err(top.location, "Grammar declaration is incorrect: " ++ qn.name)];
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


abstract production agDclNone
top::AGDcl ::=
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);

  top.errors := [];

  forwards to agDclDefault(); 
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

abstract production agDclDefault
top::AGDcl ::=
{
  -- can't provide pp or location!
  top.moduleNames = [];
  top.defs = emptyDefs();
  --top.errors := []; -- should never be omitted, really.
  top.warnings := [];
  
}
