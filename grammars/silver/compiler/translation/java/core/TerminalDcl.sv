grammar silver:compiler:translation:java:core;

imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:modification:copper;

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers
{
  top.initProd := s"\t\tcommon.RTTIManager.registerTerminal(${makeName(top.grammarName)}.T${id.name}.terminalton);\n\n";
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
import silver.core.NLocation;

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

  public static final common.RTTIManager.Terminalton<${className}> terminalton = new Terminalton();

  public static final class Terminalton extends common.RTTIManager.Terminalton<${className}> {
    public ${className} construct(final common.StringCatter lexeme, final silver.core.NLocation location) {
      return new ${className}(lexeme, location);
    }
    public String getName() { return "${fName}"; }
  }

  public common.RTTIManager.Terminalton<${className}> getTerminalton() { return terminalton; }
}

""")];
}
