grammar silver:extension:to_terminal_descriptor;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;

terminal ToTerminalDescriptor_kwd 'toTerminalDescriptor' lexer classes {BUILTIN,RESERVED};

concrete production toTerminalDescriptorFunction
top::Expr ::= 'toTerminalDescriptor' '(' e::Expr ')'
{
  top.pp = "toTerminalDescriptor(" ++ e.pp ++ ")";

  top.errors := e.errors;
  top.typerep = nonterminalTypeExp("TerminalDescriptor", []);
}
