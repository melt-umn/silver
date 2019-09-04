grammar silver:definition:core;

nonterminal Grammar with
  -- Global inherited attributes
  config, compiledGrammars, productionFlowGraphs, grammarFlowTypes,
  -- Grammar inherited attributes
  grammarName, env, globalImports, grammarDependencies,
  -- Synthesized attributes
  declaredName, moduleNames, exportedGrammars, optionalGrammars, condBuild,
  defs, importedDefs, grammarErrors;

{--
- A list of grammars that this grammar depends upon,
- directly or indirectly. (i.e. based on other grammar's exports)
- NOT including options.
-}
autocopy attribute grammarDependencies :: [String];
{--
 - Grammar-wide imports definitions.  Exists because we need to place
 - a file's individual imports between grammar definitions and grammar
 - wide imports.
 -}
autocopy attribute globalImports :: Decorated Env;
{--
 - The definitions resulting from grammar-wide imports definitions.
 - At the top of a grammar, these are echoed down as globalImports
 -}
synthesized attribute importedDefs :: [Def];
{--
 - An overall listing of error messages for a grammar
 -}
synthesized attribute grammarErrors :: [Pair<String [Message]>];

abstract production nilGrammar
top::Grammar ::=
{
  -- A value here is actually used. Grammars without any .sv files
  -- turn into this, and this "aren't found". TODO verify this is true?
  top.declaredName = ":null";
  top.moduleNames = [];
  top.exportedGrammars = [];
  top.optionalGrammars = [];
  top.condBuild = [];
  
  top.importedDefs = [];
  top.defs = [];
  top.grammarErrors = [];
}

abstract production consGrammar
top::Grammar ::= h::Root  t::Grammar
{
  top.declaredName = if h.declaredName == t.declaredName then h.declaredName else top.grammarName;
  top.moduleNames = h.moduleNames ++ t.moduleNames;
  top.exportedGrammars = h.exportedGrammars ++ t.exportedGrammars;
  top.optionalGrammars = h.optionalGrammars ++ t.optionalGrammars;
  top.condBuild = h.condBuild ++ t.condBuild;

  top.importedDefs = h.importedDefs ++ t.importedDefs;
  top.defs = h.defs ++ t.defs;
  top.grammarErrors =
    if null(h.errors) then t.grammarErrors
    else pair(h.location.filename, h.errors) :: t.grammarErrors;

}
