
package silver.definition.env.env_parser;

import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import core.NLocation;

public class TComma_t extends common.Terminal {
  public TComma_t(final String lexeme, final VirtualLocation vl, final int index, final int endIndex) {
    super(lexeme, vl, index, endIndex);
  }

  public TComma_t(final common.StringCatter lexeme, final NLocation location) {
    super(lexeme, location);
  }

  @Override
  public String getName() { return "silver:definition:env:env_parser:Comma_t"; }

  private static String[] lexerclasses = null;
  @Override
  public String[] getLexerClasses() {
    // Avoid doing more work at class-load time, in case we don't need this
    if (lexerclasses == null) {
      lexerclasses = new String[] {};
    }
    return lexerclasses;
  }
}

