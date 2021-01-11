package common;

import edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.*;
import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import edu.umn.cs.melt.copper.runtime.io.Location;
import java.text.ParseException;
import java.util.List;

public final class CopperUtil {
  private static Location LOCATION = new VirtualLocation("<silver>", -1, -1);

  public static CopperElementReference makeElementReference(String grammarName,
                                                            String name) {
    try {
      return CopperElementReference.ref(CopperElementName.newName(grammarName),
                                        name, LOCATION);
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static NonTerminal makeNonTerminal(String id, String pp,
                                            String type_) {
    throw new RuntimeException("TODO CopperUtil.makeNonTerminal");
  }

  public static ParserBean
  makeParserBean(String id, String pp, String grammarName,
                 CopperElementReference startSymbol,
                 List<CopperElementReference> startLayout,
                 String parserClassAuxCode, String parserInitCode,
                 String preambleCode, List<Grammar> grammars) {
    throw new RuntimeException("TODO CopperUtil.makeParserBean");
  }

  public static Terminal makeTerminal(String id, String pp, Regex regex,
                                      Integer precedence, Object associativity,
                                      String type_, String code,
                                      List<StringCatter> classes, String prefix,
                                      List<StringCatter> submits,
                                      List<StringCatter> dominates) {
    throw new RuntimeException("TODO CopperUtil.makeTerminal");
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
