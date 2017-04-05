grammar silver:langutil;

synthesized attribute startLoc :: Location;
synthesized attribute endLoc :: Location;
synthesized attribute lexeme :: String;
synthesized attribute lexerClasses :: [String];
synthesized attribute grammarName :: String;
synthesized attribute terminalName :: String;

nonterminal TerminalDescriptor with startLoc, endLoc, lexeme, lexerClasses, grammarName, terminalName;

abstract production terminalDescriptor
top::TerminalDescriptor ::= startLoc::Location endLoc::Location lexeme::String lexerClasses::[String] grammarName::String terminalName::String
{
  top.startLoc = startLoc;
  top.endLoc = endLoc;
  top.lexeme = top.lexeme;
  top.lexerClasses = lexerClasses;
  top.grammarName = grammarName;
  top.terminalName = terminalName;
}
