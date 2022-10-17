package edu.umn.cs.melt.silver.langserver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import common.OriginContext;
import common.StringCatter;
import silver.compiler.driver.PisValidSilverFile;

/**
 * Silver-specific utilities used in SilverLanguageService.
 *
 * @author krame505
 */
public class SilverUtil {
    private static Pattern grammarDecl = Pattern.compile("grammar *([a-zA-Z0-9_:]+) *;");
    /**
     * Attempt to infer the grammar from a source file URI.
     * @param uriString  The URI of a source file.
     * @return  The file's grammar.
     */
    public static Optional<String> uriToGrammar(String uriString) {
        URI uri;
        try {
            uri = new URI(uriString);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URI", e);
        }

        // Search for a grammar declaration in a file in the directory
        Matcher m = grammarDecl.matcher("");
        try {
            Optional<String> fromGrammarDecl = Files.list(Path.of(uri).getParent())
                .filter(p -> !Files.isDirectory(p) && isValidSilverFileName(p.toString()))
                .flatMap((Path p) -> {
                    try {
                        return Files.lines(p).findFirst().flatMap(line ->
                            m.reset(line).find()? Optional.of(m.toMatchResult().group(1)) :
                            Optional.empty()).stream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Stream.empty();
                    }
                }).findFirst();
            if (fromGrammarDecl.isPresent()) {
                return fromGrammarDecl;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fall back: look for "grammars/" in the path
        String path = uri.getPath();
        if (path.contains("grammars/")) {
            return Optional.of(
                path.substring(0, path.lastIndexOf("/"))
                .split("grammars/", 2)[1].replace("/", ":").replace(".", ":"));
        }

        return Optional.empty();

    }

    public static boolean isValidSilverFileName(String file) {
        return PisValidSilverFile.invoke(OriginContext.FFI_CONTEXT, new StringCatter(file));
    }
}
