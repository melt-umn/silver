grammar silver:core;

synthesized attribute lexeme :: String;
synthesized attribute lexerClasses :: [String];
synthesized attribute terminalLocation :: Location;
synthesized attribute terminalName :: String;

data nonterminal TerminalDescriptor with lexeme, lexerClasses, terminalLocation, terminalName;

-- TODO: Derive this
instance Eq TerminalDescriptor {
  eq = \ x::TerminalDescriptor y::TerminalDescriptor ->
    x.lexeme == y.lexeme &&
    x.lexerClasses == y.lexerClasses &&
    x.terminalName == y.terminalName &&
    x.terminalLocation == y.terminalLocation;
}

abstract production terminalDescriptor
top::TerminalDescriptor ::= lexeme::String lexerClasses::[String] terminalName::String terminalLocation::Location
{
  top.lexeme = lexeme;
  top.lexerClasses = lexerClasses;
  top.terminalLocation = terminalLocation;
  top.terminalName = terminalName;
}
