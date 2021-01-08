package common;

import edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.*;
import java.text.ParseException;
import java.util.List;

public final class CopperUtil {
  public static NonTerminal makeNonTerminal(String id, String pp,
                                            String type_) {
    return null;
  }

  public static Terminal makeTerminal(String id, String pp, Regex regex,
                                      Integer precedence, Object associativity,
                                      String type_, String code,
                                      List<StringCatter> classes, String prefix,
                                      List<StringCatter> submits,
                                      List<StringCatter> dominates) {
    return null;
  }

  public static TerminalClass makeTerminalClass(String id) {
    TerminalClass out = new TerminalClass();
    try {
      out.setName(id);
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
    return out;
  }
}
