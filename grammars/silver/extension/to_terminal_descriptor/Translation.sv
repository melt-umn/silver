grammar silver:extension:to_terminal_descriptor;

import silver:definition:core;
import silver:definition:type;
import silver:translation:java:core;

aspect production toTerminalDescriptorFunction
top::Expr ::= 'toTerminalDescriptor' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                    | terminalTypeExp(name) -> "TODO"
                    end;
  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication);
}
