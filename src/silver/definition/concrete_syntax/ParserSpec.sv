grammar silver:definition:concrete_syntax;
import silver:definition:env;

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
