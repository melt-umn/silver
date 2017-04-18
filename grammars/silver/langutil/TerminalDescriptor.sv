grammar silver:langutil;

attribute unparse occurs on TerminalDescriptor;

aspect production terminalDescriptor
top::TerminalDescriptor ::= lexeme::String lexerClasses::[String] terminalName::String terminalLocation::Location
{
  local showString :: (String ::= String) = \s::String -> "'" ++ s ++ "'";
  top.unparse = "terminalDescriptor(" ++ showString(lexeme) ++ ", ["
             ++ implode(", ", map(showString, lexerClasses)) ++ "], "
             ++ showString(terminalName) ++ ", "
             ++ terminalLocation.unparse ++ ")";
}
