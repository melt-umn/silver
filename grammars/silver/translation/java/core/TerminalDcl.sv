grammar silver:translation:java:core;

imports silver:definition:concrete_syntax;

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr
tm::TerminalModifiers
{
  local className :: String = "T" ++ id.name;


  top.genFiles := [pair(className ++ ".java",
s"""
package ${makeName(top.grammarName)};

import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import core.NLocation;
import core.Ploc;
import core.Alocation;

class ${className} extends common.Terminal {
  public ${className}(final String lexeme, final VirtualLocation vl, final int index, final int endIndex) {
    super(lexeme, vl, index, endIndex);
  }
}

""")];

}

