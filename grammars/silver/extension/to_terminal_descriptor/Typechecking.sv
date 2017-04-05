grammar silver:extension:to_terminal_descriptor;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;

aspect production toTerminalDescriptorFunction
top::Expr ::= 'toTerminalDescriptor' '(' e::Expr ')'
{
  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;

  top.errors <-
    if (e.typerep.isTerminal)
    then []
    else [err(e.location, "Operand to toTerminalDescriptor must be a Terminal, instead it is " ++ prettyType(e.typerep))];
}
