grammar silver:definition:core;

import silver:util:cmdargs only CmdArgs; -- TODO: maybe we need to structure some of this better...

{--
 - Root represents one textual file of Silver source.
 -}
nonterminal Root with
  config, grammarName, file, env, location, pp, errors, defs, 
  declaredName, moduleNames, importedDefs, exportedGrammars, condBuild, compiledGrammars, globalImports;
nonterminal GrammarDcl with 
  config, grammarName, file, location, pp, errors, declaredName;

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
{--
 - All grammars Silver looked at. Despite the name, including interface files.
 -}
autocopy attribute compiledGrammars :: EnvTree<Decorated RootSpec>;
{--
 - Compiler configuration information, made available everywhere.
 -}
autocopy attribute config :: Decorated CmdArgs;

concrete production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  production attribute allImports :: ImportStmts with appendImportStmts;
  allImports := if top.grammarName == "core" || contains("core", ims.moduleNames)
		then ims 
		else consImportStmts(importStmt('import', moduleAll(qNameId(nameIdLower(terminal(IdLower_t, "core")))), ';'), ims);

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

