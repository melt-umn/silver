package edu.umn.cs.melt.silver.langserver;

import edu.umn.cs.melt.copper.runtime.engines.CopperParser;
import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import common.HasTokens;
import common.Terminal;

public class CopperSemanticTokenEncoder<P extends CopperParser<?, CopperParserException> & HasTokens> {
    private final P parser;

    private final Map<String, Integer> tokenTypes;
    private final Map<String, Integer> tokenModifiers;

    public CopperSemanticTokenEncoder(P parser, List<String> tokenTypes, List<String> tokenModifiers) {
        this.parser = parser;
        this.tokenTypes = IntStream.range(0, tokenTypes.size()).boxed()
            .collect(Collectors.toMap(tokenTypes::get, i -> i));
        this.tokenModifiers = IntStream.range(0, tokenModifiers.size()).boxed()
            .collect(Collectors.toMap(tokenModifiers::get, i -> i));
    }

    public List<Integer> parseTokens(String content) {
        try {
            parser.parse(content);
        } catch (CopperParserException e) {
            // Ignore parse errors
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodeTokens(parser.getTokens());
    }

    private final Map<String, int[]> tokenEncodings = new HashMap<>();
    private List<Integer> encodeTokens(List<Terminal> tokens) {
        List<Integer> result = new ArrayList<>();
        int prevLine = 0;
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
                int deltaLine = t.getLine() - prevLine;
                if (deltaLine != 0) {
                    prevStartChar = 0;
                }
                int deltaStartChar = t.getColumn() - prevStartChar;
                int length = t.getEndOffset() - t.getStartOffset();
                result.add(deltaLine);
                result.add(deltaStartChar);
                result.add(length);
                result.add(type);
                result.add(modifiers);
            }
        }
        return result;
    }
}
