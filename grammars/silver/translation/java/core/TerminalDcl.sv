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

public class ${className} extends common.Terminal {
  public ${className}(final String lexeme, final VirtualLocation vl, final int index, final int endIndex) {
    super(lexeme, vl, index, endIndex);
  }

  public ${className}(final common.StringCatter lexeme, final NLocation location) {
    super(lexeme, location);
  }

  @Override
  public String getName() { return "${className}"; }

  private static String[] lexerclasses = null;
  @Override
  public String[] getLexerClasses() {
    // Avoid doing more work at class-load time, in case we don't need this
    if (lexerclasses == null) {
      // FIXME: get the real lexerclasses
      lexerclasses = new String[] {"some:class", "z:another:class"};
    }
    return lexerclasses;
  }
}

""")];

}

