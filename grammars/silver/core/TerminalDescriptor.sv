grammar silver:core;

annotation lexeme :: String;
annotation lexerClasses :: [String];
annotation terminalLocation :: Location;
annotation terminalName :: String;

data TerminalDescriptor = terminalDescriptor
  with lexeme, lexerClasses, terminalLocation, terminalName;
derive Eq on TerminalDescriptor;
