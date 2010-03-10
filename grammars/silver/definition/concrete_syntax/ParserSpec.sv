grammar silver:definition:concrete_syntax;
import silver:definition:env;

import silver:definition:core;

synthesized attribute startName :: String;

nonterminal ParserSpec with startName, nonTerminalDcls, terminalDcls, ruleDcls, fullName, moduleNames;

function parserSpec
Decorated ParserSpec ::= ps::Decorated ParserDcl {
  return decorate i_parserSpec(ps) with {};
}
abstract production i_parserSpec
top::ParserSpec ::= ps::Decorated ParserDcl {
  top.fullName = ps.fullName;
  top.startName = ps.typerep.typeName;
  top.nonTerminalDcls = ps.nonTerminalDcls;
  top.terminalDcls = ps.terminalDcls;
  top.ruleDcls = ps.ruleDcls;
  top.moduleNames = ps.moduleNames;
}

function parserSpecFromList
Decorated ParserSpec ::= name::String start::String mods::[String] grams::[Decorated RootSpec] {
  return decorate i_parserSpecFL(name, start, mods, grams) with {};
}
abstract production i_parserSpecFL
top::ParserSpec ::= name::String start::String mods::[String] grams::[Decorated RootSpec] {
  top.fullName = name;
  top.startName = start;
  top.moduleNames = mods;
  
  production attribute med :: ModuleExportedDefs;
  med = moduleExportedDefs(grams, mods, []);
  med.importLocation = loc("INTERFACEFILEPARSERDECL", -1, -1);

  top.ruleDcls = med.ruleDcls;
  top.terminalDcls = med.terminalDcls;
  top.nonTerminalDcls = med.nonTerminalDcls;
}

