
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import core.NLocation;

public class TGNU_AlignOfUU_T extends common.Terminal {
  public TGNU_AlignOfUU_T(final String lexeme, final VirtualLocation vl, final int index, final int endIndex) {
    super(lexeme, vl, index, endIndex);
  }

  public TGNU_AlignOfUU_T(final common.StringCatter lexeme, final NLocation location) {
    super(lexeme, location);
  }

  @Override
  public String getName() { return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:GNU_AlignOfUU_T"; }

  private static String[] lexerclasses = null;
  @Override
  public String[] getLexerClasses() {
    // Avoid doing more work at class-load time, in case we don't need this
    if (lexerclasses == null) {
      lexerclasses = new String[] {"edu:umn:cs:melt:ableC:concretesyntax:Ckeyword"};
    }
    return lexerclasses;
  }
}

