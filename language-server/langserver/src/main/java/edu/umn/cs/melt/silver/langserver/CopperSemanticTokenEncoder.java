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
 * A utility for invoking a Silver-generated Copper parser to obtain semantic
 * tokens, encoded as specified in the LSP specification.
 * 
 * Semantic tokens are specified for a grammar by defining the lexer classes
 * in silver:langutil:lsp on the terminals of the grammar. Additional semantic
 * tokens that do not directly correspond to a terminal in the grammar can be
 * included with the `insert semantic token` parser action.
 * This class provides a utility to run the generated parser, extract the list
 * of parsed terminals, and encode them as a stream of integers. Terminals with
 * any of the specified "token type" lexer classes are returned as semantic
 * tokens in the stream, with their position, length, token type and modifiers
 * encoded as integers according to the language server protocol specificiation.
 * 
 * TODO: This class should move to a generic utility library for use in
 * implementing LSP servers from languages written in Silver.
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
     * We pass a parser factory here because Copper parsers are non-reentrant,
     * so to be thread safe we need to construct a new parser object for every call to parseTokens.
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
                // We've seen this type of terminal before, look up its type and modifier encoding
                int[] encoding = tokenEncodings.get(t.getName());
                if (encoding != null) {
                    type = encoding[0];
                    modifiers = encoding[1];
                }
            } else {
                // We've haven't this type of terminal before, look for lsp lexer classes
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
                // Cache the encoding (or lack thereof) that we computed
                tokenEncodings.put(t.getName(), type != -1? new int[]{type, modifiers} : null);
            }
            if (type != -1) {
                // This terminal has an encoding, add it to the result.
                // For each line in the parsed terminal, we emit a semantic token.
                String[] lines = t.lexeme.toString().split("\n");
                assert lines.length == t.getEndLine() - t.getLine() + 1;
                for (int line = t.getLine(); line <= t.getEndLine(); line++) {
                    // Compute delta start line, delta start char and length
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

                    // Add the encoded token info to the result
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
