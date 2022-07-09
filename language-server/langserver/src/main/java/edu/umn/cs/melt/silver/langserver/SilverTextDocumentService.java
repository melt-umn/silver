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
    private boolean buildInProgress = false, buildTriggered = false;
    private boolean cleanBuild = false;
    private String silverGen;
    private String silverStdlibGrammars = null;
    private Set<String> grammarDirs = new HashSet<>();
    private Set<String> buildGrammars = new HashSet<>();

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

    public void setSilverGrammarsPath(Path path) {
        this.silverStdlibGrammars = path.toString() + "/";
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {
        // System.err.println("Opened " + params);
        String uri = params.getTextDocument().getUri();
        fileContents.put(uri, params.getTextDocument().getText());
        fileVersions.put(uri, params.getTextDocument().getVersion());
        savedVersions.put(uri, params.getTextDocument().getVersion());
        triggerBuild(false);
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
        triggerBuild(false);
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
        grammarDirs.clear();
        buildGrammars.clear();

        for (WorkspaceFolder folder : folders) {
            URI uri;
            try {
                uri = new URI(folder.getUri());
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid URI", e);
            }
            findGrammars(new File(uri));
        }
        triggerBuild(false);
    }

    private void findGrammars(File root) {
        // Workaround to avoid copied resource grammars in the maven build
        // directory in this LSP plugin under the Silver repo.
        // We might want to add a more intelegent way of specifying grammars to
        // exclude in case duplicates are found.
        if (root.getPath().contains("/target/classes/")) return;

        for (File file : root.listFiles()) {
            if (file.isDirectory()) {
                findGrammars(file);
            } else if (isValidSilverFileName(file.getName())) {
                Optional<String> fileGrammar = uriToGrammar("file://" + file.getAbsolutePath());
                fileGrammar.ifPresent(buildGrammars::add);
                fileGrammar.ifPresent(grammar -> grammarDirs.add(
                    root.getAbsolutePath().replace("/", ":").replace(".", ":")
                    .split(grammar, 2)[0].replace(":", "/")));
            }
        }
    }

    public synchronized void triggerBuild(boolean cleanBuild) {
        this.cleanBuild = cleanBuild;

        buildTriggered = true;
        if (!buildInProgress) {
            buildInProgress = true;
            new Thread(() -> {
                while(true) {
                    Map<String, Integer> buildVersions;
                    synchronized (this) {
                        buildTriggered = false;
                        buildVersions = new HashMap<>(savedVersions);
                    }
                    doBuild(buildVersions);
                    synchronized (this) {
                        if (!buildTriggered) {
                            buildInProgress = false;
                            break;
                        }
                    }
                };
            }).start();
        }
    }

    private void doBuild(Map<String, Integer> buildVersions) {
        String silverHome = "";  // Not needed since we aren't doing translation
        List<String> args = new ArrayList<>();
        List<String> grammarPath = new ArrayList<>(grammarDirs);
        List<String> silverHostGen = List.of(silverGen);

        if (cleanBuild) {
            args.add("--clean");
        }

        if (silverStdlibGrammars == null) {
            throw new IllegalStateException("Silver host grammars path not set");
        } else {
            // Add the silver resource grammars to the end of the grammar path,
            // so if silver is in the workspace we will find it there first.
            grammarPath.add(silverStdlibGrammars);
        }

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

        // Note that we must demand allGrammars from comp before demanding
        // recompiledGrammars to ensure that IO happens properly,
        // due to the circularity in the driver involving unsafeInterleaveIO.
        Collection<DecoratedNode> allGrammars = new ConsCellCollection<>(
            comp.synthesized(silver.compiler.driver.util.Init.silver_compiler_driver_util_allGrammars__ON__silver_compiler_driver_util_Compilation));
        Collection<DecoratedNode> recompiledGrammars = new ConsCellCollection<>(
            comp.synthesized(silver.compiler.driver.util.Init.silver_compiler_driver_util_recompiledGrammars__ON__silver_compiler_driver_util_Compilation));

        // Check that we built all triggered grammars.
        Set<String> builtGrammars = allGrammars.stream()
            .map(r -> r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_env_declaredName__ON__silver_compiler_driver_util_RootSpec).toString())
            .collect(Collectors.toSet());
        for (String grammar : buildGrammars) {
            if (!builtGrammars.contains(grammar)) {
                System.err.println("Failed to find triggered grammar " + grammar);
            }
        }

        // Report diagnostics
        System.err.println("Reporting diagnostics");
        for (DecoratedNode r : allGrammars) {
            String grammarSource =
                r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_env_grammarSource__ON__silver_compiler_driver_util_RootSpec).toString();

            Collection<NPair> allFileErrors = new ConsCellCollection<>(
                r.synthesized(silver.compiler.driver.util.Init.silver_compiler_definition_core_allFileErrors__ON__silver_compiler_driver_util_RootSpec));
            for (NPair fileErrors : allFileErrors) {
                DecoratedNode decFileErrors = fileErrors.decorate();
                String fileName = decFileErrors.synthesized(silver.core.Init.silver_core_fst__ON__silver_core_Pair).toString();
                ConsCell messages = decFileErrors.synthesized(silver.core.Init.silver_core_snd__ON__silver_core_Pair);
                String uri = "file://" + grammarSource + fileName;

                // System.err.println("Reporting diagnostics for " + grammarSource + fileName);
                client.publishDiagnostics(new PublishDiagnosticsParams(uri, Util.messagesToDiagnostics(messages, uri), buildVersions.get(uri)));
            }
        }

        // Write updated interface files
        System.err.println("Writing interface files");
        for (DecoratedNode r : recompiledGrammars) {
            PunsafeEvalIO.invoke(OriginContext.FFI_CONTEXT,
                PwriteInterface.invoke(OriginContext.FFI_CONTEXT, new StringCatter(silverGen), r));
        }

        this.cleanBuild = false;
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
