grammar core;

synthesized attribute lexeme :: String;
synthesized attribute lexerClasses :: [String];
synthesized attribute terminalGrammarName :: String;
synthesized attribute terminalLocation :: Location;
synthesized attribute terminalName :: String;

nonterminal TerminalDescriptor with lexeme, lexerClasses, terminalGrammarName, terminalLocation, terminalName;

abstract production terminalDescriptor
top::TerminalDescriptor ::= lexeme::String lexerClasses::[String] terminalGrammarName::String terminalName::String terminalLocation::Location
{
  top.lexeme = top.lexeme;
  top.lexerClasses = lexerClasses;
  top.terminalGrammarName = terminalGrammarName;
  top.terminalLocation = terminalLocation;
  top.terminalName = terminalName;
}
