package common;

import common.javainterop.ConsCellCollection;
import edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.*;
import edu.umn.cs.melt.copper.main.ParserCompiler;
import edu.umn.cs.melt.copper.main.ParserCompilerParameters;
import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import edu.umn.cs.melt.copper.runtime.io.Location;
import edu.umn.cs.melt.copper.runtime.logging.CopperException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public final class CopperUtil {
    private static Location LOCATION = new VirtualLocation("<silver>", -1, -1);

    public static IOToken compile(ParserBean parser, IOToken tok) {
        ParserCompilerParameters params = new ParserCompilerParameters();
        try {
            ParserCompiler.compile(parser, params);
        } catch(CopperException exc) {
            throw new RuntimeException(exc);
        }
        return tok;
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

    public static Grammar makeGrammar(String id, ConsCellCollection<GrammarElement> grammarElements) {
        try {
            Grammar grammar = new Grammar();
            grammar.setName(id);
            grammarElements.iterator().forEachRemaining((ele) ->  {
                try {
                    grammar.addGrammarElement(ele);
                } catch(CopperException exc) {
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

            // TODO: parserClassAuxCode
            // TODO: parserInitCode
            // TODO: preambleCode

            parserBean.addGrammar(grammar);
            return parserBean;
        } catch (CopperException exc) {
            throw new RuntimeException(exc);
        } catch (ParseException exc) {
            throw new RuntimeException(exc);
        }
    }

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
            terminal.setName(id);
            terminal.setDisplayName(pp);
            terminal.setRegex(regex);
            // TODO: operatorClass?
            terminal.setOperatorPrecedence(precedence);
            if(associativity!= null) {
                switch(associativity) {
                case "left":
                    terminal.setOperatorAssociativity(OperatorAssociativity.LEFT);
                    break;
                case "right":
                    terminal.setOperatorAssociativity(OperatorAssociativity.RIGHT);
                    break;
                default:
                    throw new RuntimeException("associativity = " + associativity);
                }
            }
            // TODO: type_
            // TODO: code
            // TODO: classes
            // TODO: prefix
            // TODO: submits
            // TODO: dominates
            return terminal;
        } catch (ParseException exc) {
            throw new RuntimeException(exc);
        }
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
