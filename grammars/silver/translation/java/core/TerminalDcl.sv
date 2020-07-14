grammar silver:translation:java:core;

imports silver:definition:concrete_syntax;
imports silver:modification:copper;

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers
{
  top.genFiles := terminalTranslation(id.name, top.grammarName, tm.lexerClasses);
}

function terminalTranslation
[Pair<String String>] ::= name::String grammarName::String lexerClasses::[String]
{
  local className :: String = "T" ++ name;
  local fName :: String = grammarName ++ ":" ++ name;
  local lexerClassesStr :: String = implode(", ", map(\ s::String -> s"\"${s}\"", lexerClasses));

  return [pair(className ++ ".java", s"""
package ${makeName(grammarName)};

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
  public String getName() { return "${fName}"; }

  private static String[] lexerclasses = null;
  @Override
  public String[] getLexerClasses() {
    // Avoid doing more work at class-load time, in case we don't need this
    if (lexerclasses == null) {
      lexerclasses = new String[] {${lexerClassesStr}};
    }
    return lexerclasses;
  }
}

""")];
}
