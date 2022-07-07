package edu.umn.cs.melt.silver.langserver;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.SemanticTokens;
import org.eclipse.lsp4j.SemanticTokensParams;
import org.eclipse.lsp4j.TextDocumentContentChangeEvent;
import org.eclipse.lsp4j.WorkspaceFolder;
import org.eclipse.lsp4j.jsonrpc.CompletableFutures;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.TextDocumentService;

import common.ConsCell;
import common.DecoratedNode;
import common.OriginContext;
import common.StringCatter;
import common.javainterop.ConsCellCollection;
import silver.compiler.composed.Default.Parser_silver_compiler_composed_Default_svParse;
import silver.compiler.composed.Default.PsvParse;
import silver.compiler.driver.PbuildRun;
import silver.compiler.driver.PisValidSilverFile;
import silver.compiler.driver.PparseArgsOrError;
import silver.compiler.driver.util.NBuildEnv;
import silver.compiler.driver.util.PbuildEnv;
import silver.compiler.driver.util.PwriteInterface;
import silver.core.NPair;
import silver.core.PunsafeEvalIO;

public class SilverTextDocumentService implements TextDocumentService {
    private LanguageClient client;
    private Map<String, String> fileContents = new HashMap<>();
    private Map<String, Integer> fileVersions = new HashMap<>();
    private Map<String, Integer> savedVersions = new HashMap<>();
    private Set<String> grammarsToBuild = new HashSet<>();
    private boolean buildInProgress = false;
    private String silverGen;
    private List<String> grammarPath = new ArrayList<>();

    public SilverTextDocumentService() {
        try {
            silverGen = Files.createTempDirectory("silver_generated").toString() + "/";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClient(LanguageClient client) {
        this.client = client;
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {
        // System.err.println("Opened " + params);
        String uri = params.getTextDocument().getUri();
        fileContents.put(uri, params.getTextDocument().getText());
        fileVersions.put(uri, params.getTextDocument().getVersion());
        savedVersions.put(uri, params.getTextDocument().getVersion());
        uriToGrammar(uri).ifPresent(this::triggerGrammar);
    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {
        // System.err.println("Changed " + params);
        String uri = params.getTextDocument().getUri();
        for (TextDocumentContentChangeEvent change : params.getContentChanges()) {
            fileContents.put(uri, change.getText());
            fileVersions.put(uri, params.getTextDocument().getVersion());
        }
    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {
        // System.err.println("Closed " + params);
        String uri = params.getTextDocument().getUri();
        fileContents.remove(uri);
        fileVersions.remove(uri);
        savedVersions.remove(uri);
    }

    @Override
    public void didSave(DidSaveTextDocumentParams params) {
        // System.err.println("Saved " + params);
        String uri = params.getTextDocument().getUri();
        if (!fileVersions.containsKey(uri)) {
            throw new IllegalStateException("File saved before it was changed");
        }
        savedVersions.put(uri, fileVersions.get(uri));
        uriToGrammar(uri).ifPresent(this::triggerGrammar);
    }

    public static final List<String> tokenTypes = Arrays.asList(new String[] {
        "namespace", "type", "interface", "class", "typeParameter", "parameter", "variable", "function",
        "keyword", "modifier", "comment", "string", "number", "regexp", "operator"
    });
    public static final List<String> tokenModifiers = Arrays.asList(new String[] {
        "declaration", "definition", "documentation", "defaultLibrary"
    });

    private CopperSemanticTokenEncoder<?> semanticTokenEncoder =
        new CopperSemanticTokenEncoder<>(Parser_silver_compiler_composed_Default_svParse::new, tokenTypes, tokenModifiers);

    @Override
    public CompletableFuture<SemanticTokens> semanticTokensFull(SemanticTokensParams params) {
        //System.err.println(params);
        String uri = params.getTextDocument().getUri();
        return CompletableFutures.computeAsync((cancelChecker) -> {
            List<Integer> tokens;
            int requestVersion;
            // Recompute tokens if the file is changed while tokens are being computed
            do {
                requestVersion = fileVersions.get(uri);
                if (fileContents.containsKey(uri)) {
                    tokens = semanticTokenEncoder.parseTokens(fileContents.get(uri));
                } else {
                    tokens = new ArrayList<Integer>();
                }
            } while (requestVersion != fileVersions.get(uri));
            return new SemanticTokens(tokens);
        });
    }

    public void setWorkspaceFolders(List<WorkspaceFolder> folders) {
        grammarPath.clear();
        for (WorkspaceFolder folder : folders) {
            URI uri;
            try {
                uri = new URI(folder.getUri());
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid URI", e);
            }
            List<String> result = getGrammarPath(new File(uri));
            if (result != null) {
                grammarPath.addAll(result);
            }
        }
    }

    /**
     * Find all folders that may contain Silver files and should be included in the grammar path.
     * 
     * @param root
     * @return The grammar path arising from root, or null if root contains no Silver sources.
     */
    public static List<String> getGrammarPath(File root) {
        boolean foundSources = false;
        List<String> result = new ArrayList<>();
        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                List<String> recurse = getGrammarPath(file);
                if (recurse != null) {
                    foundSources = true;
                    result.addAll(recurse);
                }
            } else if (PisValidSilverFile.invoke(OriginContext.FFI_CONTEXT, new StringCatter(file.getName()))) {
                // This folder contains Silver sources
                return List.of();
            }
        }
        if (foundSources) {
            result.add(root.getAbsolutePath() + "/");
            return result;
        } else {
            return null;
        }
    }

    public synchronized void triggerGrammar(String grammar) {
        System.err.println("Triggered grammar " + grammar);
        grammarsToBuild.add(grammar);
        if (!buildInProgress) {
            buildInProgress = true;
            new Thread(() -> {
                while(true) {
                    Set<String> buildGrammars = grammarsToBuild;
                    Map<String, Integer> buildVersions;
                    synchronized (this) {
                        grammarsToBuild = new HashSet<>();
                        buildVersions = new HashMap<>(savedVersions);
                    }
                    doBuild(buildGrammars, buildVersions);
                    synchronized (this) {
                        if (grammarsToBuild.isEmpty()) {
                            buildInProgress = false;
                            break;
                        }
                    }
                };
            }).start();
        }
    }

    private void doBuild(Set<String> buildGrammars, Map<String, Integer> buildVersions) {
        String silverHome = "";
        List<String> args = List.of();
        List<String> silverHostGen = List.of(silverGen);

        // Set up the build environment
        NBuildEnv benv = new PbuildEnv(
            new StringCatter(silverHome),
            new StringCatter(silverGen),
            ConsCellCollection.fromStringList(grammarPath),
            ConsCellCollection.fromStringList(silverHostGen)
        );
        DecoratedNode a = PparseArgsOrError.invoke(OriginContext.FFI_CONTEXT, ConsCellCollection.fromStringList(args));
        
        // Build!
        DecoratedNode comp = (DecoratedNode)PunsafeEvalIO.invoke(OriginContext.FFI_CONTEXT,
            PbuildRun.invoke(OriginContext.FFI_CONTEXT,
                new PsvParse.Factory(),
                a, benv,
                ConsCellCollection.fromIterator(buildGrammars.stream().<StringCatter>map(StringCatter::new).iterator())));

        // Check that we built all triggered grammars.
        // Note that we must demand allGrammars from comp before demanding
        // recompiledGrammars to ensure that IO happens properly,
        // due to the circularity in the driver involving unsafeInterleaveIO.
        buildGrammars.removeAll(
            new ConsCellCollection<DecoratedNode>(
                comp.synthesized(silver.compiler.driver.util.Init.silver_compiler_driver_util_allGrammars__ON__silver_compiler_driver_util_Compilation)).stream()
            .map(r -> r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_env_declaredName__ON__silver_compiler_driver_util_RootSpec).toString())
            .collect(Collectors.toSet()));
        for (String remainingGrammar : buildGrammars) {
            System.err.println("Failed to find triggered grammar " + remainingGrammar);
        }

        // Report diagnostics
        Collection<DecoratedNode> rootSpecs = new ConsCellCollection<>(
            comp.synthesized(silver.compiler.driver.util.Init.silver_compiler_driver_util_recompiledGrammars__ON__silver_compiler_driver_util_Compilation));
        for (DecoratedNode r : rootSpecs) {
            String grammarSource =
                r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_env_grammarSource__ON__silver_compiler_driver_util_RootSpec).toString();
            String declaredName =
                r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_env_declaredName__ON__silver_compiler_driver_util_RootSpec).toString();
            //System.err.println("Reporting diagnostics for " + declaredName);

            Collection<NPair> allFileErrors = new ConsCellCollection<>(
                r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_core_allFileErrors__ON__silver_compiler_driver_util_RootSpec));
            for (NPair fileErrors : allFileErrors) {
                DecoratedNode decFileErrors = fileErrors.decorate();
                String uri = "file://" + grammarSource + decFileErrors.synthesized(silver.core.Init.silver_core_fst__ON__silver_core_Pair).toString();
                ConsCell messages = decFileErrors.synthesized(silver.core.Init.silver_core_snd__ON__silver_core_Pair);
                client.publishDiagnostics(new PublishDiagnosticsParams(uri, Util.messagesToDiagnostics(messages, uri), buildVersions.get(uri)));
            }
        }

        // Check that we built all triggered grammars
        for (String remainingGrammar : buildGrammars) {
            System.err.println("Triggered grammar " + remainingGrammar + " was not built");
        }

        // Write updated interface files
        for (DecoratedNode r : rootSpecs) {
            PunsafeEvalIO.invoke(OriginContext.FFI_CONTEXT,
                PwriteInterface.invoke(OriginContext.FFI_CONTEXT, new StringCatter(silverGen), r));
        }
    }

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
        Optional<String> fromGrammarDecl = Optional.empty();
        try {
            fromGrammarDecl = Files.list(Path.of(uri).getParent())
                .filter(p -> !Files.isDirectory(p))
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Fall back: look for "grammars/" in the path
        return fromGrammarDecl.or(() -> uri.getPath().contains("grammars/")?
            Optional.of(uri.getPath().split("grammars/", 2)[1].replace("/", ":").replace(".", ":").replaceAll(":$", "")) :
            Optional.empty());

    }
}
