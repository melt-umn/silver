grammar silver:definition:core;

{--
 - Root represents one textual file of Silver source.
 -}
nonterminal Root with
  grammarName, file, env, location, pp, errors, defs, 
  declaredName, moduleNames, importedDefs, exportedGrammars, condBuild, warnings, compiledGrammars, globalImports;
nonterminal GrammarDcl with 
  grammarName, file, location, pp, errors, declaredName;

{--
 - Grammar-wide imports definitions.  Exists because we need to place
 - a file's individual imports between grammar definitions and grammar
 - wide imports.
 -}
autocopy attribute globalImports :: Decorated Env;
{--
 - The definitions resulting from grammar-wide imports definitions.
 -}
synthesized attribute importedDefs :: Defs;


concrete production root1
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  forwards to root(gdcl, ms, ims, ags);
}

concrete production root3
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ags::AGDcls
{
  forwards to root(gdcl, ms, importStmtsNone(), ags);
}

concrete production root5
top::Root ::= gdcl::GrammarDcl ims::ImportStmts ags::AGDcls
{
  forwards to root(gdcl, moduleStmtsNone(), ims, ags);
}

concrete production root7
top::Root ::= gdcl::GrammarDcl ags::AGDcls
{
  forwards to root(gdcl, moduleStmtsNone(), importStmtsNone(), ags);
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

  top.importedDefs = ms.defs;
  top.exportedGrammars = ms.exportedGrammars;
  top.condBuild = ms.condBuild;

  top.errors := gdcl.errors ++ ms.errors ++ allImports.errors ++ ags.errors;
  top.warnings := ags.warnings;
  
  -- We have an mismatch in how the environment gets put together:
  --  Outermost, we have grammar-wide imports in one sope.  That's top.globalImports here.
  --  THEN, we have this particular file's list of local imports. That's allImports.defs here.
  --  THEN, we have the grammar-wide definitions, from the whole grammr. That's top.env here.
  -- So we're kind of injecting local imports in between two grammar-wide things there.
  ags.env = appendEnv(top.env, newScopeEnv(allImports.defs, top.globalImports));
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

