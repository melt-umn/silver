grammar core;

synthesized attribute lexeme :: String;
synthesized attribute lexerClasses :: [String];
synthesized attribute terminalLocation :: Location;
synthesized attribute terminalName :: String;

nonterminal TerminalDescriptor with lexeme, lexerClasses, terminalLocation, terminalName;

abstract production terminalDescriptor
top::TerminalDescriptor ::= lexeme::String lexerClasses::[String] terminalName::String terminalLocation::Location
{
  top.lexeme = top.lexeme;
  top.lexerClasses = lexerClasses;
  top.terminalLocation = terminalLocation;
  top.terminalName = terminalName;
}
