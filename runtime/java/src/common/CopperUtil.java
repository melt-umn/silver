package common;

import common.javainterop.ConsCellCollection;
import edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.*;
import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import edu.umn.cs.melt.copper.runtime.io.Location;
import edu.umn.cs.melt.copper.runtime.logging.CopperException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

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
  makeParserBean(String id, String pp, CopperElementReference startSymbol,
                 ConsCellCollection<CopperElementReference> startLayout,
                 String parserClassAuxCode, String parserInitCode,
                 String preambleCode, Grammar grammar) {
    try {
      System.err.println("bruh 36");
      ParserBean parserBean = new ParserBean();
      System.err.println("bruh 38");
      parserBean.setName(id);
      System.err.println("bruh 40");
      parserBean.setDisplayName(pp);
      System.err.println("bruh 42");
      parserBean.setUnitary(true);
      System.err.println("bruh 44");
      parserBean.addGrammar(grammar);
      System.err.println("bruh 46");
      parserBean.setStartSymbol(startSymbol);
      System.err.println("bruh 48");

      Set<CopperElementReference> startLayoutSet =
          new HashSet<CopperElementReference>();
      System.err.println("bruh 52");
      System.out.println(startLayout);
      System.err.println("bruh 54");
      System.out.println(startLayoutSet);
      System.err.println("bruh 56");
      startLayout.iterator().forEachRemaining(startLayoutSet::add);
      System.err.println("bruh 58");
      System.out.println(startLayout);
      System.err.println("bruh 60");
      System.out.println(startLayoutSet);
      System.err.println("bruh 62");
      parserBean.setStartLayout(startLayoutSet);
      System.err.println("bruh 64");

      throw new RuntimeException("TODO CopperUtil.makeParserBean");
    } catch (CopperException exc) {
      throw new RuntimeException(exc);
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static Terminal
  makeTerminal(String id, String pp, Regex regex, Integer precedence,
               Object associativity, String type_, String code,
               ConsCellCollection<StringCatter> classes, String prefix,
               ConsCellCollection<StringCatter> submits,
               ConsCellCollection<StringCatter> dominates) {
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
