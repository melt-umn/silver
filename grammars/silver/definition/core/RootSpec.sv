grammar silver:definition:core;

attribute importedDefs, errors occurs on RootSpec;

aspect production i_emptyRootSpec
top::RootSpec ::= 
{
  top.importedDefs = emptyDefs();
  top.errors := [];
}

function rootSpecRoot
Decorated RootSpec ::= c1::Decorated Root
{
  return decorate i_rootSpecRoot(c1) with {};
}
abstract production i_rootSpecRoot
top::RootSpec ::= c1::Decorated Root
{ 
  top.unparse = unparseRootSpec(top).unparse;
  top.declaredName = c1.declaredName;
  top.moduleNames = makeSet(c1.moduleNames);

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
  return decorate i_consRootSpec(c1, c2) with {};
}

abstract production i_consRootSpec
top::RootSpec ::= c1::Decorated Root c2::Decorated RootSpec
{
  forwards to i_appendRootSpec(rootSpecRoot(c1), c2);
}

function appendRootSpec
Decorated RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  return decorate i_appendRootSpec(c1, c2) with {};
}
abstract production i_appendRootSpec
top::RootSpec ::= c1::Decorated RootSpec c2::Decorated RootSpec
{
  top.unparse = unparseRootSpec(top).unparse;
  top.declaredName = c1.declaredName;
  top.moduleNames = makeSet(c1.moduleNames ++ c2.moduleNames);

  top.importedDefs = appendDefs(c1.importedDefs, c2.importedDefs);
  top.defs = appendDefs(c1.defs, c2.defs);
  top.exportedGrammars = c1.exportedGrammars ++ c2.exportedGrammars;
  top.condBuild = c1.condBuild ++ c2.condBuild;

  top.errors := c1.errors ++ c2.errors;

  forwards to i_emptyRootSpec();
}

