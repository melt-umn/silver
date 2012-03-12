grammar silver:definition:core;

attribute importedDefs, errors, allGrammarDependencies occurs on RootSpec;

{--
 - Echos the grammar's dependencies back upwards, so it's available
 - on RootSpecs.  This is mostly necessary because RootSpec is badly
 - designed at the moment... TODO: eventually make RootSpec non-decorated.
 -}
synthesized attribute allGrammarDependencies :: [String];

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.importedDefs = emptyDefs();
  top.errors := [];
  top.allGrammarDependencies = [];
}

function rootSpecRoot
Decorated RootSpec ::= c1::Decorated Root
{
  return decorate i_rootSpecRoot(c1) with {};
}
abstract production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{ 
  top.declaredName = c1.declaredName;
  top.moduleNames = makeSet(c1.moduleNames);
  top.allGrammarDependencies = c1.grammarDependencies; -- grabbing the inh

  top.importedDefs = c1.importedDefs;
  top.defs = c1.defs;
  top.exportedGrammars = c1.exportedGrammars;
  top.condBuild = c1.condBuild;

  top.errors := c1.errors;

  forwards to i_emptyRootSpec();
}

function consRootSpec
Decorated RootSpec ::= c1::Decorated Root c2::Decorated RootSpec
{
  return decorate i_appendRootSpec(rootSpecRoot(c1), c2) with {};
}

abstract production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.declaredName = c1.declaredName;
  top.moduleNames = makeSet(c1.moduleNames ++ c2.moduleNames);
  top.allGrammarDependencies = c1.allGrammarDependencies;

  top.importedDefs = appendDefs(c1.importedDefs, c2.importedDefs);
  top.defs = appendDefs(c1.defs, c2.defs);
  top.exportedGrammars = c1.exportedGrammars ++ c2.exportedGrammars;
  top.condBuild = c1.condBuild ++ c2.condBuild;

  top.errors := c1.errors ++ c2.errors;

  forwards to i_emptyRootSpec();
}

