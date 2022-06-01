package common;

import common.exceptions.SilverInternalError;
import common.javainterop.ConsCellCollection;
import edu.umn.cs.melt.copper.compiletime.dumpers.XMLSpecDumper;
import edu.umn.cs.melt.copper.compiletime.logging.CompilerLogger;
import edu.umn.cs.melt.copper.compiletime.pipeline.AuxiliaryMethods;
import edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.*;
import edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.visitors.ParserSpecProcessor;
import edu.umn.cs.melt.copper.main.CopperDumpControl;
import edu.umn.cs.melt.copper.main.CopperDumpType;
import edu.umn.cs.melt.copper.main.CopperIOType;
import edu.umn.cs.melt.copper.main.CopperPipelineType;
import edu.umn.cs.melt.copper.main.ParserCompiler;
import edu.umn.cs.melt.copper.main.ParserCompilerParameters;
import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import edu.umn.cs.melt.copper.runtime.io.Location;
import edu.umn.cs.melt.copper.runtime.logging.CopperException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import silver.core.Init;
import silver.core.NIOVal;
import silver.core.NLocation;

public final class CopperUtil {
  private static Location svToCuLocation(String sourceGrammar,
                                         NLocation locTerm) {
    // datatypes when...
    DecoratedNode locTree = locTerm.decorate();

    String fileName =
        String.format("%s/%s", sourceGrammar,
                      locTree.synthesized(
                          Init.silver_core_filename__ON__silver_core_Location));
    Integer line = (Integer)locTree.synthesized(
        Init.silver_core_line__ON__silver_core_Location);
    Integer column = (Integer)locTree.synthesized(
        Init.silver_core_column__ON__silver_core_Location);

    return new VirtualLocation(fileName, line, column);
  }

  public static NIOVal compile(ParserBean parser, String packageName,
                               String parserName, Boolean runMDA,
                               String outFile, Boolean dumpHtml,
                               String dumpHtmlTo, Boolean xmlDump,
                               IOToken tok) {
    if (xmlDump) {
      try {
        new XMLSpecDumper(parser).dump(CopperDumpType.XML_SPEC, System.out);
      } catch (IOException exc) {
        throw new SilverInternalError(
            "Failed to dump XML for Copper specification", exc);
      }
    }

    ParserCompilerParameters params = new ParserCompilerParameters();
    params.setPackageName(packageName);
    params.setParserName(parserName);
    if (!runMDA) {
      params.setOutputFile(new java.io.File(outFile));
      params.setOutputType(CopperIOType.FILE);
    }
    params.setUsePipeline(CopperPipelineType.GRAMMARBEANS);
    params.setRunMDA(runMDA);
    params.setDumpOutputType(CopperIOType.FILE);
    params.setDumpFile(new File(dumpHtmlTo));
    params.setDumpFormat(CopperDumpType.HTML);
    params.setDump(dumpHtml? CopperDumpControl.ON : CopperDumpControl.ERROR_ONLY);

    CompilerLogger logger = AuxiliaryMethods.getOrMakeLogger(params);

    try {
      ParserSpecProcessor.normalizeParser(parser, logger);
      return tok.wrap(ParserCompiler.compile(parser, params));
    } catch (CopperException exc) {
      throw new SilverInternalError("Failed to compile Copper specification",
                                    exc);
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
  makeDisambiguationFunction(String sourceGrammar, NLocation location,
                             String id, String code,
                             ConsCellCollection<CopperElementReference> members,
                             Boolean applicableToSubsets) {
    try {
      DisambiguationFunction f = new DisambiguationFunction();
      f.setLocation(svToCuLocation(sourceGrammar, location));
      f.setName(id);
      f.setCode(code);
      Set<CopperElementReference> memberSet =
          new HashSet<CopperElementReference>();
      members.iterator().forEachRemaining(memberSet::add);
      f.setMembers(memberSet);
      f.setApplicableToSubsets(applicableToSubsets);
      return f;
    } catch (ParseException exc) {
      throw new SilverInternalError(
          "Copper ParseException while constructing DisambiguationFunction",
          exc);
    }
  }

  public static CopperElementReference
  makeElementReference(String sourceGrammar, NLocation location,
                       String grammarName, String name) {
    try {
      return CopperElementReference.ref(
          CopperElementName.newName(grammarName), name,
          svToCuLocation(sourceGrammar, location));
    } catch (ParseException exc) {
      throw new SilverInternalError(
          "Copper ParseException while constructing ElementReference", exc);
    }
  }

  public static ExtendedParserBean
  makeExtendedParserBean(String sourceGrammar, NLocation location, String id,
                         String pp, CopperElementReference startSymbol,
                         ConsCellCollection<CopperElementReference> startLayout,
                         ConsCellCollection<StringCatter> interfaceNames,
                         String parserClassAuxCode, String parserInitCode,
                         String preambleCode, Grammar hostGrammar,
                         Grammar extGrammar) {
    try {
      ExtendedParserBean parserBean = new ExtendedParserBean();
      parserBean.setLocation(svToCuLocation(sourceGrammar, location));
      parserBean.setName(id);
      parserBean.setDisplayName(pp);
      parserBean.setUnitary(false);
      parserBean.setStartSymbol(startSymbol);

      Set<CopperElementReference> startLayoutSet = new HashSet<>();
      startLayout.iterator().forEachRemaining(startLayoutSet::add);

      Set<String> interfaceNameSet = new HashSet<String>();
      interfaceNames.stream().map(StringCatter::toString).forEach(interfaceNameSet::add);
      parserBean.setInterfaceNames(interfaceNameSet);

      parserBean.setStartLayout(startLayoutSet);
      parserBean.setParserClassAuxCode(parserClassAuxCode);
      parserBean.setParserInitCode(parserInitCode);
      parserBean.setPreambleCode(preambleCode);
      parserBean.addGrammar(hostGrammar);
      parserBean.addGrammar(extGrammar);
      parserBean.setHostGrammar(hostGrammar);
      return parserBean;
    } catch (CopperException exc) {
      throw new SilverInternalError(
          "CopperException while constructing ExtendedParserBean", exc);
    } catch (ParseException exc) {
      throw new SilverInternalError(
          "Copper ParseException while constructing ExtendedParserBean", exc);
    }
  }

  public static ExtensionGrammar makeExtensionGrammar(
      String sourceGrammar, NLocation location, String id,
      ConsCellCollection<GrammarElement> grammarElements,
      ConsCellCollection<CopperElementReference> markingTerminals,
      ConsCellCollection<CopperElementReference> bridgeProductions,
      ConsCellCollection<CopperElementReference> glueDisambiguationFunctions) {
    try {
      ExtensionGrammar grammar = new ExtensionGrammar();
      grammar.setLocation(svToCuLocation(sourceGrammar, location));
      grammar.setName(id);
      grammarElements.iterator().forEachRemaining((ele) -> {
        try {
          grammar.addGrammarElement(ele);
        } catch (CopperException exc) {
          throw new SilverInternalError(
              "CopperException while adding elements to ExtensionGrammar", exc);
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
      throw new SilverInternalError(
          "Copper ParseException while constructing ExtensionGrammar", exc);
    }
  }

  public static Grammar
  makeGrammar(String sourceGrammar, NLocation location, String id,
              ConsCellCollection<GrammarElement> grammarElements) {
    try {
      Grammar grammar = new Grammar();
      grammar.setLocation(svToCuLocation(sourceGrammar, location));
      grammar.setName(id);
      grammarElements.iterator().forEachRemaining((ele) -> {
        try {
          grammar.addGrammarElement(ele);
        } catch (CopperException exc) {
          throw new SilverInternalError(
              "CopperException while adding elements to Grammar", exc);
        }
      });
      return grammar;
    } catch (ParseException exc) {
      throw new SilverInternalError(
          "Copper ParseException while constructing Grammar", exc);
    }
  }

  public static NonTerminal makeNonTerminal(String sourceGrammar,
                                            NLocation location, String id,
                                            String pp, String type_) {
    try {
      NonTerminal nt = new NonTerminal();
      nt.setLocation(svToCuLocation(sourceGrammar, location));
      nt.setName(id);
      nt.setDisplayName(pp);
      nt.setReturnType(type_);
      return nt;
    } catch (ParseException exc) {
      throw new SilverInternalError(
          "Copper ParseException while constructing NonTerminal", exc);
    }
  }

  public static ParserBean
  makeParserBean(String sourceGrammar, NLocation location, String id, String pp,
                 CopperElementReference startSymbol,
                 ConsCellCollection<CopperElementReference> startLayout,
                 ConsCellCollection<StringCatter> interfaceNames,
                 String parserClassAuxCode, String parserInitCode,
                 String preambleCode, Grammar grammar) {
    try {
      ParserBean parserBean = new ParserBean();
      parserBean.setLocation(svToCuLocation(sourceGrammar, location));
      parserBean.setName(id);
      parserBean.setDisplayName(pp);
      parserBean.setUnitary(true);
      parserBean.setStartSymbol(startSymbol);

      Set<CopperElementReference> startLayoutSet = new HashSet<>();
      startLayout.iterator().forEachRemaining(startLayoutSet::add);
      parserBean.setStartLayout(startLayoutSet);

      Set<String> interfaceNameSet = new HashSet<>();
      interfaceNames.stream().map(StringCatter::toString).forEach(interfaceNameSet::add);
      parserBean.setInterfaceNames(interfaceNameSet);

      parserBean.setParserClassAuxCode(parserClassAuxCode);
      parserBean.setParserInitCode(parserInitCode);
      parserBean.setPreambleCode(preambleCode);
      parserBean.addGrammar(grammar);
      return parserBean;
    } catch (CopperException exc) {
      throw new SilverInternalError(
          "CopperException while constructing ParserBean", exc);
    } catch (ParseException exc) {
      throw new SilverInternalError(
          "Copper ParseException while constructing ParserBean", exc);
    }
  }

  public static ParserAttribute makeParserAttribute(String sourceGrammar,
                                                    NLocation location,
                                                    String id, String type_,
                                                    String code) {
    try {
      ParserAttribute attr = new ParserAttribute();
      attr.setLocation(svToCuLocation(sourceGrammar, location));
      attr.setName(id);
      attr.setDisplayName(id);
      attr.setAttributeType(type_);
      attr.setCode(code);
      return attr;
    } catch (ParseException exc) {
      throw new SilverInternalError(
          "Copper ParseException while constructing ParserAttribute", exc);
    }
  }

  public static Production
  makeProduction(String sourceGrammar, NLocation location, String id,
                 Integer precedence, CopperElementReference operator,
                 String code, CopperElementReference lhs,
                 ConsCellCollection<CopperElementReference> rhsConsList,
                 ConsCellCollection<CopperElementReference> prodLayout) {
    try {
      Production prod = new Production();
      prod.setLocation(svToCuLocation(sourceGrammar, location));
      prod.setName(id);
      prod.setDisplayName(id);
      if (precedence != null)
        prod.setPrecedence(precedence);
      prod.setOperator(operator);
      prod.setCode(code);
      prod.setLhs(lhs);
      ArrayList<CopperElementReference> rhs = new ArrayList<>(rhsConsList);
      prod.setRhs(rhs);
      ArrayList<String> rhsVarNames = new ArrayList<String>();
      for (int i = 0; i < rhs.size(); i++)
        rhsVarNames.add(String.format("rhsVar_%d", i));
      prod.setRhsVarNames(rhsVarNames);
      prod.setLayout(new HashSet<>(prodLayout));
      return prod;
    } catch (ParseException exc) {
      throw new SilverInternalError(
          "Copper ParseException while constructing Production", exc);
    }
  }

  // The full path needs to be given, since common.Terminal exists...
  public static edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Terminal
  makeTerminal(String sourceGrammar, NLocation location, String id, String pp,
               Regex regex, Integer precedence, String associativity,
               String type_, String code,
               ConsCellCollection<CopperElementReference> classes,
               CopperElementReference prefix,
               ConsCellCollection<CopperElementReference> submits,
               ConsCellCollection<CopperElementReference> dominates) {
    try {
      edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Terminal terminal =
          new edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Terminal();
      terminal.setLocation(svToCuLocation(sourceGrammar, location));
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
      throw new SilverInternalError(
          "Copper ParseException while constructing Terminal", exc);
    }
  }

  public static TerminalClass makeTerminalClass(String sourceGrammar,
                                                NLocation location, String id) {
    try {
      TerminalClass out = new TerminalClass();
      out.setLocation(svToCuLocation(sourceGrammar, location));
      out.setName(id);
      return out;
    } catch (ParseException exc) {
      throw new SilverInternalError(
          "Copper ParseException while constructing TerminalClass", exc);
    }
  }
}
