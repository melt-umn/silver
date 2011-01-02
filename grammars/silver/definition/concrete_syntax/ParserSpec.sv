grammar silver:definition:concrete_syntax;

synthesized attribute startName :: String;

nonterminal ParserSpec with startName, nonTerminalDcls, terminalDcls, ruleDcls, fullName, moduleNames;

function parserSpecFromList
Decorated ParserSpec ::= locat::Decorated Location name::String start::String mods::[String] grams::[Decorated RootSpec]
{
  return decorate i_parserSpecFL(locat, name, start, mods, grams) with {};
}
abstract production i_parserSpecFL
top::ParserSpec ::= locat::Decorated Location name::String start::String mods::[String] grams::[Decorated RootSpec]
{
  top.fullName = name;
  top.startName = start;
  top.moduleNames = mods;
  
  production attribute med :: ModuleExportedDefs;
  med = moduleExportedDefs(grams, mods, []);
  med.importLocation = locat;

  top.ruleDcls = med.ruleDcls;
  top.terminalDcls = med.terminalDcls;
  top.nonTerminalDcls = med.nonTerminalDcls;
}

