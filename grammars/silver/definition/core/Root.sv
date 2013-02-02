grammar silver:definition:core;

{--
 - Root represents one textual file of Silver source.
 -}
nonterminal Root with
  -- Global inherited attributes
  config, compiledGrammars,
  -- Grammar inherited attributes
  grammarName, env, globalImports, grammarDependencies,
  -- File inherited attributes
  file,
  -- Synthesized attributes
  declaredName, pp, location, errors, defs, moduleNames, importedDefs,
  exportedGrammars, optionalGrammars, condBuild;

nonterminal GrammarDcl with 
  declaredName, grammarName, file, location, pp, errors;

concrete production root
top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls
{
  ims.compiledGrammars = top.compiledGrammars;
  ims.grammarDependencies = top.grammarDependencies;
  ims.grammarName = top.grammarName;
  ims.file = top.file;
  ims.config = top.config;

  top.pp = gdcl.pp ++ "\n\n" ++ ms.pp ++ "\n\n" ++ ims.pp ++ "\n\n" ++ ags.pp;
  top.location = gdcl.location;
  top.declaredName = gdcl.declaredName;

  top.moduleNames = ims.moduleNames ++ ms.moduleNames ++ ags.moduleNames;

  top.defs = ags.defs;

  top.importedDefs = ms.defs;
  top.exportedGrammars = ms.exportedGrammars;
  top.optionalGrammars = ms.optionalGrammars;
  top.condBuild = ms.condBuild;

  top.errors := gdcl.errors ++ ms.errors ++ ims.errors ++ ags.errors;
  
  -- We have an mismatch in how the environment gets put together:
  --  Outermost, we have grammar-wide imports in one sope.  That's top.globalImports here.
  --  THEN, we have this particular file's list of local imports. That's ims.defs here.
  --  THEN, we have the grammar-wide definitions, from the whole grammr. That's top.env here.
  -- So we're kind of injecting local imports in between two grammar-wide things there.
  ags.env = appendEnv(top.env, newScopeEnv(ims.defs, top.globalImports));
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

