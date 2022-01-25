package common;

import common.javainterop.ConsCellCollection;
import edu.umn.cs.melt.copper.compiletime.logging.CompilerLogger;
import edu.umn.cs.melt.copper.compiletime.pipeline.AuxiliaryMethods;
import edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.*;
import edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.visitors.ParserSpecProcessor;
import edu.umn.cs.melt.copper.main.CopperIOType;
import edu.umn.cs.melt.copper.main.CopperPipelineType;
import edu.umn.cs.melt.copper.main.ParserCompiler;
import edu.umn.cs.melt.copper.main.ParserCompilerParameters;
import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import edu.umn.cs.melt.copper.runtime.io.Location;
import edu.umn.cs.melt.copper.runtime.logging.CopperException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import silver.core.NIOVal;

public final class CopperUtil {
  private static Location LOCATION = new VirtualLocation("<silver>", -1, -1);

  public static NIOVal compile(ParserBean parser, String packageName,
                               String parserName, Boolean runMDA,
                               String outFile, Boolean dumpHtml,
                               String dumpHtmlTo, IOToken tok) {
    ParserCompilerParameters params = new ParserCompilerParameters();
    params.setPackageName(packageName);
    params.setParserName(parserName);
    if (!runMDA) {
      params.setOutputFile(new java.io.File(outFile));
      params.setOutputType(CopperIOType.FILE);
    }
    params.setUsePipeline(CopperPipelineType.GRAMMARBEANS);
    params.setRunMDA(runMDA);

    CompilerLogger logger = AuxiliaryMethods.getOrMakeLogger(params);

    try {
      ParserSpecProcessor.normalizeParser(parser, logger);
      return tok.wrap(ParserCompiler.compile(parser, params));
    } catch (CopperException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static CharacterSetRegex makeCharRange(String lo, String hi) {
    CharacterSetRegex re = new CharacterSetRegex();
    re.addRange(lo.charAt(0), hi.charAt(0));
    return re;
  }

  public static CharacterSetRegex makeSingleChar(String ch) {
    CharacterSetRegex re = new CharacterSetRegex();
    re.addLooseChar(ch.charAt(0));
    return re;
  }

  public static DisambiguationFunction
  makeDisambiguationFunction(String id, String code,
                             ConsCellCollection<CopperElementReference> members,
                             Boolean applicableToSubsets) {
    try {
      DisambiguationFunction f = new DisambiguationFunction();
      f.setLocation(LOCATION);
      f.setName(id);
      f.setCode(code);
      Set<CopperElementReference> memberSet =
          new HashSet<CopperElementReference>();
      members.iterator().forEachRemaining(memberSet::add);
      f.setMembers(memberSet);
      f.setApplicableToSubsets(applicableToSubsets);
      return f;
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static CopperElementReference makeElementReference(String grammarName,
                                                            String name) {
    try {
      return CopperElementReference.ref(CopperElementName.newName(grammarName),
                                        name, LOCATION);
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static ExtendedParserBean makeExtendedParserBean(
      String id, String pp, CopperElementReference startSymbol,
      ConsCellCollection<CopperElementReference> startLayout,
      String parserClassAuxCode, String parserInitCode, String preambleCode,
      Grammar hostGrammar, Grammar extGrammar) {
    try {
      ExtendedParserBean parserBean = new ExtendedParserBean();
      parserBean.setLocation(LOCATION);
      parserBean.setName(id);
      parserBean.setDisplayName(pp);
      parserBean.setUnitary(false);
      parserBean.setStartSymbol(startSymbol);

      Set<CopperElementReference> startLayoutSet =
          new HashSet<CopperElementReference>();
      startLayout.iterator().forEachRemaining(startLayoutSet::add);
      parserBean.setStartLayout(startLayoutSet);
      parserBean.setParserClassAuxCode(parserClassAuxCode);
      parserBean.setParserInitCode(parserInitCode);
      parserBean.setPreambleCode(preambleCode);
      parserBean.addGrammar(hostGrammar);
      parserBean.addGrammar(extGrammar);
      parserBean.setHostGrammar(hostGrammar);
      return parserBean;
    } catch (CopperException exc) {
      throw new RuntimeException(exc);
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static ExtensionGrammar makeExtensionGrammar(
      String id, ConsCellCollection<GrammarElement> grammarElements,
      ConsCellCollection<CopperElementReference> markingTerminals,
      ConsCellCollection<CopperElementReference> bridgeProductions,
      ConsCellCollection<CopperElementReference> glueDisambiguationFunctions) {
    try {
      ExtensionGrammar grammar = new ExtensionGrammar();
      grammar.setLocation(LOCATION);
      grammar.setName(id);
      grammarElements.iterator().forEachRemaining((ele) -> {
        try {
          grammar.addGrammarElement(ele);
        } catch (CopperException exc) {
          throw new RuntimeException(exc);
        }
      });

      for (CopperElementReference name : markingTerminals)
        grammar.addMarkingTerminal(name.getName());
      for (CopperElementReference name : bridgeProductions)
        grammar.addBridgeProduction(name.getName());
      for (CopperElementReference name : glueDisambiguationFunctions)
        grammar.addGlueDisambiguationFunction(name.getName());

      return grammar;
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static Grammar
  makeGrammar(String id, ConsCellCollection<GrammarElement> grammarElements) {
    try {
      Grammar grammar = new Grammar();
      grammar.setLocation(LOCATION);
      grammar.setName(id);
      grammarElements.iterator().forEachRemaining((ele) -> {
        try {
          grammar.addGrammarElement(ele);
        } catch (CopperException exc) {
          throw new RuntimeException(exc);
        }
      });
      return grammar;
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static NonTerminal makeNonTerminal(String id, String pp,
                                            String type_) {
    try {
      NonTerminal nt = new NonTerminal();
      nt.setLocation(LOCATION);
      nt.setName(id);
      nt.setDisplayName(pp);
      nt.setReturnType(type_);
      return nt;
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static ParserBean
  makeParserBean(String id, String pp, CopperElementReference startSymbol,
                 ConsCellCollection<CopperElementReference> startLayout,
                 String parserClassAuxCode, String parserInitCode,
                 String preambleCode, Grammar grammar) {
    try {
      ParserBean parserBean = new ParserBean();
      parserBean.setLocation(LOCATION);
      parserBean.setName(id);
      parserBean.setDisplayName(pp);
      parserBean.setUnitary(true);
      parserBean.setStartSymbol(startSymbol);

      Set<CopperElementReference> startLayoutSet =
          new HashSet<CopperElementReference>();
      startLayout.iterator().forEachRemaining(startLayoutSet::add);
      parserBean.setStartLayout(startLayoutSet);
      parserBean.setParserClassAuxCode(parserClassAuxCode);
      parserBean.setParserInitCode(parserInitCode);
      parserBean.setPreambleCode(preambleCode);
      parserBean.addGrammar(grammar);
      return parserBean;
    } catch (CopperException exc) {
      throw new RuntimeException(exc);
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static ParserAttribute makeParserAttribute(String id, String type_,
                                                    String code) {
    try {
      ParserAttribute attr = new ParserAttribute();
      attr.setLocation(LOCATION);
      attr.setName(id);
      attr.setDisplayName(id);
      attr.setAttributeType(type_);
      attr.setCode(code);
      return attr;
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static Production
  makeProduction(String id, Integer precedence, CopperElementReference operator,
                 String code, CopperElementReference lhs,
                 ConsCellCollection<CopperElementReference> rhsConsList,
                 ConsCellCollection<CopperElementReference> prodLayout) {
    try {
      Production prod = new Production();
      prod.setLocation(LOCATION);
      prod.setName(id);
      prod.setDisplayName(id);
      if (precedence != null)
        prod.setPrecedence(precedence);
      prod.setOperator(operator);
      prod.setCode(code);
      prod.setLhs(lhs);
      ArrayList<CopperElementReference> rhs = new ArrayList(rhsConsList);
      prod.setRhs(rhs);
      ArrayList<String> rhsVarNames = new ArrayList<String>();
      for (int i = 0; i < rhs.size(); i++)
        rhsVarNames.add(String.format("rhsVar_%d", i));
      prod.setRhsVarNames(rhsVarNames);
      prod.setLayout(new HashSet(prodLayout));
      return prod;
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  // The full path needs to be given, since common.Terminal exists...
  public static edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Terminal
  makeTerminal(String id, String pp, Regex regex, Integer precedence,
               String associativity, String type_, String code,
               ConsCellCollection<CopperElementReference> classes,
               CopperElementReference prefix,
               ConsCellCollection<CopperElementReference> submits,
               ConsCellCollection<CopperElementReference> dominates) {
    try {
      edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Terminal terminal =
          new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Terminal();
      terminal.setLocation(LOCATION);
      terminal.setName(id);
      terminal.setDisplayName(pp);
      terminal.setRegex(regex);
      // TODO: operatorClass?
      terminal.setOperatorPrecedence(precedence);
      switch (associativity) {
      case "left":
        terminal.setOperatorAssociativity(OperatorAssociativity.LEFT);
        break;
      case "right":
        terminal.setOperatorAssociativity(OperatorAssociativity.RIGHT);
        break;
      default:
        terminal.setOperatorAssociativity(OperatorAssociativity.NONASSOC);
        break;
      }
      terminal.setReturnType(type_);
      terminal.setCode(code);
      classes.iterator().forEachRemaining(terminal::addTerminalClass);
      terminal.setPrefix(prefix);
      submits.iterator().forEachRemaining(terminal::addSubmitsTo);
      dominates.iterator().forEachRemaining(terminal::addDominates);
      return terminal;
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }

  public static TerminalClass makeTerminalClass(String id) {
    try {
      TerminalClass out = new TerminalClass();
      out.setLocation(LOCATION);
      out.setName(id);
      return out;
    } catch (ParseException exc) {
      throw new RuntimeException(exc);
    }
  }
}
