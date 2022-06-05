package edu.umn.cs.melt.silver.langserver;

import edu.umn.cs.melt.copper.runtime.engines.CopperParser;
import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import common.HasTokens;
import common.Terminal;

/**
 * A utility for invoking a Copper parser to obtain semantic tokens,
 * encoded as specified in the LSP spec.
 * 
 * TODO: This class should move to a seperate utility library for use in LSP
 * server implementations for Silver languages.
 * 
 * @author krame505
 * @see https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#textDocument_semanticTokens
 */
public class CopperSemanticTokenEncoder<P extends CopperParser<?, CopperParserException> & HasTokens> {
    // The constructor for the Copper parser
    private final Supplier<P> parserFactory;

    // The legend mapping semantic token types and modifier names to their encodings
    private final Map<String, Integer> tokenTypes;
    private final Map<String, Integer> tokenModifiers;

    /**
     * Construct a CopperSemanticTokenEncoder from a parser constructor and encoding legend.
     * 
     * @param parserFactory The constructor for a Copper parser
     * @param tokenTypes Semantic token types that will be used
     * @param tokenModifiers Semantic token modifiers that will be used
     */
    public CopperSemanticTokenEncoder(Supplier<P> parserFactory, List<String> tokenTypes, List<String> tokenModifiers) {
        this.parserFactory = parserFactory;
        this.tokenTypes = IntStream.range(0, tokenTypes.size()).boxed()
            .collect(Collectors.toMap(tokenTypes::get, i -> i));
        this.tokenModifiers = IntStream.range(0, tokenModifiers.size()).boxed()
            .collect(Collectors.toMap(tokenModifiers::get, i -> i));
    }

    /**
     * Parse a string and encode the result as semantic tokens.
     * 
     * @param content A string to parse
     * @return The encoded tokens
     */
    public List<Integer> parseTokens(String content) {
        P parser = parserFactory.get();
        try {
            parser.parse(content);
        } catch (CopperParserException e) {
            // Ignore parse errors
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodeTokens(parser.getTokens());
    }

    // A map of previously seen terminal names to 2-element arrays containing
    // {encoded type, encoded modifier}, or null if the terminal does not have
    // a corresponding semantic token.
    private final Map<String, int[]> tokenEncodings = new HashMap<>();

    /**
     * Encode a list of parsed terminals as semantic tokens.
     * @param tokens The terminals to encode
     * @return The encoded tokens
     */
    private List<Integer> encodeTokens(List<Terminal> tokens) {
        List<Integer> result = new ArrayList<>();
        int prevLine = 1;
        int prevStartChar = 0;
        for (Terminal t : tokens) {
            int type = -1;
            int modifiers = 0;
            if (tokenEncodings.containsKey(t.getName())) {
                int[] encoding = tokenEncodings.get(t.getName());
                if (encoding != null) {
                    type = encoding[0];
                    modifiers = encoding[1];
                }
            } else {
                for (String c : t.getLexerClasses()) {
                    if (c.startsWith("silver:langutil:lsp:")) {
                        String baseName = c.split("silver:langutil:lsp:")[1].split("_")[0];
                        String id = baseName.substring(0, 1).toLowerCase() + baseName.substring(1);
                        if (tokenTypes.containsKey(id)) {
                            type = tokenTypes.get(id);
                        } else if (tokenModifiers.containsKey(id)) {
                            modifiers |= 1 << tokenModifiers.get(id);
                        }
                    }
                }
                tokenEncodings.put(t.getName(), type != -1? new int[]{type, modifiers} : null);
            }
            if (type != -1) {
                String[] lines = t.lexeme.toString().split("\n");
                assert lines.length == t.getEndLine() - t.getLine() + 1;
                for (int line = t.getLine(); line <= t.getEndLine(); line++) {
                    int column = line == t.getLine()? t.getColumn() : 0;
                    int deltaLine = line - prevLine;
                    if (deltaLine != 0) {
                        prevStartChar = 0;
                    }
                    int deltaStartChar = column - prevStartChar;
                    int length = lines.length > 1?
                        lines[line - t.getLine()].length() :
                        t.getEndOffset() - t.getStartOffset();
                    prevLine = line;
                    prevStartChar = column;
                    result.add(deltaLine);
                    result.add(deltaStartChar);
                    result.add(length);
                    result.add(type);
                    result.add(modifiers);
                    //System.err.println(t.getName() + ": " + line + " " + column + " " + (lines.length > 1? lines[line - t.getLine()] : ""));
                }
            }
        }
        return result;
    }
}
