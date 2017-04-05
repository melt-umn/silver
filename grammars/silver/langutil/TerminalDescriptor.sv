grammar silver:langutil;

synthesized attribute location :: Location;
synthesized attribute lexeme :: String;
synthesized attribute lexerClasses :: [String];
synthesized attribute grammarName :: String;
synthesized attribute terminalName :: String;

nonterminal TerminalDescriptor with location, lexeme, lexerClasses, grammarName, terminalName;

abstract production terminalDescriptor
top::TerminalDescriptor ::= location::Location lexeme::String lexerClasses::[String] grammarName::String terminalName::String
{
  top.location = location;
  top.lexeme = top.lexeme;
  top.lexerClasses = lexerClasses;
  top.grammarName = grammarName;
  top.terminalName = terminalName;
}
